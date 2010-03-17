using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_single_feature_with_no_scenario : LexerSpec 
    { 
        [Fact] public void should_find_the_feature() {
            a_lexer().lexing_input("Feature: Feature Text\n").
                should_result_in("(root " +
                                 "(feature 1 Feature \"Feature Text\")" +
                                 "(eof )" +
                                 "");
        }

        [Fact] public void should_parse_a_one_line_feature_with_no_newline() {
            a_lexer().lexing_input("Feature: hi").
                should_result_in("(root " + 
                                 "(feature 1   Feature   hi  )" + 
                                 "(eof )" +
                                 "");
        }
    }
}