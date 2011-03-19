require 'v8'

module Gherkin
  class JSLexer
    def initialize(listener, lang='en')
      @cxt = V8::Context.new
      @cxt['exports'] = {}

      # Mimic Node.js / Firebug console.log
      @cxt['console'] = STDOUT
      def STDOUT.log(*a)
        p a
      end

      @cxt.load(File.dirname(__FILE__) + "/../lib/gherkin/lexer/i18n/#{lang}.js")

      @lexer = @cxt['exports']['Lexer'].new(listener)
    end

    def scan(gherkin)
      @lexer['scan'].methodcall(@lexer, gherkin)
    end
  end
end

class Listener
  def feature(keyword, name, description, line)
    puts(keyword + ': ' + name)
  end
  def background(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
  end
  def scenario(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
  end
  def scenario_outline(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
  end
  def examples(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
  end
  def step(keyword, name, line)
    puts('    ' + keyword + name)
  end
  def py_string(string, line)
    puts('      """\n' + string + '\n      """')
  end
  def row(row, line)
    puts('      | ' + row.join(' | ') + ' |')
  end
  def end
    puts('=====')
  end
end
Gherkin::JSLexer.new(Listener.new).scan(IO.read(ARGV[0]).unpack("c*") )