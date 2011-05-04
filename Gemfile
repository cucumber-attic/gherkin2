source "http://rubygems.org"
gemspec

if !ENV['TM_RUBY'] && File.directory?('../cucumber')
  @dependencies.reject!{|dep| dep.name == 'cucumber'}
  gem 'cucumber', :path => '../cucumber'
end