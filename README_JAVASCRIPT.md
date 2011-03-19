Building and Running the js lexer
=================================

You need Node.js and http://github.com/dominicmarks/ragel-js installed. Then:

  rake js/lib/gherkin/lexer/i18n/en.js && node js/example/print.js features/json_formatter.feature

Or: (using therubyracer)

  # TODO: Why doesn't this print anything?? Sprinkle some console.log statements in the scan functio
  # to verify that we actually get into the scan function. However - from there nothing seems to happen.
  #
  rake js/lib/gherkin/lexer/i18n/en.js && ruby js/example/print.rb features/json_formatter.feature

That prints gherkin to stdout - almost like the original.