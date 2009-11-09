require 'gherkin_lexer'

module Gherkin
  module JavaLexer
    def self.[](i18n_language)
      i18n_lexer_class_name = i18n_language.gsub(/[\s-]/, '').capitalize
      Java::GherkinLexer.__send__(i18n_lexer_class_name)
    end
  end
end