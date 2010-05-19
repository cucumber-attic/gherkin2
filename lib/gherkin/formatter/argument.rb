module Gherkin
  module Formatter
    class Argument
      require 'gherkin/java_impl'
      java_impl('gherkin.jar')
      require 'gherkin/ikvm_impl'
      ikvm_impl('gherkin')

      attr_reader :byte_offset, :val

      def initialize(byte_offset, val)
        @byte_offset, @val = byte_offset, val
      end
      
      def self.format(string, argument_format, arguments)
        s = string.dup
        offset = past_offset = 0
        arguments.each do |arg|
          next if arg.byte_offset.nil? || arg.byte_offset < past_offset
          replacement = argument_format.format_argument(arg.val)
          s[arg.byte_offset + offset, arg.val.length] = replacement
          offset += replacement.unpack("U*").length - arg.val.unpack("U*").length
          past_offset = arg.byte_offset + arg.val.length
        end
        s
      end
    end
  end
end
