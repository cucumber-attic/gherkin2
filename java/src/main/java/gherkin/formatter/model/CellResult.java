package gherkin.formatter.model;

import gherkin.formatter.Mappable;

import java.util.ArrayList;
import java.util.List;

public class CellResult extends Mappable {
    private List<Result> results = new ArrayList<Result>();
    private String status;

    public CellResult(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public List<Result> getResults() {
        return results;
    }

    public void addResult(Result result) {
        // TODO: Update status
        results.add(result);
    }
}
