[![Build Status](https://secure.travis-ci.org/cucumber/gherkin.png)](http://travis-ci.org/cucumber/gherkin)

A fast lexer and parser for the Gherkin language based on Ragel. Gherkin is two things:

* The language that has evolved out of the Cucumber project.
* This library.

Supported platforms:

* [Ruby](https://rubygems.org/gems/gherkin) 1.8.7-1.9.3 (MRI, JRuby, REE, Rubinius)
* [Pure Java](http://search.maven.org/#search%7Cga%7C1%7Cgherkin) (jar file)
* [JavaScript](http://search.npmjs.org/#/gherkin) (Tested with V8/node.js/Chrome, but might work on other JavaScript engines)
* [.NET](http://nuget.org/List/Packages/gherkin) (dll file)

## Installation

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
        <version>2.12.0</version>
    </dependency>

You can get it manually from [Maven Central](http://search.maven.org/#browse%7C-2073395818)

### .NET

Get the dll from [NuGet](http://nuget.org/List/Packages/gherkin)

## API Docs

* [Ruby](http://cukes.info/api/gherkin/yardoc/)
* [Java](http://cukes.info/api/gherkin/javadoc/)

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

* Node.js (0.6.17 or higher with npm)
* Ragel with JavaScript support: http://github.com/dominicmarks/ragel-js
  * Make sure you have gcc/g++ 4.6 (4.7 is to strict to build ragel-js)
  * Make sure you have `autoconf` and `automake` (`brew install automake`)
  * Make sure you have the official ragel (`brew install ragel`)
  * Make sure you have kelbt (`brew install kelbt`). If that fails, install manually from http://www.complang.org/kelbt/
  * `cd ragel-js/ragel-svn && ./autogen.sh && ./configure --disable-manual`
  * `make && make install`
* Define the GHERKIN_JS environment variable in your shell (any value will do)

Update gems (to install gems which are needed only for js support):

    bundle update

Now you can build the JavaScript with:

    bundle exec rake js

And you can try it out with node.js:

    node js/example/print.js spec/gherkin/fixtures/1.feature

Or the json formatter:

    node js/example/json_formatter_example.js

If you're hacking and just want to rebuild the English parser:

    rake js/lib/gherkin/lexer/en.js

In order to test the native JavaScript implementation of JSONFormatter, you also need to define the `GHERKIN_JS_NATIVE` environment
variable. It's recommended you don't do this permanently, as it will disable testing the Ruby implementation. Try this instead:

    GHERKIN_JS_NATIVE=true GHERKIN_JS=true bundle exec rake

TODO: Make all specs pass with js lexer - replace 'c(listener)' with 'js(listener)' in i18n.rb

### .NET dll

You must install Mono SDK 2.10.8. The OS X package installer is not recommended as it modifies your system PATH and makes Homebrew unhappy. Install with homebrew instead:

    brew install ikvm/mono.rb

You must also download NuGet.exe from [CodePlex](http://nuget.codeplex.com/releases) and place it in `/usr/local/nuget/NuGet.exe`. When it's installed, update it and register your NuGet API Key:

    # In case we need to update
    mono /usr/local/nuget/NuGet.exe Update -self

    # The key is at https://nuget.org/account
    mono --runtime=v4.0.30319 /usr/local/nuget/NuGet.exe SetApiKey xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxx
    (Note: you may need to run 'mozroots --import --sync' to help mono trusts https setificate, see http://monomvc.wordpress.com/2012/03/06/nuget-on-mono/ for more information)

Now you can build the .NET dll with:

    mkdir release
    rake ikvm
    rake release/nuspec/lib/gherkin.dll

This should build `release/nuspec/lib/gherkin.dll`

### MinGW Rubies (for Windows gems)

In order to build Windows binaries (so we can release Windows gems from OS X/Linux) we first need to install MinGW:

    ./install_mingw_os_x.sh

Now, make sure you have openssl installed - it's needed to build the rubies.

    brew install openssl

Next, we're going to install Ruby 1.8.7 and Ruby 1.9.3 for MinGW. We need both versions so we can build Windows binaries for both.
OS X Lion (or later) doesn't ship with an LLVM free gcc, which you will need in order to install ruby 1.8.7. We can install it with:

    brew install https://raw.github.com/Homebrew/homebrew-dupes/master/apple-gcc42.rb
    export PATH=/usr/local/mingw/bin:$PATH
    # Test that it's on your PATH
    i686-w64-mingw32-gcc -v

For more info see:

* http://stackoverflow.com/questions/6170813/why-cant-i-install-rails-on-lion-using-rvm
* https://github.com/mxcl/homebrew/wiki/Custom-GCC-and-cross-compilers

Now we're ready to install the Windows rubies:

    unset GHERKIN_JS

    # 1.9.3
    rvm install 1.9.3-p392
    rvm use 1.9.3-p392
    rvm gemset use cucumber --create
    gem install bundler
    bundle install
    PATH=/usr/local/mingw/bin:$PATH CC=/usr/local/mingw/bin/i686-w64-mingw32-gcc rake-compiler cross-ruby VERSION=1.9.3-p392

    # 1.8.7
    CC=gcc-4.2 rvm install 1.8.7-p371
    rvm use 1.8.7-p371
    rvm gemset use cucumber --create
    gem install bundler
    bundle install
    PATH=/usr/local/mingw/bin:$PATH CC=/usr/local/mingw/bin/i686-w64-mingw32-gcc rake-compiler cross-ruby VERSION=1.8.7-p371

Now you can build Windows gems:

    rake compile
    mkdir release
    rake gems:win

## Release process

Make sure you have access to all the servers where packages are being uploaded:

* npm registry: `npm login`
* rubygems.org: `gem push`
* cukes.info: `ssh cukes.info`
* sonatype: Check `~/.m2/settings.xml` and that you have gnupg (OS X users: Install [GPGTools](http://www.gpgtools.org/installer/index.html))
  * Make sure you have a key [with no sub-key](https://docs.sonatype.org/display/Repository/How+To+Generate+PGP+Signatures+With+Maven)
* nuget: See .NET section above

Run tests once with GHERKIN_JS_NATIVE=true:

    GHERKIN_JS_NATIVE=true GHERKIN_JS=true bundle exec rake

Now we can release:

* Make sure GHERKIN_JS is defined (see JavaScript section above)
* Bump version in:
  * This file (Installation/Java section)
  * lib/gherkin/platform.rb
  * java/pom.xml
  * js/package.json
  * History.md
* Run `bundle update`, so Gemfile.lock gets updated with the changes.
* Commit changes, otherwise you will get an error at the end when a tag is made.
* Run `i686-w64-mingw32-gcc -v && bundle exec rake gems:prepare && ./build_native_gems.sh && bundle exec rake release:ALL`

## Note on Patches/Pull Requests

* Fork the project.
* Run `bundle install` to install dependencies.
* Run `rake` to make sure all the tests are passing.
* Make your feature addition or bug fix.
* Add tests for it. This is important so I don't break it in a future version unintentionally.
* Commit, do not mess with History.md.
* Send me a pull request. Bonus points for topic branches.

## Copyright

Copyright (c) 2009-2013 Mike Sassak, Gregory Hnatiuk, Aslak Hellesøy. See LICENSE for details.
