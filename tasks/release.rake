namespace :release do
  desc 'Uplad all packages and tag git'
  task :ALL => [:gems, :push_dll, :push_jar, :push_gems, 'git:release']

  task :push_gems do
    Dir.chdir('release') do
      Dir['*.gem'].each do |gem_file|
        sh("gem push #{gem_file}")
      end
    end
  end

  task :push_dll => :ikvm do
    Dir.chdir('release') do
      # This requires aslakhellesoy's build of the github gem,
      # which has tekkub's upload command.
      sh("github upload #{Dir['gherkin*.dll'][0]}")
    end
  end

  task :push_jar do
    Dir.chdir('java') do
      sh("mvn -Dmaven.wagon.provider.http=httpclient deploy")
    end
  end
end