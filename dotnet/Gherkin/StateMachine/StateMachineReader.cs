using System.Reflection;
using System.Resources;

namespace Gherkin.StateMachine
{
    using System;
    using System.Collections.Generic;
    using System.IO;
    using Lexer;

    public class StateMachineReader : IListener
    {
        private readonly string machinePath;
        private IList<IList<string>> transitionTable;

        public StateMachineReader(string name) {
            machinePath = "Gherkin.StateMachine." + name + ".txt";
        }

        public IList<IList<string>> TransitionTable 
        {
            get
            {
                ILexer lexer = new En(this);
                try
                {
                    var currentAssembly = Assembly.GetExecutingAssembly();
                    var stream = currentAssembly.GetManifestResourceStream(machinePath);
                    using (var reader = new StreamReader(stream))
                    {
                        lexer.Scan(reader);
                    }
                }
                catch (IOException e)
                {
                    throw new Exception("Fatal error. Couldn't read " + machinePath);
                }
                return transitionTable;
            }
        }

        public void Tag(string name, int line) {
        }

        public void Comment(string content, int line) {
        }

        public void Feature(string keyword, string name, int line) {
        }

        public void Background(string keyword, string name, int line) {
        }

        public void Scenario(string keyword, string name, int line) {
        }

        public void ScenarioOutline(string keyword, string name, int line) {
        }

        public void Examples(string keyword, string name, int line) {
        }

        public void Step(string keyword, StepKind stepKind, string name, int line) {
        }

        public void PythonString(string pyString, int line) {
        }

        public void SyntaxError(string name, string @event, IEnumerable<string> strings, int line) {
        }

        public void Table(IList<IList<string>> rows, int line) {
            transitionTable = rows;
        }
    }
}