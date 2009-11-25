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
    task :compile => "ragel:java:#{i18n}"
  end
  task :compile => "ragel:rb"
else # Not JRuby
  require 'rake/extensiontask'

  i18n_all.each do |i18n|
    file "ext/gherkin_lexer_#{i18n}/extconf.rb".to_sym do
      extconf = "ext/gherkin_lexer_#{i18n}/extconf.rb"
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

    # The way tasks are defined with compile:xxx (but without namespace) in rake-compiler forces us
    # to use these hacks for setting up dependencies. Ugly!
    Rake::ExtensionTask.new("gherkin_lexer_#{i18n}")
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift("ext/gherkin_lexer_#{i18n}/extconf.rb")
    Rake::Task["compile:gherkin_lexer_#{i18n}"].prerequisites.unshift("ragel:c:#{i18n}")
    Rake::Task["compile"].prerequisites.unshift("ext/gherkin_lexer_#{i18n}/extconf.rb")
    Rake::Task["compile"].prerequisites.unshift("ragel:c:#{i18n}")
  end
  Rake::Task["compile"].prerequisites.unshift("ragel:rb")
end