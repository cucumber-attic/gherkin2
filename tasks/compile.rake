require File.dirname(__FILE__) + '/ragel_task'
require 'gherkin/i18n'

CLEAN.include [
  'pkg', 'tmp',
  '**/*.{o,bundle,jar,so,obj,pdb,lib,def,exp,log}', 'ext',
  'java/target',
  'ragel/i18n/*.rl',
  'lib/gherkin/rb_lexer/*.rb',
  'ext/**/*.c',
  'java/src/gherkin/lexer/*.java'
]

desc "Compile the Java extensions"
task :jar do
  sh("ant -f java/build.xml")
end

Gherkin::I18n.all.each do |i18n|
  java = RagelTask.new('java', i18n)
  rb   = RagelTask.new('rb', i18n)

  task :jar     => java.target
  task :jar     => rb.target

  begin
    require 'rake/extensiontask'
    c = RagelTask.new('c', i18n)

    extconf = "ext/gherkin_lexer_#{i18n.sanitized_key}/extconf.rb"

    file extconf do
      FileUtils.mkdir(File.dirname(extconf)) unless File.directory?(File.dirname(extconf))
      File.open(extconf, "w") do |io|
        io.write(<<-EOF)
require 'mkmf'
$CFLAGS << ' -O0 -Wall -Werror'
dir_config("gherkin_lexer_#{i18n.sanitized_key}")
have_library("c", "main")
create_makefile("gherkin_lexer_#{i18n.sanitized_key}")
EOF
      end
    end

    Rake::ExtensionTask.new("gherkin_lexer_#{i18n.sanitized_key}") do |ext|
      if ENV['RUBY_CC_VERSION']
        ext.cross_compile = true
        ext.cross_platform = 'i386-mingw32'
      end
    end

    task :compile => c.target
    task :compile => rb.target

    # The way tasks are defined with compile:xxx (but without namespace) in rake-compiler forces us
    # to use these hacks for setting up dependencies. Ugly!
    Rake::Task["compile:gherkin_lexer_#{i18n.sanitized_key}"].prerequisites.unshift(extconf)
    Rake::Task["compile:gherkin_lexer_#{i18n.sanitized_key}"].prerequisites.unshift(c.target)
    Rake::Task["compile:gherkin_lexer_#{i18n.sanitized_key}"].prerequisites.unshift(rb.target)

    Rake::Task["compile"].prerequisites.unshift(extconf)
    Rake::Task["compile"].prerequisites.unshift(c.target)
    Rake::Task["compile"].prerequisites.unshift(rb.target)
  rescue LoadError
    unless defined?($c_warned)
      warn "WARNING: Rake::ExtensionTask not installed. Skipping C compilation." 
      $c_warned = true
      task :compile # no-op
    end
  end
end