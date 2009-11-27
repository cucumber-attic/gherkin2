require File.dirname(__FILE__) + '/ragel_compiler'

CLEAN.include [
  '**/*.{o,bundle,jar,so,obj,pdb,lib,def,exp,log}', 'ext',
  'java/target'
]

i18n_all = YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml').keys.sort.map{|i18n| i18n.gsub(/[\s-]/, '')}

if(defined?(JRUBY_VERSION))
  desc "Compile the Java extensions"
  task :compile do
    sh("ant -f java/build.xml")
  end
  i18n_all.each do |i18n|
    task :compile => RagelCompiler.target('java', i18n)
    task :compile => RagelCompiler.target('rb', i18n)
  end
else # Not JRuby
  require 'rake/extensiontask'

  i18n_all.each do |i18n|
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
    end

    # The way tasks are defined with compile:xxx (but without namespace) in rake-compiler forces us
    # to use these hacks for setting up dependencies. Ugly!
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift(extconf)
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift(RagelCompiler.target('c', i18n))
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift(RagelCompiler.target('rb', i18n))

    Rake::Task["compile"].prerequisites.unshift(extconf)
    Rake::Task["compile"].prerequisites.unshift(RagelCompiler.target('c', i18n))
    Rake::Task["compile"].prerequisites.unshift(RagelCompiler.target('rb', i18n))
  end
end