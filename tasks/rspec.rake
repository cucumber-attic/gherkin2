require 'rspec/core/rake_task'

desc "Run RSpec"
task :spec do
  sh "rspec spec"	
end
