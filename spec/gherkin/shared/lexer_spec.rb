#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')

module Gherkin
  module Lexer
    shared_examples_for "a Gherkin lexer" do

      describe "Comments" do
        it "should parse a one line comment" do
          @lexer.scan("# My comment\n")
          @listener.to_sexp.should == [[:comment, "# My comment", 1]]
        end

        it "should parse a multiline comment" do
          @lexer.scan("# Hello\n\n# World\n")
          @listener.to_sexp.should == [
            [:comment, "# Hello", 1],
            [:comment, "# World", 3]
          ]
        end

        it "should not consume comments as part of a multiline name" do
          @lexer.scan("Scenario: test\n#hello\n Scenario: another")
          @listener.to_sexp.should == [
            [:scenario, "Scenario", "test", 1],
            [:comment, "#hello", 2],
            [:scenario, "Scenario", "another", 3]
          ]
        end

        it "should allow empty comment lines" do 
          @lexer.scan("#\n   # A comment\n   #\n")
          @listener.to_sexp.should == [
            [:comment, "#", 1],
            [:comment, "# A comment", 2],
            [:comment, "#", 3]
          ]
        end
        
        it "should not allow comments within the Feature description" do
          lambda { 
            @lexer.scan("Feature: something\nAs a something\n# Comment\nI want something") 
            }.should raise_error(/Lexing error on line 4/)
        end
      end

      describe "Tags" do
        it "should not take the tags as part of a multiline name feature element" do
          @lexer.scan("Feature: hi\n Scenario: test\n\n@hello\n Scenario: another")
          @listener.to_sexp.should == [
            [:feature, "Feature", "hi", 1],
            [:scenario, "Scenario", "test", 2],
            [:tag, "hello", 4],
            [:scenario, "Scenario", "another", 5]
          ]
        end  
      end

      describe "Background" do
        it "should allow an empty background description" do
          @lexer.scan("Background:\nGiven I am a step\n")
          @listener.to_sexp.should == [
            [:background, "Background", "", 1],
            [:step, "Given", "I am a step", 2]
          ]
        end
        
        it "should allow multiline names ending at eof" do
          @lexer.scan("Background: I have several\n   Lines to look at\n None starting with Given")
          @listener.to_sexp.should == [
            [:background, "Background", "I have several\nLines to look at\nNone starting with Given", 1]
          ]
        end
         
        it "should allow multiline names" do
          @lexer.scan(%{Feature: Hi
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
        it "should be parsed" do
          @lexer.scan("Scenario: Hello\n")
          @listener.to_sexp.should == [
            [:scenario, "Scenario", "Hello", 1]
          ]
        end
 
        it "should allow whitespace lines after the Scenario line" do
          @lexer.scan(%{Scenario: bar

                          Given baz
                          })
          @listener.to_sexp.should == [
            [:scenario, "Scenario", "bar", 1],
            [:step, "Given", "baz", 3]
          ]
        end
        
        it "should allow multiline names" do
          @lexer.scan(%{Scenario: It is my ambition to say
                          in ten sentences
                          what others say 
                          in a whole book.
                          Given I am a step
                          })
          @listener.to_sexp.should == [
            [:scenario, "Scenario", "It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.", 1],
            [:step, "Given", "I am a step", 5]
          ]
        end

        it "should allow multiline names ending at eof" do
          @lexer.scan("Scenario: I have several\n       Lines to look at\n None starting with Given")
          @listener.to_sexp.should == [
            [:scenario, "Scenario", "I have several\nLines to look at\nNone starting with Given", 1]
          ]
        end
  
        it "should ignore gherkin keywords embedded in other words" do
          @lexer.scan(%{Scenario: I have a Button
          Buttons are great
  Given I have some
  But I might not because I am a Charles Dickens character
})
          @listener.to_sexp.should == [
            [:scenario, "Scenario", "I have a Button\nButtons are great", 1],
            [:step, "Given", "I have some", 3],
            [:step, "But", "I might not because I am a Charles Dickens character", 4]
          ]
        end
        
        it "should allow step names in Scenario descriptions" do
          @lexer.scan(%{Scenario: When I have when in scenario
          I should be fine
Given I am a step
})
          @listener.to_sexp.should == [
            [:scenario, "Scenario", "When I have when in scenario\nI should be fine", 1],
            [:step, "Given", "I am a step", 3]
          ]
        end
      end

      describe "Scenario Outlines" do
        it "should be parsed" do
          @lexer.scan(%{Scenario Outline: Hello
                          Given a <what> cucumber
                          Examples:
                          |what|
                          |green|
                          })
          @listener.to_sexp.should == [
            [:scenario_outline, "Scenario Outline", "Hello", 1],
            [:step, "Given", "a <what> cucumber", 2],
            [:examples, "Examples", "", 3],
            [:table, [["what"],["green"]], 4]
          ]
        end

        it "should parse with no steps or examples" do
          @lexer.scan(%{Scenario Outline: Hello

                          Scenario: My Scenario
                          })
          @listener.to_sexp.should == [
            [:scenario_outline, "Scenario Outline", "Hello", 1],
            [:scenario, "Scenario", "My Scenario", 3]
          ]
        end

        it "should allow multiline names" do
          @lexer.scan(%{Scenario Outline: It is my ambition to say 
                          in ten sentences
                          what others say 
                          in a whole book.
                          Given I am a step

                          })
          @listener.to_sexp.should == [
            [:scenario_outline, "Scenario Outline", "It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.", 1],
            [:step, "Given", "I am a step", 5]
          ]
        end        
      end

      describe "Examples" do
        it "should be parsed" do
          @lexer.scan(%{Examples:
                          |x|y|
                          |5|6|
                          })
          @listener.to_sexp.should == [
            [:examples, "Examples", "", 1],
            [:table, [["x","y"],["5","6"]], 2]
          ]
        end
        
        it "should parse multiline example names" do
          @lexer.scan(%{Examples: I'm a multiline name
                          and I'm ok
                          f'real
                          |x|
                          |5|
                          })
          @listener.to_sexp.should == [
            [:examples, "Examples", "I'm a multiline name\nand I'm ok\nf'real", 1],
            [:table, [["x"],["5"]], 4]
          ]
        end
      end
      
      describe "Steps" do
        it "should parse steps with inline table" do
          @lexer.scan(%{Given I have a table 
                          |a|b|
                          })
          @listener.to_sexp.should == [
            [:step, "Given", "I have a table", 1],
            [:table, [['a','b']], 2]
          ]
        end
        
        it "should parse steps with inline py_string" do
          @lexer.scan("Given I have a string\n\"\"\"\nhello\nworld\n\"\"\"")
          @listener.to_sexp.should == [
            [:step, "Given", "I have a string", 1],
            [:py_string, 0, "hello\nworld", 2]
          ]
        end
      end
            
      describe "A single feature, single scenario, single step" do
        it "should find the feature, scenario, and step" do
          @lexer.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2],
            [:step, "Given", "there is a step", 3]
          ]
        end
      end

      describe "A feature ending in whitespace" do
        it "should not raise an error when whitespace follows the Feature, Scenario, and Steps" do
          @lexer.scan("Feature: Feature Text\n Scenario: Reading a Scenario\n    Given there is a step\n    ")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2],
            [:step, "Given", "there is a step", 3]
          ]
        end
      end

      describe "A single feature, single scenario, three steps" do
        
        it "should find the feature, scenario, and three steps" do
          @lexer.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n    And another step\n   And a third step\n")
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
          @lexer.scan("Feature: Feature Text\n")
          @listener.to_sexp.should == [[:feature, "Feature", "Feature Text", 1]]
        end

        it "should parse a one line feature with no newline" do
          @lexer.scan("Feature: hi")
          @listener.to_sexp.should == [[:feature, "Feature", "hi", 1]]
        end
      end
      
      describe "A multi-line feature with no scenario" do
        it "should find the feature" do
          @lexer.scan("Feature: Feature Text\n  And some more text")
          @listener.to_sexp.should == [[:feature, "Feature", "Feature Text\nAnd some more text", 1]]
        end
      end

      describe "A feature with a scenario but no steps" do
        it "should find the feature and scenario" do
          @lexer.scan("Feature: Feature Text\nScenario: Reading a Scenario\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2]
          ]
        end
      end

      describe "A feature with two scenarios" do
        it "should find the feature and two scenarios" do
          @lexer.scan("Feature: Feature Text\nScenario: Reading a Scenario\n  Given a step\n\nScenario: A second scenario\n Given another step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2],
            [:step, "Given", "a step", 3],
            [:scenario, "Scenario", "A second scenario", 5],
            [:step, "Given", "another step", 6]
          ]
        end
        
        it "should find the feature and two scenarios without indentation" do
          @lexer.scan("Feature: Feature Text\nScenario: Reading a Scenario\nGiven a step\nScenario: A second scenario\nGiven another step\n")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", 1],
            [:scenario, "Scenario", "Reading a Scenario", 2],
            [:step, "Given", "a step", 3],
            [:scenario, "Scenario", "A second scenario", 4],
            [:step, "Given", "another step", 5]
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
      
      describe "A feature with tags everywhere" do
        it "should find the feature, scenario, step, and tags in the proper order" do
          scan_file("simple_with_tags.feature")
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
   
      describe "A complex feature with tags, comments, multiple scenarios, and multiple steps and tables" do
        it "should find things in the right order" do
          scan_file("complex.feature")
          @listener.to_sexp.should == [
            [:comment, "#Comment on line 1", 1],
            [:tag, "tag1", 2],
            [:tag, "tag2", 2],
            [:comment, "#Comment on line 3", 3],
            [:feature, "Feature", "Feature Text\nIn order to test multiline forms\nAs a ragel writer\nI need to check for complex combinations", 4],
            [:comment, "#Comment on line 9", 9],
            [:comment, "#Comment on line 11", 11],
            [:background, "Background", "", 13],
            [:step, "Given", "this is a background step", 14],
            [:step, "And", "this is another one", 15],
            [:tag, "tag3", 17],
            [:tag, "tag4", 17],
            [:scenario, "Scenario", "Reading a Scenario", 18],
            [:step, "Given", "there is a step", 19],
            [:step, "But", "not another step", 20],
            [:tag, "tag3", 22],
            [:scenario, "Scenario", "Reading a second scenario", 23],
            [:comment, "#Comment on line 24", 24],
            [:step, "Given", "a third step with a table", 25],
            [:table, [['a','b'],['c','d'],['e','f']], 26],
            [:step, "And", "I am still testing things", 29],
            [:table, [['g','h'],['e','r'],['k','i'],['n','']], 30],
            [:step, "And", "I am done testing these tables", 34],
            [:comment, "#Comment on line 29", 35],
            [:step, "Then", "I am happy", 36],
            [:scenario, "Scenario", "Hammerzeit", 38],
            [:step, "Given", "All work and no play", 39],
            [:py_string, 6, "      Makes Homer something something", 40],
            [:step, "Then", "crazy", 43]
          ]
        end        
      end

      describe "errors" do
        it "should raise a Lexing error if an unparseable token is found" do
          ["Some text\nFeature: Hi", 
            "Feature: Hi\nBackground:\nGiven something\nScenario A scenario",
            "Scenario: My scenario\nGiven foo\nAand bar\nScenario: another one\nGiven blah"].each do |text|
              lambda { @lexer.scan(text) }.should raise_error(/Lexing error on line/)
          end
        end
        
        it "should include the line number and context of the error" do
          lambda {
            @lexer.scan("Feature: hello\nScenario: My scenario\nGiven foo\nAand blah\nHmmm wrong\nThen something something")
          }.should raise_error(/Lexing error on line 4/)
        end
      end
    end
  end
end
