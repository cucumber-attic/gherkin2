require 'spec_helper'
require 'stringio'
require 'gherkin/formatter/json_formatter'
require 'gherkin/formatter/model'

module Gherkin
  module Formatter
    describe JSONFormatter do
      it "renders results" do
        io = StringIO.new
        f = JSONFormatter.new(io)
        f.uri("f.feature")
        f.feature(Model::Feature.new([], [], "Feature", "f", "", 1))
        f.scenario(Model::Scenario.new([], [], "Feature", "f", "", 2))
        f.step(Model::Step.new([], "Given ", "g", 3, nil, nil))
        f.step(Model::Step.new([], "When ", "w", 4, nil, nil))

        f.match(Model::Match.new([], "def.rb:33"))
        f.result(Model::Result.new(:passed, 1, nil))

        f.match(Model::Match.new([], "def.rb:44"))
        f.result(Model::Result.new(:passed, 1, nil))

        f.eof
        f.close
        
        expected = %{
          [
            {
              "uri": "f.feature",
              "keyword": "Feature",
              "name": "f",
              "line": 1,
              "description": "",
              "elements": [
                {
                  "keyword": "Feature",
                  "name": "f",
                  "line": 2,
                  "description": "",
                  "type": "scenario",
                  "steps": [
                    {
                      "keyword": "Given ",
                      "name": "g",
                      "line": 3,
                      "match": {
                        "location": "def.rb:33"
                      },
                      "result": {
                        "status": "passed",
                        "duration": 1
                      }
                    },
                    {
                      "keyword": "When ",
                      "name": "w",
                      "line": 4,
                      "match": {
                        "location": "def.rb:44"
                      },
                      "result": {
                        "status": "passed",
                        "duration": 1
                      }
                    }
                  ]
                }
              ]
            }
          ]
        }

        JSON.parse(expected).should == JSON.parse(io.string)
      end
    end
  end
end
