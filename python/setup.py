import os
import os.path

from setuptools import setup, Extension

extensions = []
for lexer_src in os.listdir('src'):
    name = lexer_src.split('.', 1)[0]
    name = 'gherkin.lexer.ext.' + name
    lexer = Extension(name, sources=[os.path.join('src', lexer_src)])
    extensions.append(lexer)

setup(
    name='gherkin',
    version='2.41',
    ext_modules=extensions,
)
