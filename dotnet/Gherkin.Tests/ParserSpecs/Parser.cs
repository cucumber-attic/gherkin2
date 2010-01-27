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
                should_delegate(l => l.Comment("# Content", It.IsAny<int>()));
        }

        [Fact]
        public void should_raise_helpful_error_messages_by_default()
        {
            a_parser().
                receiving(p => p.Scenario("Scenario", "My pet scenario", 12)).
                should_raise_error("Parse error on line 12. Found scenario when expecting one of: comment, feature, tag. (Current state: root).");
        }

        [Fact]
        public void should_delegate_an_error_message_when_raise_on_error_is_false()
        {
            a_parser()
                .with_raise_on_error(false)
                .receiving(p => p.Background("Background", "Content", 1))
                .should_delegate(
                l => l.SyntaxError("root", "background", Containing<string>.Same("comment", "feature", "tag"), 1));
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
