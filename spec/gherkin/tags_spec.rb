#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe "parsing" do
      describe "tags" do
        before do
          @listener = mock('listener')
          @feature = Parser['en'].new(@listener)
        end
      
        it "should parse a single tag" do
          @listener.should_receive(:tag).with("dog", 1)
          @feature.scan("@dog\n")
        end
    
        it "should parse multiple tags" do
          @listener.should_receive(:tag).twice
          @feature.scan("@dog @cat\n")
        end
    
        it "should parse UTF-8 tags" do
          @listener.should_receive(:tag).with("シナリオテンプレート", 1)
          @feature.scan("@シナリオテンプレート\n")
        end
          
        it "should parse mixed tags" do
          @listener.should_receive(:tag).with("wip", 1).ordered
          @listener.should_receive(:tag).with("Значения", 1).ordered
          @feature.scan("@wip @Значения\n")
        end
    
        it "should parse wacky identifiers" do
          @listener.should_receive(:tag).exactly(4).times
          @feature.scan("@BJ-x98.77 @BJ-z12.33 @O_o" "@#not_a_comment\n")
        end

        # TODO: Ask on ML for opinions about this one
        it "should parse tags without spaces between them?" do
          @listener.should_receive(:tag).twice
          @feature.scan("@one@two\n")
        end
      
        it "should not parse tags beginning with two @@ signs" do
          @listener.should_not_receive(:tag)
          @feature.scan("@@test\n")
        end
      
        it "should not parse a lone @ sign" do
          @listener.should_not_receive(:tag)
          @feature.scan("@\n")
        end
      end
    end
  end
end
