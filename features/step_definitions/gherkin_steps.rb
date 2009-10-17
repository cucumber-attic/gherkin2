Given /^an? (\w+)-language feature parser$/ do |lang_name|
  i18n_lang = code_from_lang_name(lang_name)
  load_feature_parser(i18n_lang)
end

Given "the following text is parsed:" do |text|
  parser.scan(text)
end

Then "there should be no syntax errors" do
  listener.errors.should == []
end

Then /^there should be a syntax error on (line \d+)$/ do |line|
  puts listener.to_sexp
  listener.line(line).should include(:syntax_error, line)
end

Then /^there should be syntax errors on (lines .*)$/ do |lines|
  lines.each do |line|
    Then "there should be a syntax error on line #{line}"
  end
end

Transform /^line \d+$/ do |step_arg|
  tr_line_number(step_arg)
end

Transform /^lines .*$/ do |step_arg|
  tr_line_numbers(step_arg)
end
