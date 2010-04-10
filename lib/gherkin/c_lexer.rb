module Gherkin
  module CLexer
    def self.[](i18n_sanitized_key)
      require "gherkin_lexer_#{i18n_sanitized_key}"
      const_get(i18n_sanitized_key.capitalize)
    end
  end
end
