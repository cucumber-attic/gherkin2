#encoding: utf-8
require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

module Gherkin
  module Parser
    describe "C Parser" do
      before do
        @listener = Gherkin::SexpRecorder.new
        @feature = Gherkin::Parser['C'].new(@listener)
      end

      def scan_file(file)
        @feature.scan(File.new(File.dirname(__FILE__) + "/gherkin_parser/" + file).read)
      end

      it_should_behave_like "a Gherkin parser"
      it_should_behave_like "a Gherkin parser parsing tags"
      it_should_behave_like "a Gherkin parser parsing py_strings"
      it_should_behave_like "a Gherkin parser parsing tables"
    end
  end
end
