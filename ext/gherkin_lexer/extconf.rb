require 'mkmf'

dir_config("gherkin_lexer")
have_library("c", "main")

create_makefile("gherkin_lexer")
