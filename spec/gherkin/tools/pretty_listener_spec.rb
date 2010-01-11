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

    end
  end
end
