using System.IO;

namespace Gherkin
{
    public interface ILexer 
    {
        void Scan(TextReader inputSequence);
    }
}
