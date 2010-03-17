using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace Gherkin
{
    public class Parser : IListener
    {
        private IList<Machine> machines = new List<Machine>();

        private IListener listener;
        private bool throwOnError;

        public Parser(IListener listener)
            : this(listener, true)
        {
        }

        public Parser(IListener listener, bool throwOnError)
            : this(listener, throwOnError, "root")
        {
        }

        public Parser(IListener listener, bool throwOnError, string machineName)
        {
            this.listener = listener;
            this.throwOnError = throwOnError;
            pushMachine(machineName);
        }

        private void pushMachine(string machineName)
        {
            machines.Add(new Machine(this, machineName));
        }

        private void popMachine()
        {
            machines.RemoveAt(machines.Count - 1);
        }

        public void Tag(Token name)
        {
            if (NextEvent("tag", name.Position))
                listener.Tag(name);
        }

        public void PythonString(Token pyString)
        {
            if (NextEvent("py_string", pyString.Position))
                listener.PythonString(pyString);
        }

        public void Feature(Token keyword, Token name)
        {
            if (NextEvent("feature", keyword.Position))
                listener.Feature(keyword, name);
        }

        public void Background(Token keyword, Token name)
        {
            if (NextEvent("background", keyword.Position))
                listener.Background(keyword, name);
        }

        public void Scenario(Token keyword, Token name)
        {
            if (NextEvent("scenario", keyword.Position))
                listener.Scenario(keyword, name);
        }

        public void ScenarioOutline(Token keyword, Token name)
        {
            if (NextEvent("scenario_outline", keyword.Position))
                listener.ScenarioOutline(keyword, name);
        }

        public void Examples(Token keyword, Token name)
        {
            if (NextEvent("examples", keyword.Position))
                listener.Examples(keyword, name);
        }

        public void Step(Token keyword, Token name, StepKind stepKind)
        {
            if (NextEvent("step", keyword.Position))
                listener.Step(keyword, name, stepKind);
        }

        public void Comment(Token content)
        {
            if (NextEvent("comment", content.Position))
                listener.Comment(content);
        }

        public void Row(IList<Token> row, Position tablePosition)
        {
            if (NextEvent("row", tablePosition))
                listener.Row(row, tablePosition);
        }

        public void Eof()
        {
            if (NextEvent("eof", new Position(-1,-1)))
                listener.Eof();
        }

        public void SyntaxError(string state, string @event, IEnumerable<string> legalEvents, Position position)
        {
        }

        private bool NextEvent(string @event, Position position)
        {
            try
            {
                machine().NextEvent(@event, position);
                return true;
            }
            catch (ParseException e)
            {
                if (throwOnError) throw;

                listener.SyntaxError(e.State, @event, e.ExpectedEvents, position);
                return false;
            }
        }

        private Machine machine()
        {
            return machines[machines.Count - 1];
        }

        private sealed class Machine
        {
            private static readonly Regex PUSH = new Regex("push\\((.+)\\)");
            private static readonly IDictionary<string, IDictionary<string, IDictionary<string, string>>> TRANSITION_MAPS = new Dictionary<string, IDictionary<string, IDictionary<string, string>>>();

            private readonly Parser parser;
            private readonly string name;
            private string state;
            private IDictionary<string, IDictionary<string, string>> transitionMap;

            public Machine(Parser parser, string name)
            {
                this.parser = parser;
                this.name = name;
                this.state = name;
                this.transitionMap = TransitionMap(name);
            }

            public void NextEvent(string @event, Position position)
            {
                if (!transitionMap.ContainsKey(state))
                {
                    throw new Exception("Unknown state: " + state + " for machine " + name);
                }
                var states = transitionMap[state];

                if (!states.ContainsKey(@event))
                {
                    throw new Exception("Unknown transition: " + @event + " among " + states + " for machine " + name + " in state " + state);
                }

                var newState = states[@event];

                if ("E" == newState)
                {
                    throw new ParseException(state, @event, ExpectedEvents, position);
                }

                Match push = PUSH.Match(newState);
                if (push.Success)
                {
                    parser.pushMachine(push.Groups[1].Captures[0].Value);
                    parser.NextEvent(@event, position);
                }
                else if ("pop()" == newState)
                {
                    parser.popMachine();
                    parser.NextEvent(@event, position);
                }
                else
                {
                    state = newState;
                }
            }

            private IEnumerable<string> ExpectedEvents
            {
                get
                {
                    var result = new List<string>();
                    foreach (var @event in transitionMap[state].Keys)
                    {
                        if (transitionMap[state][@event] != "E")
                        {
                            result.Add(@event);
                        }
                    }
                    result.Sort();
                    return result;
                }
            }

            private IDictionary<string, IDictionary<string, string>> TransitionMap(string name)
            {
                if (!TRANSITION_MAPS.ContainsKey(name))
                {
                    TRANSITION_MAPS[name] = BuildTransitionMap(name);
                }
                return TRANSITION_MAPS[name];
            }

            private IDictionary<string, IDictionary<string, string>> BuildTransitionMap(string name)
            {
                var result = new Dictionary<string, IDictionary<string, string>>();
                var transitionTable = new StateMachine.StateMachineReader(name).TransitionTable;
                var events = transitionTable[0].Skip(1);
                foreach (var actions in transitionTable.Skip(1))
                {
                    var transitions = new Dictionary<string, string>();
                    int col = 1;
                    foreach (var @event in events)
                    {
                        transitions[@event] = actions[col++];
                    }
                    var state = actions[0];
                    result[state] = transitions;
                }
                return result;
            }
        }
    }
}