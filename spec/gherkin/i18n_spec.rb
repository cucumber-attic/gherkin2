#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe "i18n parsing" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @parser = Parser['no'].new(@listener)
      end

      def scan_file(file)
        @parser.scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read)
      end

      it "should recognize keywords in the language of the parser" do
        scan_file("i18n_no.feature")
        @listener.to_sexp.should == [
          [:feature, "Egenskap", "i18n support", 1], 
          [:scenario, "Scenario", "Parsing many languages", 3], 
          [:step, "Gitt", "Gherkin supports many languages", 4],
          [:step, "Når",  "Norwegian keywords are parsed", 5],
          [:step, "Så", "they should be recognized", 6]
        ]
      end
    end
  end
end
