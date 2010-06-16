require 'stringio'
require 'gherkin'
require 'gherkin/formatter/pretty_formatter'
require 'gherkin/formatter/json_formatter'
require 'gherkin/parser/json_parser'

module PrettyPlease
  def pretty_machinery(gherkin)
    io        = StringIO.new
    formatter = Gherkin::Formatter::PrettyFormatter.new(io, false)
    listener  = Gherkin::Parser::FormatterListener.new(formatter)
    parser    = Gherkin::Parser::Parser.new(listener, true)
    lexer     = Gherkin::I18nLexer.new(parser)
    lexer.scan(gherkin, "test.feature", 0)
    io.string
  end

  def json_machinery(gherkin)
    json                = StringIO.new
    json_formatter      = Gherkin::Formatter::JSONFormatter.new(json)
    formatter_listener  = Gherkin::Parser::FormatterListener.new(json_formatter)
    gherkin_lexer       = Gherkin::I18nLexer.new(formatter_listener)
    gherkin_lexer.scan(gherkin, "test.feature", 0)

    result              = StringIO.new
    pretty_formatter    = Gherkin::Formatter::PrettyFormatter.new(result, false)
    formatter_listener  = Gherkin::Parser::FormatterListener.new(pretty_formatter)
    json_lexer          = Gherkin::Parser::JSONParser.new(formatter_listener)
    json_lexer.parse(json.string)
    
    result.string
  end
end

World(PrettyPlease)

Given /^I have Cucumber's source code next to Gherkin's$/ do
  @cucumber_home = File.dirname(__FILE__) + '/../../../cucumber'
  raise "No Cucumber source in #{@cucumber_home}" unless File.file?(@cucumber_home + '/bin/cucumber')
end

Given /^I find all of the \.feature files$/ do
  @feature_paths = Dir["#{@cucumber_home}/**/*.feature"].sort
end

When /^I send each prettified original through the "([^"]*)" machinery$/ do |machinery|
  @error = false
  @feature_paths.each do |feature_path|
    begin
      original = pretty_machinery(IO.read(feature_path))
      via_machinery = self.__send__("#{machinery}_machinery", original)
      via_machinery.should == original
    rescue RSpec::Expectations::ExpectationNotMetError => e
      announce "=========="
      announce feature_path
      if(e.message =~ /(@@.*)/m)
        announce $1
        @error = true
      else
        announce "Identical, except for newlines"
      end
    rescue => e
      e.message << "\nFatal error happened when parsing #{feature_path}"
      raise e
    end
  end
end

Then /^the machinery output should be identical to the prettified original$/ do
  raise "Some features didn't make it through the machinery" if @error
end
