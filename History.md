## [Git master](https://github.com/cucumber/gherkin/compare/v2.12.1...master)

* [Java] Durations are Longs not longs as they are optional ([#276](https://github.com/cucumber/gherkin/pull/276) jtnord)
* [Core] Added ಕನ್ನಡ kannaḍa language ([#282](https://github.com/cucumber/gherkin/pull/282) Nikhil Lingutla)
* [Java] extension of the Formatter interface for more precise lifecycle handling ([#275](https://github.com/cucumber/gherkin/pull/275) Sebastian Gröbler)
* Java JSONFormatter should record before hooks in next scenario ([#270](https://github.com/cucumber/gherkin/pull/270) Björn Rasmusson)
* [JavaScript] Fix encoding issues ([#272](https://github.com/cucumber/gherkin/pull/272) Lukas Degener, Thorsten Glaser)

## [2.12.1](https://github.com/cucumber/gherkin/compare/v2.12.0...v2.12.1)

* Dropped support for ruby 1.8.7 (Aslak Hellesøy)
* Added support for ruby 2.0.0 (still supporting 1.9.3) (Aslak Hellesøy)
* Random lexing error; native fallback fails. Switch from C lexer to Ruby lexer with `GHERKIN_RUBY=true` ([#245](https://github.com/cucumber/gherkin/issues/245) Aslak Hellesøy)
* Problem with Nuget package 2.12.0 ([#254](https://github.com/cucumber/gherkin/issues/254) Aslak Hellesøy)
* problem with "gherkin_lexer_en" ([#257](https://github.com/cucumber/gherkin/issues/257) Aslak Hellesøy)
* Fix for dissapearing examples tags ([#187](https://github.com/cucumber/gherkin/issues/187) calebTomlinson)
* Handle interleaved calls to step, match and result in the java PrettyFormatter ([#261](https://github.com/cucumber/gherkin/pull/261) Björn Rasmusson)
* Change the java JSONFormatter to handle embedding text in the report correctly ([#269](https://github.com/cucumber/gherkin/pull/269) Björn Rasmusson)
* Updated Hindi language with "pure form", Sanskrit based versions of the words. ([#262](https://github.com/cucumber/gherkin/pull/262) anandpathaksharma)
* [Core] Added support for Panjabi/Punjabi language (Gurmukhi Script) ([#267](https://github.com/cucumber/gherkin/pull/267) Arvinder Singh Kang)
* [Core] Update i18n.json change Malay translation using suitable words ([#268](https://github.com/cucumber/gherkin/pull/268) CallMeLaNN)
* [Core] Fix typos in Malay ([#256](https://github.com/cucumber/gherkin/pull/256) glts)
* [Core] Add plural of Given in German ([#255](https://github.com/cucumber/gherkin/pull/255) glts)
* [Core] Added Thai (th) ([#253](https://github.com/cucumber/gherkin/pull/253) Twin Panichsombat)

## [2.12.0](https://github.com/cucumber/gherkin/compare/v2.11.8...v2.12.0)

* [Java, Ruby] TagExpression.eval() removed (Oleg Sukhodolsky)
* [Java, Ruby] Filter.eval() renamed to evaluate()  (Oleg Sukhodolsky)
* [Java, JRuby] JSONFormatter's java version changed to connect embedding to the recent step the same way as Ruby's one does. (Oleg Sukhodolsky)

## [2.11.8](https://github.com/cucumber/gherkin/compare/v2.11.7...v2.11.8)

* [Core] Added Galician (gl) ([#249](https://github.com/cucumber/gherkin/pull/249) son-cativo)
* [Java, JRuby] Set default i18n when create Parser ([#229](https://github.com/cucumber/gherkin/pull/229) Tomohiko Himura)
* [Java, JRuby] Filter out code keywords starting with a number: `I18n.code_keywords`. (Aslak Hellesøy)

## [2.11.7](https://github.com/cucumber/gherkin/compare/v2.11.6...v2.11.7)

* [Java, JRuby] Fix ArrayOutOfBoundException in JSONFormatter ([#239](https://github.com/cucumber/gherkin/issues/239) Joseph Hughes)
* [Java, Ruby] JSONFormatter outputs prettyfied json (Oleg Sukhodolsky)
* [Java, JRuby] JSONFormatter.appendDuration(), TagExpression.isEmpty() added to match ruby versions ([#234](https://github.com/cucumber/gherkin/issues/234) Oleg Sukhodolsky)
* [Core] New: Greek ([#237](https://github.com/cucumber/gherkin/issues/237), [#244](https://github.com/cucumber/gherkin/issues/244) Konstantinos Rousis)
* [Core] New: Old English (Englisc) added ([#240](https://github.com/cucumber/gherkin/issues/240), [#241](https://github.com/cucumber/gherkin/issues/241) Sean Miller)
* [JRuby] fixed problem with passing StringIO to PrettyFormatter (Oleg Sukhodolsky)
* [Ruby] MultiJson is used insted of JSon ([#235](https://github.com/cucumber/gherkin/issues/235) Erik Michaels-Ober)
* [Ruby] TagExpression.eval() deprecated, TagExpression.evaluate() added to replace it. ([#238](https://github.com/cucumber/gherkin/issues/238) Oleg Sukhodolsky)
* [Ruby] File name is displayed on parse error ([#247](https://github.com/cucumber/gherkin/issues/247), [#248](https://github.com/cucumber/gherkin/issues/248) @lukaso)
* [Java, JRuby] Fix IndexOutOfBound exception in PrettyFormatter ([#175](https://github.com/cucumber/gherkin/issues/175), [#242](https://github.com/cucumber/gherkin/issues/242) Matheus Neves, Oleg Sukhodolsky)

## [2.11.6](https://github.com/cucumber/gherkin/compare/v2.11.5...v2.11.6)

* [JavaScript] Add duration value to json formatter ([#204](https://github.com/cucumber/gherkin/pull/204) Rick Beyer)
* [JavaScript] Fix for JS Lexer ([#215](https://github.com/cucumber/gherkin/pull/215) Alexis Hevia)
* [JavaScript] Failing JS Features ([#228](https://github.com/cucumber/gherkin/issues/228) Aslak Hellesøy)
* [Core] Better Slovak ([#208](https://github.com/cucumber/gherkin/pull/208) Michal Kvasnicak)
* [Core] New: Tatar ([#213](https://github.com/cucumber/gherkin/pull/213) Valeriy Utyaganov)
* [Core] New: Telugu ([#218](https://github.com/cucumber/gherkin/pull/218) srinivasvedantam)
* [Core] New: Hindi ([#222](https://github.com/cucumber/gherkin/pull/222) Kumar Harsh)
* [Java] Fix JSON formatter ([#216](https://github.com/cucumber/gherkin/pull/216), [#195](https://github.com/cucumber/gherkin/pull/195) Joseph Hughes, Rex Hoffman)
* [Ruby,Java] Gherkin does not seem to parse BOM when feature contains language specification. ([#211](https://github.com/cucumber/gherkin/issues/211), [#212](https://github.com/cucumber/gherkin/pull/212) Rob Westgeest)
* [Java] Fixed a couple of build problems ([#226](https://github.com/cucumber/gherkin/pull/226), [#227](https://github.com/cucumber/gherkin/pull/227) signed)

## [2.11.5](https://github.com/cucumber/gherkin/compare/v2.11.4...v2.11.5)

* [Core] Czech translation cleaned a little bit up. ([#202](https://github.com/cucumber/gherkin/pull/202) Jakub Linhart)
* [JavaScript] No lexer modules in 2.11.4 NPM package ([#198](https://github.com/cucumber/gherkin/issues/198) Aslak Hellesøy)

## [2.11.4](https://github.com/cucumber/gherkin/compare/v2.11.3...v2.11.4)

* [Core] Support for file encodings different from UTF-8 in feature files. ([#158](https://github.com/cucumber/gherkin/issues/158) Aslak Hellesøy)
* [Core] Added Persian/Farsi (Sam Naseri)
* [JavaScript] Fix lexer instantiation. ([#197](https://github.com/cucumber/gherkin/pull/197) Julien Biezemans)

## [2.11.3](https://github.com/cucumber/gherkin/compare/v2.11.2...v2.11.3)

* [.NET] Upgraded IKVM from 0.46.0.1 to 7.1.4532.2 - quite a version bump! (Aslak Hellesøy)
* [JavaScript] Added a README to prevent npm warnings. (Aslak Hellesøy)
* [Ruby] Don't use C++ style comments. ([#191](https://github.com/cucumber/gherkin/pull/191) Sam Goldman)
* [Core] Fix for Australian language support ([#196](https://github.com/cucumber/gherkin/pull/196) hogfish)
* [Ruby] Add encoding option to IO.read ([#190](https://github.com/cucumber/gherkin/pull/190), [#192](https://github.com/cucumber/gherkin/issues/192) [#194](https://github.com/cucumber/gherkin/pull/194) HUANG Wei, Levin Alexander)
* [JavaScript] Can't run on IE because of `const` keyword ([#186](https://github.com/cucumber/gherkin/issues/186) Aslak Hellesøy)

## [2.11.2](https://github.com/cucumber/gherkin/compare/v2.11.1...v2.11.2)

* [Java] Depend on an external gherkin-jvm-deps jar with repackaged dependencies (Aslak Hellesøy, Rex Hoffman)
* [Core] Renamed i18n.yml to i18n.json, which simplifies the build system for Java. (Aslak Hellesøy)
* [Java Reporter should take embeddings as `byte[]` and not `InputStream` ([#184](https://github.com/cucumber/gherkin/issues/184) Aslak Hellesøy)
* [Core] A little addition to russian translation ([#183](https://github.com/cucumber/gherkin/pull/183) Sergey Sytsevich)

## [2.11.1](https://github.com/cucumber/gherkin/compare/v2.11.0...v2.11.1)

* [JavaScript] Native implementation of JSONFormatter (Aslak Hellesøy)
* [Core] Add more accurate polish translations. ([#181](https://github.com/cucumber/gherkin/pull/181) Adam Stankiewicz)

## [2.11.0](https://github.com/cucumber/gherkin/compare/v2.10.0...v2.11.0)

* [Core] Alias Feature with Business Need and Ability. ([#167](https://github.com/cucumber/gherkin/issues/167) Aslak Hellesøy)
* [Java] Better exception on bad filter mix. ([#179](https://github.com/cucumber/gherkin/issues/179) Aslak Hellesøy)
* [Java, Ruby] Formatters don't deal well with things not associated with a step ([#172](https://github.com/cucumber/gherkin/issues/172) David Kowis, Aslak Hellesøy)
* [Java] Make model classes implement `java.io.Serializable` ([#180](https://github.com/cucumber/gherkin/issues/180) Aslak Hellesøy)

## [2.10.0](https://github.com/cucumber/gherkin/compare/v2.9.3...v2.10.0)

* [Core] Added Malay language support. ([#176](https://github.com/cucumber/gherkin/pull/176) Choon Siong)
* [JRuby] Fixed `I18n.language_table` so that `cucumber --i18n help` works again on JRuby. ([cucumber #272](https://github.com/cucumber/cucumber/issues/272) Aslak Hellesøy)
* [Java] Line numbers are Integer instead of int or Long (Aslak Hellesøy)
* [Java, Ruby] Fix for exception when an argument is missing. ([#171](https://github.com/cucumber/gherkin/pull/171) Matt Nathan, Aslak Hellesøy)

## [2.9.3](https://github.com/cucumber/gherkin/compare/v2.9.2...v2.9.3)

* [Java] Fixed a bug in PrettyFormatter.setMonochrome(false) (Aslak Hellesøy)

## [2.9.2](https://github.com/cucumber/gherkin/compare/v2.9.1...v2.9.2)

* [Java] Ability to PrettyFormatter.setMonochrome(false) post construction (Aslak Hellesøy)

## [2.9.1](https://github.com/cucumber/gherkin/compare/v2.9.0...v2.9.1)

* [Java] Package gherkin.jar as OSGi bundle ([#166](https://github.com/cucumber/gherkin/pull/166) Jan Stamer)
* The build system and instruction has been updated to work on OS X Lion. (Aslak Hellesøy)

## [2.9.0](https://github.com/cucumber/gherkin/compare/v2.8.0...v2.9.0)

* Added output to Reporter API (Aslak Hellesøy)
* Reporter.embedding takes InputStream instead of byte[], which breaks API hence the minor bump. (Aslak Hellesøy)

## [2.8.0](https://github.com/cucumber/gherkin/compare/v2.7.7...v2.8.0)

* [Ruby/Java] TagExpression.eval() now takes a list of Tag instead of a list of String. This slightly breaks the API (minor bump), but helps fix some bugs in Cucumber-JVM. (Aslak Hellesøy)

## [2.7.7](https://github.com/cucumber/gherkin/compare/v2.7.6...v2.7.7)

* [C] Allow compilation with clang (datanoise)
* [Ruby] Expose AnsiEscapes methods on the class level as well, for easy calling without inclusion ([#161](https://github.com/cucumber/gherkin/pull/161) Ben Woosley)
* [Ruby] Lexer load failure fix ([#159](https://github.com/cucumber/gherkin/pull/159) Ben Woosley)

## [2.7.6](https://github.com/cucumber/gherkin/compare/v2.7.5...v2.7.6)

* [Java] Previous release accidentally had pretty formatting for both JSONFormatter and JSONPrettyFormatter (Aslak Hellesøy)

## [2.7.5](https://github.com/cucumber/gherkin/compare/v2.7.4...v2.7.5)

* [Java] Added Formatter.close() so we can close underlying streams after a call to Formatter.done() (Aslak Hellesøy)
* [Java] Switched from JSON simple to GSON  (Aslak Hellesøy)
* [Java] Added a JSONPrettyFormatter  (Aslak Hellesøy)

## [2.7.4](https://github.com/cucumber/gherkin/compare/v2.7.3...v2.7.4)

* Declared json and base64 dependencies as provided so they don't get included in dependant projects (Aslak Hellesøy)
* Improved Chinese translations ([#155](https://github.com/cucumber/gherkin/pull/155) Riceball LEE)
* Improved Czech translations ([#154](https://github.com/cucumber/gherkin/pull/154) Konstantin Kudryashov)

## [2.7.3](https://github.com/cucumber/gherkin/compare/v2.7.2...v2.7.3)

* [Java] Make sure Result constructed with a Throwable always has a JSON error_message (Aslak Hellesøy)

## [2.7.2](https://github.com/cucumber/gherkin/compare/v2.7.1...v2.7.2)

* [Java] Added gherkin.I18n.getAll() (Aslak Hellesøy)

## [2.7.1](https://github.com/cucumber/gherkin/compare/v2.7.0...v2.7.1)

2.7.0 release got hosed

## [2.7.0](https://github.com/cucumber/gherkin/compare/v2.6.9...v2.7.0)

### Changed Features

* Formatter.close no longer closes streams. (Alan Parkinson)
* Formatter.close renamed to Formatter.done. (Aslak Hellesøy)

## [2.6.9](https://github.com/cucumber/gherkin/compare/v2.6.8...v2.6.9)

### Changed Features

* Pretty formatter always uses \n for newlines, instead of using platform-specific newline. This makes cross-platform testing less fiddly. (Aslak Hellesøy)

## [2.6.8](https://github.com/cucumber/gherkin/compare/v2.6.7...v2.6.8)

### Changed Features

* Steps can be empty. The main reason for this is to allow Cukepatch to show an autocomplete right after a keyword. ([#149](https://github.com/cucumber/gherkin/issues/149) Aslak Hellesøy)

## [2.6.7](https://github.com/cucumber/gherkin/compare/v2.6.6...v2.6.7)

### Bugfixes

* Fix bad packaging of NuSpec package. ([#148](https://github.com/cucumber/gherkin/issues/148) Aslak Hellesøy)

## [2.6.6](https://github.com/cucumber/gherkin/compare/v2.6.5...v2.6.6)

### New Features

* .NET dll is released as a NuGet package instead of being uploaded to https://github.com/cucumber/gherkin/downloads ([#144](https://github.com/cucumber/gherkin/issues/144), [#147](https://github.com/cucumber/gherkin/pull/147) Jeffrey Cameron, Aslak Hellesøy)

### Changed Features

* The lib/gherkin.jar that goes into the JRuby gem contains json-simple and base64 classes instead of separate jars (Aslak Hellesøy)
* .NET dll, which is based on lib/gherkin.jar also contains json-simple and base64 (Aslak Hellesøy)
* gherkin jars published to Maven repo do *not* bundle the json-simple and base64 jars. (Aslak Hellesøy)

### Removed Features

* IronRuby gems are no longer published. Nobody seems to be using it, and it's too much pain to test. (Aslak Hellesøy)

## [2.6.5](https://github.com/cucumber/gherkin/compare/v2.6.4...v2.6.5)

### Bugfixes

* Bad filtering when Scenario Outline+Examples followed by Scenario ([https://github.com/cucumber/gherkin/issues/145](#145) Aslak Hellesøy)

## [2.6.4](https://github.com/cucumber/gherkin/compare/v2.6.3...v2.6.4)

### Changed Features

* [Node.js] No more restrictions about what Node.js version this runs on. (Aslak Hellesøy)

## [2.6.3](https://github.com/cucumber/gherkin/compare/v2.6.2...v2.6.3)

### Changed Features

* Windows gems are compiled against 1.9.3 instead of 1.9.2. Should still work on 1.9.3. (Aslak Hellesøy)
* NPM package contains UglifyJS-minified scripts (Aslak Hellesøy)
* NPM package no longer contains Ace ([#109](https://github.com/cucumber/gherkin/issues/109) Aslak Hellesøy)
* JavaScript exports for require.js works in non-AMD mode (needed for Ace) (Aslak Hellesøy)

## [2.6.2](https://github.com/cucumber/gherkin/compare/v2.6.1...v2.6.2)

### Changed Features

* [Java] Minor internal API changes needed for better arity mismatch reporting in Cucumber-JVM (Aslak Hellesøy)

## [2.6.1](https://github.com/cucumber/gherkin/compare/v2.6.0...v2.6.1)

### Bugfixes

* Fixed a regression on 1.8.7 (Aslak Hellesøy)

## [2.6.0](https://github.com/cucumber/gherkin/compare/v2.5.4...v2.6.0)

### Changed Features

* JSON formatter must handle multiple features ([#140](https://github.com/cucumber/gherkin/issues/140) Aslak Hellesøy)

### New Features

* Identifiers for Gherkin elements ([#131](https://github.com/cucumber/gherkin/issues/131) Matt Wynne, Aslak Hellesøy)

## [2.5.4](https://github.com/cucumber/gherkin/compare/v2.5.3...v2.5.4)

### New Features
* [Java] Rename/add constants in Match and Result. (Aslak Hellesøy)

## [2.5.3](https://github.com/cucumber/gherkin/compare/v2.5.2...v2.5.3)

### New Features
* [Java] AnsiEscapes can append to a NiceAppendable. (Aslak Hellesøy)

## [2.5.2](https://github.com/cucumber/gherkin/compare/v2.5.1...v2.5.2)

### New Features

* Added synonyms for Portuguese. (Rodrigo Dumont)
* Added synonyms for Italian. (Alessandro Mencarini)
* Added synonyms for Spanish. (Nahuel Garbezza)
* Added synonyms for French. (Julien Biezemans)

## [2.5.1](https://github.com/cucumber/gherkin/compare/v2.5.0...v2.5.1)

### New Features
* [Java] Omit transient fields in toMap so we have more control over what goes into JSON. (Aslak Hellesøy)

## [2.5.0](https://github.com/cucumber/gherkin/compare/v2.4.21...v2.5.0)

### Changed Features

The JSON representation of features has changed. The difference lies in how Doc Strings and Data Tables are represented. Consider the following steps:

    Given a Data Table:
      | Hipster | Ipsum |
      | Freegan | Vinyl |
    Given a Doc String:
      """
      Hipster
      Ipsum
      """

This is now represented in JSON as:

    "steps": [
      {
        "keyword": "Given ",
        "name": "a Data Table"
        "line": 5,
        "rows": [
          { "cells": ["Hipster", "Ipsum"], "line": 6 },
          { "cells": ["Freegan", "Vinyl"], "line": 7 }
        ]
      },
      {
        "keyword": "Given ",
        "name": "a Doc String"
        "line": 8,
        "doc_string": {
          "value": "Hipster\nIpsum"
          "line": 9,
          "content_type": "plaintext",
        }
      }
    ]

Previously it would be represented in JSON as:

    "steps": [
      {
        "keyword": "Given ",
        "name": "a Data Table"
        "line": 5,
        "multiline_arg": {
          "type": "table"
          "value": [
            { "cells": ["Hipster", "Ipsum"], "line": 6 },
            { "cells": ["Freegan", "Vinyl"], "line": 7 }
          ]
        }
      },
      {
        "keyword": "Given ",
        "name": "a Doc String"
        "line": 8,
        "multiline_arg": {
          "type": "doc_string"
          "value": "Hipster\nIpsum"
          "line": 9,
          "content_type": "plaintext",
        }
      }
    ]

### Bugfixes
* Java JSONFormatter produces invalid JSON ([#128](https://github.com/cucumber/gherkin/issues/128) Aslak Hellesøy)
* Missing matches and results in JSONFormatter output ([#129](https://github.com/cucumber/gherkin/issues/129) Aslak Hellesøy)

## [2.4.21](https://github.com/cucumber/gherkin/compare/v2.4.20...v2.4.21)

### Bugfixes
* Revert json dependency back to >= 1.4.6 since many apps still use old json. (Aslak Hellesøy)

## [2.4.20](https://github.com/cucumber/gherkin/compare/v2.4.18...v2.4.20)

(The 2.4.19 release got messed up).

### Bugfixes

* Filtering on Examples with 0 or 1 row now works ([from mailing list](http://groups.google.com/group/cukes/browse_thread/thread/3e55777ee29c445c) Aslak Hellesøy)
* Exclude .gitignore files from packaged gem ([#125](https://github.com/cucumber/gherkin/pull/125) John Hume)

## [2.4.18](https://github.com/cucumber/gherkin/compare/v2.4.17...v2.4.18)

### New Features

* Fenced Code Blocks for DocStrings ([#123](https://github.com/cucumber/gherkin/issues/123) Gregory Hnatiuk, Aslak Hellesøy)

## [2.4.17](https://github.com/cucumber/gherkin/compare/v2.4.16...v2.4.17)

### New Features

* Jar file is now deployed to Maven central, making installation a little easier (Aslak Hellesøy)
* Both Ruby and Java API docs are on cukes.info. See README for details. (Aslak Hellesøy)

## [2.4.16](https://github.com/cucumber/gherkin/compare/v2.4.15...v2.4.16)

### New Features

* [Java] TagExpression.eval() takes a Collection instead of List. That lets us pass a Set. (Aslak Hellesøy)

## [2.4.15](https://github.com/cucumber/gherkin/compare/v2.4.14...v2.4.15)

### New Features

* [Java] Pretty formatter knows how to print deleted (-) or inserted (+) table rows. (Aslak Hellesøy)

## [2.4.14](https://github.com/cucumber/gherkin/compare/v2.4.13...v2.4.14)

### New Features

* [Java] Publish source jars to Maven repo (Aslak Hellesøy)
* [Java] Made NiceAppendable public - it's handy for Cucumber-JVM too (Aslak Hellesøy)

## [2.4.13](https://github.com/cucumber/gherkin/compare/v2.4.12...v2.4.13)

No changes, but previous release failed while pushing gems (rubygems.org bug?)

## [2.4.12](https://github.com/cucumber/gherkin/compare/v2.4.11...v2.4.12)

### Bugfixes

* [Java] Fixed Maven warnings. (Aslak Hellesøy)

### New Features

* [Java] Fixed bug in Result.getErrorMessage() when it was created with a Throwable. (Aslak Hellesøy)
* [Java] PrettyFormatter and JSONFormatter take Appendable instead of OutputStream/Writer. (Aslak Hellesøy)

### Changed Features

* [Java] PrettyFormatter and JSONFormatter no longer flush. Make sure you pass an OutputStream/Writer that autoflushes. (Aslak Hellesøy)

## [2.4.11](https://github.com/cucumber/gherkin/compare/v2.4.10...v2.4.11)

### Bugfixes

* Changed rake-compiler version from '~> 1.7.9' to '>= 1.7.9' since the fix in 2.4.10 seemed to have no effect. I hate the Ruby ecosystem today. (Aslak Hellesøy)

## [2.4.10](https://github.com/cucumber/gherkin/compare/v2.4.9...v2.4.10)

### Bugfixes

* Changed rake-compiler version from '1.7.9' to '~> 1.7.9' since the fix in 2.4.9 seemed to have no effect. (Aslak Hellesøy)

## [2.4.9](https://github.com/cucumber/gherkin/compare/v2.4.8...v2.4.9)

### Bugfixes

* Changed rake-compiler version from '= 1.7.9' to '1.7.9' to work around install problems caused by https://github.com/rubygems/rubygems/pull/121 (Aslak Hellesøy)

## [2.4.8](https://github.com/cucumber/gherkin/compare/v2.4.7...v2.4.8)

### Bugfixes

* Remove another awesome_print which was left over from 2.4.7. (Aslak Hellesøy)
* Make specs/features pass when LANG=C (or LC_CTYPE=C). (#118 Antonio Terceiro, Aslak Hellesøy)

## [2.4.7](https://github.com/cucumber/gherkin/compare/v2.4.6...v2.4.7)

### Bugfixes

* Remove awesome_print (which was used for debuging). (Antonio Terceiro)

### New Features

* Added I18n.getLocale() to Java impl. (Aslak Hellesøy)

## [2.4.6](https://github.com/cucumber/gherkin/compare/v2.4.5...v2.4.6)

### Bugfixes
* Added Icelandic translation (Ægir Örn Símonarson)
* JavaScript: String prototype has no "trimRight" function. Fixes failure in Opera. (Julien Biezemans)

## [2.4.5](https://github.com/cucumber/gherkin/compare/v2.4.4...v2.4.5)

No changes, releasing again since the 2.4.4 release failed halfway through.

## [2.4.4](https://github.com/cucumber/gherkin/compare/v2.4.3...v2.4.4)

### Bugfixes
* JRuby fixes. Symbols and streams are now properly converted before passing from ruby to java. (Aslak Hellesøy)
* json-simple and base64 jar files (used by some of the java classes) are now embedded in the jruby gem (Aslak Hellesøy)

## [2.4.3](https://github.com/cucumber/gherkin/compare/v2.4.2...v2.4.3)

### Changed Features
* Added a small hack to the java Result class to work around [Cucumber bug #97](https://github.com/cucumber/cucumber/issues/97) (Aslak Hellesøy)

## [2.4.2](https://github.com/cucumber/gherkin/compare/v2.4.1...v2.4.2)

### Changed Features
* Formatter and Reporter are now two distinct interfaces. JSONParser takes one of each in ctor. (Aslak Hellesøy)

## [2.4.1](https://github.com/cucumber/gherkin/compare/v2.4.0...v2.4.1)

### New Features
* None - just updated build system to the latest Cucumber (Aslak Hellesøy)

## [2.4.0](https://github.com/cucumber/gherkin/compare/v2.3.10...v2.4.0)

### Bugfixes
* Don't use -Werror in production code ([#106](https://github.com/cucumber/gherkin/pull/106) Hans de Graaff)

### New Features
* YARD based API docs at http://cukes.info/gherkin/api/ruby/latest/ (Aslak Hellesøy)

### Changed Features
* py_string/PyString changed to doc_string/DocString, ref https://github.com/cucumber/cucumber/issues/74 (Aslak Hellesøy)

## [2.3.10](https://github.com/cucumber/gherkin/compare/v2.3.9...v2.3.10)

### Bugfixes
* Relax development dependency version on builder. (#105 Aslak Hellesøy).

## [2.3.9](https://github.com/cucumber/gherkin/compare/v2.3.8...v2.3.9)

### New features
* Javascript lexers support http://requirejs.org/ modules as well as node.js (Aslak Hellesøy).

## [2.3.8](https://github.com/cucumber/gherkin/compare/v2.3.7...v2.3.8)

### Insignificant changes
* Improve build system so we don't need to add generated js lexers to git.

## [2.3.7](https://github.com/cucumber/gherkin/compare/v2.3.6...v2.3.7)

* Removed incorrect (and unneeded) case statement that could blow up if V8 is installed. (Aslak Hellesøy, Niklas H)
* Added connect support for gherkin.js (Aslak Hellesøy)

## [2.3.6](https://github.com/cucumber/gherkin/compare/v2.3.5...v2.3.6)

### New Features
* Javascript implementation (#38 Aslak Hellesøy)

### Bugfixes
* Fix compilation error on Arch Linux (#98,#99 Ben Hamill)
* Corrected Russian translation (#97 Vagif Abilov)

## [2.3.5](https://github.com/cucumber/gherkin/compare/v2.3.4...v2.3.5)

### Changes
* Relaxed gem dependencies to use >=. (Rob Slifka, Aslak Hellesøy)

## [2.3.4](https://github.com/cucumber/gherkin/compare/v2.3.3...v2.3.4)

### Changes
* Fixing C90 errors on Ubuntu Natty (#92 Colin Dean)
* Romanian (ro) language update, extracted from a real-world project. (Iulian Dogariu)

## [2.3.3](https://github.com/cucumber/gherkin/compare/v2.3.2...v2.3.3)

### Changes
* No more dependencies on external ANSI escape libraries (Ruby:term-ansicolor, Java:Jansi). DIY is better! (Aslak Hellesøy)
* Added duration (in millseconds) to Result. (Aslak Hellesøy)
* Additional Polish aliases (Mike Połtyn)

## [2.3.2](https://github.com/cucumber/gherkin/compare/v2.3.0...v2.3.2)

(Somehow 2.3.1 was released improperly shortly after 2.3.0 - not sure what fixes went into that!)

### Bugfixes
* Preserve whitespace in descriptions. Leading whitespace in descriptions are stripped upto preceding keyword + 2 spaces (#87 Matt Wynne, Gregory Hnatiuk, Aslak Hellesøy)
* Fix incorrect indentation of Examples descriptions (Gregory Hnatiuk)
* Can't define new line characters in Example Table Cell's Content. (#85 George Montana Harkin, Aslak Hellesøy)

## [2.3.0](https://github.com/cucumber/gherkin/compare/v2.2.9...v2.3.0)

### New Features
* New aliases for Scenario Outline in Swedish, Norwegian and English. (Peter Krantz, Aslak Hellesøy)
* Improved build documentation for people who want to contribute. (Aslak Hellesøy)
* Results can now be outputted/parsed in JSON. (Aslak Hellesøy)
* JSON output now contains optional "match", "result" and "embeddings" elements underneath each step. (Aslak Hellesøy)
* Added support for Base64 encoded embeddings in JSON representation. Useful for screenshots etc. (Aslak Hellesøy)

## [2.2.9](https://github.com/cucumber/gherkin/compare/v2.2.8...v2.2.9)

### New Features
* PrettyFormatter can format features both with and without ANSI Colors. Using Jansi on Java. (Aslak Hellesøy)
* Extended Java Formatter API with a steps(List<Step>) method for better reporting in Java (Aslak Hellesøy)

## [2.2.8](https://github.com/cucumber/gherkin/compare/v2.2.7...v2.2.8)

### Removed Features
* Trollop based CLI - didn't find a good use for it yet. (Aslak Hellesøy)

## [2.2.7](https://github.com/cucumber/gherkin/compare/v2.2.6...v2.2.7)

### Bugfixes
* I18n.getCodeKeywords() on Java didn't strip '!'. Not anymore. (Aslak Hellesøy)

## [2.2.6](https://github.com/cucumber/gherkin/compare/v2.2.5...v2.2.6)

### Bugfixes
* I18n.getCodeKeywords() on Java included '*'. Not anymore. (Aslak Hellesøy)

## [2.2.5](https://github.com/cucumber/gherkin/compare/v2.2.4...v2.2.5)

### New Features
* Gherkin will scan all top comments for the language comment. (Aslak Hellesøy)

## [2.2.4](https://github.com/cucumber/gherkin/compare/v2.2.3...v2.2.4)

### Bugfixes
* C99 features used by gherkin code (#75 Graham Agnew)

## [2.2.3](https://github.com/cucumber/gherkin/compare/v2.2.2...v2.2.3)

### Bugfixes
* Add back missing development dependency on cucumber (Aslak Hellesøy)

## [2.2.2](https://github.com/cucumber/gherkin/compare/v2.2.1...v2.2.2)

### New Features
* Use json instead of json_pure (Aslak Hellesøy)
* JSON formatter and parser can now omit JSON serialization (for speed) and work directly on objects (Aslak Hellesøy)

## [2.2.1](https://github.com/cucumber/gherkin/compare/v2.2.0...v2.2.1)

### New Features
* Windows gems are now built against 1.8.6-p287 and 1.9.1-p243, on both mswin32 and mingw32, and should work on 1.8.6, 1.8.7, 1.9.1 and 1.9.2 versions of rubyinstaller.org as well as older windows rubies. (Aslak Hellesøy)

### Changed features
* Build system no longer uses Jeweler - only Rake, Bundler and Rubygems (Aslak Hellesøy)

## [2.2.0](https://github.com/cucumber/gherkin/compare/v2.1.5...v2.2.0)

This release breaks some APIs since the previous 2.1.5 release. If you install gherkin 2.2.0 you must also upgrade to
Cucumber 0.9.0.

### Bugfixes
* I18nLexer doesn't recognise language header with \r\n on OS X. (#70 Aslak Hellesøy)

### New Features
* Pure Java FilterFormatter. (Aslak Hellesøy)
* Pure Java JSONFormatter. (Aslak Hellesøy)

### Changed Features
* All formatter events take exactly one argument. Each argument is a single object with all data. (Aslak Hellesøy)
* Several java classes have moved to a different package in order to improve separation of concerns. (Aslak Hellesøy)

## [2.1.5](https://github.com/cucumber/gherkin/compare/v2.1.4...v2.1.5)

### Bugfixes
* Line filter works on JRuby with Scenarios without steps. (Aslak Hellesøy)

### Changed Features
* The JSON schema now puts background inside the "elements" Array. Makes parsing simpler. (Aslak Hellesøy)

## [2.1.4](https://github.com/cucumber/gherkin/compare/v2.1.3...v2.1.4)

### Bugfixes
* #steps fails on JRuby with 2.1.3 (#68 Aslak Hellesøy)

## [2.1.3](https://github.com/cucumber/gherkin/compare/v2.1.2...v2.1.3)

### Bugfixes
* Examples are not cleared when an ignored Scenario Outline/Examples is followed by a Scenario. (#67 Aslak Hellesøy)

## [2.1.2](https://github.com/cucumber/gherkin/compare/v2.1.1...v2.1.2)

### Bugfixes
* Fix some missing require statements that surfaced when gherkin was used outside Cucumber. (Aslak Hellesøy)

## [2.1.1](https://github.com/cucumber/gherkin/compare/v2.1.0...v2.1.1)

The previous release had a missing gherkin.jar in the jruby gem. This release fixes that. For good this time!

## [2.1.0](https://github.com/cucumber/gherkin/compare/v2.0.2...v2.1.0)

### New Features
* Pirate! (anteaya)
* Tag limits for negative tags (Aslak Hellesøy)

### Changed Features
* The formatter API has changed and the listener API is now only used internally. (Aslak Hellesøy)

### Removed Features
* FilterListener has been replaced with FilterFormatter. Currently only in Ruby (no Java impl yet). (Aslak Hellesøy)

## [2.0.2](https://github.com/cucumber/gherkin/compare/v2.0.1...v2.0.2)

### New Features
* New JSON Lexer. (Gregory Hnatiuk)

### Bugfixes
* Fixed incorrect indentation for descriptions in Java. (Aslak Hellesøy)
* Fixed support for xx-yy languages and Hebrew and Indonesian (JDK bugs). (Aslak Hellesøy)

### Changed Features
* Examples are now nested inside the Scenario Outline in the JSON format. (Gregory Hnatiuk)

## [2.0.1](https://github.com/cucumber/gherkin/compare/v2.0.0...v2.0.1)

The previous release had a missing gherkin.jar in the jruby gem. This release fixes that.

## [2.0.0](https://github.com/cucumber/gherkin/compare/v1.0.30...v2.0.0)

We're breaking the old listener API in this release, and added a new JSON formatter,
which calls for a new major version.

### New Features
* New JSON formatter. (Aslak Hellesøy, Joseph Wilk)
* New synonyms for Hungarian (Bence Golda)
* Upgraded to use RSpec 2.0.0 (Aslak Hellesøy)

### Bugfixes
* undefined method `<=>' on JRuby (#52 Aslak Hellesøy)
* Include link to explanation of LexingError (Mike Sassak)

### Changed Features
* The formatter API has completely changed. There is a Gherkin Listener API and a Formatter API.
  The FormatterListener acts as an adapter between them. (Aslak Hellesøy)
* The listener API now has an additional argument for description (text following the first line of Feature:, Scenario: etc.) (Gregroy Hnatiuk, Matt Wynne)

## [1.0.30](https://github.com/cucumber/gherkin/compare/v1.0.29...v1.0.30)

### New Features
* Native gems for IronRuby. Bundles IKVM OpenJDK dlls as well as ikvmc-compiled gherkin.dll. Experimental! (Aslak Hellesøy)

## [1.0.29](https://github.com/cucumber/gherkin/compare/v1.0.28...v1.0.29)

### Bugfixes
* Use I18n.class' class loader instead of context class loader to load Java lexers. Hoping this fixes loading bug for good. (Aslak Hellesøy)

## [1.0.28](https://github.com/cucumber/gherkin/compare/v1.0.27...v1.0.28)

### Bugfixes
* Use context class loader instead of boot class loader to load Java lexers. (Aslak Hellesøy)
* Only add gcc flags when the compiler is gcc. (#60 Aslak Hellesøy, Christian Höltje)

## [1.0.27](https://github.com/cucumber/gherkin/compare/v1.0.26...v1.0.27)

### New Features
* Table cells can now contain escaped bars - \| and escaped backslashes - \\. (#48. Gregory Hnatiuk, Aslak Hellesøy)
* Luxemburgish (lu) added. (Christoph König)

## [1.0.26](https://github.com/cucumber/gherkin/compare/v1.0.25...v1.0.26)

### New Features
* Ignore the BOM that many retarded Windows editors insist on sticking in the beginning of a file. (Aslak Hellesøy)

## [1.0.25](https://github.com/cucumber/gherkin/compare/v1.0.24...v1.0.25)

### Bugfixes
* Allow fallback to a slower ruby lexer if the C lexer can't be loaded for some reason.
* Can't run specs in gherkin 1.0.24 (#59 Aslak Hellesøy)

## [1.0.24](https://github.com/cucumber/gherkin/compare/v1.0.23...v1.0.24)

### Bugfixes
* hard tabs crazy indentation for pystrings in formatter (#55 Aslak Hellesøy)

## [1.0.23](https://github.com/cucumber/gherkin/compare/v1.0.22...v1.0.23)

### Changed Features
* Java API now uses camelCased method names instead of underscored (more Java-like) (Aslak Hellesøy)

## [1.0.22](https://github.com/cucumber/gherkin/compare/v1.0.21...v1.0.22)

### Bugfixes
* Make prebuilt binaries work on both Ruby 1.8.x and 1.9.x on Windows (#54 Luis Lavena, Aslak Hellesøy)

## [1.0.21](https://github.com/cucumber/gherkin/compare/v1.0.20...v1.0.21)

### Bugfixes
* Fix compile warning on ruby 1.9.2dev (2009-07-18 trunk 24186) (#53 Aslak Hellesøy)

## [1.0.20](https://github.com/cucumber/gherkin/compare/v1.0.19...v1.0.20)

### Bugfixes
* The gherkin CLI is working again (Gregory Hnatiuk)

## [1.0.19](https://github.com/cucumber/gherkin/compare/v1.0.18...v1.0.19)

### New Features
* Works with JRuby 1.5.0.RC1 (Aslak Hellesøy)

### Changed Features
* I18n.code_keywords now return And and But as well, making Cucumber StepDefs a little more flexible (Aslak Hellesøy)

## [1.0.18](https://github.com/cucumber/gherkin/compare/v1.0.17...v1.0.18)

### Bugfixes
* Explicitly use UTF-8 encoding when scanning source with Java lexer. (Aslak Hellesøy)

## [1.0.17](https://github.com/cucumber/gherkin/compare/v1.0.16...v1.0.17)

### Bugfixes
* Gherkin::I18n.keyword_regexp was broken (used for 3rd party code generation). (#51 Aslak Hellesøy)

## [1.0.16](https://github.com/cucumber/gherkin/compare/v1.0.15...v1.0.16)
(Something went wrong when releasing 1.0.15)

### Bugfixes
* Reduced risk of halfway botched releases. (Aslak Hellesøy)

## [1.0.15](https://github.com/cucumber/gherkin/compare/v1.0.14...v1.0.15)

### New Features
* Implemented more functionality in I18n.java. (Aslak Hellesøy)

### Changed Features
* Java methods are no longer throwing Exception (but RuntimeException). (Aslak Hellesøy)

## [1.0.14](https://github.com/cucumber/gherkin/compare/v1.0.13...v1.0.14)
(Something went wrong when releasing 1.0.13)

## [1.0.13](https://github.com/cucumber/gherkin/compare/v1.0.12...v1.0.13)

### New Features
* Filter on Background name. (Aslak Hellesøy)

## [1.0.12](https://github.com/cucumber/gherkin/compare/v1.0.11...v1.0.12)

### Bugfixes
* Fixed incorrect filtering of pystring in Background. (Mike Sassak)

## [1.0.11](https://github.com/cucumber/gherkin/compare/v1.0.10...v1.0.11)

### Bugfixes
* Fixed bad packaging (C files were not packaged in POSIX gem)

## [1.0.10](https://github.com/cucumber/gherkin/compare/v1.0.09...v1.0.10)

### New Features
* Added Esperanto and added a Russian synonym for Feature. (Antono Vasiljev)
* Pure Java implementation of FilterListener and TagExpression (Mike Gaffney, Aslak Hellesøy)

### Changed Features
* TagExpression takes array args instead of varargs. (Aslak Hellesøy)

## [1.0.9](https://github.com/cucumber/gherkin/compare/v1.0.8...v1.0.9)

### Bugfixes
* Triple escaped quotes (\"\"\") in PyStrings are unescaped to """. (Aslak Hellesøy)

## [1.0.8](https://github.com/cucumber/gherkin/compare/v1.0.7...v1.0.8)

### Bugfixes
* Removed illegal comma from Ukrainian synonym. (Aslak Hellesøy)

## [1.0.7](https://github.com/cucumber/gherkin/compare/v1.0.6...v1.0.7)

### Bugfixes
* Fixed problems with packaging of 1.0.6 release. (Aslak Hellesøy)

## [1.0.6](https://github.com/cucumber/gherkin/compare/v1.0.5...v1.0.6)

### New Features
* Fully automated release process. (Aslak Hellesøy)

### Changed Features
* Made generated classes use a more uniform naming convention. (Aslak Hellesøy)

### Removed Features
* Removed C# port, obsoleted by IKVM build from 1.0.5. (Aslak Hellesøy)

## [1.0.5](https://github.com/cucumber/gherkin/compare/v1.0.4...v1.0.5)

### New Features
* New .NET build of gherkin - an ikvmc build of gherkin.jar to gherkin.dll. (Aslak Hellesøy)

### Bugfixes
* Made parsers reusable so that the same instance can parse several features. (Aslak Hellesøy)

## [1.0.4](https://github.com/cucumber/gherkin/compare/v1.0.3...v1.0.4)

### New features
* Pure java releases of Gherkin at http://cukes.info/maven
* A FilterListener in Ruby that is the last missing piece to plug Gherkin into Cucumber. (Gregory Hnatiuk, Aslak Hellesøy, Matt Wynne, Mike Sassak)

### Changed features
* The Lexer now emits the '@' for tags. (Aslak Hellesøy)

## [1.0.3](https://github.com/cucumber/gherkin/compare/v1.0.2...v1.0.3)

### Bugfixes
* The C lexer correctly instantiates a new array for each table, instead of reusing the old one. (Aslak Hellesøy)
* Emit keywords with space instead of stripping (< keywords are emmitted without space) (Aslak Hellesøy)
* gherkin reformat now prints comments, and does it with proper indentation (Aslak Hellesøy)
* .NET resource files are now automatically copied into the .dll (#46 Aslak Hellesøy)

### New features
* The Pure Java implementation now has a simple main method that pretty prints a feature. (#39 Aslak Hellesøy) 
* Writing code generated i18n syntax highlighters for Gherkin is a lot easier thanks to several convenience methods in Gherkin::I18n. (Aslak Hellesøy)
* .NET (C#) port (#36, #37 Attila Sztupak)
* Tables parsed and sent by row rather than by table. (Mike Sassak)

### Changed features
* Switced to ISO 639-1 (language) and ISO 3166 alpha-2 (region - if applicable). Applies to Catalan,
  Swedish, Welsh, Romanian and Serbian. (Aslak Hellesøy)

## [1.0.2](https://github.com/cucumber/gherkin/compare/v1.0.1...v1.0.2)

### Bugfixes
* Build passes on Ruby 1.9.2 (Aslak Hellesøy)

### New features
* New command line based on trollop. Commands: reformat, stats. (Aslak Hellesøy)
* I18nLexer#scan sets #language to the I18n for the language scanned (Mike Sassak)
* I18n#adverbs, brings I18n to parity with Cucumber::Parser::NaturalLanguage (Mike Sassak)
