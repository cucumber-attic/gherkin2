# encoding: utf-8
require 'spec_helper'
require 'gherkin/formatter/pretty_formatter'
require 'gherkin/formatter/argument'
require 'gherkin/formatter/model'
require 'gherkin/listener/formatter_listener'
require 'stringio'

module Gherkin
  module Formatter
    describe PrettyFormatter do
      def assert_io(s)
        actual = @io.string
        actual.should == s
      end
      
      def assert_pretty(input, output=input)
        [true, false].each do |force_ruby|
          io = StringIO.new
          pf = Gherkin::Formatter::PrettyFormatter.new(io, true)
          parser = Gherkin::Parser::Parser.new(pf, true, "root", force_ruby)
          parser.parse(input, "test.feature", 0)
          actual = io.string
          actual.should == output
        end
      end

      def result(status, error_message, arguments, stepdef_location)
        Model::Result.new(status, error_message, arguments, stepdef_location)
      end

      before do
        @io = StringIO.new
        @l = Gherkin::Formatter::PrettyFormatter.new(@io, true)
      end

      it "should print comments when scenario is longer" do
        @l.feature(Model::Statement.new([], [], "Feature", "Hello", "World", 1), "features/foo.feature")
        @l.steps([
          ['Given ', 'some stuff'],
          ['When ', 'foo']
        ])
        @l.scenario(Model::Statement.new([], [], "Scenario", "The scenario", "", 4))
        @l.step(Model::Statement.new([], [], "Given ", "some stuff", "", 5), nil, result('passed', nil, nil, "features/step_definitions/bar.rb:56"))
        @l.step(Model::Statement.new([], [], "When ", "foo", "", 6), nil, result('passed', nil, nil, "features/step_definitions/bar.rb:96"))

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario # features/foo.feature:4
    Given some stuff     # features/step_definitions/bar.rb:56
    When foo             # features/step_definitions/bar.rb:96
})
      end

      it "should print comments when step is longer" do
        @l.feature(Model::Statement.new([], [], "Feature", "Hello", "World", 1), "features/foo.feature")
        @l.steps([
          ['Given ', 'some stuff that is longer']
        ])
        @l.scenario(Model::Statement.new([], [], "Scenario", "The scenario", "", 4))
        @l.step(Model::Statement.new([], [], "Given ", "some stuff that is longer", "", 5), nil, result('passed', nil, nil, "features/step_definitions/bar.rb:56"))

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario            # features/foo.feature:4
    Given some stuff that is longer # features/step_definitions/bar.rb:56
})
      end

      it "should highlight arguments for regular steps" do
        @l.step(Model::Statement.new([], [], "Given ", "I have 999 cukes in my belly", "", 3), nil, result('passed', nil, [Gherkin::Formatter::Argument.new(7, '999')], nil))
        assert_io("    Given I have 999 cukes in my belly\n")
      end

      it "should prettify scenario" do
        assert_pretty(%{Feature: Feature Description
  Some preamble

  Scenario: Scenario Description
    description has multiple lines

    Given there is a step
      """
      with
        pystrings
      """
    And there is another step
      | æ   | \\|o |
      | \\|a | ø\\\\ |
    Then we will see steps
})
      end


      it "should prettify scenario outline with table" do
        assert_pretty(%{# A feature comment
@foo
Feature: Feature Description
  Some preamble
  on several
  lines

  # A Scenario Outline comment
  @bar
  Scenario Outline: Scenario Ouline Description
    Given there is a
      """
      string with <foo>
      """
    And a table with
      | <bar> |
      | <baz> |

    @zap @boing
    Examples: Examples Description
      | foo    | bar  | baz         |
      | Banana | I    | am hungry   |
      | Beer   | You  | are thirsty |
      | Bed    | They | are tired   |
})
      end

      it "should preserve tabs" do
        assert_pretty(IO.read(File.dirname(__FILE__) + '/tabs.feature'), IO.read(File.dirname(__FILE__) + '/spaces.feature'))
      end

      it "should escape backslashes and pipes" do
        io = StringIO.new
        l = Gherkin::Formatter::PrettyFormatter.new(io, true)
        l.__send__(:table, [Gherkin::Formatter::Model::Row.new(['|', '\\'], [], nil)])
        io.string.should == '      | \\| | \\\\ |' + "\n"
      end
    end
  end
end
