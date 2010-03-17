# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/tools/filter_listener'
require 'gherkin/tools/pretty_listener'
require 'stringio'

module Gherkin
  module Tools
    describe FilterListener do
      before do
        @io = StringIO.new
        pl = PrettyListener.new(@io, true)
        fl = FilterListener.new(pl)
        parser = Gherkin::Parser.new(fl, true, "root")
        @lexer  = Gherkin::I18nLexer.new(parser)
      end

      it "should replay everything when there is no filter" do
        input = %{Feature: one

  Scenario: two
    Given three
    When four
}
        @lexer.scan(input)
        @io.rewind
        @io.read.should == input
      end
    end
  end
end