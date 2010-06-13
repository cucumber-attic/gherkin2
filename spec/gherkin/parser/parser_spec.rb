require 'spec_helper'
require 'gherkin/parser/parser'

module Gherkin
  module Parser
    describe Parser do
      before do
        @listener = mock('listener')
        @parser = Gherkin::Parser::Parser.new(@listener, true)
      end

      it "should delegate events to the listener" do
        @listener.should_receive(:comment).with("# Content", 1)
        @parser.comment("# Content", 1)
      end

      it "should raise helpful error messages by default" do
        lambda { 
          @parser.scenario("Scenario", "My pet scenario", 12) 
        }.should raise_error(/Parse error on line 12\. Found scenario when expecting one of: comment, feature, tag\. \(Current state: root\)\.$/)
      end

      it "should allow empty files" do
        @listener.should_receive(:eof)
        @parser.eof
      end

      it "should delegate an error message when raise on error is false" do
        @listener.should_receive(:syntax_error).with(sym(:root), sym(:background), a([:comment, :feature, :tag]), 1)
        @parser = Gherkin::Parser::Parser.new(@listener, false)
        @parser.background("Background", "Content", 1)
      end

      [true, false].each do |native|
        it "should be reusable for several feature files (native lexer: #{native})" do
          listener = mock('listener').as_null_object
          parser = Gherkin::Parser::Parser.new(listener, true)
          lexer = Gherkin::I18nLexer.new(parser, native)
          feature = <<-EOF
Feature: foo
  Scenario: bar
    Given zap
EOF
          lexer.scan(feature, "test.feature")
          lexer.scan(feature, "test.feature")
        end
      end
    end
  end
end
