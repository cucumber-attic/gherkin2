module Gherkin
  class ParseError < StandardError
    def initialize(state, new_state, expected_states, line)
      super("Parse error on line #{line}. Found #{new_state} when expecting one of: #{expected_states.join(', ')}. (Current state: #{state}).")
    end
  end

  class Parser
    def self.new(listener, raise_on_error=false, machine_name='root')
      if defined?(JRUBY_VERSION)
        require 'gherkin.jar'
        Java::Gherkin::Parser.new(listener, raise_on_error, machine_name)
      else
        require 'gherkin/rb_parser'
        Gherkin::RbParser.new(listener, raise_on_error, machine_name)
      end
    end
  end
end