require 'gherkin'

module Gherkin
  module Tools
    # Base class for file based operations
    class Files
      include Enumerable

      def initialize(paths)
        raise "Please specify one or more paths" if paths.empty?
        @paths = paths
      end

      def each(&proc)
        globs = @paths.map do |path|
          raise "#{path} does not exist" unless File.exist?(path)
          File.directory?(path) ? File.join(path, '**', '*.feature') : path
        end

        Dir[*globs].uniq.sort.each(&proc)
      end

      def scan(file, formatter)
        parser = Gherkin::Parser::Parser.new(formatter, true, "root")
        begin
          parser.parse(IO.read(file), file, 0)
        rescue => e
          e.message << " (#{file})"
          raise e
        end
      end
    end
  end
end
