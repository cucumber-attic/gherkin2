#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe Feature do
      before do
        @listener = mock('listener').as_null_object
        @feature = Feature.new(@listener)
      end
  
      def scan_file(file)
        Feature.new(@listener).scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read)
      end

      describe "Comments" do
        it "should parse a file with only a one line comment" do
          @listener.should_receive(:comment).with("My comment",1).ordered
          @listener.should_receive(:feature).with("Feature", "hi", 2).ordered
          @feature.scan("# My comment\nFeature: hi")
        end

        it "should parse a one line comment" do
          @listener.should_receive(:comment).with("My comment", 1).once
          @feature.scan("# My comment")
        end

        it "should parse a file with only a multiline comment" do
          @listener.should_receive(:comment).with("Hello",1).ordered
          @listener.should_receive(:comment).with("World",2).ordered
          @listener.should_receive(:feature).with("Feature", "hi", 3).ordered
          @feature.scan("#Hello\n#World\nFeature: hi")
        end

        it "should parse a file with only a multiline comment" do
          pending("TODO:  Do multiline comments need to be compressed into a single message?")
          @listener.should_receive(:comment).with("Hello\nWorld", 1).ordered
          @listener.should_receive(:feature).with("Feature", "hi", 3).ordered
          @feature.scan("# Hello\n# World\nFeature: hi")
        end

        it "should parse a file with no comments" do
          @listener.should_receive(:feature).with("Feature", "hi", 1).once
          @feature.scan("Feature: hi\n")
        end

        it "should parse a file with only a multiline comment with newlines" do
          pending("TODO:  Do multiline comments need to be compressed into a single message?")
          @listener.should_receive(:comment).with("Hello\n\n# World\n").once
          @feature.scan("# Hello\n\n# World\n")
        end
  
        it "should not consume comments as part of a multiline name" do
          @listener.should_receive(:feature).with("Feature", "hi", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "test", 2).ordered
          @listener.should_receive(:comment).with("hello", 4).ordered
          @listener.should_receive(:scenario).with("Scenario", "another", 5).ordered
          @feature.scan("Feature: hi\n Scenario: test\n\n#hello\n Scenario: another")
        end
      end

      describe "Tags" do
        it "should parse a file with tags on a feature" do
          @listener.should_receive(:comment).with("My comment", 1).ordered
          @listener.should_receive(:tag).with("@hello", 2).ordered
          @listener.should_receive(:tag).with("@world", 2).ordered
          @listener.should_receive(:feature).with("Feature", "hi", 3).ordered
          @feature.scan("# My comment\n@hello @world\nFeature: hi\n")
        end

        it "should not take the tags as part of a multiline name feature element" do
          @listener.should_receive(:feature).with("Feature", "hi", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "test", 2).ordered
          @listener.should_receive(:tag).with("@hello", 4).ordered
          @listener.should_receive(:scenario).with("Scenario", "another", 5).ordered
          @feature.scan("Feature: hi\n Scenario: test\n\n@hello\n Scenario: another")
        end
  
        it "should parse a file with tags on a scenario" do
          @listener.should_receive(:comment).with("FC", 1).ordered
          @listener.should_receive(:tag).with("@ft",2 ).ordered
          @listener.should_receive(:feature).with("Feature", "hi", 3).ordered
          @listener.should_receive(:tag).with("@st1", 5).ordered
          @listener.should_receive(:tag).with("@st2", 5).ordered
          @listener.should_receive(:scenario).with("Scenario", "First", 6).ordered
          @listener.should_receive(:step).with("Given", "Pepper", 7).ordered
          @listener.should_receive(:tag).with("@st3", 9).ordered
          @listener.should_receive(:tag).with("@st4", 10).ordered
          @listener.should_receive(:tag).with("@ST5", 10).ordered
          @listener.should_receive(:tag).with("@#^%&ST6**!", 10).ordered
          @listener.should_receive(:scenario).with("Scenario", "Second", 11).ordered
          @feature.scan(%{# FC
  @ft
Feature: hi

  @st1 @st2   
  Scenario: First
    Given Pepper

@st3 
   @st4    @ST5  @#^%&ST6**!
  Scenario: Second})
        end
      end

      describe "Background" do
        it "should have steps" do
          pending
          @listener.should_receive(:feature).with("Feature", "Hi", 1).ordered
          @listener.should_receive(:background).with("Background","",2).ordered
          @listener.should_receive(:step).with("Given", "I am a step", 3).ordered
          @feature.scan("Feature: Hi\nBackground:\nGiven I am a step\n")
        end
 
        it "should allow multiline names" do
          pending
          @listener.should_receive(:feature).with("Feature", "hi", 1).ordered
          @listener.should_receive(:background).with("Background", "It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.",2).ordered
          @listener.should_receive(:step).with("Given", "I am a step", 6).ordered
          @feature.scan(%{Feature: Hi
Background: It is my ambition to say 
            in ten sentences
            what others say 
            in a whole book.
Given I am a step})
        end
      end

      describe "Scenarios" do
        it "can be empty" do
          @listener.should_receive(:feature).with("Feature", "Hi", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "Hello", 3).ordered
          @feature.scan("Feature: Hi\n\nScenario: Hello\n")
        end
 
        it "should allow whitespace lines after the Scenario line" do
          @listener.should_receive(:feature).with("Feature", "Foo", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "bar", 3).ordered
          @listener.should_receive(:step).with("Given", "baz", 5).ordered
          @feature.scan(%{Feature: Foo

Scenario: bar

  Given baz})
        end

        it "should have steps" do
          @listener.should_receive(:feature).with("Feature", "Hi", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "Hello", 2).ordered
          @listener.should_receive(:step).with("Given", "I am a step", 3).ordered
          @feature.scan("Feature: Hi\nScenario: Hello\nGiven I am a step\n")
        end

        it "should have steps with inline table" do
          pending
          @listener.should_receive(:feature).with("Feature", "Hi", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "Hello", 2).ordered
          @listener.should_receive(:step).with("Given", "I have a table", 3).ordered
          @listener.should_receive(:table).with([['a','b']], 4).ordered
          @feature.scan(%{Feature: Hi
Scenario: Hello
Given I have a table 
|a|b|
})
        end

        it "should have steps with inline py_string" do
          pending
          @listener.should_receive(:feature).with("Feature", "Hi", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "Hello", 2).ordered
          @listener.should_receive(:step).with("Given", "I have a string", 3).ordered
          @listener.should_receive(:py_string).with("hello\nworld").ordered
          @feature.scan(%{Feature: Hi
Scenario: Hello
Given I have a string


   """
  hello
  world
  """

})
        end

        it "should allow multiline names" do
          pending
          @listener.should_receive(:feature).with("Feature", "Hi", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.", 2).ordered
          @listener.should_receive(:step).with("Given", "I am a step", 6).ordered
          @feature.scan(%{Feature: Hi
Scenario: It is my ambition to say
          in ten sentences
          what others say 
          in a whole book.
Given I am a step

})
        end
  
        it "should ignore gherkin keywords which are parts of other words in the name" do
          pending
          @listener.should_receive(:feature).with("Feature", "Parser bug", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "I have a Buggon\nButtons are great", 2).ordered
          @listener.should_receive(:step).with("Given", "I have it", 4).ordered
          @feature.scan(%{Feature: Parser bug
Scenario: I have a Button
          Buttons are great
  Given I have it
})
        end
      end

      describe "A single feature, single scenario, single step" do
        
        after(:each) do
          @feature.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
        end
       
        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).once
        end

        it "should find the step" do
          @listener.should_receive(:step).with("Given", "there is a step", 3).once
        end
      end

      describe "A single feature, single scenario, three steps" do
        
        after(:each) do
          @feature.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n    And another step\n   And a third step\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
        end
       
        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).once
        end

        it "should find the step" do
          @listener.should_receive(:step).with("Given", "there is a step", 3).ordered
          @listener.should_receive(:step).with("And", "another step", 4).ordered
          @listener.should_receive(:step).with("And", "a third step", 5).ordered
        end
      end

      describe "A single feature with no scenario" do
        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
          @feature.scan("Feature: Feature Text\n")
        end

        it "should parse a one line feature with no newline" do
          @listener.should_receive(:feature).with("Feature", "hi", 1).once
          @feature.scan("Feature: hi")
        end
      end
      
      describe "A multi-line feature with no scenario" do
        it "should find the feature" do
          pending
          @listener.should_receive(:feature).with("Feature", "Feature Text\n  And some more text", 1).once
          @feature.scan("Feature: Feature Text\n  And some more text")
        end
      end

      describe "A feature with a scenario but no steps" do
        after(:each) do
          @feature.scan("Feature: Feature Text\nScenario: Reading a Scenario\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
        end

        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).once
        end
      end

      describe "A feature with two scenarios" do
        after(:each) do
          @feature.scan("Feature: Feature Text\nScenario: Reading a Scenario\n  Given a step\n\nScenario: A second scenario\n Given another step\n")
        end

        it "should find things in the right order" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).ordered
          @listener.should_receive(:step).with("Given", "a step", 3).ordered
          @listener.should_receive(:scenario).with("Scenario", "A second scenario", 5).ordered
          @listener.should_receive(:step).with("Given", "another step", 6).ordered
        end
      end

      describe "A simple feature with comments" do
        it "should find things in the right order" do
          @listener.should_receive(:comment).with("Here is a comment", 1).ordered
          @listener.should_receive(:feature).with("Feature", "Feature Text", 2).ordered
          @listener.should_receive(:comment).with("Here is another # comment", 3).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 4).ordered
          @listener.should_receive(:comment).with("Here is a third comment", 5).ordered
          @listener.should_receive(:step).with("Given", "there is a step", 6).ordered
          @listener.should_receive(:comment).with("Here is a fourth comment", 7).ordered
          scan_file("simple_with_comments.feature")
        end
      end
      
      describe "A simple feature with tags" do
        it "should find things in the right order" do
          @listener.should_receive(:tag).with("@tag1", 1).ordered
          @listener.should_receive(:tag).with("@tag2", 1).ordered
          @listener.should_receive(:feature).with("Feature", "Feature Text", 2).ordered
          @listener.should_receive(:tag).with("@tag3", 3).ordered
          @listener.should_receive(:tag).with("@tag4", 3).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 4).ordered
          @listener.should_receive(:step).with("Given", "there is a step", 5).ordered
          scan_file("simple_with_tags.feature")
        end
      end
   
      describe "A complex feature with tags, comments, multiple scenarios, and multiple steps" do
        it "should find things in the right order" do
          @listener.should_receive(:comment).with("Comment on line 1", 1).ordered
          @listener.should_receive(:tag).with("@tag1", 2).ordered
          @listener.should_receive(:tag).with("@tag2", 2).ordered
          @listener.should_receive(:comment).with("Comment on line 3", 3).ordered
          @listener.should_receive(:feature).with("Feature", "Feature Text\n  In order to test multiline forms\n  As a ragel writer\n  I need to check for complex combinations", 4).ordered
          @listener.should_receive(:comment).with("Comment on line 9", 9).ordered
          @listener.should_receive(:comment).with("Comment on line 11", 11).ordered
          @listener.should_receive(:tag).with("@tag3", 13).ordered
          @listener.should_receive(:tag).with("@tag4", 13).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 14).ordered
          @listener.should_receive(:step).with("Given", "there is a step", 15).ordered
          @listener.should_receive(:step).with("But", "not another step", 16).ordered
          @listener.should_receive(:tag).with("@tag3", 18).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a second scenario", 19).ordered
          @listener.should_receive(:comment).with("Comment on line 20", 20).ordered
          @listener.should_receive(:step).with("Given", "a third step", 21).ordered
          @listener.should_receive(:comment).with("Comment on line 22", 22).ordered
          @listener.should_receive(:step).with("Then", "I am happy", 23).ordered
          scan_file("complex.feature")
        end

        it "should find things in the right order (using SexpRecorder)" do
          @listener = SexpRecorder.new
          scan_file("complex.feature")
          @listener.to_sexp.should == [
            [:comment, "Comment on line 1", 1],
            [:tag, "@tag1", 2],
            [:tag, "@tag2", 2],
            [:comment, "Comment on line 3", 3],
            [:feature, "Feature", "Feature Text\n  In order to test multiline forms\n  As a ragel writer\n  I need to check for complex combinations", 4],
            [:comment, "Comment on line 9", 9],
            [:comment, "Comment on line 11", 11],
            [:tag, "@tag3", 13],
            [:tag, "@tag4", 13],
            [:scenario, "Scenario", "Reading a Scenario", 14],
            [:step, "Given", "there is a step", 15],
            [:step, "But", "not another step", 16],
            [:tag, "@tag3", 18],
            [:scenario, "Scenario", "Reading a second scenario", 19],
            [:comment, "Comment on line 20", 20],
            [:step, "Given", "a third step", 21],
            [:comment, "Comment on line 22", 22],
            [:step, "Then", "I am happy", 23]
          ]
        end
      end
    end
  end
end
