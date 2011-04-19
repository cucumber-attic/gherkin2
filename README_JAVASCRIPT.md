Building and Running the js lexer
=================================

You need Node.js and http://github.com/dominicmarks/ragel-js installed. Then:

  rake js/lib/gherkin/lexer/en.js && node js/example/print.js spec/gherkin/fixtures/1.feature

Or: (using therubyracer)

  rake js/lib/gherkin/lexer/en.js && ruby js/example/print.rb spec/gherkin/fixtures/1.feature 

That prints gherkin to stdout - almost like the original. Well, not quite, but you get the idea. We're firing events.

Trying out the lexer in a browser (only tried with Chrome)
==========================================================

After you have built the lexer (see steps above), pull in the Ace editor:

  git submodule update --init

(This is not *really* needed in order to test the lexer, but it's nice to see gherkin.js playing
nice along with Ace - they will most likely be used together). Open the demo page:

  open js/example/index.html
