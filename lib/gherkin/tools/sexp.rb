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
      
      def filter_match?(filters)
        line_match?(filters[:lines] || []) ||
        name_match?(filters[:name_regexen] || []) ||
        tag_match?(filters[:tags] || [])
      end

      def replay(listener)
        listener.__send__(event, *args)
      end

    private

      def line_match?(lines)
        lines.include?(line)
      end

      def name_match?(name_regexen)
        return false unless [:feature, :scenario, :scenario_outline, :examples].include?(event)
        name_regexen.detect{|name_regex| name =~ name_regex}
      end

      def tag_match?(tags)
        return false unless :tag == event
        tags.detect{|tag| tag == tag_value}
      end

      def tag_value
        self[1]
      end

      def name
        self[2]
      end

      def line
        self[-1]
      end

      def args
        self[1..-1]
      end
    end
  end
end