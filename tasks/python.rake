require File.dirname(__FILE__) + '/ragel_task'
require 'gherkin/i18n'

CLEAN.include [
  'python/src/*.c'
]

rl_langs = ENV['RL_LANGS'] ? ENV['RL_LANGS'].split(',') : []
langs = Gherkin::I18n.all.select { |lang| rl_langs.empty? || rl_langs.include?(lang.iso_code) }

desc "Generate Python lexers"
task :python

langs.each do |i18n|
  pyc = RagelTask.new('pyc', i18n)

  Rake::Task["python"].prerequisites.unshift(pyc.target)
end
