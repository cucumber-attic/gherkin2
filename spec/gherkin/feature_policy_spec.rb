require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module SyntaxPolicy
    describe FeaturePolicy, "ensuring correct feature syntax" do
      before do
        @policy = FeaturePolicy.new
      end
    
      it "should allow comments and tags before the feature name" do
        lambda {
          @policy.comment("# Comment", 1)
          @policy.tag("tag", 2)
          @policy.feature("Feature", "hi", 3)
        }.should_not raise_error(FeatureSyntaxError)
      end
      
      it "should not allow any keywords before the the feature name" do
        %w{background scenario_outline scenario examples}.each do |keyword|
          lambda { @policy.send keyword, "Keyword", "Content", 1 }.should raise_error(FeatureSyntaxError)
        end
      end
      
      it "should allow a background to follow a feature" do
        lambda {
          @policy.feature("Feature", "Feature", 1)
          @policy.background("Background", "Content", 2) 
        }.should_not raise_error(FeatureSyntaxError)
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
    end
    
    describe FeaturePolicy, "error modes" do
      it "should raise errors by default" do
        policy = FeaturePolicy.new
        lambda { policy.background("Background", "Out of order", 1) }.should raise_error(FeatureSyntaxError)
      end
      
      it "should send an error message to the listener when in permissive mode" do
        policy = FeaturePolicy.new(false)
        lambda { policy.background("Background", "Out of order", 1) }.should_not raise_error(FeatureSyntaxError)
      end
    end
    
    describe FeaturePolicy, "delegating events to a listener" do
      it "should delegate events to the listener"
      it "should not delegate when there is an error"
    end
  end
end