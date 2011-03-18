exports.Lexer = function(lang) {
  return require('gherkin/lexer/i18/' + lang).Lexer;
};
