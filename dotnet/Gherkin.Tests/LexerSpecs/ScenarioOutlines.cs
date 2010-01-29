using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class ScenarioOutlines : LexerSpec { 
        [Fact] public void should_be_parsed() {
            a_lexer().lexing_input(@"Scenario Outline: Hello
                          Given a <what> cucumber
                          Examples:
                          |what|
                          |green|
                          ").
                should_result_in("(root " + 
                                 "(scenario_outline 1  \"Scenario Outline\"  Hello  )" +
                                 "(step 2 Given \"Given \" \"a <what> cucumber\" )" +
                                 "(examples 3   Examples  \"\" )" +
                                 "(table 4  (row (cell what)) (row (cell green)) )" +
                                 "");
        }

        [Fact] public void should_parse_with_no_steps_or_examples() {
            a_lexer().lexing_input(@"Scenario Outline: Hello

                          Scenario: My Scenario
                          ").
                should_result_in("(root " + 
                                 "(scenario_outline 1  \"Scenario Outline\"  Hello  )" +
                                 "(scenario 3   Scenario  \"My Scenario\" )" +
                                 "");
        }

        [Fact] public void should_allow_multiline_names() {
            a_lexer().lexing_input(@"Scenario Outline: It is my ambition to say 
                          in ten sentences
                          what others say 
                          in a whole book.
                          Given I am a step

                          ").
                should_result_in("(root " + 
                                 "(scenario_outline 1  \"Scenario Outline\" \"It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.\" )" +
                                 "(step 5 Given \"Given \" \"I am a step\" )" +
                                 "");
        }        
    }
}