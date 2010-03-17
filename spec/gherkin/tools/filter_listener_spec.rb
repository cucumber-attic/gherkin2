# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/tools/filter_listener'
require 'gherkin/tools/pretty_listener'
require 'stringio'

module Gherkin
  module Tools
    describe FilterListener do
      it "should replay identically when there is no filter" do
        input = %{Feature: one

  Scenario: two
    Given three
    When four
}

        verify_filter(input, input)
      end

      def verify_filter(input, expected_output)
        io = StringIO.new
        pl = PrettyListener.new(io, true)
        fl = FilterListener.new(pl)
        parser = Gherkin::Parser.new(fl, true, "root")
        lexer  = Gherkin::I18nLexer.new(parser)
        lexer.scan(input)
        io.rewind
        io.read.should == expected_output
      end
    end
  end
end