# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe Table do
      before do
        @listener = Gherkin::SexpRecorder.new
        @table = Table.new(@listener, 1)
        @listener.should_not_receive(:table_error)
      end
    
      tables = {
        "|a|b|\n"        => [%w{a b}],
        "|a|b|c|\n"      => [%w{a b c}],
        "|c|d|\n|e|f|\n" => [%w{c d}, %w{e f}]
      }
    
      tables.each do |text, expected|
        it "should parse #{text}" do
          @listener.should_receive(:table).with(expected, 1)
          @table.scan(text)
        end
      end      
    
      it "should parse a multicharacter cell content" do
        @listener.should_receive(:table).with([%w{foo bar}], 1)
        @table.scan("| foo | bar |\n")
      end
    
      it "should parse cells with spaces within the content" do
        @listener.should_receive(:table).with([["Dill pickle", "Valencia orange"], ["Ruby red grapefruit", "Tire iron"]], 1)
        @table.scan("| Dill pickle | Valencia orange |\n| Ruby red grapefruit | Tire iron |\n")
      end

      it "should parse a 1x2 table with newline" do
        @listener.should_receive(:table).with([%w{1 2}], 1)
        @table.scan("| 1 | 2 |\n")
      end
      
      it "should parse a row with whitespace after" do
        pending
        @listener.should_receive(:table).with([%w{1 2}], 1)
        @listener.should_not_receive(:table_error)
        @table.scan("| 1 | 2 | \n ")
      end
      
      it "should allow utf-8" do
        pending
        @listener.should_receive(:table).with([%w{ůﻚ 2}])
        @table.scan(" | ůﻚ | 2 | \n")
      end

      it "should parse a 2x2 table" do
        @listener.should_receive(:table).with([%w{1 2}, %w{3 4}], 1)
        @table.scan("| 1 | 2 |\n| 3 | 4 |\n")
      end

      it "should parse a 2x2 table with several newlines" do
        @listener.should_receive(:table).with([%w{1 2}, %w{3 4}], 1)
        @table.scan("| 1 | 2 |\n| 3 | 4 |\n\n\n")
      end

      it "should parse a 2x2 table with empty cells" do
        @listener.should_receive(:table).with([['1', nil], [nil, '4']], 1)
        @listener.should_not_receive(:table_error)
        @table.scan("| 1 |  | \n || 4 | \n")
      end
    
      it "should parse a 1x2 table without newline" do
        @listener.should_receive(:table).with([%w{1 2}], 1)
        @table.scan("| 1 | 2 |")
      end

      it "should parse a 1x2 table without spaces and newline" do
        @listener.should_receive(:table).with([%w{1 2}], 1)
        @table.scan("|1|2|\n")
      end
      
      it "should parse a table with lots of whitespace" do
        @listener.should_receive(:table).with([["abc", "123"]], 1)
        @listener.should_not_receive(:table_error)
        @table.scan("  \t| \t   abc\t| \t123\t \t\t| \t\t   \t \t\n  ")
      end

      describe "Bad tables" do

        it "should raise ParsingError for rows that aren't closed" do
          pending
          lambda { @table.scan("|| 4 \n") }.should raise_error(ParsingError) # "Unclosed table row (|| 4) on line 1"
        end
        
        xit "should send all closed rows after sending the error" do
          @table.scan("| 1 |  |\n| hi | bad\n| 3 | 4 |")
          @listener.to_sexp.should == [
            [:table_error, "Unclosed table row", "| hi | bad", 2],
            [:table, [['1', nil],['3','4']], 1]
          ]
        end

        xit "should send multiple table errors for rows that aren't closed" do
          @table.scan("|| 4\n|hi|there")
          @listener.to_sexp.should == [
            [:table_error, "Unclosed table row", "|| 4", 1],
            [:table_error, "Unclosed table row", "|hi|there", 2],
          ]
        end
        
        xit "should allow multiple newlines between error rows and good rows" do
          @table.scan("|| 4\n\n|hi|there\n\n|1|2|")
          @listener.to_sexp.should == [
            [:table_error, "Unclosed table row", "|| 4", 1],
            [:table_error, "Unclosed table row", "|hi|there", 3],
            [:table, [['1', '2']], 1]
          ]
        end
         
        xit "should not send a good table row if it ends with a newline" do
          @table.scan("|| 4\n|1|2|\n")
          @listener.to_sexp.should == [
            [:table_error, "Unclosed table row", "|| 4", 1],
            [:table, [['1', '2']], 1]
          ]
        end
      end
    end
  end
end
