require 'mkmf'

dir_config("gherkin_parser")
have_library("c", "main")

create_makefile("gherkin_parser")
