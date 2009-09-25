require 'cucumber/rake/task'

Cucumber::Rake::Task.new(:cucumber) do |t|
  t.cucumber_opts = "--profile default"
end

Cucumber::Rake::Task.new(:cuke_rcov) do |t|
  t.cucumber_opts = "--profile default"
  t.rcov = true
  t.rcov_opts = %w{--exclude spec\/}
end

task :cucumber => [:check_dependencies, "ragel:rb"]
