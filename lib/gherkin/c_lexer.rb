module Gherkin
  module CLexer
    def self.[](i18n_language)
      name = i18n_language.gsub(/[\s-]/, '')
      require "gherkin_lexer_#{name}"
      i18n_lexer_class_name = name.capitalize
      const_get(i18n_lexer_class_name)
    end
  end
end
