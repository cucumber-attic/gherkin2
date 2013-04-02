require 'spec_helper'

module Gherkin
  module Parser
    describe Parser do
      unless defined?(JRUBY_VERSION)
        it "should raise when feature doesn't parse" do
          p = Parser.new(mock('formatter').as_null_object)
          lambda do
            p.parse("Feature: f\nFeature: f", __FILE__, __LINE__-1)
          end.should raise_error(Regexp.new("Parse error at #{__FILE__}:\\d+"))
        end
      end
    end
  end
end
