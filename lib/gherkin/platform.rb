# Detect the platform we're running and warn if it is not supported

module Gherkin
unless defined?(Gherkin::VERSION)
  VERSION       = '2.11.7'
  JRUBY         = defined?(JRUBY_VERSION)

  if !JRUBY && !(RUBY_VERSION =~ /^(1\.8\.7|1\.9\.3|2\.0)/)
    warn("WARNING: Unsupported Ruby version - #{RUBY_VERSION}")
  end
end
end
