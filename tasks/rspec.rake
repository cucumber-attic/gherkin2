require 'rspec/core/rake_task'

desc "Run RSpec"
RSpec::Core::RakeTask.new(:spec)

desc "Run RSpec with RCov"
RSpec::Core::RakeTask.new(:rcov) do |t|
  t.rcov = true
  t.rcov_opts = %w{--exclude osx\/objc,gems\/,spec\/}
end
