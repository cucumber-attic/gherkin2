class RagelCompiler
  def initialize
    require 'yaml'
    require 'erb'
    
    @template = ERB.new(IO.read(File.dirname(__FILE__) + '/../ragel/feature_common.rl.erb'))
    @langs = YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml')
  end

  def compile
    i18n = @langs['en']
    rule_file = File.dirname(__FILE__) + '/../ragel/i18n/feature_common.en.rl'
    rules = @template.result(binding)

    File.open(rule_file, "wb") do |file|
      file.write(rules)
    end
  end
end

namespace :ragel do
  desc "Generate Ruby from the Ragel rule files"
  task :rb do
    Dir["ragel/*.rb.rl"].each do |rl|
      basename = File.basename(rl[0..-4])
      sh "ragel -R #{rl} -o lib/gherkin/parser/#{basename}" 
    end
  end

  desc "Generate C from the Ragel rule files"
  task :c do
    Dir["ragel/*.c.rl"].each do |rl|
      basename = File.basename(rl[0..-4])
      sh "ragel -C #{rl} -o ext/gherkin/#{basename}" 
    end
  end

  desc "Generate all common i18n rule files"
  task :i18n do
    RagelCompiler.new.compile
  end

  desc "Generate a dot file of the Ragel state machine"
  task :dot do
    Dir["ragel/*.rb.rl"].each do |path|
      sh "ragel -V #{path} -o #{File.basename(path, '.rl')}.dot"
    end
  end

  desc "Generate a png diagram of the Ragel state machine"
  task :png => :dot do
    Dir["*dot"].each do |path|
      sh "dot -Tpng #{path} > #{File.basename(path, '.dot')}.png"
    end
  end
end

