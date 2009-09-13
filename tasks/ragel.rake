class RagelCompiler
  def initialize
    require 'yaml'
    require 'erb'
    
    @impl = ERB.new(IO.read(File.dirname(__FILE__) + '/../ragel/feature.rb.rl.erb'))
    @common = ERB.new(IO.read(File.dirname(__FILE__) + '/../ragel/feature_common.rl.erb'))
    @i18n_languages = YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml')
  end

  def compile_all_rb
    @i18n_languages.keys.each do |lang|
      compile_rb(lang)
    end
  end

  def compile_rb(i18n_language)
    i18n = prep_keywords(@i18n_languages['en'].merge(@i18n_languages[i18n_language]))
    i18n_parser_class_name = i18n_language.gsub(/[\s-]/, '').capitalize + "Feature"
    common_file = File.dirname(__FILE__) + "/../ragel/feature_common.#{i18n_language}.rl"
    impl_file = File.dirname(__FILE__) + "/../ragel/feature_#{i18n_language}.rb.rl"

    common = @common.result(binding)
    impl = @impl.result(binding)

    write common, common_file
    write impl, impl_file

    sh "ragel -R #{impl_file} -o lib/gherkin/parser/feature_#{i18n_language}.rb"

    FileUtils.rm([impl_file, common_file])
  end

  def prep_keywords(i18n)
    %w{feature background scenario scenario_outline examples given when then and but}.each do |keyword|
      i18n[keyword] = i18n[keyword].split("|")
    end
    %w{given when then and but}.each { |keyword| i18n[keyword].map! { |v| v += ' '} } if i18n['space_after_keyword']
    %w{feature background scenario scenario_outline examples}.each { |keyword| i18n[keyword].map! { |v| v += ':'}}
    %w{feature background scenario scenario_outline examples given when then and but}.each do |keyword|
      i18n[keyword] = "('" + i18n[keyword].join("' | '") + "')"
    end
    i18n
  end

  def write(content, filename)
    File.open(filename, "wb") do |file|
      file.write(content)
    end
  end
end

namespace :ragel do
  desc "Generate Ruby from the Ragel rule files"
  task :rb => :i18n_rb_en do
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

  desc "Generate all i18n Ruby parsers"
  task :i18n_rb do
    RagelCompiler.new.compile_all_rb
  end

  desc "Generate Ruby English language parser"
  task :i18n_rb_en do
    RagelCompiler.new.compile_rb('en')
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

