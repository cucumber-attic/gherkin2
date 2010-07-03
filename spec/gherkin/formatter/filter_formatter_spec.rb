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
      it "should filter on tags" do
        filters = ['@tag4']
        
        io = StringIO.new
        pretty_formatter = Gherkin::Formatter::PrettyFormatter.new(io, true)
        @formatter = Gherkin::Formatter::FilterFormatter.new(pretty_formatter, filters)
        fl = Gherkin::Listener::FormatterListener.new(@formatter)
        @lexer = Gherkin::I18nLexer.new(fl)

        source = File.new(File.dirname(__FILE__) + "/../fixtures/complex_for_filtering.feature").read
        @lexer.scan(source, "complex.feature", 0)
        
        io.string.should == source.split("\n")[0..18].join("\n")
      end
    end
  end
end
