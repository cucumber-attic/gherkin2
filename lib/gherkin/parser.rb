module Gherkin
  class ParseError < StandardError
    def initialize(state, new_state, expected_states, line)
      super("Parse error on line #{line}. Found #{new_state} when expecting one of: #{expected_states.join(', ')}. (Current state: #{state}).")
    end
  end

  class Parser
    def self.new(listener, raise_on_error=false, machine_names='root')
      if defined?(JRUBY_VERSION)
        Java::Gherkin::Parser.new(listener, raise_on_error, machine_names)
      else
        require 'gherkin/rb_parser'
        Gherkin::RbParser.new(listener, raise_on_error, machine_names)
      end
    end
  end
end