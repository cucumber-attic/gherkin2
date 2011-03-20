$:.unshift(File.dirname(__FILE__) + '/../../spec')
require 'gherkin/js_lexer'

class SimplePrettyPrinterListener
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

Gherkin::JsLexer.new(SimplePrettyPrinterListener.new).scan(IO.read(ARGV[0]).unpack("c*"))