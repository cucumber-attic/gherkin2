namespace Gherkin
{
    public struct Position
    {
        public readonly int Line;
        public readonly int Column;

        public Position(int line, int column)
        {
            Line = line;
            Column = column;
        }
    }
}