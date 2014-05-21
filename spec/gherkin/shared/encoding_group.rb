#encoding: utf-8
module Gherkin
  module Lexer
    shared_examples_for "encoding" do
      describe "with BOM" do
        it "works just fine" do
          scan_file("with_bom.feature")
          expect(@listener.to_sexp).to eq([
            [:feature, "Feature", "Feature Text", "", 1],
            [:scenario, "Scenario", "Reading a Scenario", "", 2],
            [:step, "Given ", "there is a step", 3],
            [:eof]
          ])
        end

        describe "and language specification" do
          it "works just fine" do
            @lexer = Gherkin::Lexer::I18nLexer.new(@listener)
            scan_file("with_bom_and_language_spec.feature")
            expect(@listener.to_sexp).to eq([
              [:comment, "# language: nl", 1],
              [:feature, "Functionaliteit", "Feature Text", "", 2],
              [:scenario, "Scenario", "Reading a Scenario", "", 3],
              [:step, "Gegeven ", "there is a step", 4],
              [:eof]
            ])
          end
        end

        describe "with ISO-8859-1 encoding" do
          it "works just fine" do
            @lexer = Gherkin::Lexer::I18nLexer.new(@listener)
            scan_file("iso-8859-1.feature")
            expect(@listener.to_sexp).to eq([
              [:comment, "# language: no", 1],
              [:comment, "# encoding: iSo-8859-1", 2],
              [:feature, "Egenskap", "ISO-8859-1", "", 3],
              [:scenario, "Scenario", "ÆØÅ", "", 4],
              [:step, "Når ", "this is encoded as Latin-1", 5],
              [:step, "Så ", "everything should parse", 6],
              [:eof]
            ])
          end
        end
      end
    end
  end
end
