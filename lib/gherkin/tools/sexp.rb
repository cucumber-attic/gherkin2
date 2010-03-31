module Gherkin
  module Tools
    class Sexp < Array
      def initialize(*args)
        super
        self[1] = self[1].to_a if event == :row # Special JRuby handling
      end
      
      def event
        self[0]
      end
      
      def line
        self[-1]
      end
    end
  end
end