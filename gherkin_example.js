var fs = require('fs')
var Lexer = new require('./js/gherkin.en').Lexer;
var lexer = new Lexer(null);

lexer.on('feature', function(keyword, name, description, line) {
  console.log(keyword + ': ' + name);
});
lexer.on('background', function(keyword, name, description, line) {
  console.log('  ' + keyword + ': ' + name);
});
lexer.on('scenario', function(keyword, name, description, line) {
  console.log('  ' + keyword + ': ' + name);
});
lexer.on('scenario_outline', function(keyword, name, description, line) {
  console.log('  ' + keyword + ': ' + name);
});
lexer.on('examples', function(keyword, name, description, line) {
  console.log('    ' + keyword + ': ' + name);
});
lexer.on('step', function(keyword, name, line) {
  console.log('    ' + keyword + name);
});
lexer.on('py_string', function(string, line) {
  console.log('      """\n' + string + '\n      """');
});
lexer.on('row', function(row, line) {
  console.log('      | ' + row.join(' | ') + ' |');
});

lexer.scan(fs.readFileSync(process.ARGV[2]));
