# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe "parsing multiline step arguments (pystrings)" do
      
      before do
        @listener = mock('listener')
        @parser = Misc.new(@listener)
        @ps = lambda { |content| '"""%s"""' % ("\n" + content + "\n") }
      end
      
      it "should parse a simple pystring" do
        @listener.should_receive(:pystring).with('I am a pystring')
        @parser.scan @ps["I am a pystring"]
      end

      it "should parse an empty pystring" do
        @listener.should_receive(:pystring).with("")
        @parser.scan @ps[""]
      end

      it "should parse a pystring containing a newline" do
        @listener.should_receive(:pystring).with("I am on line one\nI am on line two")
        @parser.scan @ps["I am on line one\nI am on line two"]
      end

      it "should remove whitespace up to the column of the opening quote"
      it "shouuld preserve whitespace after the column of the opening quote"
      it "should ignore unescaped quotes inside the string delimeters"
      it "should provide the amount of indentation to the listener"
    end
  end
end
