using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using Moq;
using Xunit;
using It=Moq.It;

namespace Gherkin.Tests.ParserSpecs
{
    public class Parser : ParserSpec
    {
        [Fact]
        public void should_delegate_events_to_the_listener()
        {
            a_parser().
                parsing_input("# Content").
                should_delegate(l => l.Comment(new Token("# Content", new Position(1, 1))));
        }

        [Fact]
        public void should_raise_helpful_error_messages_by_default()
        {
            a_parser().
                receiving(p => p.Scenario(
                    new Token("Scenario", new Position(12,1)), 
                    new Token("My pet scenario", new Position(12, 10)))).
                should_raise_error("Parse error on line 12. Found scenario when expecting one of: comment, eof, feature, tag. (Current state: root).");
        }

        [Fact]
        public void should_delegate_an_error_message_when_raise_on_error_is_false()
        {
            a_parser()
                .with_raise_on_error(false)
                .receiving(p => p.Background(
                    new Token("Background", new Position(1, 1)), 
                    new Token("Content", new Position(1, 12))))
                .should_delegate(
                l => l.SyntaxError("root", "background", Containing<string>.Same("comment", "eof", "feature", "tag"), It.Is<Position>(p => p.Line == 1)));
        }
    }

    public static class Containing<TItem>
    {
        public static IEnumerable<TItem> Same(params TItem[] values)
        {
            return
                Match<IEnumerable<TItem>>.Create(
                    input => input.OrderBy(item => item).SequenceEqual(values.OrderBy(item => item)));
        }
    }
}
