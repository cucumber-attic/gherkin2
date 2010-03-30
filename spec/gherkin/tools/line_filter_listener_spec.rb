# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/tools/line_filter_listener'
require 'stringio'

module Gherkin
  module Tools
    describe LineFilterListener do
      def verify_lines(expected_lines, lines)
        fl = LineFilterListener.new(nil, lines)
        parser = Gherkin::Parser.new(fl, true, "root")
        lexer  = Gherkin::I18nLexer.new(parser, true)
        lexer.scan(@input)
        fl.lines.should == expected_lines
      end

      context "Scenario" do
        before do
          @input = %{Feature: 1

  Scenario: 3
    Given 4
    When 5

  Scenario: 7
    Given 8
    When 9
     |10|10|
     |11|11|
}
        end

        it "should replay identically when there is no filter" do
          verify_lines([1,3,4,5,7,8,9,10,11,:eof], [])
        end

        it "should filter on step line of first scenario" do
          verify_lines([1,3,4,5,:eof], [5])
        end

        it "should filter on scenario line of second scenario" do
          verify_lines([1,7,8,9,10,11,:eof], [7])
        end
      end

      context "Scenario Outline" do
        before do
          @input = %{Feature: 1

  Scenario Outline: 3
    Given <foo> 4
    When <bar> 5

    Examples: 7
      |foo|bar|
      |  9|  9|
      | 10| 10|
      | 11| 11|
      | 12| 12|
      | 13| 13|

    Examples: 15
      |snip|snap|
      |  17|  17|
      |  18|  18|

  Scenario: 17
    Given 18
    When 19
}
        end

        it "should filter on step line of first scenario outline" do
          verify_lines([1,3,4,5,7,8,9,10,11,12,13,15,16,17,18,:eof], [5])
        end

        it "should filter on row line of first scenario outline" do
          verify_lines([1,3,4,5,7,8,11,:eof], [11])
        end

        it "should filter on examples line of second scenario outline" do
          verify_lines([1,3,4,5,15,16,17,18,:eof], [15])
        end

        it "should filter on header row line of second scenario outline" do
          verify_lines([1,3,4,5,15,17,18,:eof], [16])
        end
      end
    end
  end
end