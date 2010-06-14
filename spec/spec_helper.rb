require 'gherkin'
require 'stringio'
require 'gherkin/sexp_recorder'
require 'rubygems'
require 'rspec/autorun'
require 'gherkin/shared/lexer_group'
require 'gherkin/shared/tags_group'
require 'gherkin/shared/py_string_group'
require 'gherkin/shared/row_group'

if defined?(JRUBY_VERSION)
  class OutputStreamStringIO < Java.java.io.ByteArrayOutputStream
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
  # TODO: Rename to gherkin_scan_file
  def scan_file(file)
    @lexer.scan(File.new(File.dirname(__FILE__) + "/gherkin/fixtures/" + file).read, file, 0)
  end

  # TODO: Remove
  def parse_file(file)
    @parser.parse(File.new(File.dirname(__FILE__) + "/gherkin/fixtures/" + file).read)
  end

  def rubify_hash(hash)
    if defined?(JRUBY_VERSION)
      h = {}
      hash.keySet.each{|key| h[key] = hash[key]}
      h
    else
      hash
    end
  end
end

RSpec.configure do |c|
  c.include(GherkinSpecHelper)
end

# Allows comparison of Java List with Ruby Array (rows)
RSpec::Matchers.define :r do |expected|
  match do |row|
    def row.inspect
      "r " + self.map{|cell| cell}.inspect
    end
    row.map{|cell| cell}.should == expected
  end
end

RSpec::Matchers.define :a do |expected|
  match do |array|
    def array.inspect
      "a " + self.map{|e| e.to_sym}.inspect
    end
    array.map{|e| e.to_sym}.should == expected
  end
end

RSpec::Matchers.define :sym do |expected|
  match do |actual|
    expected.to_s == actual.to_s
  end
end

RSpec::Matchers.define :allow do |event|
  match do |parser|
    parser.expected.index(event)
  end  
end
