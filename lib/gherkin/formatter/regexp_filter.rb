module Gherkin
  module Formatter
    class RegexpFilter
      def initialize(regexen)
        @regexen = regexen
      end

      def eval(names)
        @regexen.detect do |regexp| 
          names.detect do |name|
            name =~ regexp
          end
        end
      end
    end
  end
end