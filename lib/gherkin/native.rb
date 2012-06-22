if ENV['GHERKIN_JS_NATIVE']
  require 'gherkin/native/therubyracer'
elsif defined?(JRUBY_VERSION)
  require 'gherkin/native/java'
else
  require 'gherkin/native/null'
end
