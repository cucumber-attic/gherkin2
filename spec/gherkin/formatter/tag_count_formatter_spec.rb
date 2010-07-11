# encoding: utf-8
require 'spec_helper'
require 'gherkin/i18n_lexer'
require 'gherkin/listener/formatter_listener'
require 'gherkin/formatter/tag_count_formatter'

module Gherkin
  module Formatter
    describe TagCountFormatter do
      it "should count tags" do
        dummy = Gherkin::SexpRecorder.new
        @formatter = Gherkin::Formatter::TagCountFormatter.new(dummy)
        fl = Gherkin::Listener::FormatterListener.new(@formatter)
        @lexer = Gherkin::I18nLexer.new(fl)

        fl.location('complex.feature')
        @lexer.scan(File.new(File.dirname(__FILE__) + "/../fixtures/complex_with_tags.feature").read)
        
        @formatter.tag_counts.should == {
          "@hamster" => ["complex.feature:58"],
          "@tag1"    => ["complex.feature:18","complex.feature:23","complex.feature:39","complex.feature:52","complex.feature:58"],
          "@tag2"    => ["complex.feature:18","complex.feature:23","complex.feature:39","complex.feature:52","complex.feature:58"],
          "@tag3"    => ["complex.feature:18", "complex.feature:23"],
          "@tag4"    => ["complex.feature:18"],
          "@neat"    => ["complex.feature:52"],
          "@more"    => ["complex.feature:52", "complex.feature:58"]
        }
      end
    end
  end
end
