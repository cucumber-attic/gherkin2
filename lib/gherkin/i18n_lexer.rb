require 'gherkin/lexer'

module Gherkin
  # The main entry point to lexing Gherkin source.
  class I18nLexer
    LANGUAGE_PATTERN = /language\s*:\s*(.*)/ #:nodoc:

    def initialize(parser)
      @parser = parser
    end

    def scan(source)
      lang = lang(source) || 'en'
      delegate = Lexer[lang].new(@parser)
      delegate.scan(source)
    end

    private

    def lang(source)
      line_one = source.split(/\n/)[0]
      if line_one =~ LANGUAGE_PATTERN
        $1.strip
      else
        nil
      end
    end
  end
end