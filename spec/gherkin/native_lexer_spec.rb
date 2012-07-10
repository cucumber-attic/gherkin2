#encoding: utf-8
require 'spec_helper'
require 'gherkin/lexer/en'

module Gherkin
  module Lexer
    describe "Native Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @lexer = Gherkin::Lexer::En.new(@listener)
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer lexing tags"
      it_should_behave_like "a Gherkin lexer lexing doc_strings"
      it_should_behave_like "a Gherkin lexer lexing rows"
      it_should_behave_like "parsing windows files" unless ENV['GHERKIN_JS_NATIVE']
    end
  end
end
