using System.Linq;

namespace Gherkin.Tests
{
    public class SExp
    {
        public SExp(string symbol)
        {
            Symbol = NormalizeLineEndings(symbol);
        }

        private static string NormalizeLineEndings(string str)
        {
            return string.Join("\n", str.Split('\n').Select(s => s.Trim('\r')).ToArray());
        }

        public string Symbol { get; private set; }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (GetType() != obj.GetType()) return false;

            var other = (SExp) obj;
            return Equals(other.Symbol, Symbol);
        }

        public override int GetHashCode()
        {
            return (Symbol != null ? Symbol.GetHashCode() : 0);
        }

        public override string ToString()
        {
            var symbol = Symbol.Replace("\"", "\"\"");
            if (symbol.Contains(' '))
                return string.Format("\"{0}\"", symbol);
            return symbol;
        }
    }
}