using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class Comment_or_tag_between_Feature_elements_where_previous_narrative_starts_with_same_letter_as_a_keyword : LexerSpec { 
        [Fact] public void should_lex_this_feature_properly() {
            a_lexer().scan_file("1.feature").
                should_result_in("(root " + 
                                 "(feature 1   Feature  \"Logging in\nSo that I can be myself\" )" +
                                 "(comment 3  \"# Comment\" )" +
                                 "(scenario 4   Scenario  \"Anonymous user can get a login form.\nScenery here\" )" +
                                 "(tag 7   tag  )" +
                                 "(scenario 8   Scenario  \"Another one\" )" +
                                 "");
        }
    }
}