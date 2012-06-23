/**
 * Creates a new Lexer for a specific language.
 */
exports.Lexer = function(lang) {
  return require('./gherkin/lexer/' + lang).Lexer;
};

/**
 * Creates a connect middleware for loading lexer sources (typically for browsers).
 */
exports.connect = function(path) {
  var gherkinFiles = require('connect').static(__dirname);

  return function(req, res, next) {
    if(req.url.indexOf(path) == 0) {
      req.url = req.url.slice(path.length);
      gherkinFiles(req, res, next);
    } else {
      next();
    }
  };
};