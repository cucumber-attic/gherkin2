require 'gherkin/native'

module Gherkin
  module Formatter
    module Model
      class Comment
        native_impl('gherkin')

        attr_reader :value, :line
        
        def initialize(value, line)
          @value, @line = value, line
        end
      end

      class Tag
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

      class PyString
        native_impl('gherkin')

        attr_reader :value, :line
        
        def initialize(value, line)
          @value, @line = value, line
        end

        def line_range
          line_count = value.split(/\r?\n/).length
          line..(line+line_count+1)
        end
      end

      class Row
        native_impl('gherkin')

        attr_reader :comments, :cells, :line

        def initialize(comments, cells, line)
          @comments, @cells, @line = comments, cells, line
        end
      end

      class Statement
        native_impl('gherkin')

        attr_reader :comments, :tags, :keyword, :name, :description, :line
        
        def initialize(comments, tags, keyword, name, description, line)
          @comments, @tags, @keyword, @name, @description, @line = comments, tags, keyword, name, description, line
        end

        def line_range
          first = @comments[0] ? @comments[0].line : (@tags[0] ? @tags[0].line : line)
          first..line
        end
      end

      class Result
        native_impl('gherkin')

        attr_reader :status, :error_message, :arguments, :stepdef_location
        
        def initialize(status, error_message, arguments, stepdef_location)
          @status, @error_message, @arguments, @stepdef_location = status, error_message, arguments, stepdef_location
        end
      end
    end
  end
end