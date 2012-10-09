#encoding: utf-8
require 'spec_helper'

module Gherkin
  module Lexer
    shared_examples_for "encoding" do
      describe "with BOM" do
        it "should work just fine" do
          scan_file("with_bom.feature")
          @listener.to_sexp.should == [
            [:feature, "Feature", "Feature Text", "", 1],
            [:scenario, "Scenario", "Reading a Scenario", "", 2],
            [:step, "Given ", "there is a step", 3],
            [:eof]
          ]
        end

        describe "with ISO-8859-1 encoding" do
          it "should work just fine" do
            @lexer = Gherkin::Lexer::I18nLexer.new(@listener)
            scan_file("iso-8859-1.feature")
            @listener.to_sexp.should == [
              [:comment, "# language: no", 1], 
              [:comment, "# encoding: iSo-8859-1", 2],
              [:feature, "Egenskap", "ISO-8859-1", "", 3],
              [:scenario, "Scenario", "ÆØÅ", "", 4],
              [:step, "Når ", "this is encoded as Latin-1", 5],
              [:step, "Så ", "everything should parse", 6],
              [:eof]
            ]
          end
        end
      end
    end
  end
end
