class RagelCompiler
  def initialize
    require 'yaml'
    require 'erb'
    
    @impl = ERB.new(IO.read(File.dirname(__FILE__) + '/../ragel/feature.rb.rl.erb'))
    @common = ERB.new(IO.read(File.dirname(__FILE__) + '/../ragel/feature_common.rl.erb'))
    @langs = YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml')
  end

  def compile_all
    @langs.keys.each do |lang|
      compile(lang)
    end
  end

  def compile(lang)
    i18n = @langs['en'].merge(@langs[lang])
    common_file = File.dirname(__FILE__) + "/../ragel/feature_common.#{lang}.rl"
    impl_file = File.dirname(__FILE__) + "/../ragel/feature_#{lang}.rb.rl"
    rb_file = File.dirname(__FILE__) + "/../lib/gherkin/parser/feature_#{lang}.rb"

    common = @common.result(binding)
    impl = @impl.result(binding)

    File.open(common_file, "wb") do |file|
      file.write(common)
    end

    File.open(impl_file, "wb") do |file|
      file.write(impl)
    end

    sh "ragel -R #{impl_file} -o lib/gherkin/parser/feature_#{lang}.rb"

    FileUtils.rm(impl_file)
    FileUtils.rm(common_file)
  end
end

namespace :ragel do
  desc "Generate Ruby from the Ragel rule files"
  task :rb => :i18n_en do
    Dir["ragel/*.rb.rl"].each do |rl|
      basename = File.basename(rl[0..-4])
      sh "ragel -R #{rl} -o lib/gherkin/parser/#{basename}"
    end
  end

  desc "Generate C from the Ragel rule files"
  task :c do
    Dir["ragel/*.c.rl"].each do |rl|
      basename = File.basename(rl[0..-3])
      sh "ragel -C #{rl} -o ext/gherkin/#{basename}" 
    end
  end

  desc "Regenerate all Ruby i18n parsers"
  task :i18n do
    RagelCompiler.new.compile_all
  end

  desc "Regenerate Ruby English language parser"
  task :i18n_en do
    RagelCompiler.new.compile('en')
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

