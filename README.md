[![Build Status](https://secure.travis-ci.org/cucumber/gherkin.png)](http://travis-ci.org/cucumber/gherkin)

A fast lexer and parser for the Gherkin language based on Ragel. Gherkin is two things:

* The language that has evolved out of the Cucumber project.
* This library.

Supported platforms:

* Ruby 1.8.6-1.9.3 (MRI, JRuby, REE, Rubinius)
* Pure Java (jar file)
* JavaScript (NPM package. Tested with V8/node.js/Chrome, but might work on other JavaScript engines)
* .NET (dll file)

## Installation.pmlcolor"

### Ruby/JRuby

    gem install gherkin

#### Troubleshooting

On JRuby you may get an error saying:

    ERROR:  While executing gem ... (ArgumentError)
    undefined class/module YAML::Syck::DefaultKey

You can get around this problem by upgrading rubygems:

    jruby -S gem install rubygems-update
    gem update --system

Another problem you might encounter is:

    ERROR: While executing gem ... (ArgumentError)
    invalid byte sequence in US-ASCII

If this happens, try defining your shell's encoding:

    # Linux
    export LANG=en_US.UTF-8

    # OS X
    export LC_CTYPE=en_US.UTF-8

### Node.js

    npm install gherkin

### Java

The jar file is in the central Maven repo.

    <dependency>
        <groupId>info.cukes</groupId>
        <artifactId>gherkin</artifactId>
        <version>2.6.2</version>
    </dependency>

You can get it manually from [Maven Central](http://search.maven.org/#browse%7C-2073395818)

### .NET

Get the dll from https://github.com/cucumber/gherkin/downloads

## API Docs

* [Ruby](http://cukes.info/gherkin/api/ruby/latest/)
* [Java](http://cukes.info/gherkin/api/java/latest/apidocs/index.html)

## Hacking: Installing the toolchain

Due to the cross-platform nature of this library, you have to install a lot of tools to build gherkin yourself.
In order to make it easier for occasional contributors to get the development environment up and running, you don't
have to install everything up front. The build scripts should tell you if you are missing something. For example,
you shouldn't have to install MinGW to build windows binaries if you are a Linux user and just want to fix a bug in
the C code.

### Common dependencies

These are the minimal tools you need to install:

* Ragel (brew install ragel or apt-get install ragel)
* Ruby (any version should do).
* A clone of the cucumber git repo to a "cucumber" sibling folder of your gherkin folder. (Only needed to run cucumber tests)
* RVM (you may not need this if you are only building for a single platform)

With this minimal tool chain installed, install Ruby gems needed by the build:

    gem install bundler
    bundle install

Running RSpec and Cucumber tests

    rake clean spec cucumber

If the RL_LANGS environment variable is set, only the parsers for the languages specified there will be built.
E.g. in Bash, export RL_LANGS="en,fr,no". This can be quite helpful when modifying the Ragel grammar.

See subsections for building for a specific platform.

### MRI, REE or Rubinius

You'll need GCC installed.

Build the gem with:

    rake build

### Pure Java and JRuby

You must install JRuby to build the pure Java jar or the JRuby gem:

    rvm install jruby
    rvm use jruby
    rvm gemset create cucumber
    rvm gemset use cucumber
    gem install bundler
    bundle install

Now you can build the jar with:

    rake clean jar

### JavaScript

In order to build and test Gherkin for JavaScript you must install:

* Node.js (0.4.6 or higher)
* NPM (0.3.18 or higher)
* Ragel with JavaScript support: http://github.com/dominicmarks/ragel-js
* Define the GHERKIN_JS environment variable in your shell (any value will do)

Prepare the environment:

    pushd js
    npm link
    popd

Now you can build the JavaScript with:

    rake js

And you can try it out with node.js:

    node js/example/print.js spec/gherkin/fixtures/1.feature

You can also try out Gherkin running in the browser (likely to move to a separate project):

    # Pull in the Ace (http://ace.ajax.org/) editor:
    git submodule update --init
    # Open a sample Gherkin-powered editor in Chrome
    open js/example/index.html

If you're hacking and just want to rebuild the English parser:

  rake js/lib/gherkin/lexer/en.js

TODO: Make all specs pass with js lexer - replace 'c(listener)' with 'js(listener)' in i18n.rb

### .NET and IronRuby

You must install Mono and IKVM to build the pure .NET dll and the IronRuby gem:

* Install Mono from http://www.mono-project.com/ and make sure it's on your $PATH
* Download IKVM from http://www.ikvm.net/ and extract it to /usr/local/ikvm so that you have a /usr/local/ikvm/bin/ikvmc.exe

Now you can build the .NET dll with:

    rake ikvm

### MinGW Rubies (for Windows gems)

In order to build Windows binaries (so we can release Windows gems from OS X/Linux) we first need to install MinGW. 
On OS X, download prebuilt binaries (version 4.3.0) from http://crossgcc.rts-software.org/doku.php. 
Unpack it under /usr/local and add it to your PATH, typically in your `~/.bashrc`:

    export PATH=$PATH:/usr/local/i386-mingw32-4.3.0/bin

Now we need to set up [rake-compiler](http://github.com/luislavena/rake-compiler/)
We'll start by installing some rubies.

Make sure you have openssl installed first.

    brew install openssl

    # 1.8.7
    rvm install 1.8.7-p352
    rvm use 1.8.7-p352
    rvm gemset create cucumber
    rvm gemset use cucumber
    gem install bundler
    unset GHERKIN_JS
    bundle install
    rake-compiler cross-ruby VERSION=1.8.7-p352

    # 1.9.3
    rvm install 1.9.3-p0
    rvm use 1.9.3-p0
    rvm gemset create cucumber
    rvm gemset use cucumber
    gem install bundler
    unset GHERKIN_JS
    bundle install
    rake-compiler cross-ruby VERSION=1.9.3-p0

Now you can build Windows gems:

    rake compile
    rake gems:win

## Release process

* Make sure GHERKIN_JS is defined (see JavaScript section above)
* Bump version in:
  * This file (Installation/Java section)
  * gherkin.gemspec
  * java/pom.xml
  * ikvm/Gherkin/Gherkin.csproj (2 places)
  * js/package.json
* Run `bundle update`, so Gemfile.lock gets updated with the changes.
* Commit changes, otherwise you will get an error at the end when a tag is made.
* Run `bundle exec rake gems:prepare && ./build_native_gems.sh && bundle exec rake release:ALL`
  * The specs intermittently fail with a segfault from therubyracer. Running specs can be disabled with SKIP_JS_SPECS=true
* Announce on Cucumber list, IRC and Twitter.

## Note on Patches/Pull Requests
 
* Fork the project.
* Run rake ragel:rb to generate all the I18n lexers
* Make your feature addition or bug fix.
* Add tests for it. This is important so I don't break it in a
  future version unintentionally.
* Commit, do not mess with Rakefile, VERSION, or History.txt.
  (if you want to have your own version, that is fine but
  bump version in a commit by itself I can ignore when I pull)
* Send me a pull request. Bonus points for topic branches.

## Copyright

Copyright (c) 2009-2011 Mike Sassak, Gregory Hnatiuk, Aslak Hellesøy. See LICENSE for details.
