var fs = require('fs');
var Lexer = require('./js/gherkin.en').Lexer;

var lexer = new Lexer({
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
  py_string: function(string, line) {
    console.log('      """\n' + string + '\n      """');
  },
  row: function(row, line) {
    console.log('      | ' + row.join(' | ') + ' |');
  },
  end: function() {
    console.log('=====');
  }
});

lexer.scan(fs.readFileSync(process.ARGV[2]));
