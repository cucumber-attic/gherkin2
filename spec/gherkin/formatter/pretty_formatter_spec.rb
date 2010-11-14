# encoding: utf-8
require 'gherkin/formatter/pretty_formatter'
require 'gherkin/formatter/argument'
require 'gherkin/formatter/model'
require 'gherkin/listener/formatter_listener'
require 'gherkin/parser/parser'
require 'stringio'

module Gherkin
  module Formatter
    describe PrettyFormatter do
      include Colors

      def assert_io(s)
        actual = @io.string
        actual.gsub!(/\e\[m/, "\e[0m") # JANSI resets without the 0.
        actual.should == s
      end
      
      def assert_pretty(input, expected_output=input)
        [true, false].each do |force_ruby|
          io = StringIO.new
          pf = Gherkin::Formatter::PrettyFormatter.new(io, true, false)
          parser = Gherkin::Parser::Parser.new(pf, true, "root", force_ruby)
          parser.parse(input, "test.feature", 0)
          output = io.string
          output.should == expected_output
        end
      end

      before do
        @io = StringIO.new
        @f = Gherkin::Formatter::PrettyFormatter.new(@io, false, true)
      end

      it "should print comments when scenario is longer" do
        @f.uri("features/foo.feature")
        @f.feature(Model::Feature.new([], [], "Feature", "Hello", "World", 1))

        step1 = Model::Step.new([], "Given ", "some stuff", 5)
        match1 = Model::Match.new([], "features/step_definitions/bar.rb:56")
        result1 = Model::Result.new('passed', nil)

        step2 = Model::Step.new([], "When ", "foo", 6)
        match2 = Model::Match.new([], "features/step_definitions/bar.rb:96")
        result2 = Model::Result.new('passed', nil)

        @f.steps([step1, step2])
        @f.scenario(Model::Scenario.new([], [], "Scenario", "The scenario", "", 4))

        @f.step(step1)
        @f.match(match1)
        @f.result(result1)

        @f.step(step2)
        @f.match(match2)
        @f.result(result2)

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario #{grey('# features/foo.feature:4')}
    #{magenta('Given ')}#{magenta('some stuff')}     #{grey('# features/step_definitions/bar.rb:56')}
\033[1A    #{green('Given ')}#{green('some stuff')}     #{grey('# features/step_definitions/bar.rb:56')}
    #{magenta('When ')}#{magenta('foo')}             #{grey('# features/step_definitions/bar.rb:96')}
\033[1A    #{green('When ')}#{green('foo')}             #{grey('# features/step_definitions/bar.rb:96')}
})
      end

      it "should print comments when step is longer" do
        @f.uri("features/foo.feature")
        @f.feature(Model::Feature.new([], [], "Feature", "Hello", "World", 1))
        step = Model::Step.new([], "Given ", "some stuff that is longer", 5)
        match = Model::Match.new([], "features/step_definitions/bar.rb:56")
        result = Model::Result.new('passed', nil)

        @f.steps([step])
        @f.scenario(Model::Scenario.new([], [], "Scenario", "The scenario", "", 4))
        @f.step(step)
        @f.match(match)
        @f.result(result)

        assert_io(%{Feature: Hello
  World

  Scenario: The scenario            #{grey('# features/foo.feature:4')}
    #{magenta('Given ')}#{magenta('some stuff that is longer')} #{grey('# features/step_definitions/bar.rb:56')}
\033[1A    #{green('Given ')}#{green('some stuff that is longer')} #{grey('# features/step_definitions/bar.rb:56')}
})
      end

      it "should highlight arguments for regular steps" do
        step = Model::Step.new([], "Given ", "I have 999 cukes in my belly", 3)
        match = Model::Match.new([Gherkin::Formatter::Argument.new(7, '999')], nil)
        result = Model::Result.new('passed', nil)

        @f.steps([step])
        @f.step(step)
        @f.match(match)
        @f.result(result)

        if defined?(JRUBY_VERSION)
          # Not terribly readable. The result on Java is different because JANSI uses semicolons when there are several codes.
          assert_io(
            "    \e[35mGiven \e[0m\e[35mI have \e[0m\e[35;1m999\e[0m\e[35m cukes in my belly\e[0m\n" +
            "\033[1A    \e[32mGiven \e[0m\e[32mI have \e[0m\e[32;1m999\e[0m\e[32m cukes in my belly\e[0m\n"
          )
        else
          assert_io(
            "    #{magenta('Given ')}#{magenta('I have ')}#{magenta(bold('999'))}#{magenta(' cukes in my belly')}\n" +
            "\033[1A    #{green('Given ')}#{green('I have ')}#{green(bold('999'))}#{green(' cukes in my belly')}\n"
          )
        end
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
        l = Gherkin::Formatter::PrettyFormatter.new(io, true, false)
        l.__send__(:table, [Gherkin::Formatter::Model::Row.new([], ['|', '\\'], nil)])
        io.string.should == '      | \\| | \\\\ |' + "\n"
      end
    end
  end
end
