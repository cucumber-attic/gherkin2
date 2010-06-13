module Gherkin
  module Parser
    # Adapter from the "raw" Gherkin <tt>Listener</tt> API
    # to the slightly more high-level <tt>Formatter</tt> API. 
    class FormatterListener
      def initialize(formatter)
        @formatter = formatter
      end

      def comment(content, line)
        @comments ||= []
        @comments << content
      end

      def tag(name, line)
        @tags ||= []
        @tags << name
      end

      def feature(keyword, name, line, uri=nil)
        @formatter.feature(grab_comments!, grab_tags!, keyword, name, uri)
      end

      def background(keyword, name, line)
        @formatter.background(grab_comments!, grab_tags!, keyword, name, line)
      end

      def scenario(keyword, name, line)
        replay_step_or_examples
        @formatter.scenario(grab_comments!, grab_tags!, keyword, name, line)
      end

      def scenario_outline(keyword, name, line)
        replay_step_or_examples
        @formatter.scenario_outline(grab_comments!, grab_tags!, keyword, name, line)
      end

      def examples(keyword, name, line)
        replay_step_or_examples
        @examples = [grab_comments!, grab_tags!, keyword, name, line]
      end

      def step(keyword, name, line)
        replay_step_or_examples
        @step = [grab_comments!, keyword, name, line]
      end

      def row(row, line)
        @rows ||= []
        @rows << row
      end

      def py_string(py_string, line)
        @py_string = py_string
      end

      def eof
        replay_step_or_examples
        @formatter.eof
      end

    private

      def grab_comments!
        comments = @comments || []
        @comments = nil
        comments
      end

      def grab_tags!
        tags = @tags || []
        @tags = nil
        tags
      end

      def grab_rows!
        rows = @rows
        @rows = nil
        rows
      end

      def grab_py_string!
        py_string = @py_string
        @py_string = nil
        py_string
      end

      def replay_step_or_examples
        if(@step)
          multiline_arg = grab_py_string! || grab_rows!
          @formatter.step(*(@step + [multiline_arg]))
          @step = nil
        end
        if(@examples)
          table = grab_rows!
          @formatter.examples(*(@examples + [table]))
          @examples = nil
        end
      end
    end
  end
end
