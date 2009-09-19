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
  end
end