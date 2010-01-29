using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class Examples : LexerSpec { 
        [Fact] public void should_be_parsed() {
            a_lexer().lexing_input(@"Examples:
                          |x|y|
                          |5|6|
                          ").
                should_result_in("(root " + 
                                 "(examples 1 Examples \"\" )" +
                                 "(table 2 (row (cell x) (cell y)) (row (cell 5) (cell 6)))" +
                                 "");
        }
        
        [Fact] public void should_parse_multiline_example_names() {
            a_lexer().lexing_input(@"Examples: I'm a multiline name
                          and I'm ok
                          f'real
                          |x|
                          |5|
                          ").
                should_result_in("(root " + 
                                 "(examples 1 Examples \"I'm a multiline name\nand I'm ok\nf'real\" )" +
                                 "(table 4 (row (cell x))(row (cell 5)))" +
                                 "");
        }
    }
}