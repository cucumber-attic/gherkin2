require 'yaml'
require 'erb'

class RagelTask
  RL_OUTPUT_DIR = File.dirname(__FILE__) + "/../ragel/i18n"

  def initialize(lang, i18n, keywords)
    @lang     = lang
    @i18n     = i18n
    @keywords = keywords
    define_tasks
  end

  def define_tasks
    file target => [lang_ragel, common_ragel] do
      mkdir_p(File.dirname(target)) unless File.directory?(File.dirname(target))
      sh "ragel #{flags} #{lang_ragel} -o #{target}"
    end

    file lang_ragel => lang_erb do
      impl = ERB.new(IO.read(lang_erb)).result(binding)
      write(impl, lang_ragel)
    end

    file common_ragel => common_erb  do
      keywords = prep_keywords
      common = ERB.new(IO.read(common_erb)).result(binding)
      write(common, common_ragel)
    end
  end

  def target
    {
      'c'    => "ext/gherkin_lexer_#{@i18n}/gherkin_lexer_#{@i18n}.c",
      'java' => "java/src/gherkin/lexer/#{@i18n.capitalize}.java",
      'rb'   => "lib/gherkin/rb_lexer/#{@i18n}.rb"
    }[@lang]
  end

  def common_ragel
    RL_OUTPUT_DIR + "/lexer_common.#{@i18n}.rl"
  end

  def common_erb
    File.dirname(__FILE__) + '/../ragel/lexer_common.rl.erb'
  end

  def lang_ragel
    RL_OUTPUT_DIR + "/#{@i18n}.#{@lang}.rl"
  end

  def lang_erb
    File.dirname(__FILE__) + "/../ragel/lexer.#{@lang}.rl.erb"
  end

  def flags
    {
      'c'    => '-C',
      'java' => '-J',
      'rb'   => '-R'
    }[@lang]
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
    mkdir_p(File.dirname(filename)) unless File.directory?(File.dirname(filename))
    File.open(filename, "wb") do |file|
      file.write(content)
    end
  end  
end
