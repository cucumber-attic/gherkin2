# encoding: utf-8
require 'stringio'
require 'spec_helper'
require 'gherkin/i18n_lexer'
require 'gherkin/listener/formatter_listener'
require 'gherkin/formatter/filter_formatter'
require 'gherkin/formatter/pretty_formatter'

module Gherkin
  module Formatter
    describe FilterFormatter do
      def verify_filter(filters, *line_ranges)
        io = StringIO.new
        pretty_formatter = Gherkin::Formatter::PrettyFormatter.new(io, true)
        formatter = Gherkin::Formatter::FilterFormatter.new(pretty_formatter, filters)
        fl = Gherkin::Listener::FormatterListener.new(formatter)
        lexer = Gherkin::I18nLexer.new(fl)

        source = File.new(File.dirname(__FILE__) + "/../fixtures/complex_for_filtering.feature").read + "# __EOF__"
        lexer.scan(source, "complex.feature", 0)
        
        source_lines = source.split("\n")
        expected = (line_ranges.map do |line_range|
          source_lines[(line_range.first-1..line_range.last-1)]
        end.flatten).join("\n").gsub(/# __EOF__/, '')
        io.string.should == expected
      end

      context "tags" do
        it "should filter on scenario tag" do
          verify_filter(['@tag4'], 1..19)
        end

        it "should filter on abother scenario tag" do
          verify_filter(['@tag3'], 1..37)
        end

        it "should filter on scenario outline tag" do
          verify_filter(['@more'], 1..14, 46..61)
        end

        it "should filter on first examples tag" do
          verify_filter(['@neat'], 1..14, 46..55)
        end

        it "should filter on second examples tag" do
          verify_filter(['@hamster'], 1..14, 46..49, 56..61)
        end
      end

      context "names" do
        it "should filter on scenario name" do
          verify_filter([/Reading a Scenario/], 1..19)
        end

        it "should filter on scenario outline name" do
          verify_filter([/More/], 1..14, 46..61)
        end

        it "should filter on first examples name" do
          verify_filter([/Neato/], 1..14, 46..55)
        end

        it "should filter on second examples name" do
          verify_filter([/Rodents/], 1..14, 46..49, 56..61)
        end
      end

      context "lines" do
        context "on the same line as feature element keyword" do
          it "should filter on scenario line" do
            verify_filter([16], 1..19)
          end

          it "should filter on scenario outline line" do
            verify_filter([47], 1..14, 46..61)
          end

          it "should filter on first examples line" do
            verify_filter([51], 1..14, 46..55)
          end

          it "should filter on second examples line" do
            verify_filter([57], 1..14, 46..49, 56..61)
          end
        end

        context "on the same line as step keyword" do
          it "should filter on step line" do
            verify_filter([17], 1..19)
          end

          it "should filter on scenario outline line" do
            verify_filter([48], 1..14, 46..61)
          end
        end

        context "on the same line as table line" do
          it "should filter on first examples line" do
            verify_filter([52], 1..14, 46..55)
          end

          it "should filter on second examples line" do
            verify_filter([58], 1..14, 46..49, 56..61)
          end
        end
      end
    end
  end
end
