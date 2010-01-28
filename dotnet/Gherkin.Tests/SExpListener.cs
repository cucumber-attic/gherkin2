using System.Collections.Generic;
using System.Linq;

namespace Gherkin.Tests
{
    public class SExpListener : IListener
    {
        private List<SExp> root = new List<SExp>();
        public SExp Value
        {
            get
            {
                return new SExpList("root", root.ToArray());
            }
        }

        public void Tag(string name, int line)
        {
            root.Add(new SExpList("tag", new SExp(line.ToString()), new SExp(name)));
        }

        public void Comment(string comment, int line)
        {
            root.Add(new SExpList("comment", new SExp(line.ToString()), new SExp(comment)));
        }

        public void Feature(string keyword, string name, int line)
        {
            root.Add(new SExpList("feature", new SExp(line.ToString()), new SExp(keyword), new SExp(name)));
        }

        public void Background(string keyword, string name, int line)
        {
            root.Add(new SExpList("background", new SExp(line.ToString()), new SExp(keyword), new SExp(name)));
        }

        public void Scenario(string keyword, string name, int line)
        {
            root.Add(new SExpList("scenario", new SExp(line.ToString()), new SExp(keyword), new SExp(name)));
        }

        public void ScenarioOutline(string keyword, string name, int line)
        {
            root.Add(new SExpList("scenario_outline", new SExp(line.ToString()), new SExp(keyword), new SExp(name)));
        }

        public void Examples(string keyword, string name, int line)
        {
            root.Add(new SExpList("examples", new SExp(line.ToString()), new SExp(keyword), new SExp(name)));
        }

        public void Step(string keyword, StepKind stepKind, string name, int line)
        {
            root.Add(new SExpList("step", new SExp(line.ToString()), new SExp(stepKind.ToString()), new SExp(keyword), new SExp(name)));
        }

        public void Table(IList<IList<string>> rows, int line)
        {
            root.Add(new SExpList("table", 
                                  new [] { new SExp(line.ToString()) }.Concat<SExp>(rows.Select(
                                                                                        row => new SExpList("row", row.Select(cell => new SExpList("cell", new SExp(cell))).ToArray())).Cast<SExp>()).ToArray()));
        }

        public void PythonString(string pyString, int line)
        {
            root.Add(new SExpList("py_string", new SExp(line.ToString()), new SExp(pyString)));
        }

        public void SyntaxError(string state, string @event, IEnumerable<string> legalEvents, int line)
        {
            root.Add(new SExpList("syntax error", 
                                  new SExp(line.ToString()), 
                                  new SExp(state), 
                                  new SExp(@event), 
                                  new SExpList("legalEvents", 
                                               legalEvents.Select(le => new SExpList("legalEvent", new SExp(le))).ToArray())));
        }
    }
}