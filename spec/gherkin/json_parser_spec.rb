#encoding: utf-8
require 'stringio'
require 'spec_helper'
require 'gherkin/json_parser'
require 'gherkin/formatter/json_formatter'

module Gherkin
  describe JSONParser do 

    def check_json(json)
      io = StringIO.new
      f = Formatter::JSONFormatter.new(io)
      p = JSONParser.new(f, f)
      p.parse(json)
      f.done
      expected = JSON.parse(json)
      actual   = JSON.parse(io.string)

      puts "EXPECTED"
      puts json
      puts "ACTUAL"
      puts io.string
      puts "======"

      actual.should == expected
    end

    xit "should parse a barely empty feature" do
      check_json(%{[
        {
          "id": "one",
          "uri": "test.feature",
          "keyword": "Feature", 
          "name": "One", 
          "description": "", 
          "line" : 3 
        }
      ]})
    end

    xit "should parse feature with tags and one scenario" do
      check_json(%{[
        {
          "id": "one",
          "uri": "test.feature",
          "tags": [
            {
              "name": "@foo",
              "line": 22
            }
          ],
          "keyword": "Feature", 
          "name": "One", 
          "description": "", 
          "line": 3,
          "elements": [
            {
              "id": "one/two",
              "type": "scenario",
              "steps": [
                {
                  "name": "Hello",
                  "rows": [
                    {
                      "cells": ["foo", "bar"]
                    }
                  ]
                }
              ]
            }
          ]
        }
      ]})
    end

    it "should parse feature with match, result and embedding" do
      check_json(%{
        [
          {
            "id": "one",
            "uri": "test.feature",
            "tags": [
              {
                "name": "@foo",
                "line": 22
              }
            ],
            "keyword": "Feature", 
            "name": "One", 
            "description": "", 
            "line": 3,
            "elements": [
              {
                "id": "one/a-scenario",
                "type": "scenario",
                "steps": [
                  {
                    "keyword": "Given ",
                    "name": "a passing step",
                    "line": 6,
                    "match": {
                      "arguments": [
                        {
                          "offset": 22,
                          "val": "cukes"
                        }
                      ],
                      "location": "features/step_definitions/steps.rb:1"
                    },
                    "result": {
                      "status": "failed",
                      "error_message": "You suck",
                      "duration": -1
                    },
                    "embeddings": [
                      {
                        "mime_type": "text/plain",
                        "data": "Tm8sIEknbSBub3QgaW50ZXJlc3RlZCBpbiBkZXZlbG9waW5nIGEgcG93ZXJmdWwgYnJhaW4uIEFsbCBJJ20gYWZ0ZXIgaXMganVzdCBhIG1lZGlvY3JlIGJyYWluLCBzb21ldGhpbmcgbGlrZSB0aGUgUHJlc2lkZW50IG9mIHRoZSBBbWVyaWNhbiBUZWxlcGhvbmUgYW5kIFRlbGVncmFwaCBDb21wYW55Lg=="
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      })
    end

    xit "shoud parse a complex feature" do
      check_json('[' + fixture("complex.json") + ']')
    end
  end
end
