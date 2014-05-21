require 'spec_helper'
require 'gherkin/formatter/model'
require 'gherkin/formatter/argument'

module Gherkin
  module Formatter
    module Model
      describe Tag do
        it "is equal when name is equal" do
          tags = [Tag.new('@x', 1), Tag.new('@y', 2), Tag.new('@x', 3)]
          expect(tags.to_a.uniq.length).to eq(2)
        end
      end

      describe Step do
        it "provides arguments for outline tokens" do
          step = Step.new([], 'Given ', "I have <number> cukes in <whose> belly", 10, nil, nil)
          expect(step.outline_args.map{|arg| [arg.offset, arg.val]}).to eq([[7, "<number>"], [25, "<whose>"]])
        end

        it "provides no arguments when there are no outline tokens" do
          step = Step.new([], 'Given ', "I have 33 cukes in my belly", 10, nil, nil)
          expect(step.outline_args.to_a).to eq([])
        end
      end
    end
  end
end
