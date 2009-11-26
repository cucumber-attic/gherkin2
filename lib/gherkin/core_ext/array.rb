class Array
  def utf8_pack(fmt)
    (RUBY_VERSION =~ /^1\.9/) ? pack(fmt).force_encoding("UTF-8") : pack(fmt)
  end
end