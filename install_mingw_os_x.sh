#!/bin/sh
mkdir -p /usr/local/mingw

# We need GCC 4.7 in order to build ruby 1.8.7. When we drop support for that we can use a more recent one
cd /usr/local/mingw && curl --silent --location http://downloads.sourceforge.net/project/mingw-w64/Toolchains%20targetting%20Win32/Automated%20Builds/mingw-w32-1.0-bin_i686-darwin_20120227.tar.bz2 | tar xvj
