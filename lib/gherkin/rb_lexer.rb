module Gherkin
  module RbLexer
    def self.[](i18n_language)
      require "gherkin/rb_lexer/#{i18n_language}"
      i18n_lexer_class_name = i18n_language.gsub(/[\s-]/, '').capitalize
      const_get(i18n_lexer_class_name)
    end
  end
end