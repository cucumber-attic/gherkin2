#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe Feature do
  
      def scan_file(file)
        Feature.new(@listener).scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read)
      end

      before(:each) do
        @listener = mock('listener').as_null_object
      end

      describe "A single feature, single scenario, single step" do
        
        after(:each) do
          Feature.new(@listener).scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature Text").once
        end
       
        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Reading a Scenario")
        end

        it "should find the step" do
          @listener.should_receive(:step).with("there is a step") 
        end
      end

      describe "A single feature, single scenario, three steps" do
        
        after(:each) do
          Feature.new(@listener).scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n    And another step\n   And a third step\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature Text").once
        end
       
        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Reading a Scenario")
        end

        it "should find the step" do
          @listener.should_receive(:step).with("there is a step").ordered
          @listener.should_receive(:step).with("another step").ordered
          @listener.should_receive(:step).with("a third step").ordered
        end
      end

      describe "A single multi-line feature with no scenario" do

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature Text\n  Line 2 \n Line 3")
          Feature.new(@listener).scan("Feature: Feature Text\n  Line 2 \n Line 3")
        end

      end

      describe "A multi-line feature with a multi-line scenario but no steps" do
        after(:each) do
          Feature.new(@listener).scan("Feature: Feature Text\n  Line 2 \n Line 3\n    Scenario: Reading a Scenario\n  With two lines\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature Text\n  Line 2 \n Line 3")
        end

        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Reading a Scenario\n  With two lines")
        end
      end
    end
  end
end
