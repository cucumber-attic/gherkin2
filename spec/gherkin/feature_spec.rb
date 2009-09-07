#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe Feature do
      before do
        @listener = mock('listener').as_null_object
        @feature = Feature.new(@listener)
      end
  
      def scan_file(file)
        Feature.new(@listener).scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read)
      end

      describe "A single feature, single scenario, single step" do
        
        after(:each) do
          @feature.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
        end
       
        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).once
        end

        it "should find the step" do
          @listener.should_receive(:step).with("Given", "there is a step", 3).once
        end
      end

      describe "A single feature, single scenario, three steps" do
        
        after(:each) do
          @feature.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n    And another step\n   And a third step\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
        end
       
        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).once
        end

        it "should find the step" do
          @listener.should_receive(:step).with("Given", "there is a step", 3).ordered
          @listener.should_receive(:step).with("And", "another step", 4).ordered
          @listener.should_receive(:step).with("And", "a third step", 5).ordered
        end
      end

      describe "A single feature with no scenario" do
        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
          @feature.scan("Feature: Feature Text\n")
        end
      end
      
      describe "A multi-line feature with no scenario" do
        it "should find the feature" do
          pending
          @listener.should_receive(:feature).with("Feature", "Feature Text\n  And some more text", 1).once
          @feature.scan("Feature: Feature Text\n  And some more text")
        end
      end

      describe "A feature with a scenario but no steps" do
        after(:each) do
          @feature.scan("Feature: Feature Text\nScenario: Reading a Scenario\n")
        end

        it "should find the feature" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).once
        end

        it "should find the scenario" do
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).once
        end
      end

      describe "A feature with two scenarios" do
        after(:each) do
          @feature.scan("Feature: Feature Text\nScenario: Reading a Scenario\n  Given a step\n\nScenario: A second scenario\n Given another step\n")
        end

        it "should find things in the right order" do
          @listener.should_receive(:feature).with("Feature", "Feature Text", 1).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 2).ordered
          @listener.should_receive(:step).with("Given", "a step", 3).ordered
          @listener.should_receive(:scenario).with("Scenario", "A second scenario", 5).ordered
          @listener.should_receive(:step).with("Given", "another step", 6).ordered
        end
      end

      describe "A simple feature with comments" do
        it "should find things in the right order" do
          @listener.should_receive(:comment).with("Here is a comment", 1).ordered
          @listener.should_receive(:feature).with("Feature", "Feature Text", 2).ordered
          @listener.should_receive(:comment).with("Here is another # comment", 3).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 4).ordered
          @listener.should_receive(:comment).with("Here is a third comment", 5).ordered
          @listener.should_receive(:step).with("Given", "there is a step", 6).ordered
          @listener.should_receive(:comment).with("Here is a fourth comment", 7).ordered
          scan_file("simple_with_comments.feature")
        end
      end
      
      describe "A simple feature with tags" do
        it "should find things in the right order" do
          @listener.should_receive(:tag).with("@tag1", 1).ordered
          @listener.should_receive(:tag).with("@tag2", 1).ordered
          @listener.should_receive(:feature).with("Feature", "Feature Text", 2).ordered
          @listener.should_receive(:tag).with("@tag3", 3).ordered
          @listener.should_receive(:tag).with("@tag4", 3).ordered
          @listener.should_receive(:scenario).with("Scenario", "Reading a Scenario", 4).ordered
          @listener.should_receive(:step).with("Given", "there is a step", 5).ordered
          scan_file("simple_with_tags.feature")
        end
      end
    end
  end
end
