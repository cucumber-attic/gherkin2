# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe "parsing multiline step arguments (py_strings)" do
            
      def ps(content)
        "Feature:Hi\nScenario: Hi\nGiven a step\n" + '"""%s"""' % ("\n" + content + "\n")
      end
                  
      before do
        @listener = Gherkin::SexpRecorder.new
        @parser = Gherkin::Parser['en'].new(@listener)
      end
      
      it "should provide the amount of indentation of the triple quotes to the listener" do
str = <<EOS
Feature: some feature
  Scenario: some scenario
    Given foo
    """
      Hello
    Goodbye
    """
    Then bar
EOS
        @listener.should_receive(:py_string).with("      Hello\n    Goodbye", 4, 4)
        @parser.scan(str)
      end

      it "should parse a simple py_string" do
        @listener.should_receive(:py_string).with("I am a py_string", 4, 0)
        @parser.scan ps("I am a py_string")
      end

      it "should parse an empty py_string" do
        @listener.should_receive(:py_string).with("", 4, 0)
        @parser.scan("Feature: Hi\nScenario: Hi\nGiven a step\n\"\"\"\n\"\"\"")
      end

      it "should treat a string containing only newlines as an empty string" do
        @parser.scan(%{
Feature: Hi
Scenario: Hi
Given a step
"""


"""}) 
        @listener.to_sexp.should == [
          [:feature, "Feature", "Hi", 2],
          [:scenario, "Scenario", "Hi", 3],
          [:step, "Given", "a step", 4],
          [:py_string, "", 5, 0],
        ]
      end
      
      it "should parse a content separated by two newlines" do
        @parser.scan ps("A\n\nB")
        @listener.to_sexp.should == [
          [:feature, "Feature", "Hi", 1],
          [:scenario, "Scenario", "Hi", 2],
          [:step, "Given", "a step", 3],
          [:py_string, "A\n\nB", 4, 0],
        ]
      end
      
      it "should parse a multiline string" do
        @listener.should_receive(:py_string).with("A\nB\nC\nD", 4, 0)
        @parser.scan ps("A\nB\nC\nD")
      end
            
      it "should ignore unescaped quotes inside the string delimeters" do
        @listener.should_receive(:py_string).with("What does \"this\" mean?", 4, 0)
        @parser.scan ps('What does "this" mean?')
      end
      
      it "should preserve whitespace within the triple quotes" do
str = <<EOS
Feature: My feature
  Scenario: a scenario
    Given a pystring
    """
      Line one
 Line two
    """
EOS
        @listener.should_receive(:py_string).with("      Line one\n Line two", 4, 4)
        @parser.scan(str)
      end
            
      it "should preserve tabs within the content" do
        @listener.should_receive(:py_string).with("I have\tsome tabs\nInside\t\tthe content", 4, 0)
        @parser.scan ps("I have\tsome tabs\nInside\t\tthe content")
      end
  
      it "should handle complex py_strings" do
py_string = <<EOS
# Feature comment
@one
Feature: Sample

  @two @three
  Scenario: Missing
    Given missing

1 scenario (1 passed)
1 step (1 passed)

EOS
        
        @listener.should_receive(:py_string).with(py_string, 4, 0)
        @parser.scan ps(py_string)
      end

      it "should set indentation to zero if the content begins before the start delimeter" do
        py_string = %{
Feature: Hi
Scenario: Hi
Given a step
    """
Content
"""}
        @listener.should_receive(:py_string).with("Content", 5, 4)
        @parser.scan(py_string)
      end
    end
  end
end
