require 'mkmf'

dir_config("parser")
have_library("c", "main")

create_makefile("parser")
