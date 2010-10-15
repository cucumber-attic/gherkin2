using System;
using System.IO;
using ikvm.io;
using gherkin;
using gherkin.formatter;
using gherkin.lexer;
using gherkin.parser;

namespace Gherkin
{
	// Just a small demo app to illustrate how to write .NET code against
	// the ikvmc compiled gherkin.jar. See tasks/ikvm.rake for details.
	class MainClass
	{
		public static void Main (string[] args)
		{
			try 
			{
				java.io.Writer output = new TextWriterAdapter(Console.Out);
				Parser parser = new Parser(new PrettyFormatter(output, false));
				Lexer lexer = new I18nLexer(parser);
				string source = File.OpenText(args[0]).ReadToEnd();
				lexer.scan(source, args[0], 0);
			} catch(Exception e) {
				Console.Error.WriteLine(e.Message);
				System.Environment.Exit(1);
			}
		}
	}
}
