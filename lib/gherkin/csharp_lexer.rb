require 'Gherkin.dll'

module Gherkin
  module CSharpLexer
    def self.[](i18n_language)
      i18n_lexer_class_name = i18n_language.gsub(/[\s-]/, '').capitalize
      puts "In C# lexer: #{i18n_lexer_class_name}"
      Gherkin::ILexer
      puts "OK1"
      l = Gherkin::Lexer.__send__(i18n_lexer_class_name)
      puts "OK2"
      l
    end
  end
end