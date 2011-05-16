# encoding: utf-8
require 'rubygems'
require 'bundler'
unless ENV['RUBY_CC_VERSION']
  Bundler.setup
  Bundler::GemHelper.install_tasks
end
ENV['RUBYOPT'] = nil # Necessary to prevent Bundler from *&^%$#ing up rake-compiler.

require 'rake/clean'

$:.unshift(File.dirname(__FILE__) + '/lib')

Dir['tasks/**/*.rake'].each { |rake| load File.expand_path(rake) }

task :default  => [:spec, :cucumber]
task :spec     => defined?(JRUBY_VERSION) ? :jar : :compile
task :cucumber => defined?(JRUBY_VERSION) ? :jar : :compile