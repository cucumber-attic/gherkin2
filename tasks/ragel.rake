CLEAN.include [
  'ragel/i18n/*.rl',
  'lib/gherkin/rb_lexer/*.rb',
  'ext/**/*.c',
  'java/src/gherkin/lexer/*.java',
]

class RagelCompiler
  RL_OUTPUT_DIR = File.dirname(__FILE__) + "/../ragel/i18n"
  
  def initialize(lang, i18n, keywords)
    require 'yaml'
    require 'erb'
    
    @lang = lang
    @i18n = i18n
    @keywords = keywords
    raise "Hmm" if keywords.nil?

    @common_ragel_file = RL_OUTPUT_DIR + "/lexer_common.#{@i18n}.rl"
    @main_ragel_file   = RL_OUTPUT_DIR + "/#{@i18n}.#{@lang}.rl"

    @flag, @emit_path = case(@lang)
      when "rb"   then ["-R", "lib/gherkin/rb_lexer/#{@i18n}.rb"]
      when "c"    then ["-C", "ext/gherkin_lexer_#{@i18n}/gherkin_lexer_#{@i18n}.c"]
      when "java" then ["-J", "java/src/gherkin/lexer/#{@i18n.gsub(/[\s-]/, '').capitalize}.java"]
    end
  end

  def emit
    FileUtils.mkdir(RL_OUTPUT_DIR) unless File.directory?(RL_OUTPUT_DIR)
    generate_lang_rl
    generate_common_rl
    run_ragel
  end
  
  def generate_common_rl
    keywords = prep_keywords
    common = ERB.new(IO.read(File.dirname(__FILE__) + '/../ragel/lexer_common.rl.erb')).result(binding)
    write(common, @common_ragel_file)
  end
  
  def generate_lang_rl
    impl = ERB.new(IO.read(File.dirname(__FILE__) + "/../ragel/lexer.#{@lang}.rl.erb")).result(binding)
    write(impl, @main_ragel_file)
  end

  def run_ragel
    mkdir_p(File.dirname(@emit_path)) unless File.directory?(File.dirname(@emit_path))
    sh "ragel #{@flag} #{@main_ragel_file} -o #{@emit_path}"
  end

  def prep_keywords
    keywords = @keywords.dup
    delimited_keywords = %w{feature background scenario scenario_outline examples}
    bare_keywords = %w{given when then and but}
    all_keywords = delimited_keywords + bare_keywords
    
    all_keywords.each       { |k| keywords[k] = keywords[k].split("|") }
    delimited_keywords.each { |k| keywords[k].map! { |v| v += ':'} }
    bare_keywords.each      { |k| keywords[k].map! { |v| (v + ' ').sub(/< $/,'')} }
    all_keywords.each       { |k| keywords[k] = '("' + keywords[k].join('" | "') + '")' }    
    keywords
  end

  def write(content, filename)
    File.open(filename, "wb") do |file|
      file.write(content)
    end
  end  
end

YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml').each do |i18n, keywords|
  i18n = i18n.gsub(/[\s-]/, '')

  namespace :ragel do
    namespace :c do
      task i18n do
        RagelCompiler.new("c", i18n, keywords).emit
      end
    end

    namespace :java do
      task i18n do
        RagelCompiler.new("java", i18n, keywords).emit
      end
    end

    namespace :rb do
      task i18n do
        RagelCompiler.new("rb", i18n, keywords).emit
      end
    end
    
    desc "Emit all ruby ragel parsers"
    task :rb => "rb:#{i18n}"
  end
end
