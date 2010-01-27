using System;
using System.IO;
using System.Linq;
using System.Text;
using Gherkin.Lexer;
using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class LexerSpec
    {
        private readonly string defaultLangauge = "en";
        protected LexingResult lexing_input(string input)
        {
            return lexing_input(input, defaultLangauge);
        }

        protected LexingResult lexing_input(string input, string language)
        {
            var listener = new SExpListener();
            var lexer = Gherkin.Lexers.Create(language, listener);

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

        protected LexingResult scan_file(string fileName)
        {
            return scan_file(fileName, defaultLangauge);
        }

        protected LexingResult scan_file(string fileName, string language)
        {
            using (var fileStream = new FileStream(string.Format("fixtures/{0}", fileName), FileMode.Open))
            {
                var encoding = DetermineEncoding(fileStream, Encoding.UTF8);
                using (var stream = new StreamReader(fileStream, encoding))
                {
                    var content = stream.ReadToEnd();
                    return lexing_input(content, language);
                }
            }
        }

        protected Encoding DetermineEncoding(Stream stream, Encoding defaultEncoding)
        {
            if (!stream.CanSeek)
                return defaultEncoding;

            var encodingsToTest = new[] { Encoding.BigEndianUnicode, Encoding.Unicode, Encoding.UTF32, Encoding.UTF8 };

            foreach (var enc in encodingsToTest)
            {
                var preamble = enc.GetPreamble();
                var start = new byte[preamble.Length];
                var read = stream.Read(start, 0, preamble.Length);
                stream.Position = 0;

                if (read == preamble.Length && start.SequenceEqual(preamble))
                    return enc;
            }
            return defaultEncoding;
        }

        protected class LexingResult
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
                Assert.True(exception == null, string.Format("Lexing failed with message {0}", exception != null ? exception.Message : ""));
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
}