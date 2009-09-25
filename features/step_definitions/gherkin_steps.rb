Given /^an? '(\w+)' feature parser$/ do |i18n_lang|
  load_feature_parser(i18n_lang)
end

Given "the following text is parsed:" do |text|
  parser.scan(text)
end

Then "there should be no errors" do
  listener.errors.should be_empty
end

Then /^there should be a syntax error on line (\d+)$/ do |line|
  line = line.to_i
  error = listener.error_on(line)
  error.first.should == :syntax_error
  error.last.should == line
end
