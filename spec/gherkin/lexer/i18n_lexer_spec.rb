#encoding: utf-8
require 'spec_helper'

module Gherkin
  module Lexer
    describe I18nLexer do
      before do
        @lexer = Gherkin::Lexer::I18nLexer.new(Gherkin::SexpRecorder.new, false)
      end

      it "should store the i18n language of the last scanned feature" do
        @lexer.scan("# language: fr\n")
        @lexer.i18n_language.iso_code.should == "fr"
        @lexer.scan("# language: no\n")
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
  end
end
