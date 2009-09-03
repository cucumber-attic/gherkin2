module Gherkin
  module Parser
    extend self
    
    def parse_table(text, builder)
      listener = Listener.new
      parser = Table.new
      parser.scan(text, listener)
      builder.build(listener.table)
    end    
  end
end