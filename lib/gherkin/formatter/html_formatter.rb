# encoding: utf-8
require 'gherkin/formatter/argument'
require 'gherkin/formatter/escaping'
require 'gherkin/formatter/model'
require 'gherkin/native'

module Gherkin
  module Formatter
    class HtmlFormatter
      native_impl('gherkin')

      include Escaping

      def initialize(io, executing)
        @io = io
        @executing = executing
        @background = nil
        @tag_statement = nil
        @steps = []
		@io.puts "<div class=\"feature\">"
      end

      def uri(uri)
        @uri = uri
      end

      def feature(feature)
        print_comments(feature.comments)
        print_tags(feature.tags)
        @io.puts "<h2>#{feature.keyword}: #{feature.name}</h2>"
        print_description(feature.description, false)
      end

      def background(background)
        replay
        @statement = background
      end

      def scenario(scenario)
	    replay
        @io.puts "</div><div class=\"feature\">"
        print_tags(scenario.tags)
		@io.puts "<h3>" + scenario.keyword + ": " + escape_brackets(scenario.name) + "</h3>"
      end

      def scenario_outline(scenario_outline)
        scenario scenario_outline
      end
      
      def replay
        print_statement
        print_steps
      end
      
      def print_statement
        return if @statement.nil?
        @io.puts
        print_comments(@statement.comments)
        print_tags(@statement.tags) if @statement.respond_to?(:tags) # Background doesn't
        @io.write "  #{@statement.keyword}: #{@statement.name}"
        location = @executing ? "#{@uri}:#{@statement.line}" : nil
        @io.write location
        print_description(@statement.description)
        @statement = nil
      end

      def print_steps
        while(@steps.any?)
          print_step('b', [], nil, true)
        end
      end

      def examples(examples)
        replay
        @io.puts
        print_comments(examples.comments)
        print_tags(examples.tags)
        @io.write "<b>#{examples.keyword}:</b> #{examples.name}"
        print_description(examples.description)
        table(examples.rows, true)
      end

      def step(step)
        @steps << step
      end

      def match(match)
        @match = match
        print_statement
        print_step('executing', @match.arguments, @match.location, false)
      end

      def result(result)
        @io.write(up(1))
        print_step(result.status, @match.arguments, @match.location, true)
      end

      def print_step(status, arguments, location, proceed)
        step = proceed ? @steps.shift : @steps[0]
        
        print_comments(step.comments)
        @io.write("<" + status + ">" + step.keyword + "</" + status + ">")
		@io.write(escape_brackets(step.name))
        @io.write(escape_brackets(location))
		@io.puts("<br />")
        
        doc_string(step.doc_string) if step.doc_string
        table(step.rows) if step.rows
      end

      def eof
        replay
        # NO-OP
      end

      def done
        @io.puts("</div>")
      end

      def table(rows, header=false)
	    @io.puts("<table>")
		
		rows.each_with_index do |row, i|
		  @io.puts("<tr>")
		  row.cells.each do |cell|
		    bal = header && i == 0 ? "th" : "td" 
		    @io.puts("  <" + bal + ">" + cell + "</" + bal + ">")
		  end
		  @io.puts("</tr>")
		end
		
		@io.puts("</table>")
      end

    private
      def escape_brackets(s)
        return if s.nil?
        s.gsub("<", "&lt;").gsub(">", "&gt;")
      end

      def doc_string(doc_string)
        @io.puts doc_string.content_type + "<br />\n" + escape_triple_quotes(doc_string.value) + "<br />\n"
      end

      def exception(exception)
        exception_text = "#{exception.message} (#{exception.class})\n#{(exception.backtrace || []).join("\n")}".gsub(/^/, '      ')
        @io.puts(failed(exception_text))
      end

      if(RUBY_VERSION =~ /^1\.9|2\.0/)
        START = /#{'^'.encode('UTF-8')}/
        TRIPLE_QUOTES = /#{'"""'.encode('UTF-8')}/
      else
        START = /^/
        TRIPLE_QUOTES = /"""/
      end

      def escape_triple_quotes(s)
        s.gsub(TRIPLE_QUOTES, '\"\"\"')
      end

      def print_tags(tags)
	    # @MonTag
        @io.write(tags.empty? ? '' : tags.map{|tag| "<span class=\"tag\">" + tag.name + "</span>" }.join(' ') + "\n")
      end

      def print_comments(comments)
	    @io.puts(comments.empty? ? '' : "<p>" + comments.map{|comment| "<i>" + comment.value.gsub( /#/m, "" ) + "</i>"}.join("<br />\n") + "</p>\n")
      end

      def print_description(description, newline=true)
        if description != ""
		  description = description.gsub( /</m, "&lt;" )
          description = description.gsub( />/m, "&gt;" )
		  description = description.gsub( /\n/m, "<br />\n\r" )
          @io.puts "<p>" + description + "</p>"
          @io.puts if newline
		end
      end
    end
  end
end
