require 'spec/rake/spectask'

Spec::Rake::SpecTask.new(:spec) do |spec|
  spec.libs << 'lib' << 'spec'
  spec.spec_opts << '--color'
  spec.spec_files = FileList['spec/**/*_spec.rb'].exclude("c_lexer_spec.rb")
end
task :spec => [:check_dependencies, "ragel:rb"]

Spec::Rake::SpecTask.new(:rcov) do |spec|
  spec.libs << 'lib' << 'spec'
  spec.spec_opts << '--color'
  spec.pattern = 'spec/**/*_spec.rb'
  spec.rcov = true
  spec.rcov_opts = %w{--exclude spec\/}
end

namespace :spec do
  desc "Run the C lexer specs"
  Spec::Rake::SpecTask.new(:c_lexer) do |spec|
    spec.libs << 'lib' << 'spec'
    spec.spec_opts << '--color'
    spec.spec_files = FileList['spec/gherkin/c_lexer_spec.rb']
  end

  task :c_lexer => [:check_dependencies, :clean, :compile]
end
