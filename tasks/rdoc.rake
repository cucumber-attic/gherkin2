require 'rake/rdoctask'

Rake::RDocTask.new do |rdoc|
  config = YAML.load_file('VERSION.yml')
  version = "#{config[:major]}.#{config[:minor]}.#{config[:patch]}"

  rdoc.rdoc_dir = 'rdoc'
  rdoc.title = "Gherkin #{version}"
  rdoc.rdoc_files.include('README.rdoc')
  rdoc.rdoc_files.include('lib/**/*.rb')
  rdoc.rdoc_files.exclude('lib/gherkin/parser')
end
