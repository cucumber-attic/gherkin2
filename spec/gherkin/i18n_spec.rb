#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  class Lexer
    describe "i18n parsing" do
      before do
        @listener = Gherkin::SexpRecorder.new
      end

      def scan_file(lexer, file)
        lexer.scan(File.new(File.dirname(__FILE__) + "/fixtures/" + file).read)
      end

      it "should recognize keywords in the language of the lexer" do
        lexer = Gherkin::Lexer.rb['no'].new(@listener)
        scan_file(lexer, "i18n_no.feature")
        @listener.to_sexp.should == [
          [:feature, "Egenskap", "i18n support", 1], 
          [:scenario, "Scenario", "Parsing many languages", 3], 
          [:step, "Gitt", "Gherkin supports many languages", 4],
          [:step, "Når",  "Norwegian keywords are parsed", 5],
          [:step, "Så", "they should be recognized", 6]
        ]
      end

      it "should parse languages without a space after keywords" do
        lexer = Gherkin::Lexer.rb['zh-CN'].new(@listener)
        scan_file(lexer, "i18n_zh-CN.feature")
        @listener.to_sexp.should == [
          [:feature, "功能", "加法", 1],
          [:scenario, "场景", "两个数相加", 3],
          [:step, "假如", "我已经在计算器里输入6", 4],
          [:step, "而且", "我已经在计算器里输入7", 5],
          [:step, "当", "我按相加按钮", 6],
          [:step, "那么", "我应该在屏幕上看到的结果是13", 7]
        ] 
      end

      it "should parse languages with spaces after some keywords but not others" do
        lexer = Gherkin::Lexer.rb['fr'].new(@listener)
        scan_file(lexer, "i18n_fr.feature")
        @listener.to_sexp.should == [
          [:feature, "Fonctionnalité", "Addition", 1],
          [:scenario_outline, "Plan du scénario", "Addition de produits dérivés", 2],
          [:step, "Soit", "une calculatrice", 3],
          [:step, "Etant donné", "qu'on tape <a>", 4],
          [:step, "Et", "qu'on tape <b>", 5],
          [:step, "Lorsqu'", "on tape additionner", 6],
          [:step, "Alors", "le résultat doit être <somme>", 7],
          [:examples, "Exemples", "", 9],
          [:table, [["a","b","somme"],["2","2","4"],["2","3","5"]], 10]
        ]
      end
    end
  end
end
