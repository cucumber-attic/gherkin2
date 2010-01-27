using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_feature_with_tags_everywhere : LexerSpec { 
        [Fact] public void should_find_the_feature_scenario_step_and_tags_in_the_proper_order() {
            scan_file("simple_with_tags.feature").
                should_result_in("(root " + 
                                 "(comment 1  \"# FC\" )" +
                                 "(tag 2   ft )" +
                                 "(feature 3   Feature   hi  )" +
                                 "(tag 5   st1  )" +
                                 "(tag 5   st2  )" +
                                 "(scenario 6   Scenario   First  )" +
                                 "(step 7  \"Given \"  Pepper  )" +
                                 "(tag 9   st3 )" +
                                 "(tag 10   st4 )" +
                                 "(tag 10   ST5 )" +
                                 "(tag 10   #^%&ST6**! )" +
                                 "(scenario 11   Scenario   Second )" +
                                 "");
        }        
    }
}