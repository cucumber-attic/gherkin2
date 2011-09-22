package gherkin.formatter.model;

import gherkin.formatter.Mappable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CellResult extends Mappable {
    private static final List<String> SEVERITY = Arrays.asList("executing", "executing_arg", "passed", "passed_arg", "undefined", "pending", "pending_arg", "skipped", "skipped_arg", "failed", "failed_arg");

    private List<Result> results = new ArrayList<Result>();
    private String status = null;
    private transient int statusIndex = -1;

    public CellResult(String status) {
        updateStatus(status);
    }

    private void updateStatus(String status) {
        int index = SEVERITY.indexOf(status);
        if (index == -1) {
            throw new IllegalStateException("Illegal state: " + status + ". Legal: " + SEVERITY);
        }
        if (index > statusIndex) {
            this.status = status;
            this.statusIndex = index;
        }
    }

    public String getStatus() {
        return status;
    }

    public List<Result> getResults() {
        return results;
    }

    public void addResult(Result result) {
        updateStatus(result.getStatus());
        results.add(result);
    }

    public String toString() {
        return status;
    }
}
