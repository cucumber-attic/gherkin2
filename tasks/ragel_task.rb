require 'yaml'
require 'erb'
require 'rbconfig'

class RagelTask
  begin
    # Support Rake >= 0.9.0
    require 'rake/dsl_definition'
    include Rake::DSL
  rescue LoadError
  end

  RL_OUTPUT_DIR = File.dirname(__FILE__) + "/../ragel/i18n"
  UGLIFYJS      = File.dirname(__FILE__) + "/../js/node_modules/uglify-js/bin/uglifyjs"

  def initialize(lang, i18n)
    @lang     = lang
    @i18n     = i18n
    define_tasks
  end

  def define_tasks
    deps = [lang_ragel, common_ragel]
    deps.unshift(UGLIFYJS) if(@lang == 'js')
    sed = RbConfig::CONFIG['host_os'] =~ /linux/i ? "sed -i" : "sed -i ''"

    min_target = target.gsub(/\.js$/, '.min.js')
    file target => deps do
      mkdir_p(File.dirname(target)) unless File.directory?(File.dirname(target))
      sh "ragel #{flags} #{lang_ragel} -o #{target}"
      # Remove absolute paths from ragel-generated sources
      sh %{#{sed} "s|#{Dir.pwd}/tasks/../||" #{target}}
      if(@lang == 'js')
        # Ragel chokes if we put the escaped triple quotes in .rl, so we'll do the replace with sed after the fact. Lots of backslashes!!
        sh %{#{sed} 's/ESCAPED_TRIPLE_QUOTE/\\\\\\\\\\\\"\\\\\\\\\\\\"\\\\\\\\\\\\"/' #{target}}
        sh %{#{sed} 's/const/var/' #{target}}
        
        # Minify
        sh %{node #{UGLIFYJS} #{target} > #{min_target}}
      end
    end

    if(@lang != 'java')
      CLEAN.include(target)
    end
    if(@lang == 'js')
      CLEAN.include(min_target)
    end

    file UGLIFYJS do
      unless File.exist?(UGLIFYJS)
        Dir.chdir('js') do
          sh "npm install"
        end
      end
    end

    file lang_ragel => lang_erb do
      write(ERB.new(IO.read(lang_erb)).result(binding), lang_ragel)
    end
    
    CLEAN.include(lang_ragel)

    file common_ragel => common_erb  do
      write(ERB.new(IO.read(common_erb)).result(binding), common_ragel)
    end
    
    CLEAN.include(common_ragel)
  end

  def target
    {
      'c'    => "ext/gherkin_lexer_#{@i18n.underscored_iso_code}/gherkin_lexer_#{@i18n.underscored_iso_code}.c",
      'java' => "java/src/main/java/gherkin/lexer/#{@i18n.underscored_iso_code.capitalize}.java",
      'rb'   => "lib/gherkin/lexer/#{@i18n.underscored_iso_code}.rb",
      'js'   => "js/lib/gherkin/lexer/#{@i18n.underscored_iso_code}.js"
    }[@lang]
  end

  def common_ragel
    RL_OUTPUT_DIR + "/lexer_common.#{@i18n.underscored_iso_code}.rl"
  end

  def common_erb
    File.dirname(__FILE__) + '/../ragel/lexer_common.rl.erb'
  end

  def lang_ragel
    RL_OUTPUT_DIR + "/#{@i18n.underscored_iso_code}.#{@lang}.rl"
  end

  def lang_erb
    File.dirname(__FILE__) + "/../ragel/lexer.#{@lang}.rl.erb"
  end

  def flags
    {
      'c'      => '-C',
      'java'   => '-J',
      'rb'     => '-R',
      'js'     => '-E'
    }[@lang]
  end

  def write(content, filename)
    mkdir_p(File.dirname(filename)) unless File.directory?(File.dirname(filename))
    File.open(filename, "wb") do |file|
      file.write(content)
    end
  end

  def ragel_list(keywords)
    "(#{keywords.map{|keyword| %{"#{keyword}"}}.join(' | ')})"
  end
end
