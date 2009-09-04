require 'gherkin/parser/feature'
require 'gherkin/parser/table'
require 'gherkin/parser/misc'

module Gherkin
  module Parser
    extend self
    
    def parse_table(text, builder)
      parser = Table.new(builder)
      parser.scan(text)
      builder
    end    
  end
end
