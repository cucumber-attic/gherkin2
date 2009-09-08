$LOAD_PATH.unshift(File.dirname(__FILE__))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'lib'))
require 'gherkin'
require 'gherkin/sexp_recorder'
require 'spec'
require 'spec/autorun'

Spec::Runner.configure do |config|
  
end
