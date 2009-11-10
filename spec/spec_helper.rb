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

Spec::Runner.configure do |config|
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
