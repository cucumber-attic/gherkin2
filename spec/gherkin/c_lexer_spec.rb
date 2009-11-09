#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Lexer
    describe "C Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @feature = Gherkin::Lexer['C'].new(@listener)
      end

      def scan_file(file)
        @feature.scan(File.new(File.dirname(__FILE__) + "/fixtures/" + file).read)
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer parsing tags"
      it_should_behave_like "a Gherkin lexer parsing py_strings"
      it_should_behave_like "a Gherkin lexer parsing tables"
    end
  end
end
