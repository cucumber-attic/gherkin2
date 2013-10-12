#encoding: utf-8
require 'spec_helper'

module Gherkin
  module Lexer
    describe I18nLexer do
      context "when default ios_code is 'en'" do
        before do
          @lexer = Gherkin::Lexer::I18nLexer.new(Gherkin::SexpRecorder.new, false)
        end

        it "should store the i18n language of the last scanned feature" do
          @lexer.scan("# language: fr\n")
          # This if is kind of dumb - it's just to avoid warnings from ruby
          if(@lexer.i18n_language.iso_code.should == "fr")
            @lexer.scan("# language: no\n")
            @lexer.i18n_language.iso_code.should == "no"
          else
            fail
          end
        end

        it "should detect language when there are spaces and CRLF" do
          @lexer.scan("# language: da  \r\n")
          @lexer.i18n_language.iso_code.should == "da"
        end

        it "should detect language when the language comment is not the first line" do
          @lexer.scan("# hello\n# language: no\n")
          @lexer.i18n_language.iso_code.should == "no"
        end

        it "should detect language when the language is on the third line, and there are empty lines above" do
          @lexer.scan("# hello\n\n# language: no\n")
          @lexer.i18n_language.iso_code.should == "no"
        end

        it "should use English i18n by default" do
          @lexer.scan("Feature: foo\n")
          @lexer.i18n_language.iso_code.should == "en"
        end

        it "should === its ruby class, even when the impl is Java" do
          Gherkin::Lexer::I18nLexer.should === Gherkin::Lexer::I18nLexer.new(Gherkin::SexpRecorder.new, true)
        end
      end

      context "when default ios_code is 'ja'" do
        before do
          @lexer = Gherkin::Lexer::I18nLexer.new(Gherkin::SexpRecorder.new, false, 'ja')
        end

        it "should store the i18n language of the last scanned feature" do
          @lexer.scan("# language: en")
          @lexer.i18n_language.iso_code.should == "en"
        end

        it "should use Japanese" do
          @lexer.scan("機能: foo\n")
          @lexer.i18n_language.iso_code.should == "ja"
        end
      end
    end
  end
end
