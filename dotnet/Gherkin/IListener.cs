using System.Collections.Generic;

namespace Gherkin
{
    public interface IListener {
        void Tag(string name, int line);

        void Comment(string content, int line);

        void Feature(string keyword, string name, int line);

        void Background(string keyword, string name, int line);

        void Scenario(string keyword, string name, int line);

        void ScenarioOutline(string keyword, string name, int line);

        void Examples(string keyword, string name, int line);

        void Step(string keyword, string name, int line);

        void Table(IList<IList<string>> rows, int line);

        void PythonString(string pyString, int line);

        void SyntaxError(string state, string @event, IEnumerable<string> legalEvents, int line);
    }
}