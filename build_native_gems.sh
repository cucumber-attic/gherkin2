#!/bin/sh
rm -Rf release
mkdir release
GEM_PLATFORM=java gem build gherkin.gemspec
GEM_PLATFORM=i386-mswin32 gem build gherkin.gemspec
GEM_PLATFORM=i386-mingw32 gem build gherkin.gemspec
GEM_PLATFORM=universal-dotnet gem build gherkin.gemspec
mv *.gem release