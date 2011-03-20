require 'v8'

module Gherkin
  class JsLexer
    def initialize(listener, lang='en')
      @cxt = V8::Context.new
      @cxt['exports'] = {}

      # Mimic Node.js / Firebug console.log
      @cxt['console'] = STDOUT
      def STDOUT.log(*a)
        p a
      end

      @cxt.load(File.dirname(__FILE__) + "/../../js/lib/gherkin/lexer/i18n/#{lang}.js")

      @lexer = @cxt['exports']['Lexer'].new(listener)
    end

    def scan(gherkin)
      @lexer['scan'].methodcall(@lexer, gherkin)
    end
  end
end