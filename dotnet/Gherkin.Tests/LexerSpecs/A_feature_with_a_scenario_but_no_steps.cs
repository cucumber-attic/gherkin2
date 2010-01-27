using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_feature_with_a_scenario_but_no_steps : LexerSpec { 
        [Fact] public void should_find_the_feature_and_scenario() {
            lexing_input("Feature: Feature Text\nScenario: Reading a Scenario\n").
                should_result_in("(root " + 
                                 "(feature 1   Feature  \"Feature Text\" )" +
                                 "(scenario 2   Scenario  \"Reading a Scenario\" )" +
                                 "");
        }
    }
}