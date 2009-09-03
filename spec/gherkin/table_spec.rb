# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  describe Table do
    def scan(text, expected)
      listener = mock('listener')
      listener.should_receive(:table_found).with(expected)
      Table.new.scan(text, listener)
    end
    
    tables = {
      "|a|b|\n"        => [%w{a b}],
      "|a|b|c|\n"      => [%w{a b c}],
      "|c|d|\n|e|f|\n" => [%w{c d}, %w{e f}]
    }
    
    tables.each do |text, expected|
      it "should parse #{text}" do
        scan(text, expected)
      end
    end
    
    it "should parse a multicharacter cell content" do
      scan("|foo|bar|\n", [%w{foo bar}])
    end
    
    it "should parse a 1x2 table with newline" do
      scan(" | 1 | 2 | \n", [%w{1 2}])
    end
    
    it "should allow utf-8" do
      scan(" | ůﻚ | 2 | \n", [%w{ůﻚ 2}])
    end

    it "should parse a 2x2 table" do
      scan("| 1 | 2 |\n| 3 | 4 |\n", [%w{1 2}, %w{3 4}])
    end

    it "should parse a 2x2 table with several newlines" do
      scan("| 1 | 2 |\n| 3 | 4 |\n\n\n", [%w{1 2}, %w{3 4}])
    end

    it "should parse a 2x2 table with empty cells" do
      scan("| 1 |  |\n|| 4 |\n", [['1', nil], [nil, '4']])
    end
    
    it "should parse a 1x2 table without newline" do
      scan("| 1 | 2 |", [%w{1 2}])
    end

    it "should parse a 1x2 table without spaces and newline" do
      scan("|1|2|", [%w{1 2}])
    end

    it "should not parse a 2x2 table that isn't closed" do
      # Not the best test, but our helper method doesn't allow for expressing should_not yet
      scan("| 1 |  |\n|| 4 ", [['1', nil], [nil]])
    end
  end
end
