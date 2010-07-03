module Gherkin
  module Formatter
    class RegexpFilter
      def initialize(regexen)
        @regexen = regexen
      end

      def eval(name)
        @regexen.detect{|regexp| name =~ regexp}
      end
    end
  end
end