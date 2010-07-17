#encoding: utf-8
require 'spec_helper'
require 'gherkin/json_parser'

module Gherkin
  describe JSONParser do 

    before do
      @listener = Gherkin::SexpRecorder.new
      @parser = Gherkin::JSONParser.new(nil)
    end

    describe "An empty feature" do
      it "should scan empty features" do
        @parser.parse_with_listener('{}', @listener)
        @listener.to_sexp.should == [
          [:location, "unknown.json"],
          [:eof]
        ]
      end
    end

    describe "A barely empty feature" do
      it "should scan a feature with no elements" do
        @parser.parse_with_listener('{ "type": "feature", "keyword": "Feature", "name": "One", "description": "", "line" : 3 }', @listener)
        @listener.to_sexp.should == [
          [:location, "unknown.json"],
          [:feature, "Feature", "One", "", 3],
          [:eof]
        ]
      end
    end

    describe "Keywords" do
      it "should use the keyword from the source when provided" do
        @parser.parse_with_listener('{ "type": "feature", "name" : "My Sweet Featur", "language": "fr", "keyword": "Feature", "description": "" }', @listener)
        @listener.to_sexp.should == [
          [:location, "unknown.json"],
          [:feature, "Feature", "My Sweet Featur", "",  nil],
          [:eof]
        ]
      end
    end

    describe "A complex feature with tags, comments, multiple scenarios, and multiple steps and tables" do
      it "should find things in the right order" do
        @parser.parse_with_listener(fixture("complex.json"), @listener)
        @listener.to_sexp.should == [
          [:location, "unknown.json"],
          [:tag, "@tag1", nil],
          [:tag, "@tag2", nil],
          [:feature, "Feature", "Feature Text","In order to test multiline forms", nil],
          [:background, "Background", "", "", nil],
          [:step, "Given ", "this is a background step", nil],
          [:step, "When ", "this is another one", 412],
          [:tag, "@foo", nil],
          [:scenario_outline, "Scenario Outline", "An Scenario Outline","", nil],
          [:step, "Given ", "A step with a table", nil],
          [:row, %w{a row for a step}, nil],
          [:tag, "@exampletag", nil],
          [:examples, "Examples", "Sweet Example", "", nil],
          [:row, %w{Fill In}, nil],
          [:row, %w{The Blanks}, nil],
          [:tag, "@tag3", nil],
          [:tag, "@tag4", nil],
          [:scenario, "Scenario", "Reading a Scenario", "", nil],
          [:step, "Given ", "there is a step", nil],
          [:step, "But ", "not another step", nil],
          [:tag, "@tag3", nil],
          [:scenario, "Scenario", "Reading a second scenario", "With two lines of text", nil],
          [:step, "Given ", "a third step with a table", nil],
          [:row, %w{a b}, 987],
          [:row, %w{c d}, nil],
          [:row, %w{e f}, nil],
          [:step, "Given ", "I am still testing things", nil],
          [:row, %w{g h}, nil],
          [:row, %w{e r}, nil],
          [:row, %w{k i}, nil],
          [:row, ['n', ''], nil], 
          [:step, "Given ", "I am done testing these tables", nil],
          [:step, "Given ", "I am happy", nil],
          [:scenario, "Scenario", "Hammerzeit", "", nil],
          [:step, "Given ", "All work and no play", nil],
          [:py_string, "Makes Homer something something\nAnd something else", 777],
          [:step, "Given ", "crazy", nil],
          [:eof]
        ]
      end        
    end
  end
end
