# encoding: utf-8
require File.dirname(__FILE__) + '/../../spec_helper'
require 'gherkin/tools/pretty_listener'
require 'stringio'

module Gherkin
  module Tools
    describe PrettyListener do
      def assert_io(s)
        @io.rewind
        actual = @io.read
        actual.should == s
      end
      
      def assert_pretty(text)
        io = StringIO.new
        l = PrettyListener.new(io)
        parser = Parser.new(l, true, "root")
        lexer  = I18nLexer.new(parser)
        lexer.scan(text)
        io.rewind
        actual = io.read
        actual.should == text
      end

      before do
        @io = StringIO.new
        @l = PrettyListener.new(@io)
      end

      it "should print comments when scenario is longer" do
        @l.feature("Feature", "Hello\nWorld", 1)
        @l.locations([
          [nil, "features/foo.feature:4"],
          ['Given some stuff', "features/step_definitions/bar.rb:56"]
        ])
        @l.scenario("Scenario", "The scenario", 4)
        @l.step("Given ", "some stuff", 5)

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario # features/foo.feature:4
    Given some stuff     # features/step_definitions/bar.rb:56
})
      end

      it "should print comments when step is longer" do
        @l.feature("Feature", "Hello\nWorld", 1)
        @l.locations([
          [nil, "features/foo.feature:4"],
          ['Given some stuff that is longer', "features/step_definitions/bar.rb:56"]
        ])
        @l.scenario("Scenario", "The scenario", 4)
        @l.step("Given ", "some stuff that is longer", 5)

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario            # features/foo.feature:4
    Given some stuff that is longer # features/step_definitions/bar.rb:56
})
      end

      it "should print ANSI coloured steps" do
        @l.feature("Feature", "Hello\nWorld", 1)
        @l.locations([
          [nil, "features/foo.feature:4"],
          ['Given some stuff that is longer', "features/step_definitions/bar.rb:56"]
        ])
        @l.scenario("Scenario", "The scenario", 4)
        @l.step("Given ", "some [stuff] that is longer", 5)

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario            # features/foo.feature:4
    Given some [stuff] that is longer # features/step_definitions/bar.rb:56
})
      end

      it "should prettify a whole table with ANSI coloured rows" do
        @l.table(
          [
            %w(a bb),
            %w(ccc d),
            %w(ee ffff),
          ],
          0,
          [
            %w([a] b[b]),
            %w(c[c]c d),
            %w(ee f[ff]f),
          ]
        )
        assert_io(
          "      | [a]   | b[b]   |\n" +
          "      | c[c]c | d    |\n" +
          "      | ee  | f[ff]f |\n"
        )
      end

      it "should prettify 1 table row with ANSI coloured rows" do
        @l.table(
          [
            %w(a bb),
            %w(ccc d),
            %w(ee ffff),
          ],
          0,
          [
            %w(c[c]c d)
          ],
          1
        )
        assert_io(
          "      | c[c]c | d    |\n"
        )
      end

      it "should prettify scenario outline" do
        assert_pretty(%{Feature: Feature Description
  Some preamble

  Scenario: Scenario Description
    Given there is a step
      """
      with
        pystrings
      """
    And there is another step
      | æ | o |
      | a | ø |
    Then we will see steps
})
      end

    end
  end
end
