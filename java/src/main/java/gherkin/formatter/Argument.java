package gherkin.formatter;

public class Argument extends Mappable {
    private final Integer offset;
    private final String val;

    public Argument(Integer offset, String val) {
        this.offset = offset;
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public Integer getOffset() {
        return offset;
    }

    public String toString() {
        return getVal();
    }
}
