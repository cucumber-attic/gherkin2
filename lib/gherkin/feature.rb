require 'gherkin/syntax_policy/feature_policy'
require 'forwardable'

module Gherkin
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
