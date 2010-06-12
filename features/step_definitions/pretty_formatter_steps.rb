require 'stringio'
require 'gherkin'
require 'gherkin/formatter/pretty_formatter'

module PrettyPlease
  def pretty(source)
    io       = StringIO.new
    listener = Gherkin::Formatter::PrettyFormatter.new(io)
    parser   = Gherkin::Parser::Parser.new(listener, true)
    lexer    = Gherkin::I18nLexer.new(parser)
    lexer.scan(source)
    io.rewind
    io.read
  end
end

World(PrettyPlease)

Given /^I have Cucumber's source code next to Gherkin's$/ do
  @cucumber_home = File.dirname(__FILE__) + '/../../../cucumber'
  raise "No Cucumber source in #{@cucumber_home}" unless File.file?(@cucumber_home + '/bin/cucumber')
end

When /^I find all of the \.feature files$/ do
  @feature_paths = Dir["#{@cucumber_home}/**/*.feature"].sort
end

When /^I parse the prettified representation of each of them$/ do
  @error = false
  @feature_paths.each do |feature_path|
    pretty1 = nil
    pretty2 = nil
    begin
      pretty1 = pretty(IO.read(feature_path))
      pretty2 = pretty(pretty1)
      pretty2.should == pretty1
    rescue RSpec::Expectations::ExpectationNotMetError => e
      File.open("p1.feature", "wb") {|io| io.write(pretty1)}
      File.open("p2.feature", "wb") {|io| io.write(pretty2)}
      announce "=========="
      announce feature_path
      if(e.message =~ /(@@.*)/m)
        announce $1
      else
        announce "Identical, except for newlines"
      end
      @error = true
    end
  end
end

Then /^they should all be identical to the pretty output$/ do
  raise "Some features didn't do pretty well" if @error
end
