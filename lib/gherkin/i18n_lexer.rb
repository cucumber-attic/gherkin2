require 'gherkin/i18n'

module Gherkin
  I18nLexerNotFound = Class.new(LoadError)
  LexingError = Class.new(StandardError)

  # The main entry point to lexing Gherkin source.
  class I18nLexer
    LANGUAGE_PATTERN = /language\s*:\s*(.*)/ #:nodoc:

    attr_reader :i18n_language

    class << self
      def new(parser, force_ruby)
        if !force_ruby && defined?(JRUBY_VERSION)
          require 'gherkin.jar'
          Java::Gherkin::I18nLexer.new(parser)
        else
          super
        end
      end

      def lexer_class(i18n_language_name, force_ruby)
        begin
          if force_ruby
            rb[i18n_language_name]
          else
            begin
              c[i18n_language_name]
            rescue NameError, LoadError => e
              puts e.message
              puts e.backtrace
              warn("WARNING: #{e.message}. Reverting to Ruby lexer.")
              rb[i18n_language_name]
            end
          end
        rescue LoadError => e
          raise I18nLexerNotFound, "No lexer was found for #{i18n_language_name} (#{e.message}). Supported languages are listed in gherkin/i18n.yml."
        end
      end

      def i18n_language(source)
        line_one = source.split(/\n/)[0]
        match = LANGUAGE_PATTERN.match(line_one)
        I18n.get(match ? match[1] : 'en')
      end

      def c
        require 'gherkin/c_lexer'
        CLexer
      end

      def rb
        require 'gherkin/rb_lexer'
        RbLexer
      end
    end

    def initialize(parser, force_ruby)
      @parser = parser
      @force_ruby = force_ruby
    end

    def scan(source)
      @i18n_language = self.class.i18n_language(source) 
      delegate = self.class.lexer_class(@i18n_language.key, @force_ruby).new(@parser)
      delegate.scan(source)
    end
  end
end
