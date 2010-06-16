#encoding: utf-8
require 'spec_helper'
require 'gherkin/parser/json_parser'

module Gherkin
  module Parser
    describe JSONParser do 

      before do
        @listener = Gherkin::SexpRecorder.new
        @parser = Gherkin::Parser::JSONParser.new(@listener)
      end

      describe "An empty feature" do
        it "should parse empty features" do
          @parser.parse('{}')
          @listener.to_sexp.should == [
            [:eof]
          ]
        end
      end

      describe "A barely empty feature" do
        it "should parse a feature with no elements" do
          @parser.parse('{ "keyword": "Feature", "name": "One", "description": "", "line" : 3 }')
          @listener.to_sexp.should == [
            [:feature, "Feature", "One", "", 3],
            [:eof]
          ]
        end
      end

      describe "Missing line numbers" do
        it "should indicate a line number of 0 if a line attribute doesn't exist" do
          @parser.parse('{ "name": "My Sweet Featur", "keyword": "Feature", "description": "" }')
          @listener.to_sexp.should == [
            [:feature, "Feature", "My Sweet Featur", "", 0],
            [:eof]
          ]
        end
      end

      describe "Keywords" do
        it "should use the keyword from the source when provided" do
          @parser.parse('{ "name" : "My Sweet Featur", "language": "fr", "keyword": "Feature", "description": "" }')
          @listener.to_sexp.should == [
            [:feature, "Feature", "My Sweet Featur", "",  0],
            [:eof]
          ]
        end
      end

      describe "A complex feature with tags, comments, multiple scenarios, and multiple steps and tables" do
        it "should find things in the right order" do
          parse_file("complex.json")
          @listener.to_sexp.should == [
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
end
