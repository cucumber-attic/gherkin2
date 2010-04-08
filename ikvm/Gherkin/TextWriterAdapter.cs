using System;
using System.IO;
using ikvm.io;
using gherkin;
using gherkin.formatter;
using gherkin.lexer;
using gherkin.parser;

namespace Gherkin
{
	// Adapter that allows Java code to write to a .NET System.IO.TextWriter
	class TextWriterAdapter: java.io.Writer
	{
		private TextWriter output;
		
		public TextWriterAdapter(TextWriter output)
		{
			this.output = output;
		}
		
		public override void flush()
		{
			output.Flush();
		}

		public override void close()
		{
			output.Close();
		}

		public override void write(char[] chars, int off, int length) 
		{
			for(int p = off; p < off+length; p++) 
			{
				output.Write(chars[p]);
			}
		}
	}
}
