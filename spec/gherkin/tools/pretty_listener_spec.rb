# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/tools/pretty_listener'
require 'gherkin/format/argument'
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
        l = PrettyListener.new(io, true)
        parser = Gherkin::Parser.new(l, true, "root")
        lexer  = Gherkin::I18nLexer.new(parser)
        lexer.scan(text)
        io.rewind
        actual = io.read
        actual.should == text
      end

      before do
        @io = StringIO.new
        @l = PrettyListener.new(@io, true)
      end

      it "should print comments when scenario is longer" do
        @l.feature("Feature", "Hello\nWorld", 1)
        @l.steps([
          ['Given ', 'some stuff'],
          ['When ', 'foo']
        ])
        @l.scenario("Scenario", "The scenario", 4, "features/foo.feature:4")
        @l.step("Given ", "some stuff", 5, nil, nil, "features/step_definitions/bar.rb:56")
        @l.step("When ", "foo", 6, nil, nil, "features/step_definitions/bar.rb:96")

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario # features/foo.feature:4
    Given some stuff     # features/step_definitions/bar.rb:56
    When foo             # features/step_definitions/bar.rb:96
})
      end

      it "should print comments when step is longer" do
        @l.feature("Feature", "Hello\nWorld", 1)
        @l.steps([
          ['Given ', 'some stuff that is longer']
        ])
        @l.scenario("Scenario", "The scenario", 4, "features/foo.feature:4")
        @l.step("Given ", "some stuff that is longer", 5, nil, nil, "features/step_definitions/bar.rb:56")

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario            # features/foo.feature:4
    Given some stuff that is longer # features/step_definitions/bar.rb:56
})
      end

      it "should print ANSI coloured steps" do
        @l.feature("Feature", "Hello\nWorld", 1)
        @l.steps([
          ['Given ', 'some stuff that is longer']
        ])
        @l.scenario("Scenario", "The scenario", 4, "features/foo.feature:4")
        @l.step("Given ", "some stuff that is longer", 5, nil, nil, "features/step_definitions/bar.rb:56")

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario            # features/foo.feature:4
    Given some stuff that is longer # features/step_definitions/bar.rb:56
})
      end

      it "should prettify a whole table with padding (typically ANSI)" do
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

      it "should prettify 1 table row with padding (typically ANSI)" do
        e = Exception.new("Hello")
        @l.table(
          [
            %w(a bb),
            %w(ccc d),
            %w(ee ffff),
          ],
          0,
          [
            %w(ccc d)
          ],
          1,
          [:passed, :failed],
          e
        )
        assert_io(
          "      | ccc | d    |\n" +
          "      Hello (Exception)\n"
        )
      end

      it "should highlight arguments for regular steps" do
        @l.step("Given ", "I have 999 cukes in my belly", 3, :passed, [Gherkin::Format::Argument.new(7, '999')])
        assert_io("    Given I have 999 cukes in my belly\n")
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


      it "should prettify scenario outline with table" do
        assert_pretty(%{Feature: Feature Description
  Some preamble

  Scenario Outline: Scenario Ouline Description
    Given there is a
      """
      string with <foo>
      """
    And a table with
      | <bar> |
      | <baz> |

    Examples: Examples Description
      | foo    | bar  | baz         |
      | Banana | I    | am hungry   |
      | Beer   | You  | are thirsty |
      | Bed    | They | are tired   |
})
      end
    end
  end
end
