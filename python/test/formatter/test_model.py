import unittest

from gherkin.formatter import model

class TestTag(unittest.TestCase):
    def test_equal_when_name_is_equal(self):
        tags = [model.Tag('@x', 1), model.Tag('@y', 2), model.Tag('@x', 3)]
        assert len(set(tags)) == 2

class TestStep(unittest.TestCase):
    def test_provide_arguments_for_outline_tokens(self):
        step = model.Step([], 'Given ',
                         "I have <number> cukes in <whose> belly", 10)
        outline_args = [(arg.offset, arg.val) for arg in step.outline_args()]
        assert outline_args == [(7, '<number>'), (25, '<whose>')]

    def test_provide_no_arguments_when_there_are_no_outline_tokens(self):
        step = model.Step([], 'Given ', "I have 33 cukes in my belly", 10)
        assert step.outline_args() == []
