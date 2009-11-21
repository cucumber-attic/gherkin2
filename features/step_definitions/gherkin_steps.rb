Given /^an? (\w+)-language feature parser$/ do |lang_name|
  i18n_lang = code_from_lang_name(lang_name)
  parser = Gherkin::Parser.new(@listener, false, 'root')
  @lexer = Gherkin::Lexer[i18n_lang].new(parser)
end

Given /^an? (\w+)-language steps parser$/ do |lang_name|
  i18n_lang = code_from_lang_name(lang_name)
  parser = Gherkin::Parser.new(@listener, false, 'steps')
  @lexer = Gherkin::Lexer[i18n_lang].new(parser)
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
