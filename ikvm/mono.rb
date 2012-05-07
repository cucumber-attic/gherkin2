require 'formula'

class Mono < Formula
  url 'http://download.mono-project.com/sources/mono/mono-2.10.8.tar.bz2'
  homepage 'http://www.mono-project.com/Release_Notes_Mono_2.10.8'
  md5 'c012b01ac17bedb0af52b7a16405da84'

  def install
    args = ["--prefix=#{prefix}",
            "--with-glib=embedded",
            "--enable-nls=no"]

    args << "--host=x86_64-apple-darwin10" if MacOS.prefer_64_bit?

    system "./configure", *args
    system "make"
    system "make install"
  end
end