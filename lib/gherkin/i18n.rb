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
      %w{given when then and but}.map{|keyword| @keywords[keyword].split('|').map{|w| w.gsub(/[\s<']/, '')}}.flatten
    end

    private

    def keyword_space(val)
      (val + ' ').sub(/< $/,'')
    end
  end
end
