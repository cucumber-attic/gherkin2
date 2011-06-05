var fs = require('fs');
var Lexer = require('../lib/gherkin').Lexer('en');

var lexer = new Lexer({
  comment: function(value, line) {
    console.log(value);
  },
  tag: function(value, line) {
    console.log(value);
  },
  feature: function(keyword, name, description, line) {
    console.log(keyword + ': ' + name);
  },
  background: function(keyword, name, description, line) {
    console.log('  ' + keyword + ': ' + name);
  },
  scenario: function(keyword, name, description, line) {
    console.log('  ' + keyword + ': ' + name);
  },
  scenario_outline: function(keyword, name, description, line) {
    console.log('  ' + keyword + ': ' + name);
  },
  examples: function(keyword, name, description, line) {
    console.log('  ' + keyword + ': ' + name);
  },
  step: function(keyword, name, line) {
    console.log('    ' + keyword + name);
  },
  doc_string: function(string, line) {
    console.log('      """\n' + string + '\n      """');
  },
  row: function(row, line) {
    console.log('      | ' + row.join(' | ') + ' |');
  },
  eof: function() {
    console.log('=====');
  }
});

lexer.scan(fs.readFileSync(process.ARGV[2]));
