#!/bin/sh
# Builds gems for all supported platforms
rake gemspec build PLATFORM=universal-java-1.5
rake cross compile gemspec build PLATFORM=i386-mswin32 RUBY_CC_VERSION=1.8.6
rake gemspec build PLATFORM=i386-mingw32 RUBY_CC_VERSION=1.8.6
