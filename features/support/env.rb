# I'm sure there's a better way than this...
%w{/../../lib /../../spec/gherkin}.each do |path|
  $LOAD_PATH << File.expand_path(File.dirname(__FILE__) + path)
end

require 'gherkin'
require "sexp_recorder"

module TransformHelpers
  def tr_line_number(step_arg)
    /(\d+)$/.match(step_arg)[0].to_i
  end

  def tr_line_numbers(step_arg)
    if step_arg =~ /through/
      Range.new(*step_arg.scan(/\d+/).collect { |i| i.to_i })
    else
      step_arg.scan(/\d+/).collect { |i| i.to_i }
    end
  end
end

class GherkinWorld
  include TransformHelpers
  
  def initialize
    @listener = Gherkin::SexpRecorder.new
  end

  def load_parser(i18n_lang, lexer_name)
    if defined?(JRUBY_VERSION)
      @parser = Gherkin::JavaLexer['en'].new(Java::Gherkin::Parser.new(@listener, false, lexer_name))
    else
      @parser = Gherkin.const_get(lexer_name.capitalize).new(i18n_lang, @listener, false)
    end
  end

  def code_from_lang_name(name)
    i18n = YAML.load_file(File.dirname(__FILE__) + "/../../lib/gherkin/i18n.yml")
    i18n["Native"] = { "name" => "Native" } # XXX HACK XXX Make it easy to retrieve the native lexer
    code = i18n.find { |_, v| v["name"] == name }
    code[0]
  end
end

World do 
  GherkinWorld.new
end
