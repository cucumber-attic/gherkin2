require 'v8'

class Class
  def native_impl(lib)
    class << self
      js = {
        'Gherkin::Formatter::JSONFormatter' => 'js/lib/gherkin/formatter/json_formatter.js'
      }[self.name]

      if (js)
        def new(*args)
          Proxy.new(js, *args)
        end
      end
    end
  end

  class Proxy
    def initialize(js, *args)
      raise "No js" if js.nil?
      cxt = V8::Context.new
      cxt['exports'] = {}

      # Mimic Node.js / Firebug console.log
      cxt['console'] = STDOUT
      def STDOUT.log(*a)
        puts sprintf(*a)
      end

      cxt.load(js)
      @js_obj = cxt['exports'].new(*args)
    end

    def method_missing(name, *args)
      a = args.map{|a| a.respond_to?(:to_hash) ? a.to_hash : a}
      @js_obj.__send__(name, *a)
    end
  end
end
