using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class Steps : LexerSpec { 
        [Fact] public void should_parse_steps_with_inline_table() {
            a_lexer().lexing_input(@"Given I have a table 
                          |a|b|
                          ").
                should_result_in("(root " + 
                                 "(step 1 Given \"Given \" \"I have a table\" )" +
                                 "(table 2 (row (cell a) (cell b)))" +
                                 "");
        }
        
        [Fact] public void should_parse_steps_with_inline_py_string() {
            a_lexer().lexing_input("Given I have a string\n\"\"\"\nhello\nworld\n\"\"\"").
                should_result_in("(root " + 
                                 "(step 1 Given \"Given \" \"I have a string\" )" +
                                 "(py_string 2 \"hello\nworld\")" +
                                 "");
        }
    }
}