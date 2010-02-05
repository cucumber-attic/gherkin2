using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class Errors : LexerSpec { 
        [Fact] public void should_raise_a_Lexing_error_if_an_unparseable_token_is_found() {
            var inputs = new [] {
                                    "Some text\nFeature: Hi",
                                    "Feature: Hi\nBackground:\nGiven something\nScenario A scenario",
                                    "Scenario: My scenario\nGiven foo\nAand bar\nScenario: another one\nGiven blah"
                                };
            foreach (var input in inputs)
            {
                a_lexer().lexing_input(input).should_fail_with(e => e.Message.Contains("Lexing error on line"));
            }
        }
        
        [Fact] public void should_include_the_line_number_and_context_of_the_error() {
            a_lexer().lexing_input("Feature: hello\nScenario: My scenario\nGiven foo\nAand blah\nHmmm wrong\nThen something something").
                should_fail_with(ex => ex.Message.Contains("Lexing error on line 4"));
        }
    }
}