$LOAD_PATH.unshift(File.dirname(__FILE__))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'lib'))
require 'gherkin'
require 'gherkin/sexp_recorder'
require 'rubygems'
require 'spec'
require 'spec/autorun'
require 'spec/gherkin/shared/lexer_spec'
require 'spec/gherkin/shared/tags_spec'
require 'spec/gherkin/shared/py_string_spec'
require 'spec/gherkin/shared/table_spec'

Spec::Runner.configure do |config|
end
