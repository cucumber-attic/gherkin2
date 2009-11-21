#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')
require 'gherkin/c_lexer'

module Gherkin
  module Lexer
    describe "C Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @lexer = Gherkin::CLexer::En.new(@listener)
      end

      def scan_file(file)
        @lexer.scan(File.new(File.dirname(__FILE__) + "/fixtures/" + file).read)
      end

      it_should_behave_like "a Gherkin lexer"
      it_should_behave_like "a Gherkin lexer lexing tags"
      it_should_behave_like "a Gherkin lexer lexing py_strings"
      it_should_behave_like "a Gherkin lexer lexing tables"
    end
  end
end
