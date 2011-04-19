exports.Lexer = function(lang) {
  return require('./gherkin/lexer/' + lang).Lexer;
};
