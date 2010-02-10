# encoding: utf-8

require 'gherkin/tools/colors'

module Gherkin
  module Tools
    # TODO: Rename to Gherkin::Pretty::PrettyReporter - that's what this class *does*
    # (The fact that it conforms to the Gherkin Listener interface is secondary)
    class PrettyListener
      include Gherkin::Tools::Colors
      
      def initialize(io)
        @io = io
        @tags = nil
        @comments = nil
      end

      def tag(name, line)
        @tags ||= []
        @tags << "@#{name}"
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

      def scenario(keyword, name, line)
        indent_locations(keyword, name)
        @io.puts "\n#{grab_comments!('  ')}#{grab_tags!('  ')}  #{keyword}: #{indent(name, '    ')}#{indented_location!}"
      end

      def scenario_outline(keyword, name, line)
        @io.puts "\n#{grab_comments!('  ')}#{grab_tags!('  ')}  #{keyword}: #{indent(name, '    ')}"
      end

      def examples(keyword, name, line)
        @io.puts "\n#{grab_comments!('    ')}#{grab_tags!('    ')}    #{keyword}: #{indent(name, '    ')}"
      end

      def step(keyword, name, line, status=nil, arguments=nil)
        status_param = "#{status}_param" if status
        name = Gherkin::Format::Argument.format(name, arguments) {|arg| status_param ? self.__send__(status_param, arg) : arg} if arguments

        step = "#{keyword}#{indent(name, '    ')}"
        step = self.__send__(status, step) if status

        @io.puts("#{grab_comments!('    ')}    #{step}#{indented_location!}")
      end

      def table(rows, line, rows_to_print = rows, first_row=0, statuses=nil, exception=nil)
        rows = rows.to_a.map {|row| row.to_a} if defined?(JRUBY_VERSION) # Convert ArrayList
        cell_lengths = rows.map { |col| col.map { |cell| cell.unpack("U*").length }}
        max_lengths = cell_lengths.transpose.map { |col_lengths| col_lengths.max }.flatten
        rows_to_print.length.times do |n|
          row_to_print = rows_to_print[n]
          i = n + first_row
          j = -1
          @io.puts '      | ' + row_to_print.zip(max_lengths).map { |cell, max_length|
            j += 1
            color(cell, statuses, j) + ' ' * (max_length - cell_lengths[i][j])
          }.join(' | ') + ' |'
          if(exception)
            @io.puts(failed("#{exception.message} (#{exception.class})\n#{(exception.backtrace || []).join("\n")}".gsub(/^/, '      ')))
          end
        end
      end

      def py_string(string, line)
        @io.puts "      \"\"\"\n" + string.gsub(START, '      ') + "\n      \"\"\""
      end

      def syntax_error(state, event, legal_events, line)
        raise "SYNTAX ERROR"
      end

      # This method is not part of the Gherkin event API. If invoked before a #scenario,
      # location "comment" lines will be printed.
      def locations(locations)
        @locations = locations
      end

    private

      def color(cell, statuses, col)
        statuses ? self.__send__(statuses[col], cell) : cell
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

      def indent_locations(container_keyword, container_name)
        return if @locations.nil?
        @locations[0][0] = "#{container_keyword}: #{container_name}"
        @lengths = @locations.transpose[0].map {|line| line.unpack("U*").length}
        @lengths[0] -= 2
        @max_length = @lengths.max
        @indent_index = -1
      end

      def indented_location!
        return if @locations.nil?
        @indent_index += 1
        indent = @max_length - @lengths[@indent_index]
        ' ' * indent + ' # ' + @locations[@indent_index][1]
      end
    end
  end
end
