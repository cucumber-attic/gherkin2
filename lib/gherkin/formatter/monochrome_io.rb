require 'gherkin/formatter/colors'
require 'gherkin/native'

module Gherkin
  module Formatter
    class MonochromeIO
      native_impl('gherkin')

      include Colors

      def initialize(io)
        @io = io
      end

      def write(*a)
        @io.write(*(a.map{|e| monochrome(e)}))
      end

      def puts(*a)
        @io.puts(*(a.map{|e| monochrome(e)}))
      end

      def string
        @io.string
      end
    end
  end
end