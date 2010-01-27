using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_multi_line_feature_with_no_scenario : LexerSpec 
    { 
        [Fact] public void should_find_the_feature() {
            lexing_input("Feature: Feature Text\n  And some more text").
                should_result_in("(root " + "(feature 1   Feature  \"Feature Text\nAnd some more text\" )");
        }
    }
}