require 'v8'

class Class
  def native_impl(lib)
    class << self
      def new(*args)
        js = {
          # Add more mappings here if needed. The mappings are only used by test code.
          'Gherkin::Formatter::JSONFormatter' => 'js/lib/gherkin/formatter/json_formatter.js',
          'Gherkin::Lexer::En' => 'js/lib/gherkin/lexer/en.js'
        }[self.name]
        if(js)
          Proxy.new(js, *args)
        else
          super(*args)
        end
      end
    end
  end

  class Proxy
    def initialize(js, *args)
      cxt = V8::Context.new
      cxt['module'] = {}

      # Mimic Node.js / Firebug console.log
      cxt['console'] = STDOUT
      def STDOUT.log(*a)
        puts sprintf(*a.map(&:to_s))
      end

      cxt.load(js)
      @js_obj = cxt['module']['exports'].new(*args)
    end

    def method_missing(name, *args)
      a = args.map{|a| a.respond_to?(:to_hash) ? a.to_hash : a}
      @js_obj.__send__(name, *a)
    end
  end
end
