package gherkin.formatter.visitors;

import gherkin.formatter.model.Visitable;
import gherkin.formatter.model.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Next {
    private final List<Visitable> visitables = new ArrayList<Visitable>();
    private final Visitor visitor;

    public Next(Visitor visitor) {
        this.visitor = visitor;
    }

    public void pushAll(List<? extends Visitable> visitables) {
        this.visitables.addAll(visitables);
    }

    public void push(Visitable visitable) {
        visitables.add(visitable);
    }

    public void next() throws Exception {
        if (!visitables.isEmpty()) {
            Visitable visitable = visitables.remove(0);
            visitable.accept(visitor, this);
        }
    }
}
