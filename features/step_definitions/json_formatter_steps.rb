require 'stringio'
require 'gherkin/formatter/json_formatter'
require 'gherkin/listener/formatter_listener'

# Monkey patching so that Hash.to_json has a predictable result.
class Hash
  alias orig_keys keys
  def keys
    orig_keys.sort
  end
end

Given /^a JSON formatter$/ do
  @out = StringIO.new
  @formatter = Gherkin::Formatter::JSONFormatter.new(@out)
end

Then /^the outputted JSON should be:$/ do |expected_json|
  require 'multi_json'
  @formatter.done
  actual_json = @out.string
  puts actual_json
  puts MultiJson.dump(MultiJson.load(actual_json), :pretty => true)
  expected = MultiJson.load(expected_json)
  actual   = MultiJson.load(actual_json)
  expect(actual).to eq expected
end
