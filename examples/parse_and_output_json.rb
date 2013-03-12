require 'gherkin/parser/parser'
require 'gherkin/formatter/json_formatter'
require 'stringio'
require 'multi_json'

# This example reads a couple of features and outputs them as JSON.

io = StringIO.new
formatter = Gherkin::Formatter::JSONFormatter.new(io)
parser = Gherkin::Parser::Parser.new(formatter)

sources = ["features/native_lexer.feature", "features/escaped_pipes.feature"]
sources.each do |s|
  path = File.expand_path(File.dirname(__FILE__) + '/../' + s)
  parser.parse(IO.read(path), path, 0)
end

formatter.done
puts MultiJson.dump(MultiJson.load(io.string), :pretty => true)
