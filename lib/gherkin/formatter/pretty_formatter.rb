# encoding: utf-8

require 'gherkin/formatter/colors'
require 'gherkin/formatter/monochrome_format'
require 'gherkin/formatter/argument'
require 'gherkin/formatter/escaping'

module Gherkin
  module Formatter
    class PrettyFormatter
      require 'gherkin/java_impl'
      java_impl('gherkin.jar')
      require 'gherkin/ikvm_impl'
      ikvm_impl('gherkin')

      include Colors
      include Escaping

      def initialize(io, monochrome=false)
        @io = io
        @monochrome = monochrome
        @format = MonochromeFormat.new #@monochrome ? MonochromeFormat.new : AnsiColorFormat.new
        @tags = nil
        @comments = nil
      end

      def tag(name, line)
        @tags ||= []
        @tags << name
      end

      def comment(content, line)
        @comments ||= []
        @comments << content
      end

      def feature(keyword, name, line)
        @io.puts "#{grab_comments!('')}#{grab_tags!('')}#{keyword}: #{indent(name, '  ')}"
      end

      def background(keyword, name, line)
        @io.puts "\n#{grab_comments!('  ')}  #{keyword}: #{indent(name, '    ')}"
      end

      def scenario(keyword, name, line, location=nil)
        flush_table
        @io.puts "\n#{grab_comments!('  ')}#{grab_tags!('  ')}  #{keyword}: #{indent(name, '    ')}#{indented_scenario_location!(keyword, name, location)}"
      end

      def scenario_outline(keyword, name, line)
        flush_table
        @io.puts "\n#{grab_comments!('  ')}#{grab_tags!('  ')}  #{keyword}: #{indent(name, '    ')}"
      end

      def examples(keyword, name, line)
        flush_table
        @io.puts "\n#{grab_comments!('    ')}#{grab_tags!('    ')}    #{keyword}: #{indent(name, '    ')}"
      end

      def step(keyword, name, line, status=nil, exception=nil, arguments=nil, location=nil)
        flush_table
        status_param = "#{status}_param" if status
        name = Gherkin::Formatter::Argument.format(name, @format, (arguments || [])) 
        #{|arg| status_param ? self.__send__(status_param, arg, @monochrome) : arg} if arguments

        step = "#{keyword}#{indent(name, '    ')}"
        step = self.__send__(status, step, @monochrome) if status

        @io.puts("#{grab_comments!('    ')}    #{step}#{indented_step_location!(location)}")
      end

      def row(row, line)
        @rows ||= []
        @rows << row.map{|cell| escape_cell(cell)}
      end

      def py_string(string, line)
        @io.puts "      \"\"\"\n" + string.gsub(START, '      ').gsub(/"""/,'\"\"\"') + "\n      \"\"\""
      end

      def syntax_error(state, event, legal_events, line)
        raise "SYNTAX ERROR"
      end

      def eof
        flush_table
      end

      # This method can be invoked before a #scenario, to ensure location arguments are aligned
      def steps(steps)
        @step_lengths = steps.map {|keyword, name| (keyword+name).unpack("U*").length}
        @max_step_length = @step_lengths.max
        @step_index = -1
      end

      def exception(exception)
        exception_text = "#{exception.message} (#{exception.class})\n#{(exception.backtrace || []).join("\n")}".gsub(/^/, '      ')
        @io.puts(failed(exception_text, @monochrome))
      end

      def flush_table(exception=nil, statuses=nil)
        return if @rows.nil?
        cell_lengths = @rows.map { |col| col.map { |cell| cell.unpack("U*").length }}
        max_lengths = cell_lengths.transpose.map { |col_lengths| col_lengths.max }.flatten

        @rows.each_with_index do |row, i|
          j = -1
          @io.puts '      | ' + row.zip(max_lengths).map { |cell, max_length|
            j += 1
            color(cell, statuses, j) + ' ' * (max_length - cell_lengths[i][j])
          }.join(' | ') + ' |'
          exception(exception) if exception
        end
        @rows = nil
      end

      private

      def color(cell, statuses, col)
        if statuses
          self.__send__(statuses[col], cell, @monochrome) + (@monochrome ? '' : reset)
        else
          cell
        end
      end

      if(RUBY_VERSION =~ /^1\.9/)
        START = /#{"^".encode('UTF-8')}/
        NL    = Regexp.new("\n".encode('UTF-8'))
      else
        START = /^/
        NL    = /\n/n
      end

      def indent(string, indentation)
        indent = ""
        string.split(NL).map do |l|
          s = "#{indent}#{l}"
          indent = indentation
          s
        end.join("\n")
      end

      def grab_tags!(indent)
        tags = @tags ? indent + @tags.join(' ') + "\n" : ''
        @tags = nil
        tags
      end

      def grab_comments!(indent)
        comments = @comments ? indent + @comments.join("\n#{indent}") + "\n" : ''
        @comments = nil
        comments
      end

      def indented_scenario_location!(keyword, name, location)
        return "" if location.nil?
        l = (keyword+name).unpack("U*").length
        @max_step_length = [@max_step_length, l].max
        indent = @max_step_length - l
        ' ' * indent + ' ' + comments("# #{location}", @monochrome)
      end

      def indented_step_location!(location)
        return "" if location.nil?
        indent = @max_step_length - @step_lengths[@step_index+=1]
        ' ' * indent + ' ' + comments("# #{location}", @monochrome)
      end
    end
  end
end
