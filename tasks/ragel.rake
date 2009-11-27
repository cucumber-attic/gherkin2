require File.dirname(__FILE__) + '/ragel_compiler'

CLEAN.include [
  'ragel/i18n/*.rl',
  'lib/gherkin/rb_lexer/*.rb',
  'ext/**/*.c',
  'java/src/gherkin/lexer/*.java',
]

YAML.load_file(File.dirname(__FILE__) + '/../lib/gherkin/i18n.yml').each do |i18n, keywords|
  i18n = i18n.gsub(/[\s-]/, '')

  %w[c java rb].each do |lang|
    file RagelCompiler.target(lang, i18n) do
      RagelCompiler.new(lang, i18n, keywords).emit
    end
  end
end
