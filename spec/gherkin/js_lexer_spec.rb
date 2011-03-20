#encoding: utf-8
#unless defined?(JRUBY_VERSION) || (defined?(RUBY_ENGINE) && RUBY_ENGINE == "ironruby")
require 'spec_helper'
require 'gherkin/js_lexer'

module Gherkin
  module Lexer
    describe "Javascript Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @lexer = Gherkin::JsLexer.new(@listener, 'en')
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer lexing tags"
      it_should_behave_like "a Gherkin lexer lexing py_strings"
      it_should_behave_like "a Gherkin lexer lexing rows"
    end
  end
end
#end
