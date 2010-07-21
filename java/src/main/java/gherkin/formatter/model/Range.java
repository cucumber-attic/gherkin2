package gherkin.formatter.model;

public class Range {
    private final long first;
    private final long last;

    public Range(long first, long last) {
        this.first = first;
        this.last = last;
    }

    public long getFirst() {
        return first;
    }

    public long getLast() {
        return last;
    }

    public boolean isInclude(long n) {
        return first <= n && n <= last;
    }
}
