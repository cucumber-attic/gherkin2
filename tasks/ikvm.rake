# To test out the pure Java main program on .NET, execute:
#
#   rake ikvm
#
# Just print dots:
#
#   [mono] pkg/gherkin.exe features
#
# Pretty print all to STDOUT:
#
#   [mono] pkg/gherkin.exe features pretty
#
# To test out the pure C# main program on .NET, execute:
#
#   rake ikvm (you need this to generate all the .dll files needed for the next step)
#
# Then build ikvm/Gherkin.sln. Then:
#
#   [mono] mono ikvm/Gherkin/bin/Debug/Gherkin.exe features/steps_parser.feature
#
namespace :ikvm do
  def mono(args)
    if(`which mono`.strip =~ /mono/)
      sh("mono #{args}")
    else
      raise "[ERROR] You must install Mono and IKVM build gherkin for .NET. See README.rdoc"
    end
  end

  def ikvmc(args)
    begin
      mono("/usr/local/ikvm/bin/ikvmc.exe #{args}")
    rescue => e
      if e.message =~ /Cannot open assembly/
        e.message << "\n\n[ERROR] You must install Mono and IKVM build gherkin for .NET. See README.rdoc"
      end
      raise e
    end
  end

  def nuget(args)
    mono("--runtime=v4.0 /usr/local/nuget/NuGet.exe #{args}")
  end

  desc 'Make a .NET .exe'
  task :exe => ['lib/gherkin.jar'] do
    ikvmc("-target:exe lib/gherkin.jar -out:release/gherkin-#{GHERKIN_VERSION}.exe")
  end

  desc 'Make a .NET .dll'
  task :dll => ['lib/gherkin.jar'] do
    mkdir_p 'release' unless File.directory?('release')
    ikvmc("-target:library lib/gherkin.jar -out:release/gherkin-#{GHERKIN_VERSION}.dll")
    cp "release/gherkin-#{GHERKIN_VERSION}.dll", 'lib/gherkin.dll'
  end

  desc 'Copy the IKVM .dll files over to the pkg dir'
  task :copy_ikvm_dlls do
    Dir['/usr/local/ikvm/bin/{IKVM.OpenJDK.Core,IKVM.OpenJDK.Text,IKVM.OpenJDK.Security,IKVM.Runtime}.dll'].each do |dll|
      mkdir_p 'release' unless File.directory?('release')
      cp dll, 'release'
      cp dll, 'lib'
    end
  end  

  desc 'Package the assembly with NuGet'
  task :package => ['lib/gherkin.jar'] do
    mkdir_p 'package' unless File.directory?('package')
    mkdir_p 'package/lib' unless File.directory?('package/lib')
    cp 'lib/gherkin.dll', 'package/lib'
    cp 'ikvm/gherkin.nuspec', 'package'
    nuget("Update -self")
    nuget("SetApiKey", IO.read("~/.nuget/key"))
    nuget("Pack package/gherkin.nuspec -Version #{GHERKIN_VERSION} -OutputDirectory package")
    nuget("Push package/gherkin-#{GHERKIN_VERSION}.nupkg")   
  end
end

task :ikvm => ['ikvm:package', 'ikvm:copy_ikvm_dlls', 'ikvm:exe', 'ikvm:dll'] do
  puts "************** Pretty printing some features with .NET. **************"
  mono "release/gherkin-#{GHERKIN_VERSION}.exe features"
  puts "************** DONE Pretty printing some features with .NET. All OK. **************"
end

