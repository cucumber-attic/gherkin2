module Gherkin
  module Lexer
    I18nLexerNotFound = Class.new(LoadError)
    LexingError = Class.new(StandardError)

    def self.[](i18n_lang)
      begin
        if i18n_lang == "Native"
          # HACK HACK - only support for English for now
          require 'gherkin/c_lexer'
          CLexer
        elsif defined?(JRUBY_VERSION)
          require 'gherkin/java_lexer'
          JavaLexer[i18n_lang]
        else
          require 'gherkin/rb_lexer'
          RbLexer[i18n_lang]
        end
      rescue LoadError
        raise I18nLexerNotFound, "No lexer was found for #{i18n_lang}. Supported languages are listed in gherkin/i18n.yml."
      end
    end
  end
end
