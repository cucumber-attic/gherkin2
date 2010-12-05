source "http://rubygems.org"
gemspec

unless ENV['TM_RUBY']
  @dependencies.reject!{|dep| dep.name == 'cucumber'}
  gem 'cucumber', :path => '../cucumber'
end