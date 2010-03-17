# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/tools/filter_listener'
require 'gherkin/tools/pretty_listener'
require 'stringio'

module Gherkin
  module Tools
    describe FilterListener do
      it "should replay identically when there is no filter" do
        input = %{Feature: 1

  Scenario: 3
    Given 4
    When 5
}

        verify_filter(input, input, [])
      end

      it "should filter on a single line" do
        input = %{Feature: 1

  Scenario: 3
    Given 4
    When 5

  Scenario: 7
    Given 8
    When 9
}

        output = %{Feature: 1

  Scenario: 3
    Given 4
    When 5
}

        verify_filter(input, output, [5])
      end

      def verify_filter(input, expected_output, lines)
        io = StringIO.new
        pl = PrettyListener.new(io, true)
        fl = FilterListener.new(pl, lines)
        parser = Gherkin::Parser.new(fl, true, "root")
        lexer  = Gherkin::I18nLexer.new(parser)
        lexer.scan(input)
        io.rewind
        io.read.should == expected_output
      end
    end
  end
end