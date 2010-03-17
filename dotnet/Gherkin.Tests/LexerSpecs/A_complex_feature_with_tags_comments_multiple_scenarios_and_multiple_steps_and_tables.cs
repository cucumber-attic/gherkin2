using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_complex_feature_with_tags_comments_multiple_scenarios_and_multiple_steps_and_tables : LexerSpec { 
        [Fact] public void should_find_things_in_the_right_order() {
            a_lexer().scan_file("complex.feature").
                should_result_in("(root " + 
                                 "(comment 1  \"#Comment on line 1\" )" +
                                 "(comment 2  \"#Comment on line 2\" )" +
                                 "(tag 3   tag1  )" +
                                 "(tag 3   tag2  )" +
                                 "(feature 4   Feature  \"Feature Text\nIn order to test multiline forms\nAs a ragel writer\nI need to check for complex combinations\" )" +
                                 "(comment 9  \"#Comment on line 9\" )" +
                                 "(comment 11  \"#Comment on line 11\" )" +
                                 "(background 13   Background  \"\" )" +
                                 "(step 14 Given \"Given \" \"this is a background step\" )" +
                                 "(step 15 And \"And \" \"this is another one\" )" +
                                 "(tag 17   tag3 )" +
                                 "(tag 17   tag4 )" +
                                 "(scenario 18   Scenario  \"Reading a Scenario\" )" +
                                 "(step 19 Given \"Given \" \"there is a step\" )" +
                                 "(step 20 But \"But \" \"not another step\" )" +
                                 "(tag 22   tag3  )" +
                                 "(scenario 23   Scenario  \"Reading a second scenario\nWith two lines of text\" )" +
                                 "(comment 25  \"#Comment on line 24\" )" +
                                 "(step 26 Given \"Given \" \"a third step with a table\" )" +
                                 "(row (cell a)(cell b)) (row (cell c)(cell d)) (row (cell e)(cell f))" +
                                 "(step 30 And \"And \" \"I am still testing things\" )" +
                                 "(row (cell g)(cell h)) (row (cell e)(cell r)) (row (cell k)(cell i)) (row (cell n) (cell \"\"))" +
                                 "(step 35 And \"And \" \"I am done testing these tables\" )" +
                                 "(comment 36  \"#Comment on line 29\" )" +
                                 "(step 37 Then \"Then \" \"I am happy\" )" +
                                 "(scenario 39   Scenario   Hammerzeit  )" +
                                 "(step 40 Given \"Given \" \"All work and no play\" )" +
                                 "(py_string 41  \"Makes Homer something something\nAnd something else\" )" +
                                 "(step 45 Then \"Then \"  crazy )" +
                                 "(eof )" +
                                 "");
        }

        [Fact]
        public void should_report_the_token_positions()
        {
            a_lexer().using_column_positions().
                scan_file("complex.feature").
                should_result_in("(root " +
                                "(comment 1:1  \"#Comment on line 1\" )" +
                                "(comment 2:1  \"#Comment on line 2\" )" +
                                "(tag 3:1   tag1  )" +
                                "(tag 3:7   tag2  )" +
                                "(feature 4:1   Feature 4:9 \"Feature Text\nIn order to test multiline forms\nAs a ragel writer\nI need to check for complex combinations\" )" +
                                "(comment 9:3  \"#Comment on line 9\" )" +
                                "(comment 11:3  \"#Comment on line 11\" )" +
                                "(background 13:3   Background 13:14 \"\" )" +
                                "(step 14:5 Given \"Given \" 14:11 \"this is a background step\" )" +
                                "(step 15:5 And \"And \" 15:9 \"this is another one\" )" +
                                "(tag 17:3   tag3 )" +
                                "(tag 17:9   tag4 )" +
                                "(scenario 18:3   Scenario 18:12 \"Reading a Scenario\" )" +
                                "(step 19:5 Given \"Given \" 19:11 \"there is a step\" )" +
                                "(step 20:5 But \"But \" 20:9 \"not another step\" )" +
                                "(tag 22:3   tag3  )" +
                                "(scenario 23:3   Scenario 23:12 \"Reading a second scenario\nWith two lines of text\" )" +
                                "(comment 25:5  \"#Comment on line 24\" )" +
                                "(step 26:5 Given \"Given \" 26:11 \"a third step with a table\" )" +
                                "(row (cell 27:6 a)(cell 27:8 b)) (row (cell 28:6 c)(cell 28:8 d)) (row (cell 29:6 e)(cell 29:8 f))" +
                                "(step 30:5 And \"And \" 30:9 \"I am still testing things\" )" +
                                "(row (cell 31:8 g)(cell 31:10 h)) (row (cell 32:8 e)(cell 32:10 r)) (row (cell 33:8 k)(cell 33:10 i)) (row (cell 34:8 n) (cell 34:10 \"\"))" +
                                "(step 35:5 And \"And \" 35:9 \"I am done testing these tables\" )" +
                                "(comment 36:5  \"#Comment on line 29\" )" +
                                "(step 37:5 Then \"Then \" 37:10 \"I am happy\" )" +
                                "(scenario 39:3   Scenario 39:12  Hammerzeit  )" +
                                "(step 40:5 Given \"Given \" 40:11 \"All work and no play\" )" +
                                "(py_string 41:7  \"Makes Homer something something\nAnd something else\" )" +
                                "(step 45:5 Then \"Then \" 45:10 crazy )" +
                                "(eof )" +
                                "");
        }
    }
}