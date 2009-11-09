Given /^an? (\w+)-language (\w+) lexer$/ do |lang_name, lexer|
  i18n_lang = code_from_lang_name(lang_name)
  load_lexer(i18n_lang, lexer)
end

Given "the following text is parsed:" do |text|
  lexer.scan(text)
end

Then "there should be no syntax errors" do
  listener.errors.should == []
end

Then /^there should be a syntax error on (line \d+)$/ do |line|
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
