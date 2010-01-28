using System;
using System.IO;
using System.Linq;
using System.Text.RegularExpressions;

namespace Gherkin
{
    public class I18NLexer : ILexer
    {
        private static Regex langRegex = new Regex("language\\s*:\\s*(.*)");

        public void Scan(TextReader inputSequence)
        {
            var firstLine = inputSequence.ReadLine();
        }
    }

    public enum StepKind
    {
        Given,
        When,
        Then,
        And,
        But,
        Any,
        Unknown
    }
}