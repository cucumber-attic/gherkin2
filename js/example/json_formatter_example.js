// This does something similar to json_formatter_spec.rb
// It is also an example of the Formatter/Reporter API for Gherkin.js

var JSONFormatter = require('../lib/gherkin/formatter/json_formatter');
var f = new JSONFormatter(process.stdout);
f.uri("f.feature");
f.feature({keyword: "Feature", name: "ff", description: "", line: 1, id: "ff"});
f.scenario({keyword: "Scenario", name: "ss", description: "", line: 2, id: "ff/ss"});

f.step({keyword: "Given", name: "g", line: 3});
f.match({location: "def.rb:33"});
f.result({result: 'passed', duration: 1});

f.step({keyword: "When", name: "w", line: 4});
f.match({location: "def.rb:44"});
f.result({result: 'passed', duration: 1});

f.after({location: "def.rb:55"}, {result: 'passed', duration: 22});

f.eof();
f.done();
