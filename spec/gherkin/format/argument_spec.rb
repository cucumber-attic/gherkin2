# encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../../spec_helper')
require 'gherkin/format/argument'

module Gherkin
  module Format
    describe Argument do
      def bracket(s)
        "[#{s}]"
      end
      
      it "should replace one arg" do
        Argument.format("I have 10 cukes", [Argument.new(7, '10')], &method(:bracket).to_proc).should == "I have [10] cukes"
      end
      
      # TODO: Add this spec: http://github.com/alg/cucumber/commit/33188e9db51f59ced74c4861524d7b2e69454630
    end
  end
end
