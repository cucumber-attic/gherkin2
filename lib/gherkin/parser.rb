require 'gherkin/parser/table'
require 'gherkin/parser/misc'

module Gherkin
  module Parser
    def self.[](lang)
      require "gherkin/parser/feature_#{lang}"
      Feature
    end
  end
end
