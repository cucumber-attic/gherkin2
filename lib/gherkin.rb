require 'gherkin/lexer' # TODO: Later we'll do this only as a fallback solution when Java/C is not available (unlikely)
require 'gherkin/feature'
require 'gherkin/steps'

if defined?(JRUBY_VERSION)
  require 'gherkin/java_lexer'
else
  require 'gherkin/c_lexer'
end
