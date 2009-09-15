require 'mkmf'

dir_config("feature")
have_library("c", "main")

create_makefile("feature")
