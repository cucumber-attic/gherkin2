#encoding: utf-8
if defined?(JRUBY_VERSION)
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module JavaLexer
    describe "Java Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @lexer = Gherkin::JavaLexer['en'].new(@listener)
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer lexing tags"
      it_should_behave_like "a Gherkin lexer lexing py_strings"
      it_should_behave_like "a Gherkin lexer lexing tables"
    end
  end
end
end