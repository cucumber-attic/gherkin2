namespace Gherkin
{
    public struct Token
    {
        public readonly string Content;
        public readonly Position Position;

        public Token(string content, Position position)
        {
            Position = position;
            Content = content;
        }
    }
}