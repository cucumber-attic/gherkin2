using System.Collections.Generic;
using System.Linq;

namespace Gherkin.Tests
{
    public class SExpList : SExp
    {
        public IList<SExp> List { get; private set; }
        public SExpList(string symbol, params SExp[] list) : base(symbol)
        {
            List = new List<SExp>(list);
        }

        public override bool Equals(object obj)
        {
            if (!base.Equals(obj)) return false;

            var other = (SExpList)obj;

            return List.Count == other.List.Count
                && Enumerable.Range(0, List.Count).All(i => other.List[i].Equals(List[i]));
        }

        public override int GetHashCode()
        {
            unchecked
            {
                {
                    return (base.GetHashCode()*397) ^ (List != null ? List.GetHashCode() : 0);
                }
            }
        }

        public override string ToString()
        {
            return string.Format("({0} {1})", Symbol, string.Join(" ", List.Select(item => item.ToString()).ToArray()));
        }
    }
}