module Gherkin
  module RbLexer
    def self.[](i18n_sanitized_key)
      require "gherkin/rb_lexer/#{i18n_sanitized_key}"
      const_get(i18n_sanitized_key.capitalize)
    end
  end
end