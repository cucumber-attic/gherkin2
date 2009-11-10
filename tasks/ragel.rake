class RagelCompiler 
  RL_OUTPUT_DIR = File.dirname(__FILE__) + "/../ragel/i18n"
  
  def initialize(target)
    require 'yaml'
    require 'erb'
    
    @target = target
    @flag, @output_dir, @filename_proc = case
      when @target == "rb"   then ["-R", "lib/gherkin/lexer", lambda{|name| name}]
      when @target == "c"    then ["-C", "ext/gherkin_lexer", lambda{|name| name}]
      when @target == "java" then ["-J", "java/src/gherkin/lexer", lambda{|name| name.gsub(/[\s-]/, '').capitalize}]
    end

    @i18n_languages = YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml')
    @common_tmpl = ERB.new(IO.read(File.dirname(__FILE__) + '/../ragel/lexer_common.rl.erb'))
    @actions_tmpl = ERB.new(IO.read(File.dirname(__FILE__) + "/../ragel/lexer.#{@target}.rl.erb"))
  end

  def compile_all
    @i18n_languages.keys.each do |lang|
      compile(lang)
    end
  end

  def compile(i18n_language)
    FileUtils.mkdir(RL_OUTPUT_DIR) unless File.exist?(RL_OUTPUT_DIR)
    
    common_path = RL_OUTPUT_DIR + "/lexer_common.#{i18n_language}.rl"
    actions_path = RL_OUTPUT_DIR + "/#{i18n_language}.#{@target}.rl"
    
    generate_common(i18n_language, common_path)
    generate_actions(i18n_language, actions_path)
    
    sh "ragel #{@flag} #{actions_path} -o #{@output_dir}/#{@filename_proc.call(i18n_language)}.#{@target}"
  end
  
  def generate_common(i18n_language, path)
    i18n = prep_keywords(@i18n_languages['en'].merge(@i18n_languages[i18n_language]))
    common = @common_tmpl.result(binding)
    write common, path
  end
  
  def generate_actions(i18n_language, path)
    i18n_lexer_class_name = i18n_language.gsub(/[\s-]/, '').capitalize
    impl = @actions_tmpl.result(binding)
    write impl, path
  end

  def prep_keywords(i18n)
    delimited_keywords = %w{feature background scenario scenario_outline examples}
    bare_keywords = %w{given when then and but}
    all_keywords = delimited_keywords + bare_keywords
    
    all_keywords.each { |kw| i18n[kw] = i18n[kw].split("|") }
    delimited_keywords.each { |kw| i18n[kw].map! { |v| v += ':'} }
    bare_keywords.each { |kw| i18n[kw].map! { |v| v += ' '} } if i18n['space_after_keyword']
    all_keywords.each { |kw| i18n[kw] = "('" + i18n[kw].join("' | '") + "')" }    
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
      sh "ragel -R #{rl} -o lib/gherkin/lexer/#{basename}"
    end
  end

  desc "Generate C from the Ragel rule files"
  task :c do
    RagelCompiler.new("c").compile('en')
  end

  desc "Generate all i18n Ruby lexers"
  task :i18n_rb do
    RagelCompiler.new("rb").compile_all
  end

  desc "Generate Ruby English language lexer"
  task :i18n_rb_en do
    RagelCompiler.new("rb").compile('en')
  end

  desc "Generate all i18n Java lexers"
  task :i18n_java do
    RagelCompiler.new("java").compile_all
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

