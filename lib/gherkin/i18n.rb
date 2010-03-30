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

    def sanitized_key
      @key.gsub(/[\s-]/, '')
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

    def step_keywords
      %w{given when then and but}.map{|key| keywords(key, true)}.flatten.uniq
    end

    def gwt_keywords
      %w{given when then}.map{|key| keywords(key, true)}.flatten.uniq
    end

    def keywords(key, space=false)
      raise "No #{key} in #{@keywords.inspect}" if @keywords[key].nil?
      @keywords[key].split('|').map{|kw| space ? keyword_space(kw) : kw}
    end

    def adverbs
      # TODO: looks very similar to #step_keywords. Lose this? Where is it used from?
      %w{given when then and but}.map{|keyword| @keywords[keyword].split('|').map{|w| w.gsub(/[\s<']/, '')}}.flatten
    end

    private

    def keyword_space(val)
      (val + ' ').sub(/< $/,'')
    end
  end
end
