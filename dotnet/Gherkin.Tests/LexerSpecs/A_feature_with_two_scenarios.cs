using System.Linq;
using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_feature_with_two_scenarios : LexerSpec { 
        [Fact] public void should_find_the_feature_and_two_scenarios() {
            a_lexer().lexing_input("Feature: Feature Text\nScenario: Reading a Scenario\n  Given a step\n\nScenario: A second scenario\n Given another step\n").
                should_result_in("(root " + 
                                 "(feature 1   Feature  \"Feature Text\" )" +
                                 "(scenario 2   Scenario  \"Reading a Scenario\" )" +
                                 "(step 3 Given \"Given \" \"a step\" )" +
                                 "(scenario 5   Scenario  \"A second scenario\" )" +
                                 "(step 6 Given \"Given \" \"another step\" )" +
                                 "");
        }
        
        [Fact] public void should_find_the_feature_and_two_scenarios_without_indentation() {
            a_lexer().lexing_input("Feature: Feature Text\nScenario: Reading a Scenario\nGiven a step\nScenario: A second scenario\nGiven another step\n").
                should_result_in("(root " + 
                                 "(feature 1   Feature  \"Feature Text\" )" +
                                 "(scenario 2   Scenario  \"Reading a Scenario\" )" +
                                 "(step 3 Given \"Given \" \"a step\" )" +
                                 "(scenario 4   Scenario  \"A second scenario\" )" +
                                 "(step 5 Given \"Given \" \"another step\" )" +
                                 "");
        }
    }
}