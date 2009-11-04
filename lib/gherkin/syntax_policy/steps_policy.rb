require 'gherkin/syntax_policy/steps_state'

module Gherkin
  module SyntaxPolicy
    class FeatureSyntaxError < SyntaxError
      attr_reader :event, :line, :expected

      def initialize(event, line, expected)
        @event, @line, @expected = event, line, expected
        super "Syntax error on line #{@line}. Found #{@event} when expecting one of: #{@expected.join(' ')}."
      end
    end

    class StepsPolicy
      attr_writer :raise_on_error

      def initialize(listener, raise_on_error=true)
        @listener, @raise_on_error = listener, raise_on_error
        @states = { :steps => StepsState.new }
        @current = @states[:steps]
      end

      def error(args)
        @raise_on_error ? raise(FeatureSyntaxError.new(args.first, args.last, @current.expected)) : @listener.syntax_error(*args)
      end

      def method_missing(meth, *args)
        @current.respond_to?(meth) ? dispatch(meth, *args) : super
      end

      private 

      def dispatch(event, *args)
        event_allowed?(event) ? @listener.send(event, *args) : error([event] + args)
      end

      def event_allowed?(event)
        @current.send(event)
      end
    end
  end
end