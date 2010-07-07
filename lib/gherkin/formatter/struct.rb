module Gherkin
  module Formatter
    module Struct
      class Comment
        attr_reader :value, :line
        
        def initialize(value, line)
          @value, @line = value, line
        end
      end
    end
  end
end