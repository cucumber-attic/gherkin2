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

    describe "Missing line numbers" do
      it "should indicate a line number of 0 if a line attribute doesn't exist" do
        @parser.parse_with_listener('{ "type": "feature", "name": "My Sweet Featur", "keyword": "Feature", "description": "" }', @listener)
        @listener.to_sexp.should == [
          [:location, "unknown.json"],
          [:feature, "Feature", "My Sweet Featur", "", 0],
          [:eof]
        ]
      end
    end

    describe "Keywords" do
      it "should use the keyword from the source when provided" do
        @parser.parse_with_listener('{ "type": "feature", "name" : "My Sweet Featur", "language": "fr", "keyword": "Feature", "description": "" }', @listener)
        @listener.to_sexp.should == [
          [:location, "unknown.json"],
          [:feature, "Feature", "My Sweet Featur", "",  0],
          [:eof]
        ]
      end
    end

    describe "A complex feature with tags, comments, multiple scenarios, and multiple steps and tables" do
      it "should find things in the right order" do
        @parser.parse_with_listener(fixture("complex.json"), @listener)
        @listener.to_sexp.should == [
          [:location, "unknown.json"],
          [:tag, "@tag1", 0],
          [:tag, "@tag2", 0],
          [:feature, "Feature", "Feature Text","In order to test multiline forms", 0],
          [:background, "Background", "", "", 0],
          [:step, "Given ", "this is a background step", 0],
          [:step, "When ", "this is another one", 412],
          [:tag, "@foo", 0],
          [:scenario_outline, "Scenario Outline", "An Scenario Outline","", 0],
          [:step, "Given ", "A step with a table", 0],
          [:row, %w{a row for a step}, 0],
          [:tag, "@exampletag", 0],
          [:examples, "Examples", "Sweet Example", "", 0],
          [:row, %w{Fill In}, 0],
          [:row, %w{The Blanks}, 0],
          [:tag, "@tag3", 0],
          [:tag, "@tag4", 0],
          [:scenario, "Scenario", "Reading a Scenario", "", 0],
          [:step, "Given ", "there is a step", 0],
          [:step, "But ", "not another step", 0],
          [:tag, "@tag3", 0],
          [:scenario, "Scenario", "Reading a second scenario", "With two lines of text", 0],
          [:step, "Given ", "a third step with a table", 0],
          [:row, %w{a b}, 0],
          [:row, %w{c d}, 0],
          [:row, %w{e f}, 0],
          [:step, "Given ", "I am still testing things", 0],
          [:row, %w{g h}, 0],
          [:row, %w{e r}, 0],
          [:row, %w{k i}, 0],
          [:row, ['n', ''], 0], 
          [:step, "Given ", "I am done testing these tables", 0],
          [:step, "Given ", "I am happy", 0],
          [:scenario, "Scenario", "Hammerzeit", "", 0],
          [:step, "Given ", "All work and no play", 0],
          [:py_string, "Makes Homer something something\nAnd something else", 0 ],
          [:step, "Given ", "crazy", 0],
          [:eof]
        ]
      end        
    end
  end
end
