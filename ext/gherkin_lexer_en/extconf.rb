require 'mkmf'
dir_config("gherkin_lexer_en")
have_library("c", "main")
create_makefile("gherkin_lexer_en")
