var assert = require("assert");
var Lexer = require('../lib/gherkin').Lexer('de');

var Recorder=function(){
  records=[];
  return {
    comment: function(value, line) {
      records.push({token:'comment',value:value,line:line});
    },
    tag: function(value, line) {
      records.push({token:'tag',value:value,line:line});
    },
    feature: function(keyword, name, description, line) {
      records.push({token:'feature',keyword:keyword,name:name,description:description,line:line});
    },
    background: function(keyword, name, description, line) {
      records.push({token:'background',keyword:keyword,name:name,description:description,line:line});
    },
    scenario: function(keyword, name, description, line) {
      records.push({token:'scenario',keyword:keyword,name:name,description:description,line:line});
    },
    scenario_outline: function(keyword, name, description, line) {
      records.push({token:'scenario_outline',keyword:keyword,name:name,description:description,line:line});
    },
    examples: function(keyword, name, description, line) {
      records.push({token:'examples',keyword:keyword,name:name,description:description,line:line});
    },
    step: function(keyword, name, line) {
      records.push({token:'step',keyword:keyword,name:name,line:line});
    },
    doc_string: function(content_type, string, line) {
      records.push({token:'doc_string',content_type:content_type,string:string,line:line});
    },
    row: function(row, line) {
      records.push({token:'row',row:row,line:line});
    },
    eof: function() {
      records.push({token:'eof'});
    },
    records:records
  };
};


describe("Lexer",function(){
  describe('#scan with String',function(){
    it("should accept keywords that include non-ascii characters",function(){
      var recorder=Recorder();
      var lexer=new Lexer(recorder);
      assert.doesNotThrow(function(){
        lexer.scan("Funktionalität: Jede Menge €€€s!");
      });
      assert.equal(recorder.records.length,2);
      assert.equal(recorder.records[0].token,"feature");
      assert.equal(recorder.records[0].keyword,"Funktionalität");
      assert.equal(recorder.records[0].name,"Jede Menge €€€s!");
    });
  });

  describe('#scan with Buffer',function(){
    it("should accept keywords that include non-ascii characters",function(){
      var recorder=Recorder();
      var lexer=new Lexer(recorder);
      assert.doesNotThrow(function(){
        // same string as above, UTF-8 encoded
	var utf8_data = [
          0x46, 0x75, 0x6e, 0x6b, 0x74, 0x69, 0x6f, 0x6e,
          0x61, 0x6c, 0x69, 0x74, 0xc3, 0xa4, 0x74, 0x3a,
          0x20, 0x4a, 0x65, 0x64, 0x65, 0x20, 0x4d, 0x65,
          0x6e, 0x67, 0x65, 0x20, 0xe2, 0x82, 0xac, 0xe2,
          0x82, 0xac, 0xe2, 0x82, 0xac, 0x73, 0x21, 0x0a
        ];

        lexer.scan(new Buffer(utf8_data));
      });
      assert.equal(recorder.records.length,2);
      assert.equal(recorder.records[0].token,"feature");
      assert.equal(recorder.records[0].keyword,"Funktionalität");
      assert.equal(recorder.records[0].name,"Jede Menge €€€s!");
    });
  });

});
