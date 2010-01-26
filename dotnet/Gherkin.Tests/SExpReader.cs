using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Gherkin.Tests
{
    public class SExpReader
    {
        public SExpReader(TextReader reader)
        {
            this.reader = reader;
        }

        private char[] buffer = new char[4096];
        private int pos = 0;
        private int length = 0;
        private TextReader reader;
        private bool eos = false;
        private char[] ws = new[] { ' ', '\n', '\t' };

        private void EnsureBuffer()
        {
            if (pos >= length)
            {
                length = reader.ReadBlock(buffer, 0, buffer.Length);
                if (length == 0)
                {
                    eos = true;
                }
                pos = 0;
            }
        }

        private char GetNext()
        {
            EnsureBuffer();
            return eos ? '\0' : buffer[pos++];
        }

        private bool IsEOS()
        {
            EnsureBuffer();
            return eos;
        }

        private char Peek()
        {
            EnsureBuffer();
            return eos ? '\0' : buffer[pos];
        }

        public SExp Read()
        {
            if (!IsEOS())
            {
                switch (Peek())
                {
                    case '(':
                        return ReadList();
                    case '"':
                        return new SExp(ReadString());
                    default:
                        return new SExp(ReadSymbol());
                }
            }
            throw new Exception("Invalid s-exp");
        }

        private string ReadSymbol()
        {
            var result = new StringBuilder();

            while (!IsEOS() && Peek() != ')' && !ws.Contains(Peek()))
            {
                result.Append(GetNext());
            }
            ConsumeWS();

            return result.ToString();
        }

        private string ReadString()
        {
            Consume('"');
            var result = new StringBuilder();

            bool possibleEnd = false;
            if (Peek() == '"') {
                possibleEnd = true; 
                GetNext(); 
            }

            while (!IsEOS() && !(possibleEnd && Peek() != '"'))
            {
                result.Append(GetNext());
                possibleEnd = false;
                if (Peek() == '"')
                {
                    possibleEnd = true;
                    GetNext();
                }
            }
            ConsumeWS();

            return result.ToString();
        }

        private SExpList ReadList()
        {
            Consume('(');
            var first = ReadSymbol();
            var rest = new List<SExp>();
            ConsumeWS();
            while (!IsEOS() && Peek() != ')')
            {
                rest.Add(Read());
                ConsumeWS();
            }
            GetNext();
            ConsumeWS();

            return new SExpList(first, rest.ToArray());
        }

        private void Consume(char expected)
        {
            var next = GetNext();
            if (next == expected)
                return;
            
            throw new Exception(string.Format("expected '{0}', got '{1}'", expected, next));
        }

        private void ConsumeWS()
        {
            while (!IsEOS() && ws.Contains(Peek()))
            {
                GetNext();
            }
        }
    }
}