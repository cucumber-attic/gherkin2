require 'stringio'
require 'gherkin/formatter/pretty_formatter'
require 'gherkin/json_parser'
require 'multi_json'

Given /^a PrettyFormatter$/ do
  @io = StringIO.new
  @formatter = Gherkin::Formatter::PrettyFormatter.new(@io, true, false)
end

Given /^a JSON lexer$/ do
  @json_parser = Gherkin::JSONParser.new(@formatter, @formatter)
end

Given /^the following JSON is parsed:$/ do |text|
  @json_parser.parse(MultiJson.dump(MultiJson.load(text), :pretty => true))
end

Then /^the outputted text should be:$/ do |expected_text|
  expect(@io.string.strip).to eq expected_text
end
