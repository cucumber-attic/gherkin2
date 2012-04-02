package gherkin.formatter.model;

public class Range {
    private final Integer first;
    private final Integer last;

    public Range(Integer first, Integer last) {
        this.first = first;
        this.last = last;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getLast() {
        return last;
    }

    public boolean isInclude(Integer n) {
        return first <= n && n <= last;
    }
}
