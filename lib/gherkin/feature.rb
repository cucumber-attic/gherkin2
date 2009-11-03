require 'gherkin/feature_policy'
require 'forwardable'

module Gherkin
  # Parses the Cucumber Feature format
  #
  # Calls methods on the listener corresponding to events as they happen. All keywords are the
  # I18N names for the event ("Feature" for English, "Egenskap" for Norwegian, etc).
  #
  #   feature(keyword, content, line_number)
  #   background(keyword, content, line_number)
  #   scenario(keyword, content, line_number)
  #   scenario_outline(keyword, content, line_number)
  #   examples(keyword, content, line_number)
  #   step(keyword, content, line_number)
  #   comment(content, line_number)
  #   tag(tag_name, line_number)
  #   table(content_array, line_number)
  #   py_string(offset, content, line_number)
  #   
  # ParsingError will be raised if Gherkin cannot continue parsing input.
  #
  # SyntaxError will be raised if the text to scan is well formed, but syntactically incorrect:
  #
  #   Gherkin::Feature.new('it', Listener.new) raises FeatureSyntaxError on error
  #   Gherkin::Feature.new('en', AstBuilder.new, :raise_on_error => false) sends #syntax_error message to listener
  class Feature
    extend Forwardable

    def initialize(i18n_lang, listener, args={})
      args = { :raise_on_error => true }.merge(args)
      @policy = FeaturePolicy.new(listener, args[:raise_on_error])
      @parser = Parser[i18n_lang].new(@policy)
    end

    def_delegators :@parser, :scan
    def_delegators :@policy, :raise_on_error
  end
end
