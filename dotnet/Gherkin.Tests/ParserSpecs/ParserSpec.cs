using System;
using System.IO;
using System.Linq.Expressions;
using Gherkin.Lexer;
using Moq;
using Xunit;

namespace Gherkin.Tests.ParserSpecs
{
    public class ParserSpec
    {
        public ParserDecl a_parser()
        {
            return new ParserDecl();
        }

        public class ParserDecl
        {
            private bool raise_on_error = true;

            public ParserDecl with_raise_on_error(bool value)
            {
                raise_on_error = value;
                return this;
            }

            public ParsingResult receiving(Action<IListener> action)
            {
                try
                {
                    var listenerMock = new Mock<IListener>();
                    var parser = new Gherkin.Parser(listenerMock.Object, raise_on_error);

                    action(parser);

                    return new ParsingResult(listenerMock);
                }
                catch (Exception ex)
                {
                    return new ParsingResult(ex);
                }
            }

            public ParsingResult parsing_input(string input)
            {
                var listenerMock = new Mock<IListener>();
                var parser = new Gherkin.Parser(listenerMock.Object, raise_on_error);
                var lexer = new En(parser);

                //lexer.Scan(new StringReader(input));
                lexer.Scan(new StringReader(input.Replace("\r", "")));
                return new ParsingResult(listenerMock);
            }
        }

        public class ParsingResult
        {
            private readonly Mock<IListener> listenerMock;
            private readonly Exception exception;

            public ParsingResult(Mock<IListener> listenerMock)
            {
                this.listenerMock = listenerMock;
                this.exception = null;
            }

            public ParsingResult(Exception exception)
            {
                this.exception = exception;
                this.listenerMock = null;
            }

            public void should_delegate<T>(Expression<Func<IListener, T>> expected)
            {
                try
                {
                    listenerMock.Verify(expected);
                }
                catch (Exception)
                {
                    Assert.True(false, "Did not receive");
                }
            }

            public void should_delegate(Expression<Action<IListener>> expected)
            {
                try
                {
                    listenerMock.Verify(expected);
                }
                catch (Exception)
                {
                    Assert.True(false, "Did not receive");
                }
            }

            public void should_raise_error(string errorMessage)
            {
                Assert.True(exception != null, "Parsing did not fail");
                Assert.Equal(errorMessage, exception.Message);
            }
        }
    }
}