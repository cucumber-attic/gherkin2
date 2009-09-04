module Gherkin
  class Listener
    attr_reader :table
    
    def table(rows)
      @table = rows
    end
  end
end