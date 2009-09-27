require 'gherkin/syntax_policy/feature_policy'
require 'forwardable'

module Gherkin
  # Parses the Cucumber Feature format
  # Sends events to the listener as they are received
  #   <insert event list here>
  # ParsingError will be raised if the parser itself cannot continue
  # Syntax error if the text to scan is well formed, but syntactically incorrect
  #   Gherkin::Feature.new('it', Listener.new) raises FeatureSyntaxError on error
  #   Gherkin::Feature.new('en', AstBuilder.new, :raise_on_error => false) sends #syntax_error message to listener
  class Feature
    extend Forwardable

    def initialize(i18n_lang, listener, args={})
      args = { :raise_on_error => true }.merge(args)
      @policy = SyntaxPolicy::FeaturePolicy.new(listener, args[:raise_on_error])
      @parser = Parser[i18n_lang].new(@policy)
    end

    def_delegators :@parser, :scan
    def_delegators :@policy, :raise_on_error
  end
end
