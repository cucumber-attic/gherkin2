package gherkin.formatter.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CellResultTest {
    @Test
    public void skipped_passed_is_skipped() {
        CellResult r = new CellResult("skipped");
        r.addResult(passed());
        assertEquals("skipped", r.getStatus());
    }

    @Test
    public void passed_skipped_is_skipped() {
        CellResult r = new CellResult("passed");
        r.addResult(skipped());
        assertEquals("skipped", r.getStatus());
    }

    @Test
    public void failed_passed_is_failed() {
        CellResult r = new CellResult("skipped");
        r.addResult(failed());
        r.addResult(passed());
        assertEquals("failed", r.getStatus());
    }

    private Result passed() {
        return new Result("passed", null);
    }

    private Result skipped() {
        return new Result("skipped", null);
    }

    private Result failed() {
        return new Result("failed", "error");
    }

}
