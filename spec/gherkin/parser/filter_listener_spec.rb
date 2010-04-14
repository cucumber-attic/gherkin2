# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/parser/filter_listener'
require 'gherkin/formatter/pretty_formatter'
require 'stringio'

module Gherkin
  module Parser
    describe FilterListener do
      
      class LineListener
        attr_reader :lines
        
        def method_missing(*sexp_args)
          @lines ||= []
          @lines << sexp_args[-1]
        end
      end
      
      def verify_filters(expected_lines, filters)
        line_listener = LineListener.new
        scan(line_listener, filters)
        line_listener.lines.should == expected_lines
      end

      def verify_output(expected_output, filters)
        io = StringIO.new
        scan(Gherkin::Formatter::PrettyFormatter.new(io, true), filters)
        io.rewind
        io.read.should == expected_output
      end

      def scan(listener, filters)
        tag_expressions = filters.delete(:tag_expressions)
        filters[:tag_expression] = TagExpression.new(tag_expressions) if tag_expressions
        filter_listener = FilterListener.new(listener, filters)
        parser = Gherkin::Parser::Parser.new(filter_listener, true, "root")
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
          verify_filters([:eof], [90])
        end

        it "should not replay anything if no names match" do
          verify_filters([:eof], [/pudding/])
        end

        it "should replay identically when there is no filter" do
          verify_filters([1,2,3,5,6,:eof], [])
        end

        it "should replay identically when line filter is feature line" do
          verify_filters([1,2,3,5,6,:eof], [1])
        end

        it "should match scenario line of first scenario" do
          verify_filters([1,2,3,:eof], [2])
        end

        it "should match name of first scenario" do
          verify_filters([1,2,3,:eof], [/2/])
        end

        it "should match step line of first scenario" do
          verify_filters([1,2,3,:eof], [3])
        end

        it "should match step line of second scenario" do
          verify_filters([1,5,6,:eof], [6])
        end

        it "should replay identically (except newlines) when the filter matches both scenarios" do
          verify_filters([1,2,3,5,6,:eof], [3,6])
        end
      end

      context "Scenario with py_string" do
        before do
          @input = %{Feature: 1
  Scenario: 2
    Given 3
      """
      5
      """

  Scenario: 8
    Given 9
}
        end

        it "should replay identically when there is no filter" do
          verify_filters([1,2,3,4,8,9,:eof], {})
        end

        it "should filter on py_string line" do
          verify_filters([1,2,3,4,:eof], [4])
        end
      end

      context "Scenario with Table and Comment and Tag" do
        before do
          @input = %{#language:en
Feature: 2
  # 3
  Scenario: 4
    Given 5
    When 6

  @tag8
  Scenario: 9
    Given 10
    When 11
     | 12 | 12 |
     | 13 | 13 |
}
        end

        it "should replay identically when there is no filter" do
          verify_filters([1,2,3,4,5,6,8,9,10,11,12,13,:eof], [])
        end

        it "should match step line of first scenario" do
          verify_filters([1,2,3,4,5,6,:eof], [5])
        end

        it "should match scenario line of second scenario" do
          verify_filters([1,2,8,9,10,11,12,13,:eof], [9])
        end

        it "should match tag of second scenario" do
          verify_filters([1,2,8,9,10,11,12,13,:eof], ['@tag8'])
        end
        
        it "should return everything when a line is given in each scenario" do
          verify_filters([1,2,3,4,5,6,8,9,10,11,12,13,:eof], [6,9])
        end

        it "should return a scenario when a line is given for its tag" do
          verify_filters([1,2,8,9,10,11,12,13,:eof], [8])
        end

        it "should return a scenario when a line is given for its comment" do
          verify_filters([1,2,3,4,5,6,:eof], [3])
        end
      end
      
      context "Scenario with Background and Comment" do
        before do
          @input = %{#language:en
Feature: 2
  # 3
  Background: 4
    Given 5

  # 7
  Scenario: 8
    Given 9
    When 10

  Scenario: 12
    Given 13
    When 14
     | 15 | 16 |
     | 15 | 16 |
}
        end

        it "should replay identically when there is no filter" do
          verify_filters([1,2,3,4,5,7,8,9,10,12,13,14,15,16,:eof], [])
        end

        it "should replay identically when filtering on the line of a background step" do
          verify_filters([1,2,3,4,5,7,8,9,10,12,13,14,15,16,:eof], [5])
        end

        it "should replay identically when filtering on the line of the background" do
          verify_filters([1,2,3,4,5,7,8,9,10,12,13,14,15,16,:eof], [4])
        end

        it "should replay the background on step line of first scenario" do
          verify_filters([1,2,3,4,5,7,8,9,10,:eof], [9])
        end
      end

      context "Scenario Outline" do
        before do
          @input = %{Feature: 1

  @tag3
  Scenario Outline: 4
    Given <foo> 5
    When <bar> 6

    @tag8
    Examples: 9
      | foo | bar |
      | 11  | 11  |
      | 12  | 12  |
      | 13  | 13  |
      | 14  | 14  |
      | 15  | 15  |

    @tag17
    Examples: 18
      | snip | snap |
      | 20   | 20   |
      | 21   | 21   |

  Scenario: 23
    Given 24
    When 25
}
        end

        it "should match step line of first scenario outline" do
          verify_filters([1,3,4,5,6,8,9,10,11,12,13,14,15,17,18,19,20,21,:eof], [6])
        end

        it "should match examples line of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,21,:eof], [18])
        end

        it "should match examples name of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,21,:eof], [/18/])
        end

        it "should match header row line of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,21,:eof], [19])
        end

        it "should match an example row of first scenario outline" do
          verify_filters([1,3,4,5,6,8,9,10,13,:eof], [13])
        end

        it "should match an example row of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,:eof], [20])
        end

        it "should match 2 example rows of first scenario outline" do
          verify_filters([1,3,4,5,6,8,9,10,12,14,:eof], [12,14])
        end

        it "should replay itself properly" do
          filtered = %{Feature: 1

  @tag3
  Scenario Outline: 4
    Given <foo> 5
    When <bar> 6

    @tag8
    Examples: 9
      | foo | bar |
      | 12  | 12  |
      | 14  | 14  |
}

          verify_output(filtered, [12,14])
        end
      end

      context "Scenarios with tags on both feature and scenarios" do
        before do
          # Lines with more than one tag per line will be repeated
          @input = %{#language:en
@a @b
Feature: 3
  @c @d
  Scenario: 5
    Given 6

  @c @e
  Scenario: 9
    Given 10

  Scenario: 12
    Given 13
}
        end

        it "should match @d" do
          verify_filters([1,2,2,3,4,4,5,6,:eof], ['@d'])
        end

        it "should match everything when feature tag matches" do
          verify_filters([1,2,2,3,4,4,5,6,8,8,9,10,12,13,:eof], ['@a'])
        end

        it "should match @a && !@d" do
          verify_filters([1,2,2,3,8,8,9,10,12,13,:eof], ['@a','~@d'])
        end

        it "should match @d || @e" do
          verify_filters([1,2,2,3,4,4,5,6,8,8,9,10,:eof], ['@d,@e'])
        end
      end

      context "Scenario Outlines with tags on examples" do
        before do
          # Lines with more than one tag per line will be repeated
          @input = %{#language:en
@a @b
Feature: 3
  @d
  Scenario Outline: 5
    Given 6

  @c @e
  Examples: 9
    | foo | bar |
    | 11  | 11  |

  @d @f
  Examples: 14
    | foo | bar |
    | 16  | 16  | 
    | 17  | 17  |

  Scenario: 19
    Given 20
}
        end

        it "should match @c" do
          verify_filters([1,2,2,3,4,5,6,8,8,9,10,11,:eof], ['@c'])
        end

        it "should match @d" do
          verify_filters([1,2,2,3,4,5,6,8,8,9,10,11,13,13,14,15,16,17,:eof], ['@d'])
        end

        it "should match @f" do
          verify_filters([1,2,2,3,4,5,6,13,13,14,15,16,17,:eof], ['@f'])
        end

        it "should match @a and not @c" do
          verify_filters([1,2,2,3,4,5,6,13,13,14,15,16,17,19,20,:eof], ['@a','~@c'])
        end

        it "should match @c or @d" do
          verify_filters([1,2,2,3,4,5,6,8,8,9,10,11,13,13,14,15,16,17,:eof], ['@c,@d'])
        end

        it "should not match @m" do
          verify_filters([:eof], ['@m'])
        end
      end
    end
  end
end
