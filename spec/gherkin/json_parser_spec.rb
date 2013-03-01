#encoding: utf-8
require 'stringio'
require 'spec_helper'
require 'gherkin/json_parser'
require 'gherkin/formatter/json_formatter'
require 'multi_json'

module Gherkin
  describe JSONParser do

    def check_json(json)
      io = StringIO.new
      f = Formatter::JSONFormatter.new(io)
      p = JSONParser.new(f, f)
      p.parse(json)
      f.done
      expected = MultiJson.load(json)
      actual   = MultiJson.load(io.string)

      begin
        actual.should == expected
      rescue
        puts "EXPECTED"
        puts json
        puts "ACTUAL"
        puts io.string
        puts "======"

        raise
      end
    end

    it "should parse a barely empty feature" do
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

    it "should parse feature with tags and one scenario" do
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
                  "id":"one",
                  "uri":"test.feature",
                  "tags":[
                      {
                          "name":"@foo",
                          "line":22
                      }
                  ],
                  "keyword":"Feature",
                  "name":"One",
                  "description":"",
                  "line":3,
                  "elements":[
                      {
                          "id":"one/a-scenario",
                          "type":"scenario",
                          "before": [
                              {
                                  "match":{
                                      "location":"features/step_definitions/hooks.rb:1"
                                  },
                                  "result":{
                                      "status":"passed",
                                      "error_message":"Passed hook",
                                      "duration": 3
                                  }
                              },
                              {
                                  "match":{
                                      "location":"features/step_definitions/hooks.rb:2"
                                  },
                                  "result":{
                                      "status":"failed",
                                      "error_message":"Failed hook",
                                      "duration": 22
                                  }
                              }
                          ],
                          "steps":[
                              {
                                  "keyword":"Given ",
                                  "name":"a passing step",
                                  "line":6,
                                  "match":{
                                      "arguments":[
                                          {
                                              "offset":22,
                                              "val":"cukes"
                                          }
                                      ],
                                      "location":"features/step_definitions/steps.rb:1"
                                  },
                                  "result":{
                                      "status":"failed",
                                      "error_message":"You suck",
                                      "duration":-1
                                  },
                                  "embeddings":[
                                      {
                                          "mime_type":"text/plain",
                                          "data":"Tm8sIEknbSBub3QgaW50ZXJlc3RlZCBpbiBkZXZlbG9waW5nIGEgcG93ZXJmdWwgYnJhaW4uIEFsbCBJJ20gYWZ0ZXIgaXMganVzdCBhIG1lZGlvY3JlIGJyYWluLCBzb21ldGhpbmcgbGlrZSB0aGUgUHJlc2lkZW50IG9mIHRoZSBBbWVyaWNhbiBUZWxlcGhvbmUgYW5kIFRlbGVncmFwaCBDb21wYW55Lg=="
                                      }
                                  ],
                                  "output":[
                                      "Hello",
                                      "World"
                                  ]
                              }
                          ],
                          "after": [
                              {
                                  "match":{
                                      "location":"features/step_definitions/hooks.rb:3"
                                  },
                                  "result":{
                                      "status":"failed",
                                      "error_message":"Failed After",
                                      "duration": 22
                                  }
                              }
                          ]
                      }
                  ]
              }
          ]
      })
    end

    it "shoud parse a complex feature" do
      check_json('[' + fixture("complex.json") + ']')
    end
  end
end
