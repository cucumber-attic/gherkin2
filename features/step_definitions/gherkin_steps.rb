Given /^a "([^\"]*)", "([^\"]*)" "([^\"]*)" parser$/ do |i18n_language, programming_language, parser_name|
  parser = Gherkin::Parser.new(@listener, false, parser_name)
  @lexer = Gherkin::Lexer.send(programming_language)[i18n_language].new(parser)
end

Given "the following text is parsed:" do |text|
  @lexer.scan(text)
end

Then "there should be no parse errors" do
  @listener.errors.should == []
end

Then /^there should be a parse error on (line \d+)$/ do |line|
  @listener.line(line).should include(:syntax_error, line)
end

Then /^there should be parse errors on (lines .*)$/ do |lines|
  lines.each do |line|
    Then "there should be a parse error on line #{line}"
  end
end

Transform /^line \d+$/ do |step_arg|
  tr_line_number(step_arg)
end

Transform /^lines .*$/ do |step_arg|
  tr_line_numbers(step_arg)
end
