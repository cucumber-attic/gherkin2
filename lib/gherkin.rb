require 'gherkin/parser'
require 'gherkin/feature'

begin
  # The C parser, begin/rescue is temporary so we don't need to have it built to test
  require 'feature'
rescue LoadError
end
