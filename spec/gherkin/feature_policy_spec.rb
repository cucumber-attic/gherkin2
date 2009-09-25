require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module SyntaxPolicy
    describe FeaturePolicy, "ensuring correct feature syntax" do
      before do
        @policy = FeaturePolicy.new(mock.as_null_object)
      end
          
      it "should not allow any keywords before the the feature name" do
        %w{background scenario_outline scenario examples}.each do |keyword|
          lambda { @policy.send(keyword, "Keyword", "Content", 1) }.should raise_error(FeatureSyntaxError)
        end
      end
            
      it "should not allow a background to follow any other keywords" do
        %w{scenario_outline scenario examples}.each do |keyword|          
          lambda {
            @policy.feature("Feature", "Feature", 1)
            @policy.send(keyword, "Keyword", "Content", 2)
            @policy.background("Background", "Background", 3)
          }.should raise_error(FeatureSyntaxError)
        end
      end
      
      it "should not allow two features" do
        @policy.feature("Feature", "First", 1)
        lambda { @policy.feature("Feature", "Second", 2) }.should raise_error(FeatureSyntaxError)
      end
      
      it "should not allow two backgrounds" do
        @policy.feature("Feature", "hi", 1)
        @policy.background("Background", "bg", 2)
        lambda { @policy.background("Background", "Another", 3) }.should raise_error(FeatureSyntaxError)
      end
    end
    
    describe FeaturePolicy, "handling errors" do
      before do
        @policy = FeaturePolicy.new(mock)
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
        }.should raise_error(FeatureSyntaxError, "Syntax error on line 12.")
      end
    end
    
    describe FeaturePolicy, "delegating events to the listener" do
      before do
        @listener = mock('listener')
        @policy = FeaturePolicy.new(@listener)
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
