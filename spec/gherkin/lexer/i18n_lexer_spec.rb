#encoding: utf-8
require 'spec_helper'

module Gherkin
  module Lexer
    describe I18nLexer do
      context "when default ios_code is 'en'" do
        before do
          @lexer = Gherkin::Lexer::I18nLexer.new(Gherkin::SexpRecorder.new, false)
        end

        it "stores the i18n language of the last scanned feature" do
          @lexer.scan("# language: fr\n")
          # This if is kind of dumb - it's just to avoid warnings from ruby
          if(expect(@lexer.i18n_language.iso_code).to eq("fr"))
            @lexer.scan("# language: no\n")
            expect(@lexer.i18n_language.iso_code).to eq("no")
          else
            fail
          end
        end

        it "detects language when there are spaces and CRLF" do
          @lexer.scan("# language: da  \r\n")
          expect(@lexer.i18n_language.iso_code).to eq("da")
        end

        it "detects language when the language comment is not the first line" do
          @lexer.scan("# hello\n# language: no\n")
          expect(@lexer.i18n_language.iso_code).to eq("no")
        end

        it "detects language when the language is on the third line, and there are empty lines above" do
          @lexer.scan("# hello\n\n# language: no\n")
          expect(@lexer.i18n_language.iso_code).to eq("no")
        end

        it "uses English i18n by default" do
          @lexer.scan("Feature: foo\n")
          expect(@lexer.i18n_language.iso_code).to eq("en")
        end

        it "is === its ruby class, even when the impl is Java" do
          expect(Gherkin::Lexer::I18nLexer).to be === Gherkin::Lexer::I18nLexer.new(Gherkin::SexpRecorder.new, true)
        end
      end

      context "when default ios_code is 'ja'" do
        before do
          @lexer = Gherkin::Lexer::I18nLexer.new(Gherkin::SexpRecorder.new, false, 'ja')
        end

        it "stores the i18n language of the last scanned feature" do
          @lexer.scan("# language: en")
          expect(@lexer.i18n_language.iso_code).to eq("en")
        end

        it "uses Japanese" do
          @lexer.scan("機能: foo\n")
          expect(@lexer.i18n_language.iso_code).to eq("ja")
        end
      end
    end
  end
end
