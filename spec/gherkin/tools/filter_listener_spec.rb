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

  Scenario: 7
    Given 8
    When 9
     |10|10|
     |11|11|
}

        verify_filter(input, [1,3,4,5,7,8,9,10,11,:eof], [], [], TagExpression.new)
      end

      it "should filter on step line of first scenario" do
        input = %{Feature: 1

  Scenario: 3
    Given 4
    When 5

  Scenario: 7
    Given 8
    When 9
}
        verify_filter(input, [1,3,4,5,:eof], [5], [], TagExpression.new)
      end

      it "should filter on scenario line of second scenario" do
        input = %{Feature: 1

  Scenario: 3
    Given 4
    When 5

  Scenario: 7
    Given 8
    When 9
}

        verify_filter(input, [1,7,8,9,:eof], [7], [], TagExpression.new)
      end

      it "should filter on scenario name of first scenario" do
        input = %{Feature: 1

  Scenario: 3
    Given 4
    When 5

  Scenario: 7
    Given 8
    When 9
}

        verify_filter(input, [1,3,4,5,:eof], [], [/3/], [])
      end

      it "should filter on step line of first scenario outline" do
        input = %{Feature: 1

  Scenario Outline: 3
    Given <foo> 4
    When <bar> 5

    Examples: 7
      |foo|bar|
      |  9|  9|
      | 10| 10|

  Scenario: 12
    Given 13
    When 14
}
        verify_filter(input, [1,3,4,5,7,8,9,10,:eof], [ 5], [], TagExpression.new)
        verify_filter(input, [1,3,4,5,7,8,  10,:eof], [10], [], TagExpression.new)
      end

      context "tags" do
        it "should filter on step line of first scenario" do
        input = %{Feature: 1
  @foo
  Scenario: 3
    Given 4
    When 5

  Scenario: 7
    Given 8
    When 9
}
          verify_filter(input, [1,2,3,4,5,:eof], [], [], TagExpression.new("@foo"))
        end
      end

      def verify_filter(input, expected_lines, lines, name_regexen, tag_expression)
        #io = StringIO.new
        #pl = PrettyListener.new(io, true)
        #fl = FilterListener.new(pl, lines)
        fl = FilterListener.new(nil, lines, name_regexen, tag_expression)
        parser = Gherkin::Parser.new(fl, true, "root")
        lexer  = Gherkin::I18nLexer.new(parser)
        lexer.scan(input)
        fl.lines.should == expected_lines
#        io.rewind
#        io.read.should == expected_output
      end
    end
  end
end