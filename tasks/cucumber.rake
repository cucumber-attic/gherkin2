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

  Cucumber::Rake::Task.new(:c_lexer, "Run @c_lexer Cucumber features") do |t|
    t.cucumber_opts = "--profile c_lexer"
  end
  task :c_lexer => [:check_dependencies, :clean, :compile]
end

