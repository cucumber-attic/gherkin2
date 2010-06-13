require 'gherkin/native'

module Gherkin
  module Parser
    # Adapter from the "raw" Gherkin <tt>Listener</tt> API
    # to the slightly more high-level <tt>Formatter</tt> API,
    # which is easier to implement (less state to keep track of).
    class FormatterListener
      native_impl('gherkin')

      def initialize(formatter)
        @formatter = formatter
        @comments = []
        @tags = []
        @table = nil
      end

      def uri(uri)
        @uri = uri
      end

      def comment(content, line)
        @comments << content
      end

      def tag(name, line)
        @tags << name
      end

      def feature(keyword, name, line)
        @formatter.feature(grab_comments!, grab_tags!, keyword, name, @uri)
      end

      def background(keyword, name, line)
        @formatter.background(grab_comments!, keyword, name, line)
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
        @table ||= []
        @table << row
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
        comments = @comments
        @comments = []
        comments
      end

      def grab_tags!
        tags = @tags
        @tags = []
        tags
      end

      def grab_table!
        table = @table
        @table = nil
        table
      end

      def grab_py_string!
        py_string = @py_string
        @py_string = nil
        py_string
      end

      def replay_step_or_examples
        if(@step)
          multiline_arg = grab_py_string! || grab_table!
          @formatter.step(*(@step + [multiline_arg, nil, nil, nil, nil]))
          @step = nil
        end
        if(@examples)
          @formatter.examples(*(@examples + [grab_table!]))
          @examples = nil
        end
      end
    end
  end
end
