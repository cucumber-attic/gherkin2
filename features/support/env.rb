$LOAD_PATH << File.expand_path(File.dirname(__FILE__) + "/../../lib")
$LOAD_PATH << File.expand_path(File.dirname(__FILE__) + "/../../spec/gherkin")
require 'gherkin'
require "sexp_recorder"

class GherkinWorld
  attr_reader :listener, :parser
  
  def initialize
    @listener = Gherkin::SexpRecorder.new
  end

  def load_feature_parser(i18n_lang)
    policy = Gherkin::SyntaxPolicy::FeaturePolicy.new(listener)
    @parser = Gherkin::Parser[i18n_lang].new(policy)
  end
end

World do 
  GherkinWorld.new
end
