require 'yaml'
require 'erb'

class RagelCompiler
  RL_OUTPUT_DIR = File.dirname(__FILE__) + "/../ragel/i18n"

  class << self
    def target(lang, i18n)
      {
        'c'    => "ext/gherkin_lexer_#{i18n}/gherkin_lexer_#{i18n}.c",
        'java' => "java/src/gherkin/lexer/#{i18n.capitalize}.java",
        'rb'   => "lib/gherkin/rb_lexer/#{i18n}.rb"
      }[lang]
    end

    def flags(lang)
      {
        'c'    => '-C',
        'java' => '-J',
        'rb'   => '-R'
      }[lang]
    end
  end

  def initialize(lang, i18n, keywords)
    @i18n     = i18n
    @flags    = self.class.flags(lang)
    @target   = self.class.target(lang, i18n)
    @keywords = keywords

    @language_template = IO.read(File.dirname(__FILE__) + "/../ragel/lexer.#{lang}.rl.erb")
    @common_ragel_file = RL_OUTPUT_DIR + "/lexer_common.#{i18n}.rl"
    @main_ragel_file   = RL_OUTPUT_DIR + "/#{i18n}.#{lang}.rl"
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
    impl = ERB.new(@language_template).result(binding)
    write(impl, @main_ragel_file)
  end

  def run_ragel
    mkdir_p(File.dirname(@target)) unless File.directory?(File.dirname(@target))
    sh "ragel #{@flags} #{@main_ragel_file} -o #{@target}"
  end

  def prep_keywords
    keywords = @keywords.dup
    delimited_keywords = %w{feature background scenario scenario_outline examples}
    bare_keywords = %w{given when then and but}
    all_keywords = delimited_keywords + bare_keywords
    
    all_keywords.each       { |k| keywords[k] = keywords[k].split("|") }
    delimited_keywords.each { |k| keywords[k].map! { |v| v += ':'} }
    bare_keywords.each      { |k| keywords[k].map! { |v| (v + ' ').sub(/< $/, '')} }
    all_keywords.each       { |k| keywords[k] = '("' + keywords[k].join('" | "') + '")' }    
    keywords
  end

  def write(content, filename)
    File.open(filename, "wb") do |file|
      file.write(content)
    end
  end  
end
