# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe Table do
      before do
        @listener = mock('listener')
        @table = Table.new(@listener)
      end
    
      tables = {
        "|a|b|\n"        => [%w{a b}],
        "|a|b|c|\n"      => [%w{a b c}],
        "|c|d|\n|e|f|\n" => [%w{c d}, %w{e f}]
      }
    
      tables.each do |text, expected|
        it "should parse #{text}" do
          @listener.should_receive(:table).with(expected)
          @table.scan(text)
        end
      end      
    
      it "should parse a multicharacter cell content" do
        @listener.should_receive(:table).with([%w{foo bar}])
        @table.scan("| foo | bar |\n")
      end
    
      it "should parse cells with spaces within the content" do
        @listener.should_receive(:table).with([["Dill pickle", "Valencia orange"], ["Ruby red grapefruit", "Tire iron"]])
        @table.scan("| Dill pickle | Valencia orange |\n| Ruby red grapefruit | Tire iron |\n")
      end

      it "should parse a 1x2 table with newline" do
        @listener.should_receive(:table).with([%w{1 2}])
        @table.scan("| 1 | 2 |\n")
      end
    
      it "should allow utf-8" do
        @listener.should_receive(:table).with([%w{ůﻚ 2}])
        @table.scan(" | ůﻚ | 2 | \n")
      end

      it "should parse a 2x2 table" do
        @listener.should_receive(:table).with([%w{1 2}, %w{3 4}])
        @table.scan("| 1 | 2 |\n| 3 | 4 |\n")
      end

      it "should parse a 2x2 table with several newlines" do
        @listener.should_receive(:table).with([%w{1 2}, %w{3 4}])
        @table.scan("| 1 | 2 |\n| 3 | 4 |\n\n\n")
      end

      it "should parse a 2x2 table with empty cells" do
        @listener.should_receive(:table).with([['1', nil], [nil, '4']])
        @table.scan("| 1 |  |\n|| 4 |\n")
      end
    
      it "should parse a 1x2 table without newline" do
        @listener.should_receive(:table).with([%w{1 2}])
        @table.scan("| 1 | 2 |")
      end

      it "should parse a 1x2 table without spaces and newline" do
        @listener.should_receive(:table).with([%w{1 2}])
        @table.scan("|1|2|")
      end

      it "should not parse a 2x2 table that isn't closed" do
        @listener.should_not_receive(:table).with([['1', nil], [nil, 4]])
        @listener.should_receive(:table).with([['1', nil], [nil]])
        @table.scan("| 1 |  |\n|| 4 ")
      end
      
      it "should parse a table with tab spacing" do
        @listener.should_receive(:table).with([["abc", "123"]])
        @table.scan("|\tabc\t|\t123\t\t\t|\n")
      end
    end
  end
end
