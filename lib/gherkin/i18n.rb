require 'yaml'

module Gherkin
  class I18n
    KEYWORD_KEYS = %w{name native feature background scenario scenario_outline examples given when then and but}
    LANGUAGES    = YAML.load_file(File.dirname(__FILE__) + '/i18n.yml')

    class << self
      # Used by code generators for other lexer tools like pygments lexer and textmate bundle
      def all
        LANGUAGES.keys.sort.map{|key| get(key)}
      end

      def get(key)
        languages[key] ||= new(key)
      end

      # Returns all keyword translations and aliases of +keywords+, escaped and joined with <tt>|</tt>.
      # This method is convenient for editor support and syntax highlighting engines for Gherkin, where
      # there is typically a code generation tool to generate regular expressions for recognising the
      # various I18n translations of Gherkin's keywords.
      #
      # The +keywords+ arguments can be one of <tt>:feature</tt>, <tt>:background</tt>, <tt>:scenario</tt>, 
      # <tt>:scenario_outline</tt>, <tt>:examples</tt>, <tt>:step</tt>.
      def keyword_regexp(*keywords)
        unique_keywords = all.map do |lang|
          keywords.map do |keyword|
            lang.__send__("#{keyword}_keywords".to_sym)
          end
        end
        
        unique_keywords.flatten.compact.sort.reverse.uniq.join('|').gsub(/\*/, '\*')
      end

      def code_keywords
        all.map{|i18n| i18n.code_keywords}.flatten.uniq.sort
      end

      def code_keyword_for(gherkin_keyword)
        gherkin_keyword.gsub(/[\s',]/, '').strip
      end

      def language_table
        require 'stringio'
        require 'gherkin/formatter/pretty_formatter'
        io = StringIO.new
        pf = Gherkin::Formatter::PrettyFormatter.new(io, true)
        all.each{|i18n| pf.row([i18n.key, i18n.name, i18n.native], 0)}
        pf.flush_table
        io.rewind
        io.read
      end

      private

      def languages
        @languages ||= {}
      end
    end

    attr_reader :key

    def initialize(key)
      @key = key
      @keywords = LANGUAGES[key]
      raise "Language not supported: #{key.inspect}" if @key.nil?
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
            raise("WARNING: #{e.message}. Reverting to Ruby lexer.")
            rb(listener)
          end
        end
      rescue LoadError => e
        raise I18nLexerNotFound, "No lexer was found for #{i18n_language_name} (#{e.message}). Supported languages are listed in gherkin/i18n.yml."
      end
    end

    def c(listener)
      require 'gherkin/c_lexer'
      CLexer[sanitized_key].new(listener)
    end

    def rb(listener)
      require 'gherkin/rb_lexer'
      RbLexer[sanitized_key].new(listener)
    end

    def sanitized_key
      @key.gsub(/[\s-]/, '_').downcase
    end

    def incomplete?
      KEYWORD_KEYS.detect{|key| @keywords[key].nil?}
    end

    def feature_keywords
      keywords('feature')
    end

    def scenario_keywords
      keywords('scenario')
    end

    def scenario_outline_keywords
      keywords('scenario_outline')
    end

    def background_keywords
      keywords('background')
    end

    def examples_keywords
      keywords('examples')
    end

    def but_keywords
      keywords('but')
    end

    def and_keywords
      keywords('and')
    end

    # Keywords that can be used in Gherkin source
    def step_keywords
      %w{given when then and but}.map{|key| keywords(key)}.flatten.uniq
    end

    def gwt_keywords
      %w{given when then}.map{|key| keywords(key)}.flatten.uniq
    end

    # Keywords that can be used in code
    def code_keywords
      result = gwt_keywords.map{|keyword| self.class.code_keyword_for(keyword)}
      result.delete('*')
      result
    end

    def name
      @keywords['name']
    end

    def native
      @keywords['native']
    end

    def keywords(key)
      raise "No #{key} in #{@keywords.inspect}" if @keywords[key].nil?
      @keywords[key].split('|').map{|kw| keyword_space(kw)}
    end

    def keyword_table
      require 'stringio'
      require 'gherkin/formatter/pretty_formatter'
      io = StringIO.new
      pf = Gherkin::Formatter::PrettyFormatter.new(io, true)

      (KEYWORD_KEYS - %w{name native}).each do |key|
        pf.row([key, keywords(key).map{|keyword| %{"#{keyword}"}}.join(', ')], 0)
      end
      %w{given when then}.each do |key|
        code_keywords = keywords(key).reject{|kw| kw == '* '}.map do |kw|
          %{"#{self.class.code_keyword_for(kw)}"}
        end.join(', ')
        pf.row(["#{key} (code)", code_keywords], 0)
      end
      
      pf.flush_table
      io.rewind
      io.read
    end

    private

    def keyword_space(val)
      (val + ' ').sub(/< $/,'')
    end
  end
end
