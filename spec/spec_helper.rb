$LOAD_PATH.unshift(File.dirname(__FILE__))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'lib'))
$LOAD_PATH.unshift(File.join(File.dirname(__FILE__), '..', 'spec/gherkin'))
require 'gherkin'
require 'stringio'
require 'gherkin/sexp_recorder'
require 'rubygems'
require 'spec'
require 'spec/autorun'
require 'shared/lexer_spec'
require 'shared/tags_spec'
require 'shared/py_string_spec'
require 'shared/row_spec'

if defined?(JRUBY_VERSION)
  class OutputStreamStringIO < java.io.ByteArrayOutputStream
    def rewind
    end

    def read
      toString("UTF-8")
    end
  end
end

class StringIO
  class << self
    def new
      if defined?(JRUBY_VERSION)
        OutputStreamStringIO.new
      else
        super
      end
    end
  end
end

module GherkinSpecHelper
  def scan_file(file)
    @lexer.scan(File.new(File.dirname(__FILE__) + "/gherkin/fixtures/" + file).read)
  end
end

Spec::Runner.configure do |c|
  c.include(GherkinSpecHelper)
end

# Allows comparison of Java List with Ruby Array (rows)
Spec::Matchers.define :r do |expected|
  match do |row|
    def row.inspect
      "r " + self.map{|cell| cell}.inspect
    end
    row.map{|cell| cell}.should == expected
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

Spec::Matchers.define :allow do |event|
  match do |parser|
    parser.expected.index(event)
  end  
end
