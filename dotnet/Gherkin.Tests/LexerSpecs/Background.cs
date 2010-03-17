using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class Background : LexerSpec { 
        [Fact] public void should_allow_an_empty_background_description() {
            a_lexer().lexing_input("Background:\nGiven I am a step\n").
                should_result_in("(root " + 
                                 "(background 1   Background  \"\" )" +
                                 "(step 2 Given  \"Given \" \"I am a step\" )" +
                                 "(eof )" +
                                 "");
        }
        
        [Fact] public void should_allow_multiline_names_ending_at_eof() {
            a_lexer().lexing_input("Background: I have several\n   Lines to look at\n None starting with Given").
                should_result_in("(root " + 
                                 "(background 1   Background  \"I have several\nLines to look at\nNone starting with Given\" )" +
                                 "(eof )" +
                                 "");
        }
         
        [Fact] public void should_allow_multiline_names() {
            a_lexer().lexing_input(@"Feature: Hi
Background: It is my ambition to say 
            in ten sentences
            what others say 
            in a whole book.
Given I am a step").
                should_result_in("(root " + 
                                 "(feature 1   Feature   Hi  )" +
                                 "(background 2   Background  \"It is my ambition to say\nin ten sentences\nwhat others say\nin a whole book.\")" +
                                 "(step 6 Given \"Given \" \"I am a step\" )" +
                                 "(eof )" +
                                 "");
        }
    }
}