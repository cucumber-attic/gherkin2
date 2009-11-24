module Gherkin
  module Tools
    class PrettyPrinter
      def initialize(io)
        @io = io
      end

      def tag(name, line)
        @tags ||= []
        @tags << "@#{name}"
      end

      def comment(content, line)
        @io.puts content
      end

      def feature(keyword, name, line)
        tags = @tags ? @tags.join(' ') + "\n" : ''
        @tags = nil
        @io.puts "#{tags}#{keyword}: #{indent(name, '  ')}"
      end

      def background(keyword, name, line)
        @io.puts "\n  #{keyword}: #{indent(name, '    ')}"
      end

      def scenario(keyword, name, line)
        tags = @tags ? '  ' + @tags.join(' ') + "\n" : ''
        @tags = nil
        @io.puts "\n#{tags}  #{keyword}: #{indent(name, '    ')}"
      end

      def scenario_outline(keyword, name, line)
        tags = @tags ? '  ' + @tags.join(' ') + "\n" : ''
        @tags = nil
        @io.puts "\n#{tags}  #{keyword}: #{indent(name, '    ')}"
      end

      def examples(keyword, name, line)
        @io.puts "\n  #{keyword}: #{indent(name, '    ')}"
      end

      def step(keyword, name, line)
        @io.puts "    #{keyword} #{indent(name, '    ')}"
      end

      def table(rows, line)
        rows = rows.to_a.map {|row| row.to_a} if defined?(JRUBY_VERSION) # Convert ArrayList
        max_lengths =  rows.transpose.map { |col| col.map { |cell| cell.unpack("U*").length }.max }.flatten
        rows.each do |line|
          @io.puts '      | ' + line.zip(max_lengths).map { |cell, max_length| cell + ' ' * (max_length-cell.unpack("U*").length) }.join(' | ') + ' |'
        end
      end

      def py_string(string, line)
        @io.puts '      """'
        @io.puts string.gsub(/^/, '      ')
        @io.puts '      """'
      end

      def syntax_error(state, event, legal_events, line)
        raise "SYNTAX ERROR"
      end

    private

      def indent(string, indentation)
        indent = ""
        string.split(/\n/n).map do |l|
          s = "#{indent}#{l}"
          indent = indentation
          s
        end.join("\n")
      end
    end
  end
end
