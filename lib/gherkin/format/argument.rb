module Gherkin
  module Format
    class Argument
      attr_reader :byte_offset, :val
      
      def initialize(byte_offset, val)
        @byte_offset, @val = byte_offset, val
      end
      
      def self.format(string, args, &format)
        s = string.dup
        offset = past_offset = 0
        args.each do |arg|
          next if arg.byte_offset.nil? || arg.byte_offset < past_offset
          replacement = format.call(arg.val)
          s[arg.byte_offset + offset, arg.val.length] = replacement
          offset += replacement.unpack("U*").length - arg.val.unpack("U*").length
          past_offset = arg.byte_offset + arg.val.length
        end
        s
      end
    end
  end
end
