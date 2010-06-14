#encoding: utf-8
require 'spec_helper'

module Gherkin
  describe I18nLexer do
    before do
      @lexer = Gherkin::I18nLexer.new(Gherkin::SexpRecorder.new, false)
    end

    it "should store the i18n language of the last scanned feature" do
      @lexer.scan("# language: fr\n", "fr.feature", 0)
      @lexer.i18n_language.iso_code.should == "fr"
      @lexer.scan("# language: no\n", "en.feature", 0)
      @lexer.i18n_language.iso_code.should == "no"
    end

    it "should use English i18n by default" do
      @lexer.scan("Feature: foo\n", "foo.feature", 0)
      @lexer.i18n_language.iso_code.should == "en"
    end

    it "should === its ruby class, even when the impl is Java" do
      Gherkin::I18nLexer.should === Gherkin::I18nLexer.new(Gherkin::SexpRecorder.new, true)
    end
  end
end
