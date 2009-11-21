require 'gherkin_lexer'

module Gherkin
  module CLexer
    def self.[](i18n_language)
      i18n_lexer_class_name = i18n_language.gsub(/[\s-]/, '').capitalize
      const_get(i18n_lexer_class_name)
    end
  end
end
