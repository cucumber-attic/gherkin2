require 'spec_helper'
require 'gherkin/parser/formatter_listener'
require 'gherkin/parser/parser'
require 'gherkin/i18n_lexer'
require 'stringio'

module Gherkin
  module Parser
    describe FormatterListener do
      before do
        @formatter = SexpRecorder.new
        @fl = FormatterListener.new(@formatter)
        @lexer = Gherkin::I18nLexer.new(Gherkin::Parser::Parser.new(@fl))
      end
      
      it "should pass tags to #feature method" do
        @fl.tag("@hello", 1)
        @fl.feature("Feature", "awesome", 2)
        @fl.eof

        @formatter.to_sexp.should == [
          [:feature, [], ["@hello"], "Feature", "awesome"]
        ]
      end

      it "should pass comments to #feature method" do
        @fl.comment("# comment", 1)
        @fl.feature("Feature", "awesome", 2)
        @fl.eof

        @formatter.to_sexp.should == [
          [:feature, ["# comment"], [], "Feature", "awesome"]
        ]
      end

      it "should pass comments and tags to #feature and #scenario methods" do
        @fl.comment("# one", 1)
        @fl.tag("@two", 2)
        @fl.feature("Feature", "three", 3)
        @fl.comment("# four", 4)
        @fl.tag("@five", 5)
        @fl.scenario("Scenario", "six", 6)
        @fl.eof

        @formatter.to_sexp.should == [
          [:feature,  ["# one"],  ["@two"],  "Feature",  "three"],
          [:scenario, ["# four"], ["@five"], "Scenario", "six"]
        ]
      end

      it "should replay step table" do
        @fl.step("Given ", "foo", 10)
        @fl.row(['yo'], 11)
        @fl.row(['bro'], 12)
        @fl.eof

        @formatter.to_sexp.should == [
          [:step, [], "Given ",  "foo", [['yo'], ['bro']]]
        ]
      end

      it "should format an entire feature" do
        @lexer.scan(File.new(File.dirname(__FILE__) + "/../fixtures/complex.feature").read)
        @formatter.to_sexp.should == [
          [:feature, 
            ["#Comment on line 1", "#Comment on line 2"],
            ["@tag1", "@tag2"],
            "Feature",
            "Feature Text\nIn order to test multiline forms\nAs a ragel writer\nI need to check for complex combinations"],
          [:background,
            ["#Comment on line 9", "#Comment on line 11"],
            [],
            "Background",
            ""],
          [:scenario, 
            [], 
            ["@tag3", "@tag4"], 
            "Scenario", "Reading a Scenario"],
          [:scenario,
            [],
            ["@tag3"],
            "Scenario",
            "Reading a second scenario\nWith two lines of text"],
          [:scenario, 
            [], 
            [], 
            "Scenario", "Hammerzeit"],
          [:step,
            [],
            "Then ",
            "crazy",
            "Makes Homer something something\nAnd something else"
          ]
        ]
      end
    end
  end
end
