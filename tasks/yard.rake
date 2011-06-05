require 'yard'
require 'yard/rake/yardoc_task'

YARD::Templates::Engine.register_template_path(File.expand_path(File.join(File.dirname(__FILE__), 'yard')))
YARD::Rake::YardocTask.new(:yard)
