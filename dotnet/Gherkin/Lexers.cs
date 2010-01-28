using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;

namespace Gherkin
{
    public static class Lexers
    {
        private readonly static IDictionary<string, Func<IListener, ILexer>> LexerActivators;
        static Lexers()
        {
            LexerActivators = Assembly.GetExecutingAssembly().GetTypes()
                .Where(t => typeof (ILexer).IsAssignableFrom(t))
                .SelectMany(t => GetLanguage(t).Select(l => new {Type = t, Language = l}))
                .ToDictionary(lexer => lexer.Language.ToLower(), lexer => GetActivator(lexer.Type));
        }

        private static Func<IListener, ILexer> GetActivator(Type lexerType)
        {
            return listener => (ILexer)Activator.CreateInstance(lexerType, listener);
        }

        private static IEnumerable<string> GetLanguage(Type type)
        {
            return type.GetCustomAttributes(typeof (LanguageAttribute), false).Cast<LanguageAttribute>().Select(attr => attr.Language);
        }

        public static ILexer Create(string language, IListener listener)
        {
            var key = language.ToLower();
            if (LexerActivators.ContainsKey(key))
                return LexerActivators[key](listener);

            throw new ArgumentOutOfRangeException(language, string.Format("No lexer exists for language '{0}'", language));
        }

        public static bool Exists(string language)
        {
            return LexerActivators.ContainsKey(language.ToLower());
        }
    }
}