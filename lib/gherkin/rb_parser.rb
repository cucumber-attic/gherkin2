module Gherkin
  class RbParser
    class Machine
      class StateMachineReader
        attr_reader :rows
        def table(rows, line_number)
          @rows = rows
        end
      end

      def initialize(parser, name)
        @parser = parser
        @name = name
        @transitions = transitions(name)
        @state = name
      end

      def event(ev, line)
        states = @transitions[@state]
        raise "Unknown state: #{@state.inspect} for machine #{@name}" if states.nil?
        new_state = states[ev]
        case new_state
        when "E"
          yield @state, expected
        when /push\((.+)\)/
          @parser.push_machine($1)
          @parser.event(ev, line)
        when /pop\(\)/
          @parser.pop_machine()
          @parser.event(ev, line)
        else
          raise "Unknown transition: #{ev.inspect} among #{states.inspect} for machine #{@name}" if new_state.nil?
          @state = new_state
        end
      end

      private

      def transitions(name)
        table = state_table(name)
        events = table.shift[1..-1]
        table.inject({}) do |machine, actions|
          state = actions.shift
          machine[state] = Hash[*events.zip(actions).flatten]
          machine
        end
      end

      def state_table(name)
        state_machine_reader = StateMachineReader.new
        lexer = Gherkin::Lexer['en'].new(state_machine_reader)
        lexer.scan(File.read(File.dirname(__FILE__) + "/parser/#{name}.txt"))
        state_machine_reader.rows
      end

      def expected
        allowed = @transitions[@state].find_all { |_, action| action != "E" }
        allowed.collect { |state| state[0] }
      end
    end

    # Initialize the parser. +machine_names+ must be an array of at least one String,
    # where each string refers to a state machine table. The first one will be the
    # current machine, and from each machine it is possible to transition to another machine.
    def initialize(listener, raise_on_error, machine_name)
      @listener = listener
      @raise_on_error = raise_on_error
      @machines = []
      push_machine(machine_name)
    end

    # Doesn't yet fall back to super
    def method_missing(method, *args)
      # TODO: Catch exception and call super
      if(event(method.to_s, args[-1]))
        @listener.send(method, *args)
      end
    end

    def push_machine(name)
      machine = Machine.new(self, name)
      @machines.push(machine)
    end

    def pop_machine
      @machines.pop
    end

    def event(ev, line)
      @machines[-1].event(ev, line) do |state, expected|
        if @raise_on_error
          raise ParseError.new(state, ev, expected, line)
        else
          @listener.syntax_error(state, ev, expected, line)
          return false
        end
      end
      true
    end
  end
end