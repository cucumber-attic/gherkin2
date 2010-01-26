using System;

namespace Gherkin
{
    public class LexingException : Exception 
    {
        public LexingException(string message) : base(message)
        {
        }

        public LexingException(string message, Exception innerException) : base(message, innerException)
        {
        }
    }
}