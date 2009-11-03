require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require File.expand_path(File.dirname(__FILE__) + '/spec_helper')

module Gherkin
  module States
    describe FeatureState do
      before do
        @state = FeatureState.new
      end
      
      context "before keyword" do      
        it "should allow tags, comments and feature" do
          [:comment, :tag, :feature].each do |event|
            @state.expected.should include(event)
            @state.should allow(event)
          end
        end
              
        it "should not allow anything else" do
          [:background, :scenario, :scenario_outline, :examples, :step, :table, :py_string].each do |event|
            @state.expected.should_not include(event)
            @state.should_not allow(event)
          end
        end
      end
      
      context "after keyword" do
        before do
          @state.feature
        end
        
        it "should allow background, scenario, scenario outline, tags and comments" do
          [:background, :scenario, :scenario_outline, :tag, :comment].each do |event|
            @state.expected.should include(event)
            @state.should allow(event)
          end
        end
      
        it "should not step, table, py_string or examples" do
          [:step, :table, :py_string, :examples].each do |event|
            @state.expected.should_not include(event)
            @state.should_not allow(event)
          end
        end
      end
      
      it "should not allow two features" do
        @state.feature
        @state.should_not allow(:feature)
      end

      it "should not allow two backgrounds" do
        @state.feature
        @state.background
        @state.should_not allow(:background)
      end
      
      describe "Background state" do
        context "before keyword" do
          before(:each) do
            @state.feature
          end
          
          it "should allow background, scenario, or scenario outline" do
            [:background, :scenario, :scenario_outline].each do |event|
              @state.expected.should include(event)
              @state.should allow(event)
            end
          end          
        end
        
        context "after keyword" do
          before do
            @state.feature
            @state.background
          end       
        
          it_should_behave_like "a section containing steps"
          
          it "should allows steps, scenarios, scenario outlines, comments and tags" do
            [:step, :scenario, :scenario_outline, :comment, :tag].each do |event|
              @state.expected.should include(event)
              @state.should allow(event)
            end
          end

          it "should not allow features or examples" do
            [:feature, :examples].each do |event|
              @state.expected.should_not include(event)
              @state.should_not allow(event)
            end
          end
        end
      end
    end
  end
end
