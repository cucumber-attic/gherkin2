using System.IO;
using Moq;
using Xunit;

namespace Gherkin.Tests.LexerSpecs
{
    public class I18NLexer
    {
        [Fact]
        public void should_store_the_i18n_language_of_the_last_scanned_feature()
        {
            var lexer = new Gherkin.I18NLexer(new Mock<IListener>().Object);
            lexer.Scan(new StringReader("# language: fr\n"));
            Assert.Equal("fr", lexer.Language);
            lexer.Scan(new StringReader("# language: no\n"));
            Assert.Equal("no", lexer.Language);
        }

        [Fact]
        public void should_use_english_i18n_by_default()
        {
            var lexer = new Gherkin.I18NLexer(new Mock<IListener>().Object);
            lexer.Scan(new StringReader("Feature: foo\n"));
            Assert.Equal("en", lexer.Language);
        }
    }
}