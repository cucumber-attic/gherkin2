module Gherkin
  module Tools
    class PrettyPrinter
      def tag(name, line)
      end

      def comment(content, line)
        puts content
      end

      def feature(keyword, name, line)
        puts "#{keyword}: #{indent(name, '  ')}"
      end

      def background(keyword, name, line)
        puts "\n  #{keyword}: #{indent(name, '    ')}"
      end

      def scenario(keyword, name, line)
        puts "\n  #{keyword}: #{indent(name, '    ')}"
      end

      def scenario_outline(keyword, name, line)
        puts "\n  #{keyword}: #{indent(name, '    ')}"
      end

      def examples(keyword, name, line)
        puts "\n  #{keyword}: #{indent(name, '    ')}"
      end

      def step(keyword, name, line)
        puts "    #{keyword} #{indent(name, '    ')}"
      end

      def table(rows, line)
        # TODO: grab table pretty printing code from the Textmate bundle
      end

      def py_string(string, line)
        puts '      """'
        # TODO: the string should come in with leading spaces stripped, and we should re-indent it.
        # Also: we don't really need to know start_col.
        puts string
        puts '      """'
      end

      def syntax_error(state, event, legal_events, line)
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
