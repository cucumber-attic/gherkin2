#encoding: utf-8
if defined?(RUBY_ENGINE) && RUBY_ENGINE == "ironruby"
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Lexer
    describe "C# Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @lexer = Gherkin::Lexer.csharp['en'].new(@listener)
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer lexing tags"
      it_should_behave_like "a Gherkin lexer lexing py_strings"
      it_should_behave_like "a Gherkin lexer lexing rows"
    end
  end
end
end
