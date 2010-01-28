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

        public Parser(IListener listener) : this(listener, true)
        {
        }

        public Parser(IListener listener, bool throwOnError) : this(listener, throwOnError, "root")
        {
        }

        public Parser(IListener listener, bool throwOnError, string machineName) {
            this.listener = listener;
            this.throwOnError = throwOnError;
            pushMachine(machineName);
        }

        private void pushMachine(string machineName) {
            machines.Add(new Machine(this, machineName));
        }

        private void popMachine() {
            machines.RemoveAt(machines.Count - 1);
        }

        public void Tag(string name, int line) {
            if (NextEvent("tag", line))
                listener.Tag(name, line);
        }

        public void PythonString(string pyString, int line) {
            if (NextEvent("py_string", line))
                listener.PythonString(pyString, line);
        }

        public void Feature(string keyword, string name, int line) {
            if (NextEvent("feature", line))
                listener.Feature(keyword, name, line);
        }

        public void Background(string keyword, string name, int line) {
            if (NextEvent("background", line))
                listener.Background(keyword, name, line);
        }

        public void Scenario(string keyword, string name, int line) {
            if (NextEvent("scenario", line))
                listener.Scenario(keyword, name, line);
        }

        public void ScenarioOutline(string keyword, string name, int line) {
            if (NextEvent("scenario_outline", line))
                listener.ScenarioOutline(keyword, name, line);
        }

        public void Examples(string keyword, string name, int line) {
            if (NextEvent("examples", line))
                listener.Examples(keyword, name, line);
        }

        public void Step(string keyword, StepKind stepKind, string name, int line) {
            if (NextEvent("step", line))
                listener.Step(keyword, stepKind, name, line);
        }

        public void Comment(string content, int line) {
            if (NextEvent("comment", line))
                listener.Comment(content, line);
        }

        public void Table(IList<IList<string>> rows, int line) {
            if (NextEvent("table", line))
                listener.Table(rows, line);
        }

        public void SyntaxError(string name, string @event, IEnumerable<string> strings, int line) {
        }

        private bool NextEvent(string @event, int line) {
            try {
                machine().NextEvent(@event, line);
                return true;
            } 
            catch (ParseException e) 
            {
                if (throwOnError) throw;

                listener.SyntaxError(e.State, @event, e.ExpectedEvents, line);
                return false;
            }
        }

        private Machine machine() {
            return machines[machines.Count - 1];
        }

        private sealed class Machine {
            private static readonly Regex PUSH = new Regex("push\\((.+)\\)");
            private static readonly IDictionary<string, IDictionary<string, IDictionary<string, string>>> TRANSITION_MAPS = new Dictionary<string, IDictionary<string, IDictionary<string, string>>>();

            private readonly Parser parser;
            private readonly string name;
            private string state;
            private IDictionary<string, IDictionary<string, string>> transitionMap;

            public Machine(Parser parser, string name) {
                this.parser = parser;
                this.name = name;
                this.state = name;
                this.transitionMap = TransitionMap(name);
            }

            public void NextEvent(string @event, int line) {
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
                    throw new ParseException(state, @event, ExpectedEvents, line);
                } 

                Match push = PUSH.Match(newState);
                if (push.Success) {
                    parser.pushMachine(push.Groups[1].Captures[0].Value);
                    parser.NextEvent(@event, line);
                } else if ("pop()" == newState) {
                    parser.popMachine();
                    parser.NextEvent(@event, line);
                } else {
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

            private IDictionary<string, IDictionary<string, string>> TransitionMap(string name) {
                if (!TRANSITION_MAPS.ContainsKey(name))
                {
                    TRANSITION_MAPS[name] = BuildTransitionMap(name);
                }
                return TRANSITION_MAPS[name];
            }

            private IDictionary<string, IDictionary<string, string>> BuildTransitionMap(string name) {
                var result = new Dictionary<string, IDictionary<string, string>>();
                var transitionTable = new StateMachine.StateMachineReader(name).TransitionTable;
                var events = transitionTable[0].Skip(1);
                foreach (var actions in transitionTable.Skip(1)) 
                {
                    var transitions = new Dictionary<string, string>();
                    int col = 1;
                    foreach (var @event in events) {
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