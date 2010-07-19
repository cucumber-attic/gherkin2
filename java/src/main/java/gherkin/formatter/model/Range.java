package gherkin.formatter.model;

public class Range {
    private final int first;
    private final int last;

    public Range(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public boolean isInclude(long n) {
        return first <= n && n <= last;
    }
}
