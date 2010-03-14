using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class I18NParsing : LexerSpec
    {
        [Fact]
        public void should_recognize_keywords_in_the_language_of_the_lexer()
        {
            a_lexer().with_language("no")
                .scan_file("i18n_no.feature")
                .should_result_in("(root " +
                                  "(feature 1 Egenskap \"i18n support\") " +
                                  "(scenario 3 Scenario \"Parsing many languages\") " +
                                  "(step 4 Given \"Gitt \" \"Gherkin supports many languages\") " +
                                  "(step 5 When \"Når \" \"Norwegian keywords are parsed\") " +
                                  "(step 6 Then \"Så \" \"they should be recognized\") " +
                                  ")");
        }

        [Fact]
        public void should_report_multibyte_character_positions_correctly()
        {
            a_lexer().with_language("no").using_column_positions()
                .scan_file("i18n_no.feature")
                .should_result_in("(root " +
                                  "(feature 1:1 Egenskap 1:10 \"i18n support\") " +
                                  "(scenario 3:3 Scenario 3:12 \"Parsing many languages\") " +
                                  "(step 4:5 Given \"Gitt \" 4:10 \"Gherkin supports many languages\") " +
                                  "(step 5:5 When \"Når \" 5:9 \"Norwegian keywords are parsed\") " +
                                  "(step 6:5 Then \"Så \" 6:8 \"they should be recognized\") " +
                                  ")");
        }

        [Fact] 
        public void should_parse_languages_without_a_space_after_keywords()
        {
            a_lexer().with_language("zhCN").
                scan_file("i18n_zh-CN.feature").
                should_result_in("(root " +
                "(feature 1 功能 加法) " +
                "(scenario 3 场景 两个数相加) " +
                "(step 4 Given 假如 我已经在计算器里输入6) " +
                "(step 5 And 而且 我已经在计算器里输入7) " +
                "(step 6 When 当 我按相加按钮) " +
                "(step 7 Then 那么 我应该在屏幕上看到的结果是13) " +
                ")");
        }

        [Fact]
        public void should_report_character_positions_for_languages_without_a_space_after_keywords()
        {
            a_lexer().with_language("zhCN").using_column_positions().
                scan_file("i18n_zh-CN.feature").
                should_result_in("(root " +
                "(feature 1:1 功能 1:4 加法) " +
                "(scenario 3:3 场景 3:6 两个数相加) " +
                "(step 4:5 Given 假如 4:7 我已经在计算器里输入6) " +
                "(step 5:5 And 而且 5:7 我已经在计算器里输入7) " +
                "(step 6:5 When 当 6:6 我按相加按钮) " +
                "(step 7:5 Then 那么 7:7 我应该在屏幕上看到的结果是13) " +
                ")");
        }
 
        [Fact]
        public void should_parse_languages_with_spaces_after_some_keywords_but_not_others()
        {
            a_lexer().with_language("fr").
                scan_file("i18n_fr.feature").
                should_result_in("(root " +
                  "(feature 1 Fonctionnalité Addition)" + 
                  "(scenario_outline 2 \"Plan du scénario\" \"Addition de produits dérivés\")" + 
                  "(step 3 Given \"Soit \" \"une calculatrice\")" + 
                  "(step 4 Given \"Etant donné \" \"qu'on tape <a>\")" + 
                  "(step 5 And \"Et \" \"qu'on tape <b>\")" + 
                  "(step 6 When Lorsqu' \"on tape additionner\")" + 
                  "(step 7 Then \"Alors \" \"le résultat doit être <somme>\")" + 
                  "(examples 9 Exemples \"\")" + 
                  "(row (cell a)(cell b)(cell somme)) (row (cell 2)(cell 2)(cell 4)) (row (cell 2)(cell 3)(cell 5))" + 
                  ")");

        }
    }
}