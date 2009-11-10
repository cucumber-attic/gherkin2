module Gherkin
  module Lexer
    I18nLexerNotFound = Class.new(LoadError)

    class ParsingError < StandardError
    end
    
    def self.[](i18n_language)
      # HACK HACK
      # We need to use a different factory instead - outside
      return defined?(JRUBY_VERSION) ? Gherkin::JavaLexer['en'] : Gherkin::Lexer::CLexer if i18n_language == "Native"

      begin
        require "gherkin/lexer/#{i18n_language}"
      rescue LoadError
        raise I18nLexerNotFound, "No lexer was found for #{i18n_language}. Supported languages are listed in gherkin/i18n.yml."
      end

      i18n_lexer_class_name = i18n_language.gsub(/[\s-]/, '').capitalize
      lexer_class = const_get(i18n_lexer_class_name)
      lexer_class
    end
  end
end
