from gherkin.formatter.argument import Argument

class BasicStatement(object):
    def __init__(self, comments, keyword, name, line):
        self.comments = comments
        self.keyword = keyword
        self.name = name
        self.line = line

    def line_range(self):
        if self.comments:
            first = self.comments[0].line
        else:
            first = self.first_non_comment_line()
        return first, self.line

    def first_non_comment_line(self):
        return self.line

class DescribedStatement(BasicStatement):
    def __init__(self, comments, keyword, name, description, line):
        super(DescribedStatement, self).__init__(comments, keyword, name, line)
        self.description = description

class TagStatement(DescribedStatement):
    def __init__(self, comments, tags, keyword, name, description, line):
        super(TagStatement, self).__init__(comments, keyword, name, description,
                                           line)
        self.tags = tags

    def first_non_comment_line(self):
        if self.tags:
            return self.tags[0].line
        return self.line

class Replayable(object):
    type = None

    def replay(self, formatter):
        getattr(formatter, self.type)(self)

class Feature(TagStatement, Replayable):
    type = "feature"

class Background(DescribedStatement, Replayable):
    type = "background"

class Scenario(TagStatement, Replayable):
    type = "scenario"

class ScenarioOutline(TagStatement, Replayable):
    type = "scenario_outline"

class Examples(TagStatement, Replayable):
    type = "examples"

    def __init__(self, comments, tags, keyword, name, description, line, rows):
        super(Examples, self).__init__(comments, tags, keyword, name,
                                       description, line)
        self.rows = rows

class Step(BasicStatement, Replayable):
    type = "step"

    def __init__(self, comments, keyword, name, line):
        super(Step, self).__init__(comments, keyword, name, line)
        self.rows = None
        self.doc_string = None

    def line_range(self):
        lrange = super(Step, self).line_range()
        if self.rows:
            return (lrange[0], self.rows[-1].line)
        elif self.doc_string:
            return (lrange[0], self.doc_string.line_range()[1])
        return lrange

    def outline_args(self):
        start = 0
        end = 0
        arguments = []
        while True:
            start = self.name.find('<', end)
            if start == -1:
                break
            end = self.name.find('>', start)
            arguments.append(Argument(start, self.name[start:end + 1]))
        return arguments

class Comment(object):
    def __init__(self, value, line):
        self.value = value
        self.line = line

class Tag(object):
    def __init__(self, name, line):
        self.name = name
        self.line = line

    def __eq__(self, other):
        return self.name == other.name

    def __hash__(self):
        return hash(self.name)

class DocString(object):
    def __init__(self, content_type, value, line):
        self.content_type = content_type
        self.value = value
        self.line = line

    def line_range(self):
        line_count = len(self.value.splitlines())
        return (self.line, self.line + line_count + 1)

class Row(object):
    def __init__(self, comments, cells, line):
        self.comments = comments
        self.cells = cells
        self.line = line

class Match(Replayable):
    type = "match"

    def __init__(self, arguments, location):
        self.arguments = arguments
        self.location = location

class Result(Replayable):
    type = "result"

    def __init__(self, status, duration, error_message):
        self.status = status
        self.duration = duration
        self.error_message = error_message

