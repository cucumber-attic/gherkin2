require 'mkmf'

dir_config("gherkin")
have_library("c", "main")

create_makefile("gherkin")