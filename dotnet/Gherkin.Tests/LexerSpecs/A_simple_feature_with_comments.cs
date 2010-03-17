using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_simple_feature_with_comments : LexerSpec { 
        [Fact] public void should_find_the_feature_scenarios_steps_and_comments_in_the_proper_order() {
            a_lexer().scan_file("simple_with_comments.feature").
                should_result_in("(root " + 
                                 "(comment 1  \"# Here is a comment\" )" +
                                 "(feature 2   Feature  \"Feature Text\" )" +
                                 "(comment 3  \"# Here is another # comment\" )" +
                                 "(scenario 4   Scenario  \"Reading a Scenario\" )" +
                                 "(comment 5  \"# Here is a third comment\" )" +
                                 "(step 6 Given \"Given \" \"there is a step\" )" +
                                 "(comment 7  \"# Here is a fourth comment\" )" +
                                 "(eof )" +
                                 "");
        }
    }
}