using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class Tags : LexerSpec { 
        [Fact] public void should_not_take_the_tags_as_part_of_a_multiline_name_feature_element() {
            a_lexer().lexing_input("Feature: hi\n Scenario: test\n\n@hello\n Scenario: another").
                should_result_in("(root " + 
                                 "(feature 1   Feature   hi  )" +
                                 "(scenario 2   Scenario   test  )" +
                                 "(tag 4   hello  )" +
                                 "(scenario 5   Scenario   another  )" +
                                 "(eof )" +
                                 "");
        }  
    }
}