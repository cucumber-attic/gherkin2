# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe "parsing multiline step arguments (pystrings)" do
            
      def ps(content)
        '"""%s"""' % ("\n" + content + "\n")
      end
      
      def indent(s, n)
        if n >= 0
          s.gsub(/^/, ' ' * n)
        else
          s.gsub(/^ {0,#{-n}}/, "")
        end
      end
            
      before do
        @listener = mock('listener')
        @parser = Misc.new(@listener)
      end
      
      it "should provide the amount of indentation to the listener"

      it "should parse a simple pystring" do
        @listener.should_receive(:pystring).with("I am a pystring")
        @parser.scan ps("I am a pystring")
      end

      it "should parse an empty pystring" do
        @listener.should_receive(:pystring).with("")
        @parser.scan ps("")
      end

      it "should parse a string containing only newlines" do
        @listener.should_receive(:pystring).with("\n\n")
        @parser.scan ps("\n\n")
      end
      
      it "should parse a content separated by two newlines" do
        @listener.should_receive(:pystring).with("A\n\nB")
        @parser.scan ps("A\n\nB")
      end
      
      it "should parse a multiline string" do
        @listener.should_receive(:pystring).with("A\nB\nC\nD")
        @parser.scan ps("A\nB\nC\nD")
      end
            
      it "should ignore unescaped quotes inside the string delimeters" do
        @listener.should_receive(:pystring).with("What does \"this\" mean?")
        @parser.scan ps('What does "this" mean?')
      end
      
      it "should remove whitespace up to the column of the opening quote" do
        @listener.should_receive(:pystring).with("I have been indented for reasons of style")
        @parser.scan indent(ps('I have been indented for reasons of style'), 4)
      end
      
      it "should preserve whitespace after the column of the opening quote" do
        @listener.should_receive(:pystring).with("  I have been indented to preserve whitespace")
        @parser.scan indent(ps('  I have been indented to preserve whitespace'), 4)
      end
      
      it "should preserve tabs within the content" do
        @listener.should_receive(:pystring).with("I have\tsome tabs\nInside\t\tthe content")
        @parser.scan ps("I have\tsome tabs\nInside\t\tthe content")
      end
  
      it "should handle complex pystrings" do
        pystring = %{
# Feature comment
@one
Feature: Sample

  @two @three
  Scenario: Missing
    Given missing

1 scenario (1 passed)
1 step (1 passed)

}
        
        @listener.should_receive(:pystring).with(pystring)
        @parser.scan ps(pystring)
      end

      it "should set indentation to zero if the content begins before the start delimeter" do
        pystring = "    \"\"\"\nContent\n\"\"\""
        @listener.should_receive(:pystring).with("Content")
        @parser.scan(pystring)
      end
    end
  end
end
