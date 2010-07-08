require 'spec_helper'
require 'gherkin/formatter/struct'

module Gherkin
  module Formatter
    module Struct
      describe Tag do
        it "should be equal when name is equal" do
          tags = [Tag.new('@x', 1), Tag.new('@y', 2), Tag.new('@x', 3)]
          tags.uniq.length.should == 2
        end
      end
    end
  end
end