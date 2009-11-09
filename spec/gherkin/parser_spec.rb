require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  describe Parser do
    before do
      @listener = mock('listener')
      @parser = Parser.new('en', @listener)
    end
    
    it "should raise errors by default" do
      lambda { @parser.background("Background", "Out of order", 1) }.should raise_error(GherkinSyntaxError)
    end
    
    it "should not raise an error message if raise on error is false" do
      @parser.raise_on_error = false
      lambda { @parser.background("Background", "Out of order", 1) }.should_not raise_error(GherkinSyntaxError)
    end
    
    it "should give helpful error messages" do
      lambda { 
        @parser.scenario("Scenario", "My pet scenario", 12) 
      }.should raise_error(GherkinSyntaxError, /Syntax error on line 12. Found scenario when expecting one of/)
    end
        
    it "should delegate events to the listener" do
      @listener.should_receive(:comment).with("# Content", 1)
      @parser.comment("# Content", 1)
    end
          
    it "by default should not delegate when there is an error" do
      @listener.should_not_receive(:feature)
      lambda { @parser.background("Background", "Content", 1) }.should raise_error(GherkinSyntaxError)
    end
    
    it "should delegate an error message when raise on error is false" do
      @listener.should_receive(:syntax_error).with(:background, "Background", "Content", 1)
      @parser.raise_on_error = false
      @parser.background("Background", "Content", 1)
    end
  end
end
