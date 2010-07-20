require 'json'
require 'gherkin/formatter/model'
require 'gherkin/native'

module Gherkin
  class JSONParser
    native_impl('gherkin')

    def initialize(formatter)
      @formatter = formatter
    end

    def parse(src, feature_uri='unknown.json', line_offset=0)
      o = JSON.parse(src)

      model(o).replay(@formatter)
      (o["elements"] || []).each do |feature_element|
        model(feature_element).replay(@formatter)
        (feature_element["steps"] || []).each do |step|
          model(step, 'step').replay(@formatter)
        end
        (feature_element["examples"] || []).each do |eo|
          examples = Formatter::Model::Examples.new(comments(eo), tags(eo), keyword(eo), name(eo), description(eo), line(eo), rows(eo['rows']))
          examples.replay(@formatter)
        end
      end

      @formatter.eof
    end

    def model(o, type = nil)
      type ||= o['type']
      case type
      when 'feature'
        Formatter::Model::Feature.new(comments(o), tags(o), keyword(o), name(o), description(o), line(o))
      when 'background'
        Formatter::Model::Background.new(comments(o), keyword(o), name(o), description(o), line(o))
      when 'scenario'
        Formatter::Model::Scenario.new(comments(o), tags(o), keyword(o), name(o), description(o), line(o))
      when 'scenario_outline'
        Formatter::Model::ScenarioOutline.new(comments(o), tags(o), keyword(o), name(o), description(o), line(o))
      when 'step'
        multiline_arg = nil
        if(ma = o['multiline_arg'])
          if(ma['type'] == 'table')
            multiline_arg = rows(ma['value'])
          else
            multiline_arg = Formatter::Model::PyString.new(ma['value'], ma['line'])
          end
        end
        Formatter::Model::Step.new(comments(o), keyword(o), name(o), description(o), line(o), multiline_arg)
      else
        raise "unknown type: #{o['type'].inspect}"
      end
    end

    def rows(o)
      o.map{|row| Formatter::Model::Row.new(comments(row), row['cells'], row['line'])}
    end

    def comments(o)
      (o['comments'] || []).map do |comment|
        Formatter::Model::Comment.new(comment['value'], comment['line'])
      end
    end

    def tags(o)
      (o['tags'] || []).map do |tag|
        Formatter::Model::Tag.new(tag['name'], tag['line'])
      end
    end

    def keyword(o)
      o['keyword']
    end

    def name(o)
      o['name']
    end

    def description(o)
      o['description']
    end

    def line(o)
      o['line']
    end
  end
end
