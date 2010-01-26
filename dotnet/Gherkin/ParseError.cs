namespace Gherkin
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    public class ParseException : Exception 
    {
        public string State { get; private set; }
        public IEnumerable<string> ExpectedEvents { get; private set; }

        public ParseException(string state, string @event, IEnumerable<string> expectedEvents, int line)
            : base("Parse error on line " + line + ". Found " + @event + " when expecting one of: " + string.Join(", ", expectedEvents.ToArray()) + ". (Current state: " + state + ").")
        {
            State = state;
            ExpectedEvents = expectedEvents;
        }
    }
}