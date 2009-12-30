require 'gherkin/lexer'
require 'gherkin/i18n'

module Gherkin
  # The main entry point to lexing Gherkin source.
  class I18nLexer
    LANGUAGE_PATTERN = /language\s*:\s*(.*)/ #:nodoc:

    attr_reader :language

    def initialize(parser)
      @parser = parser
    end

    def scan(source)
      @language = lang(source) 
      delegate = Lexer[@language.key].new(@parser)
      delegate.scan(source)
    end

    private

    def lang(source)
      line_one = source.split(/\n/)[0]
      match = LANGUAGE_PATTERN.match(line_one)
      I18n.get(match ? match[1] : 'en')
    end
  end
end
