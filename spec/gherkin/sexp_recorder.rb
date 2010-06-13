require 'gherkin/rubify'

module Gherkin
  class SexpRecorder
    include Rubify
    
    def initialize
      @sexps = []
    end

    def method_missing(event, *args)
      event = :scenario_outline if event == :scenarioOutline # Special Java Lexer handling
      event = :py_string if event == :pyString # Special Java Lexer handling
      event = :syntax_error if event == :syntaxError # Special Java Lexer handling
      args  = rubify(args)
      @sexps << [event] + args
    end

    def to_sexp
      @sexps
    end

    # Useful in IRB
    def reset!
      @sexps = []
    end

    def errors
      @sexps.select { |sexp| sexp[0] == :syntax_error }
    end

    def line(number)
      @sexps.find { |sexp| sexp.last == number }
    end
  end
end
