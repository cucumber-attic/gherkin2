# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/tools/line_filter_listener'
require 'gherkin/tools/pretty_listener'
require 'stringio'

module Gherkin
  module Tools
    describe LineFilterListener do
      
      class LineListener
        attr_reader :lines
        
        def method_missing(*sexp_args)
          @lines ||= []
          @lines << Sexp.new(sexp_args).line
        end
      end
      
      def verify_lines(expected_lines, lines)
        line_listener = LineListener.new
        scan(line_listener, lines)
        line_listener.lines.should == expected_lines
      end

      def verify_output(expected_output, lines)
        io = StringIO.new
        scan(PrettyListener.new(io, true), lines)
        io.rewind
        io.read.should == expected_output
      end

      def scan(listener, lines)
        line_filter_listener = LineFilterListener.new(listener, lines)
        parser = Gherkin::Parser.new(line_filter_listener, true, "root")
        lexer  = Gherkin::I18nLexer.new(parser, true)
        lexer.scan(@input)
      end

      context "Scenario" do
        before do
          @input = %{Feature: 1
  Scenario: 2
    Given 3

  Scenario: 5
    Given 6
}
        end

        it "should not replay anything if no lines match" do
          verify_lines([:eof], [90])
        end

        it "should replay identically (except newlines) when there is no filter" do
          verify_lines([1,2,3,5,6,:eof], [])
        end

        it "should filter on step line of first scenario" do
          verify_lines([1,2,3,:eof], [3])
        end

        it "should filter on step line of second scenario" do
          verify_lines([1,5,6,:eof], [6])
        end

        it "should replay identically (except newlines) when the filter matches both scenarios" do
          verify_lines([1,2,3,5,6,:eof], [3,6])
        end
      end
      
      context "Scenario with Table and Comment" do
        before do
          @input = %{#language:en
Feature: 2
  # 3
  Scenario: 4
    Given 5
    When 6

  Scenario: 8
    Given 9
    When 10
     | 11 | 11 |
     | 12 | 12 |
}
        end

        it "should replay identically when there is no filter" do
          verify_lines([1,2,3,4,5,6,8,9,10,11,12,:eof], [])
        end

        it "should filter on step line of first scenario" do
          verify_lines([1,2,3,4,5,6,:eof], [5])
        end

        it "should filter on scenario line of second scenario" do
          verify_lines([1,2,8,9,10,11,12,:eof], [8])
        end
        
        it "should return everything when a line is given in each scenario" do
          verify_lines([1,2,3,4,5,6,8,9,10,11,12,:eof], [6,8])
        end
      end

      context "Scenario Outline" do
        before do
          @input = %{Feature: 1

  Scenario Outline: 3
    Given <foo> 4
    When <bar> 5

    Examples: 7
      | foo | bar |
      | 9   | 9   |
      | 10  | 10  |
      | 11  | 11  |
      | 12  | 12  |
      | 13  | 13  |

    Examples: 15
      | snip | snap |
      | 17   | 17   |
      | 18   | 18   |

  Scenario: 20
    Given 21
    When 22
}
        end

        it "should filter on step line of first scenario outline" do
          verify_lines([1,3,4,5,7,8,9,10,11,12,13,15,16,17,18,:eof], [5])
        end

        it "should filter on examples line of second scenario outline" do
          verify_lines([1,3,4,5,15,16,17,18,:eof], [15])
        end

        it "should filter on header row line of second scenario outline" do
          verify_lines([1,3,4,5,15,16,17,18,:eof], [16])
        end

        it "should filter on an example row of first scenario outline" do
          verify_lines([1,3,4,5,7,8,11,:eof], [11])
        end

        it "should filter on an example row of second scenario outline" do
          verify_lines([1,3,4,5,15,16,18,:eof], [18])
        end

        it "should filter on 2 example rows of first scenario outline" do
          verify_lines([1,3,4,5,7,8,11,13,:eof], [11,13])
        end

        it "should replay itself properly" do
          filtered = %{Feature: 1

  Scenario Outline: 3
    Given <foo> 4
    When <bar> 5

    Examples: 7
      | foo | bar |
      | 11  | 11  |
      | 13  | 13  |
}

          verify_output(filtered, [11,13])
        end
      end
    end
  end
end
