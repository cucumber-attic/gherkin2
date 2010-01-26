using System.Xml.Schema;
using Xunit;

namespace Gherkin.Tests
{
    public class Comments : LexerSpec
    {
        [Fact]
        public void should_parse_a_one_line_comment()
        {
            lexing_input("# My comment\n").
            should_result_in("(root (comment 1 \"# My comment\"))");
        }

        [Fact]
        public void should_parse_a_multiline_comment()
        {
            lexing_input("# Hello\n\n# World\n").
            should_result_in("(root (comment 1 \"# Hello\") (comment 3 \"# World\")))");
        }

        [Fact] 
        public void should_not_consume_comments_as_part_of_a_multiline_name() 
        {
            lexing_input("Scenario: test\n#hello\n Scenario: another").
            should_result_in("(root " +
                "(scenario 1 Scenario test)" +
                "(comment 2 #hello)" +
                "(scenario 3 Scenario another)");
        }

        [Fact] 
        public void should_allow_empty_comment_lines() 
        {
            lexing_input("#\n   # A comment\n   #\n").
            should_result_in("(root " +
                "(comment 1 #)" +
                "(comment 2 \"# A comment\")" +
                "(comment 3 #)");
        }

        [Fact] 
        public void should_not_allow_comments_within_the_Feature_description() 
        {
            lexing_input("Feature: something\nAs a something\n# Comment\nI want something").
            should_fail_with_message("Lexing error on line 4");
        }
    }
}
