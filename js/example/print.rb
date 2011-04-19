$:.unshift(File.dirname(__FILE__) + '/../../spec')
require 'gherkin/js_lexer'

class SimplePrettyPrinterListener
  def comment(value, line)
    puts(value)
  end
  def tag(value, line)
    puts(value)
  end
  def feature(keyword, name, description, line)
    puts(keyword + ': ' + name)
    puts('  ' + description) if description.to_s != ""
  end
  def background(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
    puts('  ' + description) if description.to_s != ""
  end
  def scenario(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
    puts('  ' + description) if description.to_s != ""
  end
  def scenario_outline(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
    puts('  ' + description) if description.to_s != ""
  end
  def examples(keyword, name, description, line)
    puts('  ' + keyword + ': ' + name)
    puts('    ' + description) if description.to_s != ""
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
  def eof
    puts('=====')
  end
end

Gherkin::JsLexer['en'].new(SimplePrettyPrinterListener.new).scan(IO.read(ARGV[0]))