package gherkin.formatter;

import bool.Evaluator;
import bool.Lexer;
import bool.Node;
import bool.Parser;
import gherkin.formatter.model.ExamplesTableRow;
import gherkin.formatter.model.Range;
import gherkin.formatter.model.Tag;
import gherkin.util.Mapper;

import java.util.Collection;
import java.util.List;

import static gherkin.util.FixJava.map;

public class TagFilter implements Filter {
    private final Node tagExpression;

    public TagFilter(String tagExpression) {
        if (tagExpression != null) {
            Parser parser = new Parser(new Lexer(tagExpression));
            this.tagExpression = parser.buildAst();
        } else {
            this.tagExpression = null;
        }
    }

    public boolean evaluate(List<Tag> tags, List<String> names, List<Range> ranges) {
        if (tagExpression == null) {
            return true;
        }
        Collection<String> tagNames = map(tags, new Mapper<Tag, String>() {
            @Override
            public String map(Tag tag) {
                return tag.getName();
            }
        });
        return new Evaluator().evaluate(tagExpression, tagNames);
    }

    public List<ExamplesTableRow> filterTableBodyRows(List<ExamplesTableRow> examplesRows) {
        return examplesRows;
    }
}
