#encoding: utf-8
require 'spec_helper'

module Gherkin
  module Lexer
    begin
      require 'gherkin/lexer/en'
    rescue LoadError
      # For Java/JRuby we might not have the generated ruby lexer, which
      # won't be used anyway. Just define a stub.
      class En
        native_impl('gherkin')
      end 
    end

    describe "Native Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @lexer = Gherkin::Lexer::En.new(@listener)
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer lexing tags"
      it_should_behave_like "a Gherkin lexer lexing doc_strings"
      it_should_behave_like "a Gherkin lexer lexing rows"
      it_should_behave_like "encoding" unless ENV['GHERKIN_JS_NATIVE']
    end
  end
end
