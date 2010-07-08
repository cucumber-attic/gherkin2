require 'stringio'
require 'gherkin/formatter/pretty_formatter'
require 'gherkin/json_lexer'

Given /^a PrettyFormatter$/ do
  @io = StringIO.new
  @formatter = Gherkin::Listener::FormatterListener.new(Gherkin::Formatter::PrettyFormatter.new(@io, true))
end

Given /^a JSON lexer$/ do
  @json_parser = Gherkin::JSONLexer.new(@formatter)
end

Given /^the following JSON is parsed:$/ do |text|
  @json_parser.scan(JSON.pretty_generate(JSON.parse(text)))
end

Then /^the outputted text should be:$/ do |expected_text|
  @io.string.strip.should == expected_text
end
