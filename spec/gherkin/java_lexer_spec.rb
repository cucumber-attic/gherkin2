#encoding: utf-8
if defined?(JRUBY_VERSION)
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module JavaLexer
    describe "Java Lexer" do
      before do
        @listener = Gherkin::SexpRecorder.new
        c = Gherkin::JavaLexer['en']
        @lexer = c.new(@listener)
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
end