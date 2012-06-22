require 'v8'

class Class
  def native_impl(lib)
    js = {
      'Gherkin::Formatter::JSONFormatter' => 'js/lib/gherkin/formatter/json_formatter.js'
    }[self.name]

    if (js)
      class << self
        def new(*args)
          cxt = V8::Context.new
          cxt['exports'] = {}

          # Mimic Node.js / Firebug console.log
          cxt['console'] = STDOUT
          def STDOUT.log(*a)
            puts a.join(' ')
          end

          cxt.load('js/lib/gherkin/formatter/json_formatter.js')
          cxt['exports'].new(*args)
        end
      end
    end
  end
end
