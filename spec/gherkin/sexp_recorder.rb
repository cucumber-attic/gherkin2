module Gherkin
  class SexpRecorder
    def initialize
      @sexps = []
    end

    def method_missing(m, *args)
      args[0] = args[0].map{|row| row.map{|cell| cell}} if m == :table
      @sexps << [m] + args
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
