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

      def feature(keyword, name, line)
        @formatter.feature(grab_comments!, grab_tags!, keyword, name)
      end

      def scenario(keyword, name, line)
        @formatter.scenario(grab_comments!, grab_tags!, keyword, name)
      end

      def step(keyword, name, line)
        @step = [grab_comments!, keyword, name]
      end

      def row(row, line)
        @rows ||= []
        @rows << row
      end

      def eof
        replay_step
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
        rows = @rows || []
        @rows = nil
        rows
      end

      def replay_step
        if(@step)
          @formatter.step(@step[0], @step[1], @step[2], grab_rows!)
        end
      end
    end
  end
end
