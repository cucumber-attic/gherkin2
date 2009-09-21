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
  end
end
