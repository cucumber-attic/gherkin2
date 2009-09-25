Given /^an? (\w+[\s\w]+?)-language feature parser$/ do |lang_name|
  i18n_lang = code_from_lang_name(lang_name)
  load_feature_parser(i18n_lang)
end

Given "the following text is parsed:" do |text|
  parser.scan(text)
end

Then "there should be no errors" do
  listener.errors.should be_empty
end

Then /^there should be a syntax error on (line \d+)$/ do |line|
  error = listener.error_on(line)
  error.should_not be_nil
  error.first.should == :syntax_error
  error.last.should == line
end

Then /^there should be syntax errors on lines (.*)$/ do |lines|
  lines.scan(/\d+/).each do |line|
    Then "there should be a syntax error on line #{line}"
  end
end

Transform /^line \d+$/ do |step_arg|
  /(\d+)$/.match(step_arg)[0].to_i
end
