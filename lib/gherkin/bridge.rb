if defined?(RUBY_ENGINE) && RUBY_ENGINE == "ironruby"
  require 'gherkin/bridge/ikvm'
elsif defined?(JRUBY_VERSION)
  require 'gherkin/bridge/java'
else
  require 'gherkin/bridge/null'
end