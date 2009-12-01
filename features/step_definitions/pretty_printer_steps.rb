require 'stringio'
require 'gherkin'
require 'gherkin/tools/pretty_printer'

module PrettyPlease
  def pretty(source)
    io       = StringIO.new
    listener = Gherkin::Tools::PrettyPrinter.new(io)
    parser   = Gherkin::Parser.new(listener, true)
    lexer    = Gherkin::I18nLexer.new(parser)
    lexer.scan(source)
    io.rewind
    io.read
  end
end

World(PrettyPlease)

Given /^I have Cucumber's home dir defined in CUCUMBER_HOME$/ do
  @cucumber_home = ENV['CUCUMBER_HOME']
  raise "No CUCUMBER_HOME" if @cucumber_home.nil?
end

When /^I find all of the \.feature files$/ do
  @features = Dir["#{@cucumber_home}/**/*.feature"].sort
end

When /^I parse the prettified representation of each of them$/ do
  @errors = [['Path', 'Error']]
  @features.each do |feature|
    pretty1 = nil
    pretty2 = nil
    begin
#      announce "========== #{feature}:"
      pretty1 = pretty(IO.read(feature))
      pretty2 = pretty(pretty1)
      pretty2.should == pretty1
    rescue Spec::Expectations::ExpectationNotMetError => e
      File.open("p1.feature", "w") {|io| io.write(pretty1)}
      File.open("p2.feature", "w") {|io| io.write(pretty2)}
      announce "========== #{feature}:"
      if(e.message =~ /(@@.*)/m)
        announce $1
      else
        announce "??? NO DIFF ???"
      end
      @errors << [feature, "See announced diff"]
    rescue => e
      @errors << [feature, e.message]
    end
  end
end

Then /^the following files should have errors:$/ do |table|
  table.diff!(@errors)
end
