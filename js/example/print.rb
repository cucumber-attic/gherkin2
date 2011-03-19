require 'v8'

module Gherkin
  class JSLexer
    def initialize(listener, lang='en')
      @cxt = V8::Context.new
      @cxt['exports'] = {}

      # Mimic Node.js / Firebug console.log
      @cxt['console'] = STDOUT
      def STDOUT.log(*a)
        puts(*a)
      end

      @cxt.load(File.dirname(__FILE__) + "/../lib/gherkin/lexer/i18n/#{lang}.js")

      @lexer = @cxt['exports']['Lexer'].new(listener)
    end

    def scan(gherkin)
      @lexer['scan'].call(gherkin)
    end
  end
end

class Listener
  ['feature', 'background', 'scenario', 'scenario_outline', 'examples', 'step', 'py_string', 'row', 'end'].each do |m|
    define_method(m) do |*a|
      p [m] + a
    end
  end
end

Gherkin::JSLexer.new(Listener.new).scan(IO.read(ARGV[0]))