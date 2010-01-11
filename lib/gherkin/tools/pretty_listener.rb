# encoding: utf-8
module Gherkin
  module Tools
    class PrettyListener
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
        @io.puts "\n#{grab_comments!('  ')}#{grab_tags!('  ')}  #{keyword}: #{indent(name, '    ')}"
      end

      def scenario_outline(keyword, name, line)
        @io.puts "\n#{grab_comments!('  ')}#{grab_tags!('  ')}  #{keyword}: #{indent(name, '    ')}"
      end

      def examples(keyword, name, line)
        @io.puts "\n#{grab_comments!('    ')}#{grab_tags!('    ')}    #{keyword}: #{indent(name, '    ')}"
      end

      def step(keyword, name, line)
        @io.puts "#{grab_comments!('    ')}    #{keyword}#{indent(name, '    ')}"
      end

      def table(rows, line)
        rows = rows.to_a.map {|row| row.to_a} if defined?(JRUBY_VERSION) # Convert ArrayList
        max_lengths =  rows.transpose.map { |col| col.map { |cell| cell.unpack("U*").length }.max }.flatten
        rows.each do |table_line|
          @io.puts '      | ' + table_line.zip(max_lengths).map { |cell, max_length| cell + ' ' * (max_length-cell.unpack("U*").length) }.join(' | ') + ' |'
        end
      end

      def py_string(string, line)
        @io.puts "      \"\"\"\n" + string.gsub(START, '      ') + "\n      \"\"\""
      end

      def syntax_error(state, event, legal_events, line)
        raise "SYNTAX ERROR"
      end

    private

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
    end
  end
end
