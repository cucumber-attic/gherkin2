module Gherkin
  module CLexer
    def self.[](i18n_underscored_iso_code)
      require "gherkin_lexer_#{i18n_underscored_iso_code}"
      const_get(i18n_underscored_iso_code.capitalize)
    end
  end
end
