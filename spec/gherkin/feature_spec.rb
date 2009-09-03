#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin

  describe Feature do
  
    def scan_file(file)
      Feature.new.scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read,@listener)
    end

    before(:each) do
      @listener = mock('listener')
    end

    it "should parse a feature with a scenario and a step and pass each to the listener" do
      [[:feature, "Feature Text"],
       [:scenario, "Reading a Scenario"],
       [:given,"there is a step"]].each do |event, data|
         @listener.should_receive(event).with(data)
      end
      Feature.new.scan("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n", @listener)
    end
    
    it "should parse a feature from a file with a scenario and a step and pass each to the listener" do
      [[:feature, "Feature Text"],
       [:scenario, "Reading a Scenario"],
       [:given,"there is a step"]].each do |event, data|
         @listener.should_receive(event).with(data)
      end
      scan_file("simple.feature")
    end
  end
end
