require 'gherkin/syntax_policy/feature_policy'

module Gherkin
  class Feature
    def initialize(i18n_lang, listener, args={})
      raise_on_error = args[:raise_on_error] if args.has_key?(:raise_on_error)
      policy = SyntaxPolicy::FeaturePolicy.new(listener, raise_on_error)
      @parser = Parser[i18n_lang].new(policy)
    end

    def scan(text)
      @parser.scan(text)
    end
  end
end
