Building and Running the js lexer
=================================

You need Node.js and http://github.com/dominicmarks/ragel-js installed. Then:

  rake js/gherkin.en.js && node js/gherkin.en.js features/steps_parser.feature
  
For the time being that doesn't do much - it just prints the actions. We need to fill in the gaps.