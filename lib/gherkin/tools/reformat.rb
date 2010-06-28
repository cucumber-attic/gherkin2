require 'stringio'
require 'gherkin/tools/files'
require 'gherkin/formatter/pretty_formatter'
require 'gherkin/listener/formatter_listener'

module Gherkin
  module Tools
    class Reformat < Files
      def run
        each do |file|
          io = defined?(JRUBY_VERSION) ? Java.java.io.StringWriter.new : StringIO.new
          formatter = Formatter::PrettyFormatter.new(io, true)
          listener = Listener::FormatterListener.new(formatter)
          scan(file, listener)
          string = defined?(JRUBY_VERSION) ? io.getBuffer.toString : io.string
          File.open(file, 'w') {|io| io.write(string)}
        end
      end
    end
  end
end
