# encoding: utf-8

namespace :ikvm do
  IKVM_VERSION    = '0.46.0.1'
  IKVM_DIR        = "ikvm/ikvm-#{IKVM_VERSION}"
  IKVM_ZIP        = "ikvm/ikvmbin-#{IKVM_VERSION}.zip"
  CLOBBER.include(IKVM_DIR, IKVM_ZIP)
  IKVM_BIN_DIR    = "#{IKVM_DIR}/bin"
  IKVMC_EXE       = "#{IKVM_BIN_DIR}/ikvmc.exe"
  GHERKIN_EXE     = "release/gherkin.exe"
  GHERKIN_PKG_DLL = "release/nuspec/lib/gherkin.dll"
  GHERKIN_NUSPEC  = "release/nuspec/gherkin.nuspec"
  GHERKIN_NUPKG   = "release/gherkin.#{GHERKIN_VERSION}.nupkg"
  GHERKIN_LIB_DLL = 'lib/gherkin.dll'

  def mono(args)
    if(`which mono`.strip =~ /mono/)
      sh("mono #{args}")
    else
      raise "[ERROR] You must install Mono and IKVM build gherkin for .NET. See README.md"
    end
  end

  def ikvmc(args)
    begin
      mono("#{IKVMC_EXE} #{args}")
    rescue => e
      if e.message =~ /Cannot open assembly/
        e.message << "\n\n[ERROR] You must install Mono and IKVM build gherkin for .NET. See README.rdoc"
      end
      raise e
    end
  end

  def nuget(args)
    mono("/usr/local/nuget/NuGet.exe #{args}")
  end

  file GHERKIN_EXE => [IKVMC_EXE, 'lib/gherkin.jar'] do
    ikvmc("-target:exe lib/gherkin.jar -out:#{GHERKIN_EXE}")
  end

  file GHERKIN_PKG_DLL => [IKVMC_EXE, 'lib/gherkin.jar'] do
    mkdir_p File.dirname(GHERKIN_PKG_DLL) unless File.directory?(File.dirname(GHERKIN_PKG_DLL))
    ikvmc("-target:library lib/gherkin.jar -out:#{GHERKIN_PKG_DLL} -version:#{GHERKIN_VERSION}")
  end

  file GHERKIN_LIB_DLL => GHERKIN_PKG_DLL do
    cp GHERKIN_PKG_DLL, GHERKIN_LIB_DLL
  end

  file GHERKIN_NUPKG => [GHERKIN_PKG_DLL, GHERKIN_NUSPEC] do
    pkg_dir = File.dirname(GHERKIN_EXE)
    mkdir_p File.dirname(pkg_dir) unless File.directory?(pkg_dir)
    nuget("Pack #{GHERKIN_NUSPEC} -Version #{GHERKIN_VERSION} -OutputDirectory #{pkg_dir}")
    # Now, fix the path inside the file - see https://github.com/cucumber/gherkin/issues/148
    require 'zip/zipfilesystem'
    Zip::ZipFile.open(GHERKIN_NUPKG) do |zipfile|
      begin
        zipfile.file.rename 'lib%2Fgherkin.dll', 'lib/gherkin.dll' 
      rescue => looks_like_nuget_has_been_fixed
      end
    end
  end

  file GHERKIN_NUSPEC do
    mkdir_p File.dirname(GHERKIN_NUSPEC) unless File.directory?(File.dirname(GHERKIN_NUSPEC))
    File.open(GHERKIN_NUSPEC, "w") do |io|
      io.write(<<-EOF)
<?xml version="1.0"?>
<package>
  <metadata>
    <id>gherkin</id>
    <version>#{GHERKIN_VERSION}</version>
    <authors>aslakhellesoy</authors>
    <owners>Mike Sassak, Gregory Hnatiuk, Aslak Hellesøy</owners>
    <projectUrl>https://github.com/cucumber/gherkin</projectUrl>
    <licenseUrl>https://github.com/cucumber/gherkin/blob/master/LICENSE</licenseUrl>
    <iconUrl>https://github.com/cucumber/cukes.info/raw/master/templates/images/gherkin/gherkin_32x32.png</iconUrl>
    <requireLicenseAcceptance>false</requireLicenseAcceptance>
    <description>A fast lexer and parser for the Gherkin language based on Ragel</description>
    <copyright>Copyright (c) 2009-2011 Mike Sassak, Gregory Hnatiuk, Aslak Hellesøy</copyright>
    <dependencies>
      <dependency id="IKVM" version="#{IKVM_VERSION}" />
    </dependencies>
    <tags>gherkin cucumber specflow bdd lexer parser</tags>
  </metadata>
</package>
EOF
    end
  end

  task :nupkg => GHERKIN_NUPKG

  task :check => [GHERKIN_EXE, :copy_ikvm_dlls] do
    puts "************** Pretty printing some features with .NET. **************"
    mono "#{GHERKIN_EXE} features"
    puts "************** DONE Pretty printing some features with .NET. All OK. **************"
  end

  task :copy_ikvm_dlls => IKVMC_EXE do
    exe_dir = File.dirname(GHERKIN_EXE)
    mkdir_p File.dirname(exe_dir) unless File.directory?(exe_dir)
    Dir["#{IKVM_BIN_DIR}/*.dll"].each do |dll|
      cp dll, exe_dir
    end
  end

  task :push => GHERKIN_NUPKG do
    nuget("Push #{GHERKIN_NUPKG}")
    nuget("Publish gherkin #{GHERKIN_VERSION}")
  end

  file IKVMC_EXE do
    sh("wget http://downloads.sourceforge.net/project/ikvm/ikvm/#{IKVM_VERSION}/ikvmbin-#{IKVM_VERSION}.zip -O #{IKVM_ZIP}")
    Dir.chdir('ikvm') do
      sh("unzip ikvmbin-#{IKVM_VERSION}.zip")
    end
  end
end

task :ikvm => ['ikvm:check']

