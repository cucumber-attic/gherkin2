module Gherkin
  class Listener
    attr_reader :table
    
    def table_found(rows)
      @table = rows
    end
  end
end