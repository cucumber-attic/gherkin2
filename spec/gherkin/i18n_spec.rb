#encoding: utf-8
require 'spec_helper'

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

      it "should recognize keywords that are a little ambiguous" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_fr2.feature")
        @listener.to_sexp.should == [
          [:comment, "#language:fr", 1],
          [:feature, "Fonctionnalité", "i18n", "", 2],
          [:scenario, "Scénario", "Le French", "", 4],
          [:step, "Etant donné ", "qqch", 5],
          [:step, "Etant donnée ", "qqch", 6],
          [:step, "Etant donnés ", "qqch", 7],
          [:step, "Etant données ", "qqch", 8],
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

      it "should recognize keywords in Portuguese (1st variant)" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_pt1.feature")
        @listener.to_sexp.should == [
          [:comment, "# language: pt", 1],
          [:feature, "Funcionalidade", "Reconhece \"Funcionalidade\"", "", 2],
          [:background, "Contexto", "Reconhece \"Contexto\"", "", 4],
          [:scenario, "Cenário", "Reconhece \"Cenário\" com acento", "", 6],
          [:scenario, "Cenario", "Reconhece \"Cenário\" sem acento", "", 8],
          [:scenario_outline, "Esquema do Cenário", "Reconhece \"Esquema do Cenário\" com acento", "", 10],
          [:step, "Dado ", "que <Valor> é um valor e que reconhece \"Dado\";", 11],
          [:step, "Dada ", "a afirmação de que reconhece \"Dada\";", 12],
          [:step, "Dados ", "os factos acima e ainda que reconhece \"Dados\";", 13],
          [:step, "Dadas ", "as afirmações acima e ainda que reconhece \"Dadas\";", 14],
          [:step, "Quando ", "reconhece \"Quando\";", 15],
          [:step, "Então ", "também reconhece \"Então\" com acento e", 16],
          [:step, "Entao ", "também reconhece \"Então\" sem acento;", 17],
          [:step, "E ", "reconhece \"E\";", 18],
          [:step, "Mas ", "também reconhece \"Mas\".", 19],
          [:examples, "Exemplos", "Reconhece \"Exemplos\"", "", 21],
          [:row, ["Valor"], 22],
          [:row, ["1"], 23],
          [:scenario_outline, "Esquema do Cenario", "Reconhece \"Esquema do Cenário\" sem acento", "", 25],
          [:step, "Dado ", "que <Valor> é um valor;", 26],
          [:examples, "Cenários", "Reconhece \"Cenários\" com acento", "", 28],
          [:row, ["Valor"], 29],
          [:row, ["1"], 30],
          [:scenario_outline, "Delineação do Cenário", "Reconhece \"Delineação do Cenário\" com acento", "", 32],
          [:step, "Dado ", "que <Valor> é um valor;", 33],
          [:examples, "Cenarios", "Reconhece \"Cenários\" sem acento", "", 35],
          [:row, ["Valor"], 36],
          [:row, ["1"], 37],
          [:scenario_outline, "Delineacao do Cenario", "Reconhece \"Delineação do Cenário\" sem acento", "", 39],
          [:step, "Dado ", "que <Valor> é um valor;", 40],
          [:examples, "Exemplos", "Reconhece \"Exemplos\"", "", 42],
          [:row, ["Valor"], 43],
          [:row, ["1"], 44],
          [:eof]
        ]
      end

      it "should recognize keywords in Portuguese (2nd variant)" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_pt2.feature")
        @listener.to_sexp.should == [
          [:comment, "# language: pt", 1],
          [:feature, "Característica", "Reconhece \"Característica\" com acento", "", 2],
          [:background, "Cenário de Fundo", "Reconhece \"Cenário de Fundo\" com acento", "", 4],
          [:eof]
        ]
      end

      it "should recognize keywords in Portuguese (3rd variant)" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_pt3.feature")
        @listener.to_sexp.should == [
          [:comment, "# language: pt", 1],
          [:feature, "Caracteristica", "Reconhece \"Característica\" sem acento", "", 2],
          [:background, "Cenario de Fundo", "Reconhece \"Cenário de Fundo\" sem acento", "", 4],
          [:eof]
        ]
      end

      it "should recognize keywords in Portuguese (4th variant)" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_pt4.feature")
        @listener.to_sexp.should == [
          [:comment, "# language: pt", 1],
          [:feature, "Característica", "Reconhece \"Característica\" com acento", "", 2],
          [:background, "Fundo", "Reconhece \"Fundo\"", "", 4],
          [:eof]
        ]
      end

      it "should recognize keywords in Klingon" do
        lexer = Gherkin::Lexer::I18nLexer.new(@listener, false)
        scan_file(lexer, "i18n_tlh.feature")
        @listener.to_sexp.should == [
          [:comment, "#language:tlh", 1],
          [:feature, "poQbogh malja'", "tlhIngan Hol", "tlhIngan Hol ghItlhmeH\nghunbogh qaD lo'wI'.", 2],
          [:scenario_outline, "lut chovnatlh", "SuvwI'", "", 6],
          [:step, "DaH ghu' bejlu' ", "<jagh mI'> jaghmey Datu'.", 7],
          [:step, "qaSDI' ", "reH jaghvetlh DaqaD.", 8],
          [:step, "'ej ", "<Qaw'bogh mI'> jaghmey DaQaw'chugh,", 9],
          [:step, "vaj ", "SuvwI' SoH.", 10],
          [:examples, "ghantoH", "", "", 12],
          [:row, ["jagh mI'", "Qaw'bogh mI'"], 13],
          [:row, ["3", "2"], 14],
          [:row, ["5", "3"], 15],
          [:eof]
        ]
      end

      describe 'keywords' do
        it "should have code keywords without space, comma, exclamation or apostrophe" do
          ['Avast', 'Akkor', 'Etantdonné', 'Lorsque', '假設'].each do |code_keyword|
            Gherkin::I18n.code_keywords.should include(code_keyword)
          end
        end

        it "should reject the bullet stars" do
          Gherkin::I18n.code_keywords.should_not include('*')
        end

        it "should report keyword regexp" do
          Gherkin::I18n.keyword_regexp(:step).should =~ /\|Quando \|Quand \|Quan \|Pryd \|/
        end

        it "should print available languages" do
          ("\n" + Gherkin::I18n.language_table).should == %{
      | af        | Afrikaans           | Afrikaans         |
      | ar        | Arabic              | العربية           |
      | bg        | Bulgarian           | български         |
      | bm        | Malay               | Bahasa Melayu     |
      | ca        | Catalan             | català            |
      | cs        | Czech               | Česky             |
      | cy-GB     | Welsh               | Cymraeg           |
      | da        | Danish              | dansk             |
      | de        | German              | Deutsch           |
      | el        | Greek               | Ελληνικά          |
      | en        | English             | English           |
      | en-Scouse | Scouse              | Scouse            |
      | en-au     | Australian          | Australian        |
      | en-lol    | LOLCAT              | LOLCAT            |
      | en-old    | Old English         | Englisc           |
      | en-pirate | Pirate              | Pirate            |
      | en-tx     | Texan               | Texan             |
      | eo        | Esperanto           | Esperanto         |
      | es        | Spanish             | español           |
      | et        | Estonian            | eesti keel        |
      | fa        | Persian             | فارسی             |
      | fi        | Finnish             | suomi             |
      | fr        | French              | français          |
      | gl        | Galician            | galego            |
      | he        | Hebrew              | עברית             |
      | hi        | Hindi               | हिंदी             |
      | hr        | Croatian            | hrvatski          |
      | hu        | Hungarian           | magyar            |
      | id        | Indonesian          | Bahasa Indonesia  |
      | is        | Icelandic           | Íslenska          |
      | it        | Italian             | italiano          |
      | ja        | Japanese            | 日本語               |
      | jv        | Javanese            | Basa Jawa         |
      | kn        | Kannada             | ಕನ್ನಡ             |
      | ko        | Korean              | 한국어               |
      | lt        | Lithuanian          | lietuvių kalba    |
      | lu        | Luxemburgish        | Lëtzebuergesch    |
      | lv        | Latvian             | latviešu          |
      | nl        | Dutch               | Nederlands        |
      | no        | Norwegian           | norsk             |
      | pa        | Panjabi             | ਪੰਜਾਬੀ            |
      | pl        | Polish              | polski            |
      | pt        | Portuguese          | português         |
      | ro        | Romanian            | română            |
      | ru        | Russian             | русский           |
      | sk        | Slovak              | Slovensky         |
      | sl        | Slovenian           | Slovenski         |
      | sr-Cyrl   | Serbian             | Српски            |
      | sr-Latn   | Serbian (Latin)     | Srpski (Latinica) |
      | sv        | Swedish             | Svenska           |
      | th        | Thai                | ไทย               |
      | tl        | Telugu              | తెలుగు            |
      | tlh       | Klingon             | tlhIngan          |
      | tr        | Turkish             | Türkçe            |
      | tt        | Tatar               | Татарча           |
      | uk        | Ukrainian           | Українська        |
      | uz        | Uzbek               | Узбекча           |
      | vi        | Vietnamese          | Tiếng Việt        |
      | zh-CN     | Chinese simplified  | 简体中文              |
      | zh-TW     | Chinese traditional | 繁體中文              |
}
        end

        it "should print keywords for a given language" do
          ("\n" + Gherkin::I18n.get('fr').keyword_table).should == %{
      | feature          | "Fonctionnalité"                                                                                                                                      |
      | background       | "Contexte"                                                                                                                                            |
      | scenario         | "Scénario"                                                                                                                                            |
      | scenario_outline | "Plan du scénario", "Plan du Scénario"                                                                                                                |
      | examples         | "Exemples"                                                                                                                                            |
      | given            | "* ", "Soit ", "Etant donné ", "Etant donnée ", "Etant donnés ", "Etant données ", "Étant donné ", "Étant donnée ", "Étant donnés ", "Étant données " |
      | when             | "* ", "Quand ", "Lorsque ", "Lorsqu'"                                                                                                                 |
      | then             | "* ", "Alors "                                                                                                                                        |
      | and              | "* ", "Et "                                                                                                                                           |
      | but              | "* ", "Mais "                                                                                                                                         |
      | given (code)     | "Soit", "Etantdonné", "Etantdonnée", "Etantdonnés", "Etantdonnées", "Étantdonné", "Étantdonnée", "Étantdonnés", "Étantdonnées"                        |
      | when (code)      | "Quand", "Lorsque", "Lorsqu"                                                                                                                          |
      | then (code)      | "Alors"                                                                                                                                               |
      | and (code)       | "Et"                                                                                                                                                  |
      | but (code)       | "Mais"                                                                                                                                                |
}
        end

        it "should print keywords in Klingon" do
          ("\n" + Gherkin::I18n.get('tlh').keyword_table).should == %{
      | feature          | "Qap", "Qu'meH 'ut", "perbogh", "poQbogh malja'", "laH" |
      | background       | "mo'"                                                   |
      | scenario         | "lut"                                                   |
      | scenario_outline | "lut chovnatlh"                                         |
      | examples         | "ghantoH", "lutmey"                                     |
      | given            | "* ", "ghu' noblu' ", "DaH ghu' bejlu' "                |
      | when             | "* ", "qaSDI' "                                         |
      | then             | "* ", "vaj "                                            |
      | and              | "* ", "'ej ", "latlh "                                  |
      | but              | "* ", "'ach ", "'a "                                    |
      | given (code)     | "ghunoblu", "DaHghubejlu"                               |
      | when (code)      | "qaSDI"                                                 |
      | then (code)      | "vaj"                                                   |
      | and (code)       | "ej", "latlh"                                           |
      | but (code)       | "ach", "a"                                              |
}
        end


        it "should not list keywords that start with a number" do
          Gherkin::I18n.get('en-old').code_keywords.should include("Ðaðe")
          Gherkin::I18n.get('en-old').code_keywords.should_not include("7")
        end
      end
    end
  end
end
