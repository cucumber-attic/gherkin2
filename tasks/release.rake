namespace :release do
  desc 'Build all gems, jar and dll and release'
  task :all => [:gems, :ikvm]
end