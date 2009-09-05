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
      
      it "should parse a simple pystring" do
        @listener.should_receive(:pystring).with("I am a pystring")
        @parser.scan ps("I am a pystring")
      end

      it "should parse an empty pystring" do
        @listener.should_receive(:pystring).with("")
        @parser.scan ps("")
      end

      it "should parse a pystring containing a newline" do
        @listener.should_receive(:pystring).with("A\nB")
        @parser.scan ps("A\nB")
      end
      
      it "should parse a multiline string" do
        @listener.should_receive(:pystring).with("A\nB\nC\nD")
        @parser.scan ps("A\nB\nC\nD")
      end
      
      it "should parse a string with lots of newlines" do
        @listener.should_receive(:pystring).with("A\n\nB")
        @parser.scan ps("A\n\nB")
      end
      
      it "should ignore unescaped quotes inside the string delimeters" do
        @listener.should_receive(:pystring).with('What does "this" mean?')
        @parser.scan ps('What does "this" mean?')
      end
      
      it "should remove whitespace up to the column of the opening quote" do
        @listener.should_receive(:pystring).with('I have been indented')
        @parser.scan indent(ps('I have been indented'), 4)
      end
      
      it "should preserve whitespace after the column of the opening quote" do
        @listener.should_receive(:pystring).with('  I have been indented')
        @parser.scan indent(ps('  I have been indented'), 4)
      end
      
      it "should provide the amount of indentation to the listener"
      
      it "should handle complex pystrings" do
        pending "Indenting the pystring makes this pass... not sure why yet"
        pystring = <<EOS
# Feature comment
@one
Feature: Sample

  @two @three
  Scenario: Missing
    Given missing

1 scenario (1 passed)
1 step (1 passed)

EOS
        
        @listener.should_receive(:pystring).with(pystring)
        @parser.scan ps(pystring)
      end
    end
  end
end
