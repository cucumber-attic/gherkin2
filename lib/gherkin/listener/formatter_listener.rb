require 'gherkin/native'
require 'gherkin/listener/row'

module Gherkin
  module Listener
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

      def location(uri, offset)
        @uri = uri
        @offset = offset
      end

      def comment(content, line)
        @comments << content
      end

      def tag(name, line)
        @tags << name
      end

      def feature(keyword, name, description, line)
        @formatter.feature(grab_comments!, grab_tags!, keyword, name, description, @uri)
      end

      def background(keyword, name, description, line)
        @formatter.background(grab_comments!, keyword, name, description, line)
      end

      def scenario(keyword, name, description, line)
        replay_step_or_examples
        @formatter.scenario(grab_comments!, grab_tags!, keyword, name, description, line)
      end

      def scenario_outline(keyword, name, description, line)
        replay_step_or_examples
        @formatter.scenario_outline(grab_comments!, grab_tags!, keyword, name, description, line)
      end

      def examples(keyword, name, description, line)
        replay_step_or_examples
        @examples = [grab_comments!, grab_tags!, keyword, name, description, line]
      end

      def step(keyword, name, line)
        replay_step_or_examples
        @step = [grab_comments!, keyword, name, line]
      end

      def row(cells, line)
        @table ||= []
        @table << Row.new(cells, grab_comments!, line)
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
