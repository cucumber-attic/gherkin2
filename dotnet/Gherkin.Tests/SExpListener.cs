using System;
using System.Collections.Generic;
using System.Linq;

namespace Gherkin.Tests
{
    public class SExpListener : IListener
    {
        private readonly IFormatter formatter;

        public SExpListener(bool includeColumn)
        {
            formatter = includeColumn ? (IFormatter) new ColumnInfoFormatter() : new SimpleFormatter();
        }

        private interface IFormatter
        {
            string FormatPosition(Position position);
            SExp CreateExpression(string symbol);
            SExp CreateExpression(string symbol, Token token);
            SExp CreateMultipartExpression(string symbol, Token keyword, Token content);
            SExp CreateStepExpression(Token keyword, Token name, StepKind stepKind);
            SExp CreateCellExpression(Token cell);
        }

        private class SimpleFormatter : IFormatter
        {
            public string FormatPosition(Position position)
            {
                return position.Line.ToString();
            }

            public SExp CreateExpression(string symbol)
            {
                return new SExpList(symbol);
            }

            public SExp CreateExpression(string symbol, Token token)
            {
                return new SExpList(symbol, new SExp(FormatPosition(token.Position)), new SExp(token.Content));
            }

            public SExp CreateMultipartExpression(string symbol, Token keyword, Token content)
            {
                return new SExpList(symbol, new SExp(FormatPosition(keyword.Position)), new SExp(keyword.Content), new SExp(content.Content));
            }

            public SExp CreateStepExpression(Token keyword, Token name, StepKind stepKind)
            {
                return new SExpList("step", new SExp(FormatPosition(keyword.Position)), new SExp(stepKind.ToString()), new SExp(keyword.Content), new SExp(name.Content));
            }

            public SExp CreateCellExpression(Token cell)
            {
                return new SExpList("cell", new SExp(cell.Content));
            }
        }

        private class ColumnInfoFormatter : IFormatter
        {
            public string FormatPosition(Position position)
            {
                return string.Format("{0}:{1}", position.Line, position.Column);
            }

            public SExp CreateExpression(string symbol)
            {
                return new SExpList(symbol);
            }

            public SExp CreateExpression(string symbol, Token token)
            {
                return new SExpList(symbol, new SExp(FormatPosition(token.Position)), new SExp(token.Content));
            }

            public SExp CreateMultipartExpression(string symbol, Token keyword, Token content)
            {
                return new SExpList(
                    symbol,
                    new SExp(FormatPosition(keyword.Position)), new SExp(keyword.Content),
                    new SExp(FormatPosition(content.Position)), new SExp(content.Content));
            }

            public SExp CreateStepExpression(Token keyword, Token name, StepKind stepKind)
            {
                return new SExpList("step",
                    new SExp(FormatPosition(keyword.Position)), new SExp(stepKind.ToString()), new SExp(keyword.Content),
                    new SExp(FormatPosition(name.Position)), new SExp(name.Content));
            }

            public SExp CreateCellExpression(Token cell)
            {
                return new SExpList("cell", new SExp(FormatPosition(cell.Position)), new SExp(cell.Content));
            }
        }

        private readonly List<SExp> root = new List<SExp>();

        public SExp Value
        {
            get
            {
                return new SExpList("root", root.ToArray());
            }
        }

        public void Tag(Token name)
        {
            root.Add(formatter.CreateExpression("tag", name));
        }

        public void Comment(Token content)
        {
            root.Add(formatter.CreateExpression("comment", content));
        }

        public void Feature(Token keyword, Token name)
        {
            root.Add(formatter.CreateMultipartExpression("feature", keyword, name));
        }

        public void Background(Token keyword, Token name)
        {
            root.Add(formatter.CreateMultipartExpression("background", keyword, name));
        }

        public void Scenario(Token keyword, Token name)
        {
            root.Add(formatter.CreateMultipartExpression("scenario", keyword, name));
        }

        public void ScenarioOutline(Token keyword, Token name)
        {
            root.Add(formatter.CreateMultipartExpression("scenario_outline", keyword, name));
        }

        public void Examples(Token keyword, Token name)
        {
            root.Add(formatter.CreateMultipartExpression("examples", keyword, name));
        }

        public void Step(Token keyword, Token name, StepKind stepKind)
        {
            root.Add(formatter.CreateStepExpression(keyword, name, stepKind));
        }

        public void Row(IList<Token> row, Position rowPosition)
        {
            root.Add(new SExpList("row", row.Select(cell => formatter.CreateCellExpression(cell)).ToArray()));
        }

        public void PythonString(Token pyString)
        {
            root.Add(formatter.CreateExpression("py_string", pyString));
        }

        public void Eof()
        {
            root.Add(formatter.CreateExpression("eof"));
        }

        public void SyntaxError(string state, string @event, IEnumerable<string> legalEvents, Position position)
        {
            root.Add(new SExpList("syntax error", 
                                  new SExp(formatter.FormatPosition(position)), 
                                  new SExp(state), 
                                  new SExp(@event), 
                                  new SExpList("legalEvents", 
                                               legalEvents.Select(le => new SExpList("legalEvent", new SExp(le))).ToArray())));
        }
    }
}