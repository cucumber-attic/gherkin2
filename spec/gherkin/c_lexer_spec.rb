#encoding: utf-8
unless defined?(JRUBY_VERSION)
require 'spec_helper'
require 'gherkin_lexer_en'

module Gherkin
  module Lexer
    describe "C Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @lexer = Gherkin::CLexer::En.new(@listener)
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer lexing tags"
      it_should_behave_like "a Gherkin lexer lexing doc_strings"
      it_should_behave_like "a Gherkin lexer lexing rows"
      it_should_behave_like "encoding"
    end
  end
end
end
