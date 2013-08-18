require 'spec_helper'
require 'gherkin/formatter/json_formatter'
require 'gherkin/formatter/model'
require 'multi_json'
require 'stringio'

module Gherkin
  module Formatter
    describe JSONFormatter do
      it "renders results" do
        io = StringIO.new
        f = JSONFormatter.new(io)
        f.uri("f.feature")
        f.feature(Model::Feature.new([], [], "Feature", "ff", "", 1, "ff"))
        f.scenario(Model::Scenario.new([], [], "Scenario", "ss", "", 2, "ff/ss"))

        f.step(Model::Step.new([], "Given ", "g", 3, nil, nil))
        f.match(Model::Match.new([], "def.rb:33"))
        data = "abc"
        if defined?(JRUBY_VERSION)
          data = data.to_java_bytes
        end
        f.embedding("mime-type", data)
        f.write("step output")

        f.result(Model::Result.new(:passed, 1, nil))

        f.step(Model::Step.new([], "When ", "w", 4, nil, nil))
        f.match(Model::Match.new([], "def.rb:44"))
        f.result(Model::Result.new(:passed, 1, nil))

        f.after(Model::Match.new([], "def.rb:55"), Model::Result.new(:passed, 22, nil))

        f.eof
        f.done

        expected = %{
          [
            {
              "id": "ff",
              "uri": "f.feature",
              "keyword": "Feature",
              "name": "ff",
              "line": 1,
              "description": "",
              "elements": [
                {
                  "id": "ff/ss",
                  "keyword": "Scenario",
                  "name": "ss",
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
                      "embeddings": [
                        {
                          "mime_type": "mime-type",
                          "data": "YWJj"
                        }
                      ],
                      "output": [
                        "step output"
                      ],
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
                  ],
                  "after": [
                    {
                      "match":{
                        "location":"def.rb:55"
                      },
                      "result":{
                        "status":"passed",
                        "duration": 22
                      }
                    }
                  ]
                }
              ]
            }
          ]
        }
        MultiJson.load(io.string).should == MultiJson.load(expected)
      end

      it "renders results as pretty json" do
        io = StringIO.new
        f = JSONFormatter.new(io)
        f.uri("f.feature")
        f.feature(Model::Feature.new([], [], "Feature", nil, nil, nil, nil))
        f.eof
        f.done

        expected = %{
          [
            {
              "keyword": "Feature",
              "uri": "f.feature"
            }
          ]
        }
        io.string.should == MultiJson.dump(MultiJson.load(expected), :pretty => true)
      end

      it 'supports append_duration' do
        io = StringIO.new
        f = JSONFormatter.new(io)
        f.uri("f.feature")
        f.feature(Model::Feature.new([], [], "Feature", "ff", "", 1, "ff"))
        f.scenario(Model::Scenario.new([], [], "Scenario", "ss", "", 2, "ff/ss"))
        f.step(Model::Step.new([], "Given ", "g", 3, nil, nil))
        f.match(Model::Match.new([], "def.rb:33"))
        f.result(Model::Result.new(:passed, 3, nil))
        f.append_duration(1)
        f.eof
        f.done
        expected = %{
          [
            {
              "id": "ff",
              "uri": "f.feature",
              "keyword": "Feature",
              "name": "ff",
              "line": 1,
              "description": "",
              "elements": [
                {
                  "id": "ff/ss",
                  "keyword": "Scenario",
                  "name": "ss",
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
                        "duration": 1000000000
                      }
                    }
                  ]
                }
              ]
            }
          ]
        }
        MultiJson.load(io.string).should == MultiJson.load(expected)
      end
    end
  end
end
