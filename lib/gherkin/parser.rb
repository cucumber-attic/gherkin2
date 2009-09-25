require 'gherkin/parser/table'

module Gherkin
  module Parser
    I18nParserNotFound = Class.new(LoadError)

    class ParsingError < StandardError
      attr_reader :line
      
      def initialize(line)
        @line = line
        super "Parsing error on line #{line}."
      end
    end
    
    def self.[](i18n_language)
      begin
        require "gherkin/parser/feature_#{i18n_language}"
      rescue LoadError
        raise I18nParserNotFound, "No parser was found for #{i18n_language}. Supported languages are listed in gherkin/i18n.yml."
      end

      i18n_parser_class_name = i18n_language.gsub(/[\s-]/, '').capitalize + "Feature"
      parser_class = const_get(i18n_parser_class_name)
      parser_class
    end
  end
end
