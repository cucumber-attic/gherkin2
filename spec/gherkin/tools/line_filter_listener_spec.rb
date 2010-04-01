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
        scan(PrettyListener.new(io, true), filters)
        io.rewind
        io.read.should == expected_output
      end

      def scan(listener, filters)
        line_filter_listener = LineFilterListener.new(listener, filters)
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
          verify_filters([:eof], :lines=>[90])
        end

        it "should not replay anything if no names match" do
          verify_filters([:eof], :name_regexen=>[/pudding/])
        end

        it "should replay identically (except newlines) when there is no filter" do
          verify_filters([1,2,3,5,6,:eof], {})
        end

        it "should match scenario line of first scenario" do
          verify_filters([1,2,3,:eof], :lines=>[2])
        end

        it "should match name of first scenario" do
          verify_filters([1,2,3,:eof], :name_regexen=>[/2/])
        end

        it "should match step line of first scenario" do
          verify_filters([1,2,3,:eof], :lines=>[3])
        end

        it "should match step line of second scenario" do
          verify_filters([1,5,6,:eof], :lines=>[6])
        end

        it "should replay identically (except newlines) when the filter matches both scenarios" do
          verify_filters([1,2,3,5,6,:eof], :lines=>[3,6])
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
          verify_filters([1,2,3,4,5,6,8,9,10,11,12,13,:eof], :lines=>[])
        end

        it "should match step line of first scenario" do
          verify_filters([1,2,3,4,5,6,:eof], :lines=>[5])
        end

        it "should match scenario line of second scenario" do
          verify_filters([1,2,8,9,10,11,12,13,:eof], :lines=>[9])
        end
        
        it "should return everything when a line is given in each scenario" do
          verify_filters([1,2,3,4,5,6,8,9,10,11,12,13,:eof], :lines=>[6,9])
        end

        it "should return a scenario when a line is given for its tag" do
          verify_filters([1,2,8,9,10,11,12,13,:eof], :lines=>[8])
        end

        it "should return a scenario when a line is given for its comment" do
          verify_filters([1,2,3,4,5,6,:eof], :lines=>[3])
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
          verify_filters([1,2,3,4,5,7,8,9,10,12,13,14,15,16,:eof], :lines=>[])
        end

        it "should replay the background on step line of first scenario" do
          verify_filters([1,2,3,4,5,7,8,9,10,:eof], :lines=>[9])
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
          verify_filters([1,3,4,5,6,8,9,10,11,12,13,14,15,17,18,19,20,21,:eof], :lines=>[6])
        end

        it "should match examples line of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,21,:eof], :lines=>[18])
        end

        it "should match examples name of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,21,:eof], :name_regexen=>[/18/])
        end

        it "should match header row line of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,21,:eof], :lines=>[19])
        end

        it "should match an example row of first scenario outline" do
          verify_filters([1,3,4,5,6,8,9,10,13,:eof], :lines=>[13])
        end

        it "should match an example row of second scenario outline" do
          verify_filters([1,3,4,5,6,17,18,19,20,:eof], :lines=>[20])
        end

        it "should match 2 example rows of first scenario outline" do
          verify_filters([1,3,4,5,6,8,9,10,12,14,:eof], :lines=>[12,14])
        end

        it "should match example row of second scenario outline and scenario name of first scenario" do
          verify_filters([1,3,4,5,6,8,9,10,15,23,24,25,:eof], :lines => [15], :name_regexen=>[/23/])
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

          verify_output(filtered, :lines=>[12,14])
        end
      end
    end
  end
end
