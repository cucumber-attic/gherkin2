module Gherkin
  class SexpRecorder
    def initialize
      @sexps = []
    end

    def method_missing(m, *args)
      @sexps << [m] + args
    end

    def to_sexp
      @sexps
    end

    # Useful in IRB
    def reset!
      @sexps = []
    end

    def syntax_errors
      @sexps.select { |sexp| sexp[0] == :syntax_error }
    end
  end
end
