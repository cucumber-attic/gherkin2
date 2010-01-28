using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class Scenarios : LexerSpec { 
        [Fact] public void should_be_parsed() {
            lexing_input("Scenario: Hello\n").
                should_result_in("(root " + 
                                 "(scenario 1   Scenario   Hello  )" +
                                 "");
        }
 
        [Fact] public void should_allow_whitespace_lines_after_the_Scenario_line() {
            lexing_input(@"Scenario: bar

                          Given baz
                          ").
                should_result_in("(root " + 
                                 "(scenario 1   Scenario   bar  )" +
                                 "(step 3 Given \"Given \"  baz  )" +
                                 "");
        }
        
        [Fact] public void should_allow_multiline_names() {
            lexing_input(@"Scenario: It is my ambition to say
                          in ten sentences
                          what others say 
                          in a whole book.
                          Given I am a step
                          ").
                should_result_in("(root " + 
                                 "(scenario 1   Scenario  \"It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.\" )" +
                                 "(step 5 Given  \"Given \" \"I am a step\" )" +
                                 "");
        }

        [Fact] public void should_allow_multiline_names_ending_at_eof() {
            lexing_input("Scenario: I have several\n       Lines to look at\n None starting with Given").
                should_result_in("(root " + 
                                 "(scenario 1   Scenario  \"I have several\nLines to look at\nNone starting with Given\" )" +
                                 "");
        }
  
        [Fact] public void should_ignore_gherkin_keywords_embedded_in_other_words() {
            lexing_input(@"Scenario: I have a Button
          Buttons are great
  Given I have some
  But I might not because I am a Charles Dickens character
").
                should_result_in("(root " + 
                                 "(scenario 1   Scenario  \"I have a Button\nButtons are great\" )" +
                                 "(step 3 Given \"Given \" \"I have some\" )" +
                                 "(step 4 But \"But \" \"I might not because I am a Charles Dickens character\" )" +
                                 "");
        }
        
        [Fact] public void should_allow_step_names_in_Scenario_descriptions() {
            lexing_input(@"Scenario: When I have when in scenario
          I should be fine
Given I am a step
").
                should_result_in("(root " + 
                                 "(scenario 1   Scenario  \"When I have when in scenario\nI should be fine\" )" +
                                 "(step 3 Given \"Given \" \"I am a step\" )" +
                                 "");
        }
    }
}