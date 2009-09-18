require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module SyntaxPolicy
    describe FeaturePolicy, "ensuring correct feature syntax" do
      before do
        @policy = FeaturePolicy.new(mock.as_null_object)
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
      
      it "should not allow two backgrounds" do
        @policy.feature("Feature", "hi", 1)
        @policy.background("Background", "bg", 2)
        lambda { @policy.background("Background", "Another", 3) }.should raise_error(FeatureSyntaxError)
      end
    end
    
    describe FeaturePolicy, "error modes" do
      it "should raise errors by default" do
        policy = FeaturePolicy.new(mock)
        lambda { policy.background("Background", "Out of order", 1) }.should raise_error(FeatureSyntaxError)
      end
      
      it "should not raise an error message in permissive mode" do
        permissive = true
        policy = FeaturePolicy.new(mock, permissive)
        lambda { policy.background("Background", "Out of order", 1) }.should_not raise_error(FeatureSyntaxError)
      end
    end
    
    describe FeaturePolicy, "delegating events to a listener" do
      before do
        @listener = mock('listener')
        @policy = FeaturePolicy.new(@listener)
      end
      
      it "should delegate events to the listener" do
        @listener.should_receive(:feature).with("Feature", "Content", 1)
        @policy.feature("Feature", "Content", 1)
      end
            
      it "should not delegate when there is an error in strict mode" do
        @listener.should_not_receive(:feature)
        lambda { @policy.background("Background", "Content", 1) }.should raise_error(FeatureSyntaxError)
      end
      
      it "should send an error message when in permissive mode" do
        @listener.should_receive(:error).with(:background, ["Background", "Content", 1])
        @policy.permissive = true
        @policy.background("Background", "Content", 1)
      end
    end
  end
end