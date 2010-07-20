require 'rspec/core/rake_task'

RSpec::Core::RakeTask.new(:spec)
task :spec => [:check_dependencies]

RSpec::Core::RakeTask.new(:rcov) do |t|
  t.rcov = true
  t.rcov_opts = %w{--exclude osx\/objc,gems\/,spec\/}
end
