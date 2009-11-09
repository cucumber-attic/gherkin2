module Gherkin
  module Lexer
    I18nLexerNotFound = Class.new(LoadError)

    class ParsingError < StandardError
    end
    
    def self.[](i18n_language)
      begin
        require "gherkin/lexer/#{i18n_language}" unless i18n_language == 'C' # XXX HACK XXX: Make it easy to fetch the C lexer for the time being
      rescue LoadError
        raise I18nLexerNotFound, "No lexer was found for #{i18n_language}. Supported languages are listed in gherkin/i18n.yml."
      end

      i18n_lexer_class_name = i18n_language.gsub(/[\s-]/, '').capitalize
      lexer_class = const_get(i18n_lexer_class_name)
      lexer_class
    end
  end
end
