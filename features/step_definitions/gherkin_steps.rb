Given /^an? '(\w+)' feature parser$/ do |i18n_lang|
  load_feature_parser(i18n_lang)
end

Given "the following text is parsed:" do |text|
  parser.scan(text)
end

Then "there should be no errors" do
  listener.syntax_errors.should be_empty
end
