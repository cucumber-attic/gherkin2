# encoding: utf-8
require 'gherkin/formatter/colors'
require 'gherkin/formatter/monochrome_format'
require 'gherkin/formatter/argument'
require 'gherkin/formatter/escaping'
require 'gherkin/native'

module Gherkin
  module Formatter
    class PrettyFormatter
      native_impl('gherkin')

      include Colors
      include Escaping

      def initialize(io, monochrome)
        @io = io
        @monochrome = monochrome
        @format = MonochromeFormat.new #@monochrome ? MonochromeFormat.new : AnsiColorFormat.new
      end

      def feature(comments, tags, keyword, name, description, uri)
        @uri = uri
        print_comments(comments, '')
        print_tags(tags, '')
        @io.puts "#{keyword}: #{name}"
        print_description(description, '  ', false)
      end

      def background(comments, keyword, name, description, line)
        @io.puts
        print_comments(comments, '  ')
        @io.puts "  #{keyword}: #{name}#{indented_element_uri!(keyword, name, line)}"
        print_description(description, '    ')
      end

      def scenario(comments, tags, keyword, name, description, line)
        @io.puts
        print_comments(comments, '  ')
        print_tags(tags, '  ')
        @io.puts "  #{keyword}: #{name}#{indented_element_uri!(keyword, name, line)}"
        print_description(description, '    ')
      end

      def scenario_outline(comments, tags, keyword, name, description, line)
        scenario(comments, tags, keyword, name, description, line)
      end

      def examples(comments, tags, keyword, name, description, line, examples_table)
        @io.puts
        print_comments(comments, '    ')
        print_tags(tags, '    ')
        @io.puts "    #{keyword}: #{name}"
        print_description(description, '    ')
        table(examples_table)
      end

      def step(comments, keyword, name, line, multiline_arg, status, exception, arguments, stepdef_location)
        status_param = "#{status}_param" if status
        name = Gherkin::Formatter::Argument.format(name, @format, (arguments || [])) 

        step = "#{keyword}#{name}"
        step = self.__send__(status, step, @monochrome) if status

        print_comments(comments, '    ')
        @io.puts("    #{step}#{indented_step_location!(stepdef_location)}")
        case multiline_arg
        when String
          py_string(multiline_arg)
        when Array
          table(multiline_arg)
        when NilClass
        else
          raise "Bad multiline_arg: #{multiline_arg.inspect}"
        end
      end

      def syntax_error(state, event, legal_events, line)
        raise "SYNTAX ERROR"
      end

      def eof
      end

      # This method can be invoked before a #scenario, to ensure location arguments are aligned
      def steps(steps)
        @step_lengths = steps.map {|keyword, name| (keyword+name).unpack("U*").length}
        @max_step_length = @step_lengths.max
        @step_index = -1
      end

      def table(rows)
        cell_lengths = rows.map do |row| 
          row.cells.map do |cell| 
            escape_cell(cell).unpack("U*").length
          end
        end
        max_lengths = cell_lengths.transpose.map { |col_lengths| col_lengths.max }.flatten

        rows.each_with_index do |row, i|
          row.comments.each do |comment|
            @io.puts "      #{comment}"
          end
          j = -1
          @io.puts '      | ' + row.cells.zip(max_lengths).map { |cell, max_length|
            j += 1
            color(cell, nil, j) + ' ' * (max_length - cell_lengths[i][j])
          }.join(' | ') + ' |'
        end
      end

    private

      def py_string(string)
        @io.puts "      \"\"\"\n" + indent(string, '      ').gsub(/"""/,'\"\"\"') + "\n      \"\"\""
      end

      def exception(exception)
        exception_text = "#{exception.message} (#{exception.class})\n#{(exception.backtrace || []).join("\n")}".gsub(/^/, '      ')
        @io.puts(failed(exception_text, @monochrome))
      end

      def color(cell, statuses, col)
        if statuses
          self.__send__(statuses[col], escape_cell(cell), @monochrome) + (@monochrome ? '' : reset)
        else
          escape_cell(cell)
        end
      end

      if(RUBY_VERSION =~ /^1\.9/)
        START = /#{"^".encode('UTF-8')}/
      else
        START = /^/
      end

      def indent(string, indentation)
        string.gsub(START, indentation)
      end

      def print_tags(tags, indent)
        @io.write(tags.empty? ? '' : indent + tags.join(' ') + "\n")
      end

      def print_comments(comments, indent)
        @io.write(comments.empty? ? '' : indent + comments.join("\n#{indent}") + "\n")
      end

      def print_description(description, indent, newline=true)
        if description != ""
          @io.puts indent(description, indent)
          @io.puts if newline
        end
      end

      def indented_element_uri!(keyword, name, line)
        return '' if @max_step_length.nil?
        l = (keyword+name).unpack("U*").length
        @max_step_length = [@max_step_length, l].max
        indent = @max_step_length - l
        ' ' * indent + ' ' + comments("# #{@uri}:#{line}", @monochrome)
      end

      def indented_step_location!(location)
        return '' if location.nil?
        indent = @max_step_length - @step_lengths[@step_index+=1]
        ' ' * indent + ' ' + comments("# #{location}", @monochrome)
      end
    end
  end
end