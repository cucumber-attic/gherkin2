$LOAD_PATH.unshift(File.dirname(__FILE__))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'lib'))
require 'gherkin'
require 'gherkin/sexp_recorder'
require 'spec'
require 'spec/autorun'
require 'spec/gherkin/shared/parser_spec'
require 'spec/gherkin/shared/tags_spec'
require 'spec/gherkin/shared/py_string_spec'

Spec::Runner.configure do |config|
  
end
