$: << 'spec'
Dir.glob('spec/**/*_spec.rb').each do |f|
  require f
end
