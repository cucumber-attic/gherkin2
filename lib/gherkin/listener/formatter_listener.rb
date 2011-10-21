require 'gherkin/native'
require 'gherkin/formatter/model'

module Gherkin
  module Listener
    # Adapter from the "raw" Gherkin <tt>Listener</tt> API
    # to the slightly more high-level <tt>Formatter</tt> API,
    # which is easier to implement (less state to keep track of).
    class FormatterListener
      native_impl('gherkin')

      def initialize(formatter)
        @formatter = formatter
        @stash = Stash.new
      end

      def comment(value, line)
        @stash.comment Formatter::Model::Comment.new(value, line)
      end

      def tag(name, line)
        @stash.tag Formatter::Model::Tag.new(name, line)
      end

      def feature(keyword, name, description, line)
        @stash.tag_statement do |comments, tags|
          replay Formatter::Model::Feature.new(comments, tags, keyword, name, description, line)
        end
      end

      def background(keyword, name, description, line)
        @stash.tag_statement do |comments, tags|
          replay Formatter::Model::Background.new(comments, keyword, name, description, line)
        end
      end

      def scenario(keyword, name, description, line)
        replay_step_or_examples
        @stash.tag_statement do |comments, tags|
          replay Formatter::Model::Scenario.new(comments, tags, keyword, name, description, line)
        end
      end

      def scenario_outline(keyword, name, description, line)
        replay_step_or_examples
        @stash.tag_statement do |comments, tags|
          replay Formatter::Model::ScenarioOutline.new(comments, tags, keyword, name, description, line)
        end
      end

      def examples(keyword, name, description, line)
        replay_step_or_examples
        @stash.tag_statement do |comments, tags|
          @current_statement = Formatter::Model::Examples.new(comments, tags, keyword, name, description, line, [])
        end
      end

      def step(keyword, name, line)
        replay_step_or_examples
        @stash.basic_statement do |comments|
          @current_statement = Formatter::Model::Step.new(comments, keyword, name, line)
        end
      end

      def row(cells, line)
        @stash.basic_statement do |comments|
          @current_statement.rows ||= []
          @current_statement.rows << Formatter::Model::Row.new(comments, cells, line)
        end
      end

      def doc_string(string, content_type, line)
        @doc_string = Formatter::Model::DocString.new(string, content_type, line)
      end

      def eof
        replay_step_or_examples
        @formatter.eof
      end

      def syntax_error(state, ev, legal_events, uri, line)
        @formatter.syntax_error(state, ev, legal_events, uri, line)
      end

    private
    
      def replay(element)
        element.replay(@formatter)
      end
      
      class Stash
        attr_reader :comments, :tags
        
        def initialize
          @comments, @tags = [], []
        end
        
        def comment(comment)
          @comments << comment
        end
        
        def tag_statement
          yield @comments, @tags
          @comments, @tags = [], []
        end
        
        def basic_statement
          yield @comments
          @comments = []
        end
        
        def tag(tag)
          @tags << tag
        end
      end
      
      def grab_doc_string!
        doc_string = @doc_string
        @doc_string = nil
        doc_string
      end

      def replay_step_or_examples
        return unless @current_statement
        
        if @current_statement.respond_to?(:doc_string=)
          @current_statement.doc_string = grab_doc_string!
        end
        
        replay(@current_statement)
        @current_statement = nil
      end
    end
  end
end
