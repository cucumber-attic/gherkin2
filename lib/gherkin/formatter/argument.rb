module Gherkin
  module Formatter
    class Argument
      attr_reader :byte_offset, :val

      class << self
        def new(byte_offset, val)
          if defined?(JRUBY_VERSION)
            require 'gherkin.jar'
            Java::GherkinFormatter::Argument.new(byte_offset, val)
          else
            super
          end
        end
      end

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
