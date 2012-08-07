require File.dirname(__FILE__) + '/ragel_task'
BYPASS_NATIVE_IMPL = true
require 'gherkin/i18n'

CLEAN.include [
  'pkg', 'tmp',
  '**/*.{o,bundle,jar,so,obj,pdb,lib,def,exp,log,rbc}', 'ext',
  'java/target',
  'lib/*.dll',
  'ext/**/*.c',
  'java/src/main/resources/gherkin/*.properties',
  'doc'
]

desc "Compile the Java extensions"
task :jar => 'lib/gherkin.jar'

file 'lib/gherkin.jar' => Dir['java/src/main/java/**/*.java'] do
  sh("mvn -f java/pom.xml package")
end

desc "Build Javascript lexers"
task :js

rl_langs = ENV['RL_LANGS'] ? ENV['RL_LANGS'].split(',') : []
langs = Gherkin::I18n.all.select { |lang| rl_langs.empty? || rl_langs.include?(lang.iso_code) }

desc 'Generate Java Lexers'
task :java_lexers

langs.each do |i18n|
  java = RagelTask.new('java', i18n)
  rb   = RagelTask.new('rb', i18n)
  js   = RagelTask.new('js', i18n)

  task :java_lexers => java.target

  begin
  if !defined?(JRUBY_VERSION)
    require 'rake/extensiontask'

    c = RagelTask.new('c', i18n)

    extconf = "ext/gherkin_lexer_#{i18n.underscored_iso_code}/extconf.rb"
    file extconf do
      FileUtils.mkdir(File.dirname(extconf)) unless File.directory?(File.dirname(extconf))
      File.open(extconf, "w") do |io|
        io.write(<<-EOF)
require 'mkmf'
CONFIG['warnflags'].gsub!(/-Wshorten-64-to-32/, '') if CONFIG['warnflags']
$CFLAGS << ' -O0 -Wall' if CONFIG['CC'] =~ /gcc|clang/
dir_config("gherkin_lexer_#{i18n.underscored_iso_code}")
have_library("c", "main")
create_makefile("gherkin_lexer_#{i18n.underscored_iso_code}")
EOF
      end
    end

    Rake::ExtensionTask.new("gherkin_lexer_#{i18n.underscored_iso_code}") do |ext|
      if ENV['RUBY_CC_VERSION']
        ext.cross_compile = true
        ext.cross_platform = 'x86-mingw32'
      end
    end

    # The way tasks are defined with compile:xxx (but without namespace) in rake-compiler forces us
    # to use these hacks for setting up dependencies. Ugly!
    Rake::Task["compile:gherkin_lexer_#{i18n.underscored_iso_code}"].prerequisites.unshift(extconf)
    Rake::Task["compile:gherkin_lexer_#{i18n.underscored_iso_code}"].prerequisites.unshift(c.target)
    Rake::Task["compile:gherkin_lexer_#{i18n.underscored_iso_code}"].prerequisites.unshift(rb.target)
    Rake::Task["compile:gherkin_lexer_#{i18n.underscored_iso_code}"].prerequisites.unshift(js.target) if ENV['GHERKIN_JS']

    Rake::Task["compile"].prerequisites.unshift(extconf)
    Rake::Task["compile"].prerequisites.unshift(c.target)
    Rake::Task["compile"].prerequisites.unshift(rb.target)
    Rake::Task["compile"].prerequisites.unshift(js.target) if ENV['GHERKIN_JS']
    
    Rake::Task["js"].prerequisites.unshift(js.target) if ENV['GHERKIN_JS']
  end
  rescue LoadError
    unless defined?($c_warned)
      warn "WARNING: Rake::ExtensionTask not installed. Skipping C compilation." 
      $c_warned = true
      task :compile # no-op
    end
  end
end
