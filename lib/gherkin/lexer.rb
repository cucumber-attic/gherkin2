module Gherkin
  module Lexer

    class << self
      def [](i18n_lang)
        begin
          begin
            c[i18n_lang]
          rescue NameError, LoadError => e
            raise("WARNING: #{e.message}. Reverting to Ruby lexer.") unless defined?(@warned)
            @warned = true
            rb[i18n_lang]
          end
        rescue LoadError => e
          raise I18nLexerNotFound, "No lexer was found for #{i18n_lang} (#{e.message}). Supported languages are listed in gherkin/i18n.yml."
        end
      end

      def c
        require 'gherkin/c_lexer'
        CLexer
      end

      def csharp
        require 'gherkin/csharp_lexer'
        CSharpLexer
      end

      def rb
        require 'gherkin/rb_lexer'
        RbLexer
      end
    end
  end
end
