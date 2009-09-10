#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe Feature do
      before do
        @listener = Gherkin::SexpRecorder.new
        @feature = Feature.new(@listener)
      end

      def scan_file(file)
        Feature.new(@listener).scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read)
      end

      describe "Comments" do
        it "should parse a file with only a one line comment" do
          @feature.scan("# My comment\nFeature: hi")
          @listener.to_sexp.should == [
            [:comment, "# My comment", 1],
            [:feature, "Feature", "hi", 2],
          ]
        end

        it "should parse a one line comment" do
          @feature.scan("# My comment")
          @listener.to_sexp.should == [[:comment, "# My comment", 1]]
        end

        it "should parse a file with only a multiline comment" do
          @feature.scan("#Hello\n#World\nFeature: hi")
          @listener.to_sexp.should == [
            [:comment, "#Hello", 1],
            [:comment, "#World", 2],
            [:feature, "Feature", "hi", 3]
          ] 
        end

        it "should parse a file with only a multiline comment" do
          pending("TODO:  Do multiline comments need to be compressed into a single message?")
          @feature.scan("# Hello\n# World\nFeature: hi")
          @listener.to_sexp.should == [
            [:comment, "# Hello\n# World", 1],
            [:feature, "hi", 3]
          ]
        end

        it "should parse a file with no comments" do
          @feature.scan("Feature: hi\n")
          @listener.to_sexp.should == [[:feature, "Feature", "hi", 1]]
        end

        it "should parse a file with only a multiline comment with newlines" do
          pending("TODO:  Do multiline comments need to be compressed into a single message?")
          @feature.scan("# Hello\n\n# World\n")
          @listener.to_sexp.should == [[:comment, "# Hello\n\n# World\n"]]
        end
  
        it "should not consume comments as part of a multiline name" do
          @feature.scan("Feature: hi\n Scenario: test\n\n#hello\n Scenario: another")
          @listener.to_sexp.should == [
            [:feature, "Feature", "hi", 1],
            [:scenario, "Scenario", "test", 2],
            [:comment, "#hello", 4],
            [:scenario, "Scenario", "another", 5]
          ]
        end
      end

      describe "Tags" do
        it "should parse a file with tags on a feature" do
          @feature.scan("# My comment\n@hello @world\nFeature: hi\n")
          @listener.to_sexp.should == [
            [:comment, "# My comment", 1],
            [:tag, "hello", 2],
            [:tag, "world", 2],
            [:feature, "Feature", "hi", 3]
          ]
        end

        it "should not take the tags as part of a multiline name feature element" do
          @feature.scan("Feature: hi\n Scenario: test\n\n@hello\n Scenario: another")
          @listener.to_sexp.should == [
            [:feature, "Feature", "hi", 1],
            [:scenario, "Scenario", "test", 2],
            [:tag, "hello", 4],
            [:scenario, "Scenario", "another", 5]
          ]
        end
  
        it "should parse a file with tags on a scenario" do
          @feature.scan(%{# FC
  @ft
Feature: hi

  @st1 @st2   
  Scenario: First
    Given Pepper

@st3 
   @st4    @ST5  @#^%&ST6**!
  Scenario: Second})
          @listener.to_sexp.should == [
            [:comment, "# FC", 1],
            [:tag, "ft",2],
            [:feature, "Feature", "hi", 3],
            [:tag, "st1", 5],
            [:tag, "st2", 5],
            [:scenario, "Scenario", "First", 6],
            [:step, "Given", "Pepper", 7],
            [:tag, "st3", 9],
            [:tag, "st4", 10],
            [:tag, "ST5", 10],
            [:tag, "#^%&ST6**!", 10],
            [:scenario, "Scenario", "Second", 11]
          ]
        end
      end

      describe "Background" do
        it "should allow an empty background description" do
          @feature.scan("Feature: Hi\nBackground:\nGiven I am a step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:background, "Background", "", 2],
            [:step, "Given", "I am a step", 3]
          ]
        end
        
        it "should allow multiline names ending at eof" do
          @feature.scan("Feature: Feature Text\n  Background: I have several\n   Lines to look at\n None starting with Given")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:background, "Background", "I have several\nLines to look at\nNone starting with Given", 2]
          ]
        end
        
        it "should have steps" do
          @feature.scan("Feature: Hi\nBackground: Run this first\nGiven I am a step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:background, "Background", "Run this first", 2],
            [:step, "Given", "I am a step", 3]
          ]
        end

        it "should find scenarios after background" do
          @feature.scan("Feature: Hi\n#This needs to run first\nBackground: Run this first\nGiven I am a step\n\n  Scenario: A Scenario\nGiven I am a step")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:comment, "#This needs to run first", 2],
            [:background, "Background", "Run this first", 3],
            [:step, "Given", "I am a step", 4],
            [:scenario, "Scenario", "A Scenario", 6],
            [:step, "Given", "I am a step", 7]
          ]
        end
 
        it "should allow multiline names" do
          @feature.scan(%{Feature: Hi
Background: It is my ambition to say 
            in ten sentences
            what others say 
            in a whole book.
Given I am a step})
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:background, "Background", "It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.",2],
            [:step, "Given", "I am a step", 6]
          ]
        end
      end

      describe "Scenarios" do
        it "can be empty" do
          @feature.scan("Feature: Hi\n\nScenario: Hello\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:scenario, "Scenario", "Hello", 3]
          ]
        end
 
        it "should allow whitespace lines after the Scenario line" do
          @feature.scan(%{Feature: Foo

Scenario: bar

  Given baz})
          @listener.to_sexp.should == [
            [:feature, "Feature", "Foo", 1],
            [:scenario, "Scenario", "bar", 3],
            [:step, "Given", "baz", 5]
          ]
        end

        it "should have steps" do
          @feature.scan("Feature: Hi\nScenario: Hello\nGiven I am a step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:scenario, "Scenario", "Hello", 2],
            [:step, "Given", "I am a step", 3]
          ]
        end

        it "should have steps with inline table" do
          pending
          @feature.scan(%{Feature: Hi
Scenario: Hello
Given I have a table 
|a|b|
})
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:scenario, "Scenario", "Hello", 2],
            [:step, "Given", "I have a table", 3],
            [:table, [['a','b']], 4]
          ]
        end

        it "should have steps with inline py_string" do
          pending
          @feature.scan(%{Feature: Hi
Scenario: Hello
Given I have a string


   """
  hello
  world
  """

})
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:scenario, "Scenario", "Hello", 2],
            [:step, "Given", "I have a string", 3],
            [:py_string, "hello\nworld"]
          ]
        end

        it "should allow multiline names" do
          @feature.scan(%{Feature: Hi
Scenario: It is my ambition to say
          in ten sentences
          what others say 
          in a whole book.
Given I am a step

})
          @listener.to_sexp.should == [
            [:feature, "Feature", "Hi", 1],
            [:scenario, "Scenario", "It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.", 2],
            [:step, "Given", "I am a step", 6]
          ]
        end

        it "should allow multiline names ending at eof" do
          @feature.scan("Feature: Feature Text\n  And some more text\n\n  Scenario: I have several\n       Lines to look at\n None starting with Given")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text\n  And some more text", 1],
            [:scenario, "Scenario", "I have several\nLines to look at\nNone starting with Given", 4]
          ]
        end
  
        it "should ignore gherkin keywords which are parts of other words in the name" do
          @feature.scan(%{Feature: Parser bug
Scenario: I have a Button
          Buttons are great
  Given I have it
})
          @listener.to_sexp.should == [
            [:feature, "Feature", "Parser bug", 1],
            [:scenario, "Scenario", "I have a Button\nButtons are great", 2],
            [:step, "Given", "I have it", 4]
          ]
        end
      end

      describe "A single feature, single scenario, single step" do
        
        it "should find the feature, scenario, and step" do
          @feature.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2],
            [:step, "Given", "there is a step", 3]
          ]
        end
      end

      describe "A single feature, single scenario, three steps" do
        
        it "should find the feature, scenario, and three steps" do
          @feature.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n    And another step\n   And a third step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2],
            [:step, "Given", "there is a step", 3],
            [:step, "And", "another step", 4],
            [:step, "And", "a third step", 5]
          ]
        end
      end

      describe "A single feature with no scenario" do
        it "should find the feature" do
          @feature.scan("Feature: Feature Text\n")
          @listener.to_sexp.should == [[:feature, "Feature", "Feature Text", 1]]
        end

        it "should parse a one line feature with no newline" do
          @feature.scan("Feature: hi")
          @listener.to_sexp.should == [[:feature, "Feature", "hi", 1]]
        end
      end
      
      describe "A multi-line feature with no scenario" do
        it "should find the feature" do
          @feature.scan("Feature: Feature Text\n  And some more text")
          @listener.to_sexp.should == [[:feature, "Feature", "Feature Text\n  And some more text", 1]]
        end
      end

      describe "A feature with a scenario but no steps" do
        it "should find the feature and scenario" do
          @feature.scan("Feature: Feature Text\nScenario: Reading a Scenario\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2]
          ]
        end
      end

      describe "A feature with two scenarios" do
        it "should find the feature and two scenarios" do
          @feature.scan("Feature: Feature Text\nScenario: Reading a Scenario\n  Given a step\n\nScenario: A second scenario\n Given another step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2],
            [:step, "Given", "a step", 3],
            [:scenario, "Scenario", "A second scenario", 5],
            [:step, "Given", "another step", 6]
          ]
        end
      end

      describe "A simple feature with comments" do
        it "should find the feature, scenarios, steps, and comments in the proper order" do
          scan_file("simple_with_comments.feature")
          @listener.to_sexp.should == [
            [:comment, "# Here is a comment", 1],
            [:feature, "Feature", "Feature Text", 2],
            [:comment, "# Here is another # comment", 3],
            [:scenario, "Scenario", "Reading a Scenario", 4],
            [:comment, "# Here is a third comment", 5],
            [:step, "Given", "there is a step", 6],
            [:comment, "# Here is a fourth comment", 7]
          ]
        end
      end
      
      describe "A simple feature with tags" do
        it "should find the feature, scenario, step, and tags in the proper order" do
          scan_file("simple_with_tags.feature")
          @listener.to_sexp.should == [
            [:tag, "tag1", 1],
            [:tag, "tag2", 1],
            [:feature, "Feature", "Feature Text", 2],
            [:tag, "tag3", 3],
            [:tag, "tag4", 3],
            [:scenario, "Scenario", "Reading a Scenario", 4],
            [:step, "Given", "there is a step", 5]
          ]
        end
      end
   
      describe "A complex feature with tags, comments, multiple scenarios, and multiple steps" do
        it "should find things in the right order" do
          scan_file("complex.feature")
          @listener.to_sexp.should == [
            [:comment, "#Comment on line 1", 1],
            [:tag, "tag1", 2],
            [:tag, "tag2", 2],
            [:comment, "#Comment on line 3", 3],
            [:feature, "Feature", "Feature Text\n  In order to test multiline forms\n  As a ragel writer\n  I need to check for complex combinations", 4],
            [:comment, "#Comment on line 9", 9],
            [:comment, "#Comment on line 11", 11],
            [:tag, "tag3", 13],
            [:tag, "tag4", 13],
            [:scenario, "Scenario", "Reading a Scenario", 14],
            [:step, "Given", "there is a step", 15],
            [:step, "But", "not another step", 16],
            [:tag, "tag3", 18],
            [:scenario, "Scenario", "Reading a second scenario", 19],
            [:comment, "#Comment on line 20", 20],
            [:step, "Given", "a third step", 21],
            [:comment, "#Comment on line 22", 22],
            [:step, "Then", "I am happy", 23]
          ]
        end
      end
    end
  end
end
