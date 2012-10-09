module Gherkin
  module Lexer
    class Encoding
      native_impl('gherkin')

      COMMENT_OR_EMPTY_LINE_PATTERN = /^\s*#|^\s*$/
      ENCODING_PATTERN = /^\s*#\s*encoding\s*:\s*([0-9a-zA-Z\-]+)/i #:nodoc:

      def read_file(path)
        source = File.new(path).read
        enc = encoding(source)
        if(enc != 'UTF-8')
          # Read it again with different encoding
          source = File.new(path, "r:#{enc}:UTF-8").read
        end
        source
      end

    private

      def encoding(source)
        encoding = 'UTF-8'
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