module Gherkin
  module Lexer
    I18nLexerNotFound = Class.new(LoadError)
    LexingError = Class.new(StandardError)

    class << self
      def [](i18n_lang)
        begin
          # Uncomment the line below (during development) to force use of Ruby lexer
          # return rb[i18n_lang]

          if defined?(JRUBY_VERSION)
            java[i18n_lang]
          else
            begin
              c[i18n_lang]
            rescue NameError => e
              warn("WARNING: #{e.message}. Reverting to Ruby lexer")
              rb[i18n_lang]
            rescue LoadError
              rb[i18n_lang]
            end
          end
        rescue LoadError => e
          raise I18nLexerNotFound, "No lexer was found for #{i18n_lang} (#{e.message}). Supported languages are listed in gherkin/i18n.yml."
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
  end
end
