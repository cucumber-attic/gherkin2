require 'gherkin/native'
require 'gherkin/formatter/hashable'

module Gherkin
  module Formatter
    module Model
      class BasicStatement < Hashable
        attr_reader :comments, :keyword, :name, :line
        
        def initialize(comments, keyword, name, line)
          @comments, @keyword, @name, @line = comments, keyword, name, line
        end

        def line_range
          first = @comments.any? ? @comments[0].line : first_non_comment_line
          first..line
        end

        def first_non_comment_line
          @line
        end
      end

      class DescribedStatement < BasicStatement
        attr_reader :description

        def initialize(comments, keyword, name, description, line)
          super(comments, keyword, name, line)
          @description = description
        end
      end

      class TagStatement < DescribedStatement
        attr_reader :tags

        def initialize(comments, tags, keyword, name, description, line)
          super(comments, keyword, name, description, line)
          @tags = tags
        end

        def first_non_comment_line
          @tags.any? ? @tags[0].line : @line
        end
      end

      class Feature < TagStatement
        native_impl('gherkin')

        def initialize(comments, tags, keyword, name, description, line)
          super(comments, tags, keyword, name, description, line)
        end

        def replay(formatter)
          formatter.feature(self)
        end
      end

      class Background < DescribedStatement
        native_impl('gherkin')

        def initialize(comments, keyword, name, description, line)
          super(comments, keyword, name, description, line)
          @type = "background"
        end

        def replay(formatter)
          formatter.background(self)
        end
      end

      class Scenario < TagStatement
        native_impl('gherkin')

        def initialize(comments, tags, keyword, name, description, line)
          super(comments, tags, keyword, name, description, line)
          @type = "scenario"
        end

        def replay(formatter)
          formatter.scenario(self)
        end
      end

      class ScenarioOutline < TagStatement
        native_impl('gherkin')

        def initialize(comments, tags, keyword, name, description, line)
          super(comments, tags, keyword, name, description, line)
          @type = "scenario_outline"
        end

        def replay(formatter)
          formatter.scenario_outline(self)
        end
      end

      class Examples < TagStatement
        native_impl('gherkin')

        attr_accessor :rows # needs to remain mutable for filters

        def initialize(comments, tags, keyword, name, description, line, rows)
          super(comments, tags, keyword, name, description, line)
          @rows = rows
        end

        def replay(formatter)
          formatter.examples(self)
        end
        
        class Builder
          def initialize(*args)
            @args = *args
            @rows = nil
          end
        
          def row(comments, cells, line)
            @rows ||= []
            @rows << ExamplesTableRow.new(comments, cells, line)
          end
        
          def replay(formatter)
            build.replay(formatter)
          end
          
          def build
            Examples.new(*(@args << @rows))
          end
        end
      end

      class Step < BasicStatement
        native_impl('gherkin')

        attr_reader :rows, :doc_string
        
        def initialize(comments, keyword, name, line, rows, doc_string)
          super(comments, keyword, name, line)
          @rows, @doc_string = rows, doc_string
        end

        def line_range
          range = super
          if(rows)
            range = range.first..rows[-1].line
          elsif(doc_string)
            range = range.first..doc_string.line_range.last
          end
          range
        end

        def replay(formatter)
          formatter.step(self)
        end

        def outline_args
          offset = 0
          name.scan(/<[^<]*>/).map do |val|
            offset = name.index(val, offset)
            Argument.new(offset, val)
          end
        end
        
        class Builder
          def initialize(*args)
            @args = *args
            @rows = nil
            @doc_string = nil
          end
        
          def row(comments, cells, line)
            @rows ||= []
            @rows << DataTableRow.new(comments, cells, line)
          end
          
          def doc_string(string, content_type, line)
            @doc_string = Formatter::Model::DocString.new(string, content_type, line)
          end
        
          def replay(formatter)
            build.replay(formatter)
          end
          
          def build
            Step.new(*(@args << @rows << @doc_string))
          end
        end
      end

      class Comment < Hashable
        native_impl('gherkin')

        attr_reader :value, :line
        
        def initialize(value, line)
          @value, @line = value, line
        end
      end

      class Tag < Hashable
        native_impl('gherkin')

        attr_reader :name, :line
        
        def initialize(name, line)
          @name, @line = name, line
        end
        
        def eql?(tag)
          @name.eql?(tag.name)
        end

        def hash
          @name.hash
        end
      end

      class DocString < Hashable
        native_impl('gherkin')

        attr_reader :value, :content_type, :line
        
        def initialize(value, content_type, line)
          @value, @content_type, @line = value, content_type, line
        end

        def line_range
          line_count = value.split(/\r?\n/).length
          line..(line+line_count+1)
        end
      end

      class Row < Hashable
        attr_reader :comments, :cells, :line

        def initialize(comments, cells, line)
          @comments, @cells, @line = comments, cells, line
        end
      end

      class DataTableRow < Row
        native_impl('gherkin')
      end

      class ExamplesTableRow < Row
        native_impl('gherkin')
      end

      class Match < Hashable
        native_impl('gherkin')

        attr_reader :arguments, :location
        
        def initialize(arguments, location)
          @arguments, @location = arguments, location
        end

        def replay(formatter)
          formatter.match(self)
        end
      end

      class Result < Hashable
        native_impl('gherkin')

        attr_reader :status, :duration, :error_message
        
        def initialize(status, duration, error_message)
          @status, @duration, @error_message = status, duration, error_message
        end

        def replay(formatter)
          formatter.result(self)
        end
      end
    end
  end
end
