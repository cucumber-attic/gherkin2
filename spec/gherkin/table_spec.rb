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
    
    def parse(text)
      Table.new.parse(text)
    end

    it "should parse a multicharacter cell content" do
      parse("|foo|bar|\n").should == [%w{foo bar}]
    end
    
    it "should parse a 1x2 table with newline" do
      pending
      parse(" | 1 | 2 | \n").should == [%w{1 2}]
    end

    it "should parse a 1x2 table without newline" do
      pending
      parse("| 1 | 2 |").should == [%w{1 2}]
    end

    it "should parse a 1x2 table without spaces" do
      pending
      parse("|1|2|").should == [%w{1 2}]
    end

    it "should parse a 2x2 table" do
      pending
      parse("| 1 | 2 |\n| 3 | 4 |\n").should == [%w{1 2}, %w{3 4}]
    end

    it "should parse a 2x2 table with several newlines" do
      pending
      parse("| 1 | 2 |\n| 3 | 4 |\n\n\n").should == [%w{1 2}, %w{3 4}]
    end

    it "should parse a 2x2 table with empty cells" do
      pending
      parse("| 1 |  |\n|| 4 |\n").should == [['1', nil], [nil, '4']]
    end

    it "should not parse a 2x2 table that isn't closed" do
      pending
      parse("| 1 |  |\n|| 4 ").should_not == [['1', nil], [nil, '4']]
    end
  end
end
