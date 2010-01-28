using System;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;

namespace Gherkin
{
    public class I18NLexer : ILexer
    {
        private readonly IListener listener;
        private static readonly Regex LangRegex = new Regex("language\\s*:\\s*(.*)");
        private const string DefaultLang = "en";

        public I18NLexer(IListener listener)
        {
            Language = "";
            this.listener = listener;
        }

        public string Language { get; private set; }

        public void Scan(TextReader inputSequence)
        {
            var content = inputSequence.ReadToEnd();
            var lexer = Lexers.Create(GetLanguage(content.Split('\n')[0]), listener);
            Language = lexer.GetType()
                .GetCustomAttributes(typeof (LanguageAttribute), false).Cast<LanguageAttribute>()
                .Select(la => la.Language).FirstOrDefault();
            using (var reader = new StringReader(content)) lexer.Scan(reader);
        }

        private static string GetLanguage(string line)
        {
            var match = LangRegex.Match(line);
            if (!match.Success)
                return DefaultLang;
            var langString = match.Groups[1].Captures[0].Value;

            return new[] {langString, langString.Replace("-", ""), langString.Substring(0, 2), DefaultLang}.First(Lexers.Exists);
        }
    }
}