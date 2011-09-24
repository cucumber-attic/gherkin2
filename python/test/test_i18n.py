# -*- coding: utf8 -*-

import unittest

from gherkin.i18n import I18n

class TestI18n(unittest.TestCase):
    """
module Gherkin
  module Lexer
    describe I18n do
      before do
        @listener = Gherkin::SexpRecorder.new
      end

      def scan_file(lexer, file)
        lexer.scan(File.new(File.dirname(__FILE__) + "/fixtures/" + file).read)
      end

      it "should recognize keywords in the language of the lexer" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_no.feature")
        @listener.to_sexp.should == [
          [:comment, "#language:no", 1],
          [:feature, "Egenskap", "i18n support", "", 2],
          [:scenario, "Scenario", "Parsing many languages", "", 4],
          [:step, "Gitt ", "Gherkin supports many languages", 5],
          [:step, "Når ",  "Norwegian keywords are parsed", 6],
          [:step, "Så ", "they should be recognized", 7],
          [:eof]
        ]
      end

      it "should parse languages without a space after keywords" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_zh-CN.feature")
        @listener.to_sexp.should == [
          [:comment, "#language:zh-CN", 1],
          [:feature, "功能", "加法", "", 2],
          [:scenario, "场景", "两个数相加", "", 4],
          [:step, "假如", "我已经在计算器里输入6", 5],
          [:step, "而且", "我已经在计算器里输入7", 6],
          [:step, "当", "我按相加按钮", 7],
          [:step, "那么", "我应该在屏幕上看到的结果是13", 8],
          [:eof]
        ]
      end

      it "should parse languages with spaces after some keywords but not others" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_fr.feature")
        @listener.to_sexp.should == [
          [:comment, "#language:fr", 1],
          [:feature, "Fonctionnalité", "Addition", "", 2],
          [:scenario_outline, "Plan du scénario", "Addition de produits dérivés", "", 3],
          [:step, "Soit ", "une calculatrice", 4],
          [:step, "Etant donné ", "qu'on tape <a>", 5],
          [:step, "Et ", "qu'on tape <b>", 6],
          [:step, "Lorsqu'", "on tape additionner", 7],
          [:step, "Alors ", "le résultat doit être <somme>", 8],
          [:examples, "Exemples", "", "", 10],
          [:row, %w{a b somme}, 11],
          [:row, %w{2 2 4}, 12],
          [:row, %w{2 3 5}, 13],
          [:eof]
        ]
      end
      """

    def test_have_code_keywords_without_punctuation(self):
        code_keywords = (u'Avast', u'Akkor', u'Etantdonné', u'Lorsque', u'假設')
        for code_keyword in code_keywords:
            assert code_keyword in I18n.all_code_keywords()

    def test_reject_bullet_stars(self):
        assert '*' not in I18n.all_code_keywords()

    def test_report_keyword_regexp(self):
        expected_substring = '|Quando |Quand |Quan |Pryd |Pokud |'
        assert expected_substring in I18n.keyword_regexp('step')

    def _test_print_available_languages(self):
        assert "\n" + I18n.language_table == """
      | ar        | Arabic              | العربية           |
      | bg        | Bulgarian           | български         |
      | ca        | Catalan             | català            |
      | cs        | Czech               | Česky             |
      | cy-GB     | Welsh               | Cymraeg           |
      | da        | Danish              | dansk             |
      | de        | German              | Deutsch           |
      | en        | English             | English           |
      | en-Scouse | Scouse              | Scouse            |
      | en-au     | Australian          | Australian        |
      | en-lol    | LOLCAT              | LOLCAT            |
      | en-pirate | Pirate              | Pirate            |
      | en-tx     | Texan               | Texan             |
      | eo        | Esperanto           | Esperanto         |
      | es        | Spanish             | español           |
      | et        | Estonian            | eesti keel        |
      | fi        | Finnish             | suomi             |
      | fr        | French              | français          |
      | he        | Hebrew              | עברית             |
      | hr        | Croatian            | hrvatski          |
      | hu        | Hungarian           | magyar            |
      | id        | Indonesian          | Bahasa Indonesia  |
      | is        | Icelandic           | Íslenska          |
      | it        | Italian             | italiano          |
      | ja        | Japanese            | 日本語               |
      | ko        | Korean              | 한국어               |
      | lt        | Lithuanian          | lietuvių kalba    |
      | lu        | Luxemburgish        | Lëtzebuergesch    |
      | lv        | Latvian             | latviešu          |
      | nl        | Dutch               | Nederlands        |
      | no        | Norwegian           | norsk             |
      | pl        | Polish              | polski            |
      | pt        | Portuguese          | português         |
      | ro        | Romanian            | română            |
      | ru        | Russian             | русский           |
      | sk        | Slovak              | Slovensky         |
      | sr-Cyrl   | Serbian             | Српски            |
      | sr-Latn   | Serbian (Latin)     | Srpski (Latinica) |
      | sv        | Swedish             | Svenska           |
      | tr        | Turkish             | Türkçe            |
      | uk        | Ukrainian           | Українська        |
      | uz        | Uzbek               | Узбекча           |
      | vi        | Vietnamese          | Tiếng Việt        |
      | zh-CN     | Chinese simplified  | 简体中文              |
      | zh-TW     | Chinese traditional | 繁體中文              |
"""

    def _test_print_keyword_for_a_given_language(self):
        assert "\n" + I18n.get('fr').keyword_table == """
      | feature          | "Fonctionnalité"                       |
      | background       | "Contexte"                             |
      | scenario         | "Scénario"                             |
      | scenario_outline | "Plan du scénario", "Plan du Scénario" |
      | examples         | "Exemples"                             |
      | given            | "* ", "Soit ", "Etant donné "          |
      | when             | "* ", "Quand ", "Lorsque ", "Lorsqu'"  |
      | then             | "* ", "Alors "                         |
      | and              | "* ", "Et "                            |
      | but              | "* ", "Mais "                          |
      | given (code)     | "Soit", "Etantdonné"                   |
      | when (code)      | "Quand", "Lorsque", "Lorsqu"           |
      | then (code)      | "Alors"                                |
      | and (code)       | "Et"                                   |
      | but (code)       | "Mais"                                 |
"""
