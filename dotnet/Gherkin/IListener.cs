using System.Collections.Generic;

namespace Gherkin
{
    public interface IListener {
        void Tag(Token name);
        void Comment(Token content);
        void Feature(Token keyword, Token name);
        void Background(Token keyword, Token name);
        void Scenario(Token keyword, Token name);
        void ScenarioOutline(Token keyword, Token name);
        void Examples(Token keyword, Token name);
        void Step(Token keyword, Token name, StepKind stepKind);
        void Row(IList<Token> rows, Position tablePosition);
        void PythonString(Token pyString);
        void SyntaxError(string state, string @event, IEnumerable<string> legalEvents, Position position);
    }
}