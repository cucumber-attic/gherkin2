require 'base64'
require 'gherkin/formatter/argument'
require 'gherkin/formatter/model'
require 'gherkin/native'
require 'multi_json'

module Gherkin
  class JSONParser
    native_impl('gherkin')

    include Base64

    def initialize(reporter, formatter)
      @reporter, @formatter = reporter, formatter
    end

    # Parse a gherkin object +o+, which can either be a JSON String,
    # or a Hash (from a parsed JSON String).
    def parse(o)
      o = MultiJson.load(o) if String === o

      o.each do |f|
        @formatter.uri(f['uri'])
        Formatter::Model::Feature.new(comments(f), tags(f), keyword(f), name(f), description(f), line(f), id(f)).replay(@formatter)
        (f["elements"] || []).each do |feature_element|
          feature_element(feature_element).replay(@formatter)

          (feature_element["before"] || []).each do |hook|
            before(hook)
          end

          (feature_element["steps"] || []).each do |step|
            step(step).replay(@formatter)
            match(step)
            result(step)
            embeddings(step)
            output(step)
          end

          (feature_element["after"] || []).each do |hook|
            after(hook)
          end

          (feature_element["examples"] || []).each do |eo|
            Formatter::Model::Examples.new(comments(eo), tags(eo), keyword(eo), name(eo), description(eo), line(eo), id(eo), examples_rows(eo['rows'])).replay(@formatter)
          end
        end
      end

      @formatter.eof
    end

  private

    def feature_element(o)
      case o['type']
      when 'background'
        Formatter::Model::Background.new(comments(o), keyword(o), name(o), description(o), line(o))
      when 'scenario'
        Formatter::Model::Scenario.new(comments(o), tags(o), keyword(o), name(o), description(o), line(o), id(o))
      when 'scenario_outline'
        Formatter::Model::ScenarioOutline.new(comments(o), tags(o), keyword(o), name(o), description(o), line(o), id(o))
      end
    end

    def step(o)
      builder = Formatter::Model::Step::Builder.new(comments(o), keyword(o), name(o), line(o))

      (o['rows'] || []).each do |row|
        builder.row comments(row), row['cells'], row['line'], nil
      end

      if(o['doc_string'])
        ds = o['doc_string']
        builder.doc_string ds['value'], ds['content_type'].to_s, ds['line']
      end

      builder.build
    end

    def match(o)
      if(m = o['match'])
        Formatter::Model::Match.new(arguments(m), location(m)).replay(@reporter)
      end
    end

    def result(o)
      if(r = o['result'])
        Formatter::Model::Result.new(status(r), duration(r), error_message(r)).replay(@reporter)
      end
    end

    def before(o)
      m = o['match']
      match = Formatter::Model::Match.new([], location(m))
      r = o['result']
      result = Formatter::Model::Result.new(status(r), duration(r), error_message(r))
      @reporter.before(match, result)
    end

    def after(o)
      m = o['match']
      match = Formatter::Model::Match.new([], location(m))
      r = o['result']
      result = Formatter::Model::Result.new(status(r), duration(r), error_message(r))
      @reporter.after(match, result)
    end

    def embeddings(o)
      (o['embeddings'] || []).each do |embedding|
        @reporter.embedding(embedding['mime_type'], Base64::decode64(embedding['data']))
      end
    end

    def output(o)
      (o['output'] || []).each do |text|
        @reporter.write(text)
      end
    end

    def examples_rows(o)
      o.map{|row| Formatter::Model::ExamplesTableRow.new(comments(row), row['cells'], row['line'], row['id'])}
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

    def id(o)
      o['id']
    end

    def arguments(m)
      m['arguments'].map{|a| Formatter::Argument.new(a['offset'], a['val'])}
    end

    def location(m)
      m['location']
    end

    def status(r)
      r['status']
    end

    def duration(r)
      r['duration']
    end

    def error_message(r)
      r['error_message']
    end
  end
end
