# I'm sure there's a better way than this...
%w{/../../lib /../../spec/gherkin}.each do |path|
  $LOAD_PATH << File.expand_path(File.dirname(__FILE__) + path)
end

require 'gherkin'
require "sexp_recorder"

class GherkinWorld
  attr_reader :listener, :parser
  
  def initialize
    @listener = Gherkin::SexpRecorder.new
  end

  def load_feature_parser(i18n_lang)
    @parser = Gherkin::Feature.new(i18n_lang, listener, :raise_on_error => false)
  end
  
  def code_from_lang_name(name)
    i18n = YAML.load_file(File.dirname(__FILE__) + "/../../lib/gherkin/i18n.yml")
    i18n.find { |_, v| v["name"] == name }[0]
  end
end

World do 
  GherkinWorld.new
end
