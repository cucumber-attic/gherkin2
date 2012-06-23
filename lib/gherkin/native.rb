if defined?(JRUBY_VERSION)
  require 'gherkin/native/java'
elsif ENV['GHERKIN_JS_NATIVE']
  require 'gherkin/native/therubyracer'
else
  require 'gherkin/native/null'
end
