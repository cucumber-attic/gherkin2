require 'spec_helper'

module Gherkin
  module Parser
    describe Parser do
      unless defined?(JRUBY_VERSION)
        it "raises when feature doesn't parse" do
          p = Parser.new(double('formatter').as_null_object)
          expect do
            p.parse("Feature: f\nFeature: f", __FILE__, __LINE__-1)
          end.to raise_error(Regexp.new("Parse error at #{__FILE__}:\\d+"))
        end
      end
    end
  end
end
