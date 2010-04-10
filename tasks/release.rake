namespace :release do
  desc 'Uplad all packages and tag git'
  task :ALL => [:_gems, :dll, :jar, 'git:release']

  task :_gems => :gems do
    Dir.chdir('release') do
      Dir['*.gem'].each do |gem_file|
        sh("gem push #{gem_file}")
      end
    end
  end

  task :dll => :ikvm do
    Dir.chdir('release') do
      # This requires aslakhellesoy's build of the github gem,
      # which has tekkub's upload command.
      sh("github upload #{Dir['gherkin*.dll'][0]}")
    end
  end

  task :jar do
    Dir.chdir('java') do
      sh("mvn deploy")
    end
  end
end