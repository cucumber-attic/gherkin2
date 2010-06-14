require 'gherkin/native'

module Gherkin
  module Parser
    class Row
      native_impl('gherkin')

      attr_reader :cells, :comments, :line
      
      def initialize(cells, comments, line)
        @cells, @comments, @line = cells, comments, line
      end
    end
  end
end