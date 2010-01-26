using System;
using System.IO;
using Gherkin.Lexer;
using Xunit;

namespace Gherkin.Tests
{
    public class LexerSpec
    {
        protected LexingResult lexing_input(string input)
        {
            var listener = new SExpListener();
            var lexer = new En(listener);

            try
            {
                //lexer.Scan(new StringReader(input));
                lexer.Scan(new StringReader(input.Replace("\r", "")));
                return new LexingResult(listener.Value);
            }
            catch (Exception e)
            {
                return new LexingResult(e);
            }
        }
    }

    public class LexingResult
    {
        private readonly SExp result;
        private readonly Exception exception;
        public LexingResult(SExp result)
        {
            this.result = result;
        }

        public LexingResult(Exception exception)
        {
            this.exception = exception;
        }

        public void should_result_in(string expected)
        {
            Assert.True(result != null, "Lexing failed");
            var expectedExpression = new SExpReader(new StringReader(expected)).Read();
            Assert.Equal(expectedExpression, result);
        }

        public void should_fail_with_message(string errorMessage)
        {
            Assert.True(exception != null, "Lexing not failed");
            Assert.Equal(errorMessage, exception.Message);
        }

        public void should_fail_with(Func<Exception, bool> testException)
        {
            Assert.True(exception != null, "Lexing not failed");
            Assert.True(testException(exception));
        }
    }

}