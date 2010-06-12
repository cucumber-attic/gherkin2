require 'spec_helper'
require 'gherkin/parser/formatter_listener'
require 'stringio'

module Gherkin
  module Parser
    describe FormatterListener do
      before do
        @formatter = SexpRecorder.new
        @fl = FormatterListener.new(@formatter)
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
          [:step, [], "Given ",  "foo", [['yo'], ['bro']]],
        ]
      end
    end
  end
end
