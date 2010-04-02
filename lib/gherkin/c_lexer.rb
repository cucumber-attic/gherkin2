module Gherkin
  module CLexer
    def self.[](i18n_language_name)
      name = i18n_language_name.gsub(/[\s-]/, '')
      require "gherkin_lexer_#{name}"
      i18n_lexer_class_name = name.capitalize
      const_get(i18n_lexer_class_name)
    end
  end
end
