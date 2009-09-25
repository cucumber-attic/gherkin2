require 'cucumber/rake/task'

Cucumber::Rake::Task.new(:cucumber) do |t|
  t.cucumber_opts = "--profile default"
end
task :cucumber => [:check_dependencies, "ragel:rb"]

namespace :cucumber do
  Cucumber::Rake::Task.new(:rcov, "Run Cucumber using RCov") do |t|
    t.cucumber_opts = "--profile default"
    t.rcov = true
    t.rcov_opts = %w{--exclude spec\/}
  end

  Cucumber::Rake::Task.new(:c, "Run @c_parser Cucumber features") do |t|
    t.cucumber_opts = "--profile c_parser"
  end
  task :c => [:check_dependencies, "compile"]
end


