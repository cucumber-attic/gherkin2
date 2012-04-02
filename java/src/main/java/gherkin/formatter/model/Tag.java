package gherkin.formatter.model;

import gherkin.formatter.Mappable;

public class Tag extends Mappable {
    private final String name;
    private final Integer line;

    public Tag(String name, Integer line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public Integer getLine() {
        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
