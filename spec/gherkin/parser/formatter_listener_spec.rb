require 'spec_helper'
require 'gherkin/parser/formatter_listener'
require 'gherkin/parser/parser'
require 'gherkin/i18n_lexer'
require 'stringio'

module Gherkin
  module Parser
    describe FormatterListener do
      before do
        @formatter = Gherkin::SexpRecorder.new
        @fl = Gherkin::Parser::FormatterListener.new(@formatter)
        @lexer = Gherkin::I18nLexer.new(Gherkin::Parser::Parser.new(@fl))
      end
      
      it "should pass tags to #feature method" do
        @fl.tag("@hello", 1)
        @fl.feature("Feature", "awesome", 2)
        @fl.eof

        @formatter.to_sexp.should == [
          [:feature, [], ["@hello"], "Feature", "awesome", nil],
          [:eof]
        ]
      end

      it "should pass comments to #feature method" do
        @fl.comment("# comment", 1)
        @fl.feature("Feature", "awesome", 2)
        @fl.eof

        @formatter.to_sexp.should == [
          [:feature, ["# comment"], [], "Feature", "awesome", nil],
          [:eof]
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
          [:feature,  ["# one"],  ["@two"],  "Feature",  "three", nil],
          [:scenario, ["# four"], ["@five"], "Scenario", "six", 6],
          [:eof]
        ]
      end

      it "should replay step table" do
        @fl.step("Given ", "foo", 10)
        @fl.row(['yo'], 11)
        @fl.row(['bro'], 12)
        @fl.eof

        @formatter.to_sexp.should == [
          [:step, [], "Given ",  "foo", 10, [['yo'], ['bro']], nil, nil, nil, nil],
          [:eof]
        ]
      end

      it "should format an entire feature" do
        @lexer.scan(File.new(File.dirname(__FILE__) + "/../fixtures/complex.feature").read, "complex.feature")
        @formatter.to_sexp.should == [
          [:feature, ["#Comment on line 1", "#Comment on line 2"], ["@tag1", "@tag2"],
            "Feature",
            "Feature Text\nIn order to test multiline forms\nAs a ragel writer\nI need to check for complex combinations",
            "complex.feature"],
          [:background, ["#Comment on line 9", "#Comment on line 11"], "Background", "", 13],
          [:step, [], "Given ", "this is a background step", 14, nil, nil, nil, nil, nil],
          [:step, [], "And ", "this is another one", 15, nil, nil, nil, nil, nil],
          [:scenario, [], ["@tag3", "@tag4"], "Scenario", "Reading a Scenario", 18],
          [:step, [], "Given ", "there is a step", 19, nil, nil, nil, nil, nil],
          [:step, [], "But ", "not another step", 20, nil, nil, nil, nil, nil],
          [:scenario, [], ["@tag3"], "Scenario", "Reading a second scenario\nWith two lines of text", 23],
          [:step, ["#Comment on line 24"], "Given ", "a third step with a table", 26, [["a", "b"], ["c", "d"], ["e", "f"]], nil, nil, nil, nil],
          [:step, [], "And ", "I am still testing things", 30, [["g", "h"], ["e", "r"], ["k", "i"], ["n", ""]], nil, nil, nil, nil],
          [:step, [], "And ", "I am done testing these tables", 35, nil, nil, nil, nil, nil],
          [:step, ["#Comment on line 29"], "Then ", "I am happy", 37, nil, nil, nil, nil, nil],
          [:scenario, [], [], "Scenario", "Hammerzeit", 39],
          [:step, [], "Given ", "All work and no play", 40, "Makes Homer something something\nAnd something else", nil, nil, nil, nil],
          [:step, [], "Then ", "crazy", 45, nil, nil, nil, nil, nil],
          [:eof]
        ]
      end
    end
  end
end
