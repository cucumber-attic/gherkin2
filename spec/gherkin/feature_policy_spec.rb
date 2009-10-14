require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module SyntaxPolicy
    describe FeaturePolicy do      
      before do
        @policy = FeaturePolicy.new(mock.as_null_object)
      end
                  
      describe "initial Feature state" do
        context "before keyword" do      
          it "should allow tags and comments" do
            [:comment, :tag].each do |event|
              lambda { @policy.send(event, event.to_s.capitalize, 1) }.should_not raise_error(FeatureSyntaxError)
            end
          end
        
          it "should not allow anything else" do
            [:background, :scenario, :scenario_outline, :examples, :step, :table, :py_string].each do |event|
              lambda { @policy.send(event, event.to_s.capitalize, "Content", 1) }.should raise_error(FeatureSyntaxError)
            end
          end
        end
        
        context "after keyword" do
          before do
            @policy.feature("Feature", "Start", 1)
          end
          
          it "should allow background, scenario, scenario outline, tags and comments" do
            [:background, :scenario, :scenario_outline, :tag, :comment].each do |event|
              lambda { @policy.send(event, event.to_s.capitalize, "Content", 1) }.should_not raise_error(FeatureSyntaxError)
            end
          end
        
          it "should not step, table, py_string or examples" do
            [:step, :table, :py_string, :examples].each do |event|
              lambda { @policy.send(event, event.to_s.capitalize, "Content", 1) }.should raise_error(FeatureSyntaxError)
            end
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
      
      describe "Background state" do # probably belongs within feature state
        context "before keyword" do
          before(:each) do
            @policy.feature("Feature", "start", 1)
          end
          
          it "should allow background, scenario, or scenario outline" do
            [:background, :scenario, :scenario_outline].each do |event|
              lambda { @policy.send(event, event.to_s.capitalize, "content", 2) }.should_not raise_error(FeatureSyntaxError)
            end
          end          
        end
        
        context "after keyword" do
          before do
            @policy.feature("Feature", "start", 1)
            @policy.background("Background", "", 2)
          end       
        
          it "should allows steps, scenarios, scenario outlines, comments and tags" do
            [:step, :scenario, :scenario_outline, :comment, :tag].each do |event|
              lambda { @policy.send(event, event.to_s.capitalize, "content", 3) }.should_not raise_error(FeatureSyntaxError)
            end
          end

          it "should not allow features or examples" do
            [:feature, :examples].each do |event|
              lambda { @policy.send(event, event.to_s.capitalize, "Content", 3) }.should raise_error(FeatureSyntaxError)
            end
          end
        end
      end
      
      describe "Scenario state" do
        before do
          @policy.feature("Feature", "hi", 1)
          @policy.scenario("Scenario", "Foo", 2)
        end
        
        it "should allow step, comment and tag" do
          [:step, :comment, :tag].each do |event|
            lambda { @policy.send(event, event.to_s.capitalize, "Content", 3) }.should_not raise_error(FeatureSyntaxError)
          end
        end
        
        it "should not allow feature, background or examples" do
          [:feature, :background, :examples].each do |event|
            lambda { @policy.send(event, event.to_s.capitalize, "Content", 3) }.should raise_error(FeatureSyntaxError)
          end
        end
      end
      
      describe "Scenario outline state" do
        before do
          @policy.feature("Feature", "Hi", 1)
          @policy.scenario_outline("Scenario Outline", "Some outline", 2)
        end
        
        it "should allow step, comment, tag, scenario or scenario outline" do
          [:step, :comment, :tag, :scenario, :scenario_outline].each do |even|
            lambda { @policy.send(event, event.to_s.capitalize, "Content", 3) }.should_not raise_error(FeatureSyntaxError)
          end
        end
      
        it "should allow examples that follow a step" do
          @policy.step("Given", "a foo", 3)
          lambda { @policy.examples("Examples", "Some nice examples", 4) }.should_not raise_error(FeatureSyntaxError)
        end
        
        it "should not allow examples that do not follow a step" do
          lambda { @policy.examples("Examples", "Some nice examples", 4) }.should raise_error(FeatureSyntaxError)
        end
      
        it "should not allow feature or background" do
          [:feature, :background].each do |event|
            lambda { @policy.send(event, event.to_s.capitalize, "Content", 3) }.should raise_error(FeatureSyntaxError)
          end
        end
      end
      
      describe "Example state" do
        before do
          @policy.feature("Feature", "Hello", 1)
          @policy.scenario_outline("Scenario Outline", "Desc", 2)
          @policy.step("Given", "a <foo>", 3)
          @policy.examples("Examples", "Some examples", 4)
        end
        
        it "should allow tables, examples, scenarios, scenario outlines, comments and tags" do
          [:table, :examples, :tag, :comment, :scenario, :scenario_outline].each do |event|
            lambda { @policy.send(event, event.to_s.capitalize, "Content", 5) }.should_not raise_error(FeatureSyntaxError) 
          end
        end
        
        it "should not allow steps, py_strings, features or backgrounds" do
          [:step, :py_string, :feature, :background].each do |event|
            lambda { @policy.send(event, event.to_s.capitalize, "Content", 5) }.should raise_error(FeatureSyntaxError)
          end
        end
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
        }.should raise_error(FeatureSyntaxError, "Syntax error on line 12: 'Scenario: My pet scenario'.")
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
