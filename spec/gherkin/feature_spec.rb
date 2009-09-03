#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe Feature do
  
      def scan_file(file)
        Feature.new.scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read,@listener)
      end

      before(:each) do
        @listener = mock('listener').as_null_object
      end

      describe "A single feature, single scenario, single step" do
        it "should find the feature" do
          @listener.should_receive(:feature_found).with("Feature Text").once
          Feature.new.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n", @listener)
        end
       
        it "should find the scenario" do
          @listener.should_receive(:scenario_found).with("Reading a Scenario")
          Feature.new.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n", @listener)
        end

        it "should find the step" do
          @listener.should_receive(:step_found).with("there is a step") 
          Feature.new.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n", @listener)
        end
      end
    end
  end
end
