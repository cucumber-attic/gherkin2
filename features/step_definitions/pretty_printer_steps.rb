require 'stringio'
require 'gherkin/tools/pretty_printer'

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
    feature_name = feature[@cucumber_home.length+1..-1]
    io       = StringIO.new
    listener = Gherkin::Tools::PrettyPrinter.new(io)
    parser   = Gherkin::Parser.new(listener, true)
    lexer    = Gherkin::Lexer.new(parser)
    
    begin
      lexer.scan(IO.read(feature))
      io.rewind
      pretty = io.read
    rescue => e
      @errors << [feature_name, e.message]
    end
  end
end

Then /^the following files should have errors:$/ do |table|
  table.diff!(@errors)
end
