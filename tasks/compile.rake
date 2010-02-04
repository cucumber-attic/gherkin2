require File.dirname(__FILE__) + '/ragel_task'
require 'gherkin/i18n'

CLEAN.include [
  'pkg', 'tmp',
  '**/*.{o,bundle,jar,so,obj,pdb,lib,def,exp,log}', 'ext',
  'java/target',
  'ragel/i18n/*.rl',
  'lib/gherkin/rb_lexer/*.rb',
  'ext/**/*.c',
  'java/src/gherkin/lexer/*.java',
  'dotnet/Gherkin/Lexer'
]

desc "Compile the Java extensions"
task :jar do
  sh("ant -f java/build.xml")
end

namespace :dotnet do
  require 'albacore'

  desc "Generate the C# lexer files"
  task :lexer do
  end

  FileList['lib/gherkin/parser/*'].each do |src|
    dst = "dotnet/Gherkin/StateMachine/#{File.basename(src)}"
    file dst => src do
      cp src, dst, :verbose => true    
    end
    task 'dotnet:lexer' => dst
  end

  msbuild :compile => :lexer do |msb|
    msb.properties :configuration => :Release
    msb.targets :Build
    msb.solution = 'dotnet/Gherkin.sln'
  end

  xunit :test => :compile do |xunit|
    xunit.path_to_command = "dotnet/lib/xunit/xunit.console.exe"
    xunit.assembly = "dotnet/Gherkin.Tests/bin/Release/Gherkin.Tests.dll"
  end

  task :package => :test do
    cp 'dotnet/Gherkin/bin/Release/Gherkin.dll', 'lib/Gherkin.dll'
  end
end

desc "Compile and package the .NET extensions"
task :dotnet => ['dotnet:package']

class CSharpSByteFixTask
  def initialize(source)
    @source = source
    define_tasks
  end

  def define_tasks
    directory File.dirname(target)
    file target => [File.dirname(target), @source] do
      sh "cat #{@source} | sed ""s/sbyte/short/g"" > #{target}"
    end
  end

  def target
    "dotnet/Gherkin/Lexer/#{File.basename(@source)}"
  end
end


Gherkin::I18n.all.each do |i18n|
  java = RagelTask.new('java', i18n)
  rb   = RagelTask.new('rb', i18n)
  csharp_tmp = RagelTask.new('csharp', i18n)

  csharp = CSharpSByteFixTask.new(csharp_tmp.target)

  task :jar     => java.target
  task :jar     => rb.target
  task 'dotnet:lexer'     => csharp.target

  begin
  unless defined?(JRUBY_VERSION)
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

    # The way tasks are defined with compile:xxx (but without namespace) in rake-compiler forces us
    # to use these hacks for setting up dependencies. Ugly!
    Rake::Task["compile:gherkin_lexer_#{i18n.sanitized_key}"].prerequisites.unshift(extconf)
    Rake::Task["compile:gherkin_lexer_#{i18n.sanitized_key}"].prerequisites.unshift(c.target)
    Rake::Task["compile:gherkin_lexer_#{i18n.sanitized_key}"].prerequisites.unshift(rb.target)

    Rake::Task["compile"].prerequisites.unshift(extconf)
    Rake::Task["compile"].prerequisites.unshift(c.target)
    Rake::Task["compile"].prerequisites.unshift(rb.target)
  end
  rescue LoadError
    unless defined?($c_warned)
      warn "WARNING: Rake::ExtensionTask not installed. Skipping C compilation." 
      $c_warned = true
      task :compile # no-op
    end
  end
end
