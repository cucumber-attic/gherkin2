require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  describe Table do
    tables = {
      "|a|b|\n"        => [%w{a b}],
      "|c|d|\n|e|f|\n" => [%w{c d}, %w{e f}]
    }
    
    tables.each do |text, expected|
      it "should parse #{text}" do
        listener = mock('listener')
        listener.should_receive(:table).with(expected)
        Table.new.scan(text, listener)
      end
    end
  end
end
