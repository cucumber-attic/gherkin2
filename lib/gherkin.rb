require 'gherkin/parser'
begin
  # The C parser, begin/rescue is temporary so we don't need to have it built to test
  require 'feature'
rescue LoadError
end
