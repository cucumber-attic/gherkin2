package gherkin.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagExpression {
    private final Map<String, Integer> limits = new HashMap<String, Integer>();
    private And and = new And();

    public TagExpression(List<String> tagExpressions) {
        for (String tagExpression : tagExpressions) {
            add(tagExpression.split("\\s*,\\s*"));
        }
    }

    public boolean eval(List<String> tags) {
        return and.isEmpty() || and.eval(tags);
    }

    public Map<String, Integer> limits() {
        return limits;
    }

    private void add(String[] tags) {
        Or or = new Or();
        for (String tag : tags) {
            tag = tag.trim();
            if (tag.startsWith("~")) {
                or.add(new Not(new Var(tag.substring(1))));
            } else {
                String[] tagAndLimit = tag.split(":");
                if (tagAndLimit.length == 2) {
                    tag = tagAndLimit[0];
                    int limit = Integer.parseInt(tagAndLimit[1]);
                    limits.put(tag, limit);
                }
                or.add(new Var(tag));
            }
        }
        and.add(or);
    }

    private interface Expression {
        boolean eval(List<String> tags);
    }

    private class Not implements Expression {
        private final Expression expression;

        public Not(Expression expression) {
            this.expression = expression;
        }

        public boolean eval(List<String> tags) {
            return !expression.eval(tags);
        }
    }

    private class And implements Expression {
        private List<Expression> expressions = new ArrayList<Expression>();

        public void add(Expression expression) {
            expressions.add(expression);
        }

        public boolean eval(List<String> tags) {
            boolean result = true;
            for (Expression expression : expressions) {
                result = result && expression.eval(tags);
            }
            return result;
        }

        public boolean isEmpty() {
            return expressions.isEmpty();
        }
    }

    private class Or implements Expression {
        private List<Expression> expressions = new ArrayList<Expression>();

        public void add(Expression expression) {
            expressions.add(expression);
        }

        public boolean eval(List<String> tags) {
            boolean result = false;
            for (Expression expression : expressions) {
                result = result || expression.eval(tags);
            }
            return result;
        }
    }

    private class Var implements Expression {
        private final String tagName;

        public Var(String tagName) {
            this.tagName = tagName;
        }

        public boolean eval(List<String> tags) {
            for (String tag : tags) {
                if (tagName.equals(tag)) {
                    return true;
                }
            }
            return false;
        }
    }
}
