module Gherkin
  class RbParser
    # Initialize the parser. +machine_name+ refers to a state machine table.
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

    def event(ev, line)
      machine.event(ev, line) do |state, expected|
        if @raise_on_error
          raise ParseError.new(state, ev, expected, line)
        else
          @listener.syntax_error(state, ev, expected, line)
          return false
        end
      end
      true
    end

    def push_machine(name)
      @machines.push(Machine.new(self, name))
    end

    def pop_machine
      @machines.pop
    end

    def machine
      @machines[-1]
    end

    def expected
      machine.expected
    end

    def force_state(state)
      machine.instance_variable_set('@state', state)
    end

    class Machine
      def initialize(parser, name)
        @parser = parser
        @name = name
        @transition_map = transition_map(name)
        @state = name
      end

      def event(ev, line)
        states = @transition_map[@state]
        raise "Unknown state: #{@state.inspect} for machine #{@name}" if states.nil?
        new_state = states[ev]
        case new_state
        when "E"
          yield @state, expected
        when /push\((.+)\)/
          @parser.push_machine($1)
          @parser.event(ev, line)
        when "pop()"
          @parser.pop_machine()
          @parser.event(ev, line)
        else
          raise "Unknown transition: #{ev.inspect} among #{states.inspect} for machine #{@name}" if new_state.nil?
          @state = new_state
        end
      end

      def expected
        allowed = @transition_map[@state].find_all { |_, action| action != "E" }
        allowed.collect { |state| state[0] }.sort - ['eof']
      end

      private

      @@transition_maps = {}

      def transition_map(name)
        @@transition_maps[name] ||= build_transition_map(name)
      end

      def build_transition_map(name)
        table = transition_table(name)
        events = table.shift[1..-1]
        table.inject({}) do |machine, actions|
          state = actions.shift
          machine[state] = Hash[*events.zip(actions).flatten]
          machine
        end
      end

      def transition_table(name)
        state_machine_reader = StateMachineReader.new
        lexer = Gherkin::Lexer['en'].new(state_machine_reader)
        lexer.scan(File.read(File.dirname(__FILE__) + "/parser/#{name}.txt"))
        state_machine_reader.rows
      end

      class StateMachineReader
        attr_reader :rows

        def initialize
          @rows = []
        end

        def row(row, line_number)
          @rows << row
        end

        def eof
        end
      end

    end
  end
end
