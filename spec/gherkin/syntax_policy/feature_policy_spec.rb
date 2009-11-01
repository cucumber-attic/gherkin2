require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')

module Gherkin
  module SyntaxPolicy
    describe FeaturePolicy do
      before do
        @listener = mock('listener')
        @policy = FeaturePolicy.new(@listener)
      end
      
      it "should raise errors by default" do
        lambda { @policy.background("Background", "Out of order", 1) }.should raise_error(FeatureSyntaxError)
      end
      
      it "should not raise an error message if raise on error is false" do
        @policy.raise_on_error = false
        lambda { @policy.background("Background", "Out of order", 1) }.should_not raise_error(FeatureSyntaxError)
      end
      
      it "should give helpful error messages" do
        lambda { 
          @policy.scenario("Scenario", "My pet scenario", 12) 
        }.should raise_error(FeatureSyntaxError, "Syntax error on line 12: 'Scenario: My pet scenario'.")
      end
          
      it "should delegate events to the listener" do
        @listener.should_receive(:feature).with("Feature", "Content", 1)
        @policy.feature("Feature", "Content", 1)
      end
            
      it "by default should not delegate when there is an error" do
        @listener.should_not_receive(:feature)
        lambda { @policy.background("Background", "Content", 1) }.should raise_error(FeatureSyntaxError)
      end
      
      it "should delegate an error message when raise on error is false" do
        @listener.should_receive(:syntax_error).with(:background, "Background", "Content", 1)
        @policy.raise_on_error = false
        @policy.background("Background", "Content", 1)
      end
    end
  end
end
