#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')

module Gherkin
  module Lexer
    shared_examples_for "a Gherkin lexer lexing tables" do
      tables = {
        "|a|b|\n"        => [%w{a b}],
        "|a|b|c|\n"      => [%w{a b c}],
        "|c|d|\n|e|f|\n" => [%w{c d}, %w{e f}]
      }
    
      tables.each do |text, expected|
        it "should parse #{text}" do
          @listener.should_receive(:table).with(t(expected), 1)
          @lexer.scan(text.dup)
        end
      end

      it "should parse a table with many columns" do
        @listener.should_receive(:table).with(t([%w{a b c d e f g h i j k l m n o p}]), 1)
        @lexer.scan("|a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|\n")
      end
    
      it "should parse a multicharacter cell content" do
        @listener.should_receive(:table).with(t([%w{foo bar}]), 1)
        @lexer.scan("| foo | bar |\n")
      end
    
      it "should parse cells with spaces within the content" do
        @listener.should_receive(:table).with(t([["Dill pickle", "Valencia orange"], ["Ruby red grapefruit", "Tire iron"]]), 1)
        @lexer.scan("| Dill pickle | Valencia orange |\n| Ruby red grapefruit | Tire iron |\n")
      end
      
      it "should allow utf-8" do
        #  Fails in 1.9.1! 
        #  'Gherkin::Lexer::Table should allow utf-8 with using == to evaluate' FAILED 
        #    expected: [[:table, [["ůﻚ", "2"]], 1]],
        #         got: [[:table, [["\xC5\xAF\xEF\xBB\x9A", "2"]], 1]] (using ==)
        #  BUT, simply running:
        #     [[:table, [["ůﻚ", "2"]], 1]].should == [[:table, [["\xC5\xAF\xEF\xBB\x9A", "2"]], 1]] 
        #  passes
        #
        @lexer.scan(" | ůﻚ | 2 | \n")
        @listener.to_sexp.should == [
          [:table, [["ůﻚ", "2"]], 1]
        ]
      end 

      it "should allow utf-8 using should_receive" do
        @listener.should_receive(:table).with(t([['繁體中文  而且','並且','繁體中文  而且','並且']]), 1)
        @lexer.scan("| 繁體中文  而且|並且| 繁體中文  而且|並且|\n")
      end

      it "should parse a 2x2 table" do
        @listener.should_receive(:table).with(t([%w{1 2}, %w{3 4}]), 1)
        @lexer.scan("| 1 | 2 |\n| 3 | 4 |\n")
      end

      it "should parse a 2x2 table with several newlines" do
        @listener.should_receive(:table).with(t([%w{1 2}, %w{3 4}]), 1)
        @lexer.scan("| 1 | 2 |\n| 3 | 4 |\n\n\n")
      end

      it "should parse a 2x2 table with empty cells" do
        @listener.should_receive(:table).with(t([['1', ''], ['', '4']]), 1)
        @lexer.scan("| 1 |  |\n|| 4 |\n")
      end
    
      it "should parse a 1x2 table that does not end in a newline" do
        @listener.should_receive(:table).with(t([%w{1 2}]), 1)
        @lexer.scan("| 1 | 2 |")
      end

      it "should parse a 1x2 table without spaces and newline" do
        @listener.should_receive(:table).with(t([%w{1 2}]), 1)
        @lexer.scan("|1|2|\n")
      end
      
      it "should parse a row with whitespace after the rows" do
        @listener.should_receive(:table).with(t([%w{1 2}, %w{a b}]), 1)
        @lexer.scan("| 1 | 2 | \n | a | b | \n")
      end
      
      it "should parse a table with lots of whitespace" do
        @listener.should_receive(:table).with(t([["abc", "123"]]), 1)
        @lexer.scan("  \t| \t   abc\t| \t123\t \t\t| \t\t   \t \t\n  ")
      end
      
      it "should raise LexingError for rows that aren't closed" do
        lambda { 
          @lexer.scan("|| oh hello \n") 
        }.should raise_error(/Parsing error on line 1: '|| oh hello/)
      end
    end
  end
end
