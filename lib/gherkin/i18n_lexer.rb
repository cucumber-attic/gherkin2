require 'gherkin/i18n'

module Gherkin
  I18nLexerNotFound = Class.new(LoadError)
  LexingError = Class.new(StandardError)

  # The main entry point to lexing Gherkin source.
  class I18nLexer
    LANGUAGE_PATTERN = /language\s*:\s*(.*)/ #:nodoc:

    attr_reader :i18n_language

    class << self
      def new(listener, force_ruby)
        if !force_ruby && defined?(JRUBY_VERSION)
          require 'gherkin.jar'
          Java::Gherkin::I18nLexer.new(listener)
        else
          super
        end
      end
    end

    def initialize(listener, force_ruby)
      @listener = listener
      @force_ruby = force_ruby
    end

    def scan(source)
      create_delegate(source).scan(source)
    end

  private

    def create_delegate(source)
      @i18n_language = lang(source)
      @i18n_language.lexer(@listener, @force_ruby)
    end

    def lang(source)
      line_one = source.split(/\n/)[0]
      match = LANGUAGE_PATTERN.match(line_one)
      I18n.get(match ? match[1] : 'en')
    end

  end
end
