using System.Linq;
using Xunit;

namespace Gherkin.Tests
{
      public class Tags : LexerSpec { 
        [Fact] public void should_not_take_the_tags_as_part_of_a_multiline_name_feature_element() {
          lexing_input("Feature: hi\n Scenario: test\n\n@hello\n Scenario: another").
          should_result_in("(root " + 
            "(feature 1   Feature   hi  )" +
            "(scenario 2   Scenario   test  )" +
            "(tag 4   hello  )" +
            "(scenario 5   Scenario   another  )" +
          "");
        }  
      }

      public class Background : LexerSpec { 
        [Fact] public void should_allow_an_empty_background_description() {
          lexing_input("Background:\nGiven I am a step\n").
          should_result_in("(root " + 
            "(background 1   Background  \"\" )" +
            "(step 2  \"Given \" \"I am a step\" )" +
          "");
        }
        
        [Fact] public void should_allow_multiline_names_ending_at_eof() {
          lexing_input("Background: I have several\n   Lines to look at\n None starting with Given").
          should_result_in("(root " + 
            "(background 1   Background  \"I have several\nLines to look at\nNone starting with Given\" )" +
          "");
        }
         
        [Fact] public void should_allow_multiline_names() {
          lexing_input(@"Feature: Hi
Background: It is my ambition to say 
            in ten sentences
            what others say 
            in a whole book.
Given I am a step").
          should_result_in("(root " + 
            "(feature 1   Feature   Hi  )" +
            "(background 2   Background  \"It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.\")" +
            "(step 6  \"Given \" \"I am a step\" )" +
          "");
        }
      }

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
            "(step 3  \"Given \"  baz  )" +
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
            "(step 5  \"Given \" \"I am a step\" )" +
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
            "(step 3  \"Given \" \"I have some\" )" +
            "(step 4  \"But \" \"I might not because I am a Charles Dickens character\" )" +
          "");
        }
        
        [Fact] public void should_allow_step_names_in_Scenario_descriptions() {
          lexing_input(@"Scenario: When I have when in scenario
          I should be fine
Given I am a step
").
          should_result_in("(root " + 
            "(scenario 1   Scenario  \"When I have when in scenario\nI should be fine\" )" +
            "(step 3  \"Given \" \"I am a step\" )" +
          "");
        }
      }

      public class ScenarioOutlines : LexerSpec { 
        [Fact] public void should_be_parsed() {
          lexing_input(@"Scenario Outline: Hello
                          Given a <what> cucumber
                          Examples:
                          |what|
                          |green|
                          ").
          should_result_in("(root " + 
            "(scenario_outline 1  \"Scenario Outline\"  Hello  )" +
            "(step 2  \"Given \" \"a <what> cucumber\" )" +
            "(examples 3   Examples  \"\" )" +
            "(table 4  (row (cell what)) (row (cell green)) )" +
          "");
        }

        [Fact] public void should_parse_with_no_steps_or_examples() {
          lexing_input(@"Scenario Outline: Hello

                          Scenario: My Scenario
                          ").
          should_result_in("(root " + 
            "(scenario_outline 1  \"Scenario Outline\"  Hello  )" +
            "(scenario 3   Scenario  \"My Scenario\" )" +
          "");
        }

        [Fact] public void should_allow_multiline_names() {
          lexing_input(@"Scenario Outline: It is my ambition to say 
                          in ten sentences
                          what others say 
                          in a whole book.
                          Given I am a step

                          ").
          should_result_in("(root " + 
            "(scenario_outline 1  \"Scenario Outline\" \"It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.\" )" +
            "(step 5  \"Given \" \"I am a step\" )" +
          "");
        }        
      }

      public class Examples : LexerSpec { 
        [Fact] public void should_be_parsed() {
          lexing_input(@"Examples:
                          |x|y|
                          |5|6|
                          ").
          should_result_in("(root " + 
            "(examples 1 Examples \"\" )" +
            "(table 2 (row (cell x) (cell y)) (row (cell 5) (cell 6)))" +
          "");
        }
        
        [Fact] public void should_parse_multiline_example_names() {
          lexing_input(@"Examples: I'm a multiline name
                          and I'm ok
                          f'real
                          |x|
                          |5|
                          ").
          should_result_in("(root " + 
            "(examples 1 Examples \"I'm a multiline name\nand I'm ok\nf'real\" )" +
            "(table 4 (row (cell x))(row (cell 5)))" +
          "");
        }
      }
      
      public class Steps : LexerSpec { 
        [Fact] public void should_parse_steps_with_inline_table() {
          lexing_input(@"Given I have a table 
                          |a|b|
                          ").
          should_result_in("(root " + 
            "(step 1 \"Given \" \"I have a table\" )" +
            "(table 2 (row (cell a) (cell b)))" +
          "");
        }
        
        [Fact] public void should_parse_steps_with_inline_py_string() {
          lexing_input("Given I have a string\n\"\"\"\nhello\nworld\n\"\"\"").
          should_result_in("(root " + 
            "(step 1  \"Given \" \"I have a string\" )" +
            "(py_string 2 \"hello\nworld\")" +
          "");
        }
      }
            
      public class A_single_feature_single_scenario_single_step : LexerSpec { 
        [Fact] public void should_find_the_feature_scenario_and_step() {
          lexing_input("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n").
          should_result_in("(root " + 
            "(feature 1   Feature  \"Feature Text\" )" +
            "(scenario 2   Scenario  \"Reading a Scenario\" )" +
            "(step 3  \"Given \" \"there is a step\" )" +
          "");
        }
      }

      public class A_feature_ending_in_whitespace : LexerSpec { 
        [Fact] public void should_not_raise_an_error_when_whitespace_follows_the_Feature_Scenario_and_Steps() {
          lexing_input("Feature: Feature Text\n Scenario: Reading a Scenario\n    Given there is a step\n    ").
          should_result_in("(root " + 
            "(feature 1   Feature  \"Feature Text\" )" +
            "(scenario 2   Scenario  \"Reading a Scenario\" )" +
            "(step 3  \"Given \" \"there is a step\" )" +
          "");
        }
      }

      public class A_single_feature_single_scenario_three_steps : LexerSpec { 
        
        [Fact] public void should_find_the_feature_scenario_and_three_steps() {
          lexing_input("Feature: Feature Text\n  Scenario: Reading a Scenario\n    Given there is a step\n    And another step\n   And a third step\n").
          should_result_in("(root " + 
            "(feature 1 Feature \"Feature Text\" )" +
            "(scenario 2 Scenario \"Reading a Scenario\" )" +
            "(step 3 \"Given \" \"there is a step\" )" +
            "(step 4 \"And \" \"another step\" )" +
            "(step 5 \"And \" \"a third step\" )" +
          "");
        }
      }

      public class A_single_feature_with_no_scenario : LexerSpec { 
        [Fact] public void should_find_the_feature() {
            lexing_input("Feature: Feature Text\n").
                should_result_in("(root " +
                                 "(feature 1 Feature \"Feature Text\")");
        }

        [Fact] public void should_parse_a_one_line_feature_with_no_newline() {
            lexing_input("Feature: hi").
                should_result_in("(root " + "(feature 1   Feature   hi  )");
        }
      }
      
      public class A_multi_line_feature_with_no_scenario : LexerSpec { 
        [Fact] public void should_find_the_feature() {
            lexing_input("Feature: Feature Text\n  And some more text").
                should_result_in("(root " + "(feature 1   Feature  \"Feature Text\nAnd some more text\" )");
        }
      }

      public class A_feature_with_a_scenario_but_no_steps : LexerSpec { 
        [Fact] public void should_find_the_feature_and_scenario() {
          lexing_input("Feature: Feature Text\nScenario: Reading a Scenario\n").
          should_result_in("(root " + 
            "(feature 1   Feature  \"Feature Text\" )" +
            "(scenario 2   Scenario  \"Reading a Scenario\" )" +
          "");
        }
      }

      public class A_feature_with_two_scenarios : LexerSpec { 
        [Fact] public void should_find_the_feature_and_two_scenarios() {
          lexing_input("Feature: Feature Text\nScenario: Reading a Scenario\n  Given a step\n\nScenario: A second scenario\n Given another step\n").
          should_result_in("(root " + 
            "(feature 1   Feature  \"Feature Text\" )" +
            "(scenario 2   Scenario  \"Reading a Scenario\" )" +
            "(step 3  \"Given \" \"a step\" )" +
            "(scenario 5   Scenario  \"A second scenario\" )" +
            "(step 6  \"Given \" \"another step\" )" +
          "");
        }
        
        [Fact] public void should_find_the_feature_and_two_scenarios_without_indentation() {
          lexing_input("Feature: Feature Text\nScenario: Reading a Scenario\nGiven a step\nScenario: A second scenario\nGiven another step\n").
          should_result_in("(root " + 
            "(feature 1   Feature  \"Feature Text\" )" +
            "(scenario 2   Scenario  \"Reading a Scenario\" )" +
            "(step 3  \"Given \" \"a step\" )" +
            "(scenario 4   Scenario  \"A second scenario\" )" +
            "(step 5  \"Given \" \"another step\" )" +
          "");
        }
      }
/*
      public class A_simple_feature_with_comments : LexerSpec { 
        [Fact] public void should_find_the_feature_scenarios_steps_and_comments_in_the_proper_order() {
          scan_file("simple_with_comments.feature")
          should_result_in("(root " + 
            "(comment 1  \"# Here is a comment\" )" +
            "(feature 2   Feature  \"Feature Text\" )" +
            "(comment 3  \"# Here is another # comment\" )" +
            "(scenario 4   Scenario  \"Reading a Scenario\" )" +
            "(comment 5  \"# Here is a third comment\" )" +
            "(step 6  \"Given \" \"there is a step\" )" +
            "(comment 7  \"# Here is a fourth comment\" )" +
          "");
        }
      }
      
      public class A_feature_with_tags_everywhere : LexerSpec { 
        [Fact] public void should_find_the_feature_scenario_step_and_tags_in_the_proper_order() {
          scan_file("simple_with_tags.feature")
          should_result_in("(root " + 
            "(comment 1  \"# FC\" )" +
            "(tag 2   ft )" +
            "(feature 3   Feature   hi  )" +
            "(tag 5   st1  )" +
            "(tag 5   st2  )" +
            "(scenario 6   Scenario   First  )" +
            "(step 7  \"Given \"  Pepper  )" +
            "(tag 9   st3  )" +
            "(tag 0   st4  1)" +
            "(tag 0   ST5  1)" +
            "(tag 0   #^%&ST6**!  1)" +
            "(scenario 1   Scenario   Second  1)" +
          "");
        }        
      }

      public class Comment_or_tag_between_Feature_elements_where_previous_narrative_starts_with_same_letter_as_a_keyword : LexerSpec { 
        [Fact] public void should_lex_this_feature_properly() {
          scan_file("1.feature")
          should_result_in("(root " + 
            "(feature 1   Feature  \"Logging in\nSo that I can be myself\" )" +
            "(comment 3  \"# Comment\" )" +
            "(scenario 4   Scenario  \"Anonymous user can get a login form.\nScenery here\" )" +
            "(tag 7   tag  )" +
            "(scenario 8   Scenario  \"Another one\" )" +
          "");
        }
      }   

      public class A_complex_feature_with_tags,_comments,_multiple_scenarios,_and_multiple_steps_and_tables : LexerSpec { 
        [Fact] public void should_find_things_in_the_right_order() {
          scan_file("complex.feature")
          should_result_in("(root " + 
            "(comment 1  \"#Comment on line 1\" )" +
            "(comment 2  \"#Comment on line 2\" )" +
            "(tag 3   tag1  )" +
            "(tag 3   tag2  )" +
            "(feature 4   Feature  \"Feature Text\nIn order to test multiline forms\nAs a ragel writer\nI need to check for complex combinations\" )" +
            "(comment 9  \"#Comment on line 9\" )" +
            "(comment 1  \"#Comment on line 11\" 1)" +
            "(background 3   Background  \"\" 1)" +
            "(step 4  \"Given \" \"this is a background step\" 1)" +
            "(step 5  \"And \" \"this is another one\" 1)" +
            "(tag 7   tag3  1)" +
            "(tag 7   tag4  1)" +
            "(scenario 8   Scenario  \"Reading a Scenario\" 1)" +
            "(step 9  \"Given \" \"there is a step\" 1)" +
            "(step 0  \"But \" \"not another step\" 2)" +
            "(tag 2   tag3  2)" +
            "(scenario 3   Scenario  \"Reading a second scenario\" 2)" +
            "(comment 4  \"#Comment on line 24\" 2)" +
            "(step 5  \"Given \" \"a third step with a table\" 2)" +
            "(table 6  [['a''b']['c''d']['e''f']] 2)" +
            "(step 9  \"And \" \"I am still testing things\" 2)" +
            "(table 0  [['g''h']['e''r']['k''i']['n''']] 3)" +
            "(step 4  \"And \" \"I am done testing these tables\" 3)" +
            "(comment 5  \"#Comment on line 29\" 3)" +
            "(step 6  \"Then \" \"I am happy\" 3)" +
            "(scenario 8   Scenario   Hammerzeit  3)" +
            "(step 9  \"Given \" \"All work and no play\" 3)" +
            "(py_string 0  \"Makes Homer something something\" 4)" +
            "(step 3  \"Then \"  crazy  4)" +
          "");
        }        
      }
    */
      public class errors : LexerSpec { 
        [Fact] public void should_raise_a_Lexing_error_if_an_unparseable_token_is_found() {
          var inputs = new [] {
              "Some text\nFeature: Hi",
              "Feature: Hi\nBackground:\nGiven something\nScenario A scenario",
                "Scenario: My scenario\nGiven foo\nAand bar\nScenario: another one\nGiven blah"
            };
            foreach (var input in inputs)
            {
                lexing_input(input).should_fail_with(e => e.Message.Contains("Lexing error on line"));
            }
        }
        
        [Fact] public void should_include_the_line_number_and_context_of_the_error() {
            lexing_input("Feature: hello\nScenario: My scenario\nGiven foo\nAand blah\nHmmm wrong\nThen something something").
                should_fail_with_message("Lexing error on line 4");
        }
      }
}