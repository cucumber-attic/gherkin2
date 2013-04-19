if RUBY_VERSION =~ /1\.9|2\.0/
  Encoding.default_external = Encoding::UTF_8
  Encoding.default_internal = Encoding::UTF_8
end
if defined?(JRUBY_VERSION)
  java_import java.util.ArrayList
  ArrayList.__persistent__ = true
end

require 'rubygems'
require 'bundler'
Bundler.setup

require 'rspec'
require 'gherkin'
require 'stringio'
require 'gherkin/sexp_recorder'
require 'gherkin/shared/encoding_group'
require 'gherkin/shared/lexer_group'
require 'gherkin/shared/tags_group'
require 'gherkin/shared/doc_string_group'
require 'gherkin/shared/row_group'
require 'gherkin/lexer/encoding'
$:.unshift(File.dirname(__FILE__))

module GherkinSpecHelper
  def scan_file(file)
    @lexer.scan(fixture(file))
  end

  def fixture(file)
    encoding = Gherkin::Lexer::Encoding.new
    source = encoding.read_file(File.dirname(__FILE__) + "/gherkin/fixtures/" + file)
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
  c.filter_run_excluding :ruby => lambda {|version|
    case version.to_s
    when "!jruby"
      RUBY_ENGINE == "jruby"
    when /^> (.*)/
      !(RUBY_VERSION.to_s > $1)
    else
      !(RUBY_VERSION.to_s =~ /^#{version.to_s}/)
    end
  }
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
