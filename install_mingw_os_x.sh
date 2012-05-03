#!/bin/sh

mkdir -p /usr/local/mingw
cd /usr/local/mingw
# Don't attempt any of the newer versions - they don't work (gcc 4.7.0)
wget http://downloads.sourceforge.net/project/mingw-w64/Toolchains%20targetting%20Win32/Automated%20Builds/mingw-w32-1.0-bin_i686-darwin_20110819.tar.bz2
tar xvfj mingw-w32-1.0-bin_i686-darwin_20110819.tar.bz2
