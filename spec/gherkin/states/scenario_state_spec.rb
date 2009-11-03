require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require File.expand_path(File.dirname(__FILE__) + '/spec_helper')

module Gherkin
  module States
    describe ScenarioState do
      before do
        @state = ScenarioState.new
        @state.scenario
      end
      
      it_should_behave_like "a section containing steps"
      
      it "should allow step, comment and tag" do
        [:step, :comment, :tag].each do |event|
          @state.expected.should include(event)
          @state.should allow(event)
        end
      end
      
      it "should not allow feature, background or examples" do
        [:feature, :background, :examples].each do |event|
          @state.expected.should_not include(event)
          @state.should_not allow(event)
        end
      end
    end
  end
end
