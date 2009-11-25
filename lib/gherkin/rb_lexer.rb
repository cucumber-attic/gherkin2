module Gherkin
  module RbLexer
    def self.[](i18n_language)
      name = i18n_language.gsub(/[\s-]/, '')
      require "gherkin/rb_lexer/#{name}"
      i18n_lexer_class_name = name.capitalize
      const_get(i18n_lexer_class_name)
    end
  end
end