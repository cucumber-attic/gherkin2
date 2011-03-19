Building and Running the js lexer
=================================

You need Node.js and http://github.com/dominicmarks/ragel-js installed. Then:

  rake js/lib/gherkin/lexer/i18n/en.js && node js/example/print.js features/json_formatter.feature

Or: (using therubyracer)

  rake js/lib/gherkin/lexer/i18n/en.js && ruby js/example/print.rb features/json_formatter.feature

That prints gherkin to stdout - almost like the original.