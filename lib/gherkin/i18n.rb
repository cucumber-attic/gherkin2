require 'yaml'
require 'gherkin/rubify'
require 'gherkin/bridge'

module Gherkin
  class I18n
    native_impl('gherkin') unless defined?(BYPASS_NATIVE_IMPL)

    FEATURE_ELEMENT_KEYS = %w{feature background scenario scenario_outline examples}
    STEP_KEYWORD_KEYS    = %w{given when then and but}
    KEYWORD_KEYS         = FEATURE_ELEMENT_KEYS + STEP_KEYWORD_KEYS
    LANGUAGES            = YAML.load_file(File.dirname(__FILE__) + '/i18n.yml')

    class << self
      include Rubify

      # Used by code generators for other lexer tools like pygments lexer and textmate bundle
      def all
        LANGUAGES.keys.sort.map{|iso_code| get(iso_code)}
      end

      def get(iso_code)
        languages[iso_code] ||= new(iso_code)
      end

      # Returns all keyword translations and aliases of +keywords+, escaped and joined with <tt>|</tt>.
      # This method is convenient for editor support and syntax highlighting engines for Gherkin, where
      # there is typically a code generation tool to generate regular expressions for recognising the
      # various I18n translations of Gherkin's keywords.
      #
      # The +keywords+ arguments can be one of <tt>:feature</tt>, <tt>:background</tt>, <tt>:scenario</tt>, 
      # <tt>:scenario_outline</tt>, <tt>:examples</tt>, <tt>:step</tt>.
      def keyword_regexp(*keywords)
        unique_keywords = all.map do |i18n|
          keywords.map do |keyword|
            if keyword.to_s == 'step'
              i18n.step_keywords
            else
              i18n.keywords(keyword)
            end
          end
        end
        
        unique_keywords.flatten.compact.sort.reverse.uniq.join('|').gsub(/\*/, '\*')
      end

      def code_keywords
        rubify(all.map{|i18n| i18n.code_keywords}).flatten.uniq.sort
      end

      def code_keyword_for(gherkin_keyword)
        gherkin_keyword.gsub(/[\s',]/, '').strip
      end

      def language_table
        require 'stringio'
        require 'gherkin/formatter/pretty_formatter'
        io = defined?(JRUBY_VERSION) ? Java.java.io.StringWriter.new : StringIO.new
        pf = Gherkin::Formatter::PrettyFormatter.new(io, true)
        all.each{|i18n| pf.row([i18n.iso_code, i18n.keywords('name')[0], i18n.keywords('native')[0]], 0)}
        pf.flush_table
        if defined?(JRUBY_VERSION)
          io.getBuffer.toString
        else
          io.rewind
          io.read
        end
      end

      def unicode_escape(word, prefix="\\u")
        word = word.unpack("U*").map do |c|
          if c > 127 || c == 32
            "#{prefix}%04x" % c
          else
            c.chr
          end
        end.join
      end

      private

      def languages
        @languages ||= {}
      end
    end

    attr_reader :iso_code

    def initialize(iso_code)
      @iso_code = iso_code
      @keywords = LANGUAGES[iso_code]
      raise "Language not supported: #{iso_code.inspect}" if @iso_code.nil?
      @keywords['grammar_name'] = @keywords['name'].gsub(/\s/, '')
    end

    def lexer(listener, force_ruby=false)
      begin
        if force_ruby
          rb(listener)
        else
          begin
            c(listener)
          rescue NameError, LoadError => e
            warn("WARNING: #{e.message}. Reverting to Ruby lexer.")
            rb(listener)
          end
        end
      rescue LoadError => e
        raise I18nLexerNotFound, "No lexer was found for #{i18n_language_name} (#{e.message}). Supported languages are listed in gherkin/i18n.yml."
      end
    end

    def c(listener)
      require 'gherkin/c_lexer'
      CLexer[underscored_iso_code].new(listener)
    end

    def rb(listener)
      require 'gherkin/rb_lexer'
      RbLexer[underscored_iso_code].new(listener)
    end

    def underscored_iso_code
      @iso_code.gsub(/[\s-]/, '_').downcase
    end

    # Keywords that can be used in Gherkin source
    def step_keywords
      STEP_KEYWORD_KEYS.map{|iso_code| keywords(iso_code)}.flatten.uniq
    end

    # Keywords that can be used in code
    def code_keywords
      result = step_keywords.map{|keyword| self.class.code_keyword_for(keyword)}
      result.delete('*')
      result
    end

    def keywords(iso_code)
      iso_code = iso_code.to_s
      raise "No #{iso_code.inspect} in #{@keywords.inspect}" if @keywords[iso_code].nil?
      @keywords[iso_code].split('|').map{|keyword| real_keyword(iso_code, keyword)}
    end

    def keyword_table
      require 'stringio'
      require 'gherkin/formatter/pretty_formatter'
      io = StringIO.new
      pf = Gherkin::Formatter::PrettyFormatter.new(io, true)

      KEYWORD_KEYS.each do |key|
        pf.row([key, keywords(key).map{|keyword| %{"#{keyword}"}}.join(', ')], 0)
      end
      STEP_KEYWORD_KEYS.each do |key|
        code_keywords = keywords(key).reject{|keyword| keyword == '* '}.map do |keyword|
          %{"#{self.class.code_keyword_for(keyword)}"}
        end.join(', ')
        pf.row(["#{key} (code)", code_keywords], 0)
      end
      
      pf.flush_table
      io.rewind
      io.read
    end

    private

    def real_keyword(iso_code, keyword)
      if(STEP_KEYWORD_KEYS.index(iso_code))
        (keyword + ' ').sub(/< $/, '')
      else
        keyword
      end
    end
  end
end
