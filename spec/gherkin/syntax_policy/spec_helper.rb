require File.expand_path(File.dirname(__FILE__) + '/steps_spec.rb')

Spec::Matchers.define :allow do |event|
  match do |state|
    state.send(event)
  end    
end
