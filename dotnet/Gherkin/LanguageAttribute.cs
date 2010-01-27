using System;

namespace Gherkin
{
    [AttributeUsage(AttributeTargets.Class, AllowMultiple = false, Inherited = false)]
    internal class LanguageAttribute : Attribute
    {
        public string Language { get; private set; }
        public LanguageAttribute(string language)
        {
            Language = language;
        }
    }
}