package gherkin.formatter.model;

import gherkin.formatter.visitors.Next;

public interface Visitable {
    void accept(Visitor visitor, Next bakctrack) throws Exception;
}
