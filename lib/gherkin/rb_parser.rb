module Gherkin
  class RbParser
    class StateMachineReader
      attr_reader :rows
      def table(rows, line_number)
        @rows = rows
      end
    end

    # Initialize the parser. +machine_names+ must be an array of at least one String,
    # where each string refers to a state machine table. The first one will be the
    # current machine, and from each machine it is possible to transition to another machine.
    def initialize(listener, raise_on_error=true, machine_names=['root', 'comment'])
      @listener = listener
      @raise_on_error = raise_on_error

      @machines = {}
      machine_names.each do |machine_name|
        @machines[machine_name] = state_machine(machine_name)
      end
      set_machine(machine_names[0])
      @state = machine_names[0]
    end

    def state_machine(initial_state)
      table = state_table(initial_state)
      events = table.shift[1..-1]
      table.inject({}) do |machine, actions|
        state = actions.shift
        machine[state] = Hash[*events.zip(actions).flatten]
        machine
      end
    end

    def state_table(initial_state)
      state_machine_reader = StateMachineReader.new
      lexer = Gherkin::Lexer['en'].new(state_machine_reader)
      lexer.scan(File.read(File.dirname(__FILE__) + "/parser/#{initial_state}.txt"))
      state_machine_reader.rows
    end

    # Doesn't yet fall back to super
    def method_missing(meth, *args)
      meth = meth.to_s
      states = @machine[@state]
      raise "Unknown state: #{@state} for machine #{@machine_name}" if states.nil?
      new_state = states[meth]
      raise "Unknown state: #{new_state} for machine #{@machine_name}" if states.nil?
      case new_state
      when "E"
        if @raise_on_error
          raise ParseError.new(@state, meth, expected, args[-1])
        else
          return @listener.syntax_error(@state, meth, expected, args[-1])
        end
        @state = new_state
      when /machine\((.+)\)/
        set_machine($1)
        @state = $1
      end
      @listener.send(meth, *args)
    end

    def expected
      allowed = @machine[@state].find_all { |_, action| action != "E" }
      allowed.collect { |state| state[0] }
    end

    def set_machine(machine_name)
      @machine_name = machine_name
      @machine = @machines[machine_name]
      raise "No machine named #{machine_name}" if @machine.nil?
    end
  end
end