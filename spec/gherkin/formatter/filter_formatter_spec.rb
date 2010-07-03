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

        source = File.new(File.dirname(__FILE__) + "/../fixtures/complex_for_filtering.feature").read
        lexer.scan(source, "complex.feature", 0)
        
        source_lines = source.split("\n")
        expected = (line_ranges.map do |line_range|
          source_lines[(line_range.first-1..line_range.last-1)]
        end.flatten + [""]).join("\n")
        io.string.should == expected
      end

      context "tags" do
        it "should filter on @tag4" do
          verify_filter(['@tag4'], 1..18)
        end

        it "should filter on @more" do
          verify_filter(['@more'], 1..14, 45..59)
        end
      end
    end
  end
end
