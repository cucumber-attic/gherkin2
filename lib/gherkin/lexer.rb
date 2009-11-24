module Gherkin
  class Lexer
    LANGUAGE_PATTERN = /language\s*:\s*(.*)/ #:nodoc:
    I18nLexerNotFound = Class.new(LoadError)
    LexingError = Class.new(StandardError)

    class << self
      def [](i18n_lang)
        begin
          if defined?(JRUBY_VERSION)
            java[i18n_lang]
          else
            begin
              c[i18n_lang]
            rescue NameError => e
              warn("WARNING: #{e.message}")
              rb[i18n_lang]
            rescue LoadError
              rb[i18n_lang]
            end
          end
        rescue LoadError => e
          raise e
          raise I18nLexerNotFound, "No lexer was found for #{i18n_lang}. Supported languages are listed in gherkin/i18n.yml."
        end
      end

      def c
        require 'gherkin/c_lexer'
        CLexer
      end

      def java
        require 'gherkin/java_lexer'
        JavaLexer
      end

      def rb
        require 'gherkin/rb_lexer'
        RbLexer
      end
    end

    def initialize(parser)
      @parser = parser
    end

    def scan(source)
      delegate = self.class[lang(source) || 'en'].new(@parser)
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
