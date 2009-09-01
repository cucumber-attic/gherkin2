require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  describe Table do
    tables = {
      "|a|b|\n"        => [%w{a b}],
      "|a|b|c|\n"      => [%w{a b c}],
      "|c|d|\n|e|f|\n" => [%w{c d}, %w{e f}]
    }
    
    tables.each do |text, expected|
      it "should parse #{text}" do
        Table.new.parse(text).should == expected
      end
    end
  end
end
