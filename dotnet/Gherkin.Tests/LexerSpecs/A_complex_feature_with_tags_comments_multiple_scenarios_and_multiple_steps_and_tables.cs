using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class A_complex_feature_with_tags_comments_multiple_scenarios_and_multiple_steps_and_tables : LexerSpec { 
        [Fact] public void should_find_things_in_the_right_order() {
            scan_file("complex.feature").
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
                                 "(scenario 23   Scenario  \"Reading a second scenario\" )" +
                                 "(comment 24  \"#Comment on line 24\" )" +
                                 "(step 25 Given \"Given \" \"a third step with a table\" )" +
                                 "(table 26  (row (cell a)(cell b)) (row (cell c)(cell d)) (row (cell e)(cell f)) )" +
                                 "(step 29 And \"And \" \"I am still testing things\" )" +
                                 "(table 30  (row (cell g)(cell h)) (row (cell e)(cell r)) (row (cell k)(cell i)) (row (cell n) (cell \"\")) )" +
                                 "(step 34 And \"And \" \"I am done testing these tables\" )" +
                                 "(comment 35  \"#Comment on line 29\" )" +
                                 "(step 36 Then \"Then \" \"I am happy\" )" +
                                 "(scenario 38   Scenario   Hammerzeit  )" +
                                 "(step 39 Given \"Given \" \"All work and no play\" )" +
                                 "(py_string 40  \"Makes Homer something something\" )" +
                                 "(step 43 Then \"Then \"  crazy )" +
                                 "");
        }        
    }
}