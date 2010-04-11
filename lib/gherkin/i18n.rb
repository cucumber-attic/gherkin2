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

      def languages
        @languages ||= {}
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
    end

    attr_reader :key

    def initialize(key)
      @key = key
      @keywords = LANGUAGES[key]
      raise "Language not supported: #{key.inspect}" if @key.nil?
      @keywords['grammar_name'] = @keywords['name'].gsub(/\s/, '')
    end

    def lexer(listener, force_ruby)
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

    def but_keywords(space=true)
      keywords('but', space)
    end

    def and_keywords(space=true)
      keywords('and', space)
    end

    # Keywords that can be used in Gherkin source
    def step_keywords
      %w{given when then and but}.map{|key| keywords(key, true)}.flatten.uniq
    end

    def gwt_keywords
      %w{given when then}.map{|key| keywords(key, true)}.flatten.uniq
    end

    # Keywords that can be used in code
    def code_keywords
      result = step_keywords.map{|keyword| keyword.gsub(/[\s']/, '').strip}
      result.delete('*')
      result
    end

    def keywords(key, space=false)
      raise "No #{key} in #{@keywords.inspect}" if @keywords[key].nil?
      @keywords[key].split('|').map{|kw| space ? keyword_space(kw) : kw}
    end

    private

    def keyword_space(val)
      (val + ' ').sub(/< $/,'')
    end
  end
end
