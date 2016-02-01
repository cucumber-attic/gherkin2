
unless ENV['RUBY_CC_VERSION']
require 'cucumber/rake/task'

Cucumber::Rake::Task.new(:cucumber) do |t|
  profile = ENV['TRAVIS'] ? 'travis' : 'default'
  profile = 'javascript' if ENV['GHERKIN_JS_NATIVE']
  t.cucumber_opts = "--profile #{profile}"
end

namespace :cucumber do
  Cucumber::Rake::Task.new(:native_lexer, "Run Native lexer Cucumber features") do |t|
    t.cucumber_opts = "--profile native_lexer"
  end
  task :native_lexer => [:clean, :compile]
end
end
