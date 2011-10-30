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
  require 'json'
  @formatter.close
  actual_json = @out.string
  puts actual_json
  puts JSON.pretty_generate(JSON.parse(actual_json))
  expected = JSON.parse(expected_json)
  actual   = JSON.parse(actual_json)
  actual.should == expected
end



