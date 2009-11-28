require File.dirname(__FILE__) + '/ragel_task'

CLEAN.include [
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

YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml').each do |i18n, keywords|
  i18n = i18n.gsub(/[\s-]/, '')

  java = RagelTask.new('java', i18n, keywords)
  rb   = RagelTask.new('rb', i18n, keywords)

  task :jar     => java.target
  task :jar     => rb.target

  begin
    require 'rake/extensiontask'
    c = RagelTask.new('c', i18n, keywords)

    extconf = "ext/gherkin_lexer_#{i18n}/extconf.rb"

    file extconf do
      FileUtils.mkdir(File.dirname(extconf)) unless File.directory?(File.dirname(extconf))
      File.open(extconf, "w") do |io|
        io.write(<<-EOF)
require 'mkmf'
dir_config("gherkin_lexer_#{i18n}")
have_library("c", "main")
create_makefile("gherkin_lexer_#{i18n}")
EOF
      end
    end

    Rake::ExtensionTask.new("gherkin_lexer_#{i18n}") do |ext|
      if ENV['RUBY_CC_VERSION']
        ext.cross_compile = true
        ext.cross_platform = 'i386-mingw32'
      end
    end

    task :compile => c.target
    task :compile => rb.target

    # The way tasks are defined with compile:xxx (but without namespace) in rake-compiler forces us
    # to use these hacks for setting up dependencies. Ugly!
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift(extconf)
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift(c.target)
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift(rb.target)

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