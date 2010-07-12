package gherkin.formatter.model;

public class Tag {
    private final String name;
    private final int line;

    public Tag(String name, int line) {
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    @Override
    public boolean equals(Object o) {
        return name.equals(((Tag) o).getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
