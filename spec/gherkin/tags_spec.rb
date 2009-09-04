#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe "tags" do
      before do
        @listener = mock('listener')
        @misc = Misc.new(@listener)
      end
      
      it "should parse a single tag" do
        @listener.should_receive(:tag).with("@dog")
        @misc.scan("@dog")
      end
    
      it "should parse multiple tags" do
        @listener.should_receive(:tag).twice
        @misc.scan("@dog @cat")
      end
    
      it "should parse UTF-8 tags" do
        @listener.should_receive(:tag).with("@シナリオテンプレート")
        @misc.scan("@シナリオテンプレート")
      end
          
      it "should parse mixed tags" do
        @listener.should_receive(:tag).with("@wip").ordered
        @listener.should_receive(:tag).with("@Значения").ordered
        @misc.scan("@wip @Значения")
      end
    
      it "should parse wacky identifiers" do
        @listener.should_receive(:tag).exactly(4).times
        @misc.scan("@BJ-x98.77 @BJ-z12.33 @O_o" "@#not_a_comment")
      end

      # TODO: Ask on ML for opinions about this one
      it "should parse tags without spaces between them?" do
        @listener.should_receive(:tag).twice
        @misc.scan("@one@two")
      end
      
      it "should not parse tags beginning with two @@ signs" do
        @listener.should_not_receive(:tag)
        @misc.scan("@@test")
      end
      
      it "should not parse a lone @ sign" do
        @listener.should_not_receive(:tag)
        @misc.scan("@")
      end
    end
  end
end