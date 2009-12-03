require 'stringio'
require 'gherkin/tools/files'
require 'gherkin/tools/pretty_listener'

module Gherkin
  module Tools
    class Reformat < Files
      def run
        each do |file|
          purdy = StringIO.new
          listener = PrettyListener.new(purdy)
          scan(file, listener)
          purdy.rewind
          File.open(file, 'w') {|io| io.write(purdy.read)}
        end
      end
    end
  end
end