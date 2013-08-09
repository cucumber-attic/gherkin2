# -*- encoding: utf-8 -*-
$LOAD_PATH.unshift File.expand_path("../lib", __FILE__)
require 'gherkin/platform'

Gem::Specification.new do |s|
  s.name        = "gherkin"
  # If the MINOR version is bumped (MAJOR.MINOR.PATCH), verify that the change is ok:
  #
  # * Comment out the cucumber dependency below
  # * Uncomment the cucumber location in Gemfile
  # * Bump cucumber's gherkin dependency to the new version
  # * bundle update
  # * bundle exec rake
  #
  # Repeat these step for cucumber (swap `cucumber` with `gherkin`).
  #
  # When both are building OK, do a `bundle exec rake install` in both cucumber and gherkin projects, revert the changes in the first 2 steps
  # and release both projects. Do this for both ruby 1.9.3, ruby 2.0.0 and jruby.
  #
  s.version     = Gherkin::VERSION
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
    s.add_development_dependency('rake-compiler', '>= 0.8.2')
  end
  s.files -= Dir['**/.gitignore']

  s.add_runtime_dependency('multi_json', '~> 1.3')

  s.add_development_dependency('cucumber', '>= 1.3.4')
  s.add_development_dependency('rake', '>= 10.1.0')
  s.add_development_dependency('bundler', '>= 1.3.5') # Make sure it's in sync with /.travis.yml
  s.add_development_dependency('rspec', '~> 2.14.1')
  s.add_development_dependency('rubyzip', '>= 0.9.9')

  unless ENV['RUBY_CC_VERSION'] || Gherkin::JRUBY
    s.add_development_dependency('therubyracer', '>= 0.11.4') if ENV['GHERKIN_JS']
    # For Documentation:
    s.add_development_dependency('yard', '>= 0.8.6.2')
    s.add_development_dependency('rdiscount', '>= 2.1.6')
  end

  # Only needed by Cucumber. Remove when Cucumber no longer needs those.
  s.add_development_dependency('term-ansicolor', '>= 1.2.2')
  s.add_development_dependency('builder', '>= 3.2.2')
end
