# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name        = "gherkin"
  # If the major version is bumped, verify that the change is ok:
  #
  # * Comment out cucumber dependency in gemspec
  # * Uncomment cucumber location in Gemfile
  # * Bump cucumber's gherkin dependency to the new version
  # * bundle update
  # * bundle exec rake
  #
  # Repeat these step for cucumber (swap `cucumber` with `gherkin`).
  #
  # When both are building OK, do a `bundle exec rake install` in both cucumber and gherkin projects, revert the changes in the first 2 steps 
  # and release both projects.
  #
  s.version     = "2.9.0"
  s.authors     = ["Mike Sassak", "Gregory Hnatiuk", "Aslak Hellesøy"]
  s.description = "A fast Gherkin lexer/parser based on the Ragel State Machine Compiler."
  s.summary     = "#{s.name}-#{s.version}"
  s.email       = "cukes@googlegroups.com"
  s.homepage    = "http://github.com/cucumber/gherkin"

  s.files            = `git ls-files`.split("\n")
  s.test_files       = `git ls-files -- {spec,features}/*`.split("\n")
  s.executables      = `git ls-files -- bin/*`.split("\n").map{ |f| File.basename(f) }
  s.rdoc_options     = ["--charset=UTF-8"]
  s.require_path     = "lib"

  s.files -= Dir['ikvm/**/*']
  s.files -= Dir['java/**/*']
  s.files -= Dir['js/**/*']
  s.files -= Dir['ext/**/*']
  s.files -= Dir['lib/gherkin.jar']
  s.files -= Dir['lib/**/*.dll']
  s.files -= Dir['lib/**/*.bundle']
  s.files -= Dir['lib/**/*.so']
  
  if ENV['GEM_PLATFORM']
    puts "GEM_PLATFORM:#{ENV['GEM_PLATFORM']}"
  end
  s.platform = ENV['GEM_PLATFORM'] if ENV['GEM_PLATFORM'] 
  case s.platform.to_s
  when /java/
    s.files += Dir['lib/*.jar']
  when /mswin|mingw32/
    s.files += Dir['lib/*/*.so']
  when /dotnet/
    s.files += Dir['lib/*.dll']
  else # MRI or Rubinius
    s.files += Dir['lib/gherkin/rb_lexer/*.rb']
    s.files += Dir['ext/**/*.c']
    s.extensions = Dir['ext/**/extconf.rb']
    s.add_development_dependency('rake-compiler', '>= 0.8.0')
  end
  s.files -= Dir['**/.gitignore']

  s.add_runtime_dependency('json', '>= 1.4.6')

  s.add_development_dependency('cucumber', '>= 1.1.9')
  s.add_development_dependency('rake', '>= 0.9.2')
  s.add_development_dependency('bundler', '>= 1.1.1')
  s.add_development_dependency('rspec', '~> 2.7.0') # No 2.8.0 until this is fixed: https://github.com/rspec/rspec-expectations/issues/112
  s.add_development_dependency('rubyzip', '>= 0.9.6.1')

  unless ENV['RUBY_CC_VERSION'] || defined?(JRUBY_VERSION)
    s.add_development_dependency('therubyracer', '>= 0.9.10') if ENV['GHERKIN_JS']
    # For Documentation:
    s.add_development_dependency('yard', '>= 0.7.5')
    s.add_development_dependency('rdiscount', '>= 1.6.8')
  end

  # Only needed by Cucumber. Remove when Cucumber no longer needs those.
  s.add_development_dependency('term-ansicolor', '>= 1.0.6')
  s.add_development_dependency('builder', '>= 2.1.2')
end
