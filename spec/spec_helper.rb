$LOAD_PATH.unshift(File.dirname(__FILE__))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'lib'))
require 'gherkin'
require 'gherkin/sexp_recorder'
require 'rubygems'
require 'spec'
require 'spec/autorun'
require 'spec/gherkin/shared/lexer_spec'
require 'spec/gherkin/shared/tags_spec'
require 'spec/gherkin/shared/py_string_spec'
require 'spec/gherkin/shared/table_spec'

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
