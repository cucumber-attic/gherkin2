package gherkin.formatter;

public class Argument extends Mappable {
    private final int offset;
    private final String val;

    public Argument(int offset, String val) {
        this.offset = offset;
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public int getOffset() {
        return offset;
    }
}
