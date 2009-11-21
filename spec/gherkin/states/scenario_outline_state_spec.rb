require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require File.expand_path(File.dirname(__FILE__) + '/spec_helper')

# module Gherkin
#   module States
#     describe ScenarioOutlineState do
#       before do
#         @state = ScenarioOutlineState.new
#         @state.scenario_outline
#       end
#     
#       it_should_behave_like "a section containing steps"
#     
#       it "should allow step, comment, tag, scenario or scenario outline" do
#         [:step, :comment, :tag, :scenario, :scenario_outline].each do |event|
#           @state.expected.should include(event)
#           @state.should allow(event)
#         end
#       end
#   
#       it "should allow examples that follow a step" do
#         @state.step
#         @state.should allow(:examples)
#       end
#     
#       it "should not allow examples that do not follow a step" do
#         @state.should_not allow(:examples)
#       end
#   
#       it "should not allow feature or background" do
#         [:feature, :background].each do |event|
#           @state.expected.should_not include(event)
#           @state.should_not allow(event)
#         end
#       end
#     end  
#   end
# end
