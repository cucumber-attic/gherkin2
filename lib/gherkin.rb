require 'gherkin/parser'
require 'gherkin/feature'
require 'gherkin/steps'

begin
  # The C parser, begin/rescue is temporary so we don't need to have it built to test
  require 'gherkin_parser'
rescue LoadError
end
