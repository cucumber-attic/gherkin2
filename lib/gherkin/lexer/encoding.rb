module Gherkin
  module Lexer
    class Encoding
      native_impl('gherkin')

      COMMENT_OR_EMPTY_LINE_PATTERN = /^\s*#|^\s*$/
      ENCODING_PATTERN = /^\s*#\s*encoding\s*:\s*([0-9a-zA-Z\-]+)/i #:nodoc:
      DEFAULT_ENCODING = 'UTF-8'

      def read_file(path)
        source = File.new(path).read
        enc = encoding(source)
        if(enc != DEFAULT_ENCODING)
          # Read it again with different encoding
          source = File.new(path, "r:#{enc}:#{DEFAULT_ENCODING}").read
          if source.respond_to?(:encode)
            source = source.encode(DEFAULT_ENCODING)
          else
            require 'iconv'
            source = Iconv.new(DEFAULT_ENCODING, enc).iconv(source)
          end
        end
        source
      end

    private

      def encoding(source)
        encoding = DEFAULT_ENCODING
        source.each_line do |line|
          break unless COMMENT_OR_EMPTY_LINE_PATTERN =~ line
          if ENCODING_PATTERN =~ line
            encoding = $1
            break
          end
        end
        encoding.upcase
      end
    end
  end
end