$LOAD_PATH.unshift(File.dirname(__FILE__))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'lib'))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'spec/gherkin'))
require 'gherkin'
require 'gherkin/sexp_recorder'
require 'rubygems'
require 'spec'
require 'spec/autorun'
require 'shared/lexer_spec'
require 'shared/tags_spec'
require 'shared/py_string_spec'
require 'shared/table_spec'

module GherkinSpecHelper
  def parser(listener, raise_on_error)
    if defined?(JRUBY_VERSION)
      Java::Gherkin::Parser.new(listener, raise_on_error)
    else
      Gherkin::Parser.new('en', listener, raise_on_error)
    end
  end
end

Spec::Runner.configure do |config|
  config.include(GherkinSpecHelper)
end

# Allows comparison of Java List with Ruby Array (tables)
Spec::Matchers.define :t do |expected|
  match do |table|
    def table.inspect
      "t " + self.map{|row| row.map{|cell| cell}}.inspect
    end
    table.map{|row| row.map{|cell| cell}}.should == expected
  end
end

Spec::Matchers.define :a do |expected|
  match do |array|
    def array.inspect
      "a " + self.map{|e| e.to_sym}.inspect
    end
    array.map{|e| e.to_sym}.should == expected
  end
end

Spec::Matchers.define :sym do |expected|
  match do |actual|
    expected.to_s == actual.to_s
  end
end
