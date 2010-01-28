using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class I18NParsing : LexerSpec
    {
        [Fact]
        public void should_recognize_keywords_in_the_language_of_the_lexer()
        {
            scan_file("i18n_no.feature", "no")
                .should_result_in("(root " +
                                  "(feature 1 Egenskap \"i18n support\") " +
                                  "(scenario 3 Scenario \"Parsing many languages\") " +
                                  "(step 4 Given \"Gitt \" \"Gherkin supports many languages\") " +
                                  "(step 5 When \"Når \" \"Norwegian keywords are parsed\") " +
                                  "(step 6 Then \"Så \" \"they should be recognized\") " +
                                  ")");
        }

        [Fact] 
        public void should_parse_languages_without_a_space_after_keywords()
        {
            scan_file("i18n_zh-CN.feature", "zhCN").
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
        public void should_parse_languages_with_spaces_after_some_keywords_but_not_others()
        {
            scan_file("i18n_fr.feature", "fr").
                should_result_in("(root " +
                  "(feature 1 Fonctionnalité Addition)" + 
                  "(scenario_outline 2 \"Plan du scénario\" \"Addition de produits dérivés\")" + 
                  "(step 3 Given \"Soit \" \"une calculatrice\")" + 
                  "(step 4 Given \"Etant donné \" \"qu'on tape <a>\")" + 
                  "(step 5 And \"Et \" \"qu'on tape <b>\")" + 
                  "(step 6 When Lorsqu' \"on tape additionner\")" + 
                  "(step 7 Then \"Alors \" \"le résultat doit être <somme>\")" + 
                  "(examples 9 Exemples \"\")" + 
                  "(table 10 (row (cell a)(cell b)(cell somme)) (row (cell 2)(cell 2)(cell 4)) (row (cell 2)(cell 3)(cell 5)))" + 
                  ")");

        }
    }
}