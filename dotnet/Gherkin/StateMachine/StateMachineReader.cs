using System.Collections;
using System.Linq;
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
        private IList<IList<string>> transitionTable = new List<IList<string>>();

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
                catch (IOException)
                {
                    throw new Exception("Fatal error. Couldn't read " + machinePath);
                }
                return transitionTable;
            }
        }

        public void Tag(Token name)
        {
        }

        public void Comment(Token content)
        {
        }

        public void Feature(Token keyword, Token name)
        {
        }

        public void Background(Token keyword, Token name)
        {
        }

        public void Scenario(Token keyword, Token name)
        {
        }

        public void ScenarioOutline(Token keyword, Token name)
        {
        }

        public void Examples(Token keyword, Token name)
        {
        }

        public void Step(Token keyword, Token name, StepKind stepKind)
        {
        }

        public void Row(IList<Token> row, Position tablePosition)
        {
            transitionTable.Add(row.Select(cell => cell.Content).ToList());
        }

        public void PythonString(Token pyString)
        {
        }

        public void SyntaxError(string state, string @event, IEnumerable<string> legalEvents, Position position)
        {
        }
    }
}