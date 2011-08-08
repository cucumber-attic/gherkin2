if RUBY_VERSION =~ /1.9/
  Encoding.default_external = 'UTF-8'
end
$: << 'spec'
Dir.glob('spec/**/*_spec.rb').each do |f|
  require f
end
