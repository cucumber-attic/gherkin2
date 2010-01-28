using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_feature_ending_in_whitespace : LexerSpec { 
        [Fact] public void should_not_raise_an_error_when_whitespace_follows_the_Feature_Scenario_and_Steps() {
            lexing_input("Feature: Feature Text\n Scenario: Reading a Scenario\n    Given there is a step\n    ").
                should_result_in("(root " + 
                                 "(feature 1   Feature  \"Feature Text\" )" +
                                 "(scenario 2   Scenario  \"Reading a Scenario\" )" +
                                 "(step 3 Given \"Given \" \"there is a step\" )" +
                                 "");
        }
    }
}