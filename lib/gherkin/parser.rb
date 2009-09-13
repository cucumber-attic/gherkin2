require 'gherkin/parser/table'
require 'gherkin/parser/misc'

module Gherkin
  module Parser
    def self.[](i18n_language)
      require "gherkin/parser/feature_#{i18n_language}"
      i18n_parser_class_name = i18n_language.gsub(/[\s-]/, '').capitalize + "Feature"
      parser_class = const_get(i18n_parser_class_name)
      parser_class
    end
  end
end
