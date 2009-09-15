CLEAN.include %w(**/*.{o,bundle,jar,so,obj,pdb,lib,def,exp,log} ext/*/Makefile ext/*/*.c ext/*/conftest.dSYM)
WIN      = (RUBY_PLATFORM =~ /mswin|cygwin/)

def ext_task(name)
  ext_dir    = "ext/#{name}"
  ext_bundle = "#{ext_dir}/#{name}.#{Config::CONFIG['DLEXT']}"
  ext_files  = FileList[
    "#{ext_dir}/*.c",
    "#{ext_dir}/*.h",
    "#{ext_dir}/*.rl",
    "#{ext_dir}/extconf.rb",
    "#{ext_dir}/Makefile",
    "lib"
  ]
  
  task "compile:#{name}" => ["#{ext_dir}/Makefile", ext_bundle]
  task :compile => ['ragel:c', "compile:#{name}"]
  
  file "#{ext_dir}/Makefile" => ["#{ext_dir}/extconf.rb"] do
    cd(ext_dir) { ruby "extconf.rb" }
  end

  file ext_bundle => ext_files do
    cd ext_dir do
      sh(WIN ? 'nmake' : 'make')
    end
    cp ext_bundle, 'lib/'
  end
end

ext_task :feature
  
desc "Compile the C extensions"
task :compile
task :package => :compile
