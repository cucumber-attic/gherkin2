require 'v8'

cxt = V8::Context.new
cxt['exports'] = {}
cxt.load(File.dirname(__FILE__) + '/../lib/gherkin/lexer/i18n/en.js')

class Listener
  ['feature', 'background', 'scenario', 'scenario_outline', 'examples', 'step', 'py_string', 'row', 'end'].each do |m|
    define_method(m) do |*a|
      p [m] + a
    end
  end
end

lexer = cxt['exports']['Lexer'].new(Listener.new)
lexer['scan'].call("Feature:\n Hello")
