package gherkin.model;

import gherkin.formatter.Argument;
import gherkin.formatter.Mappable;
import gherkin.formatter.Reporter;

import java.util.Collections;
import java.util.List;

public class Match extends Mappable {
    private final List<Argument> arguments;
    private final String location;
    private final List<Integer> matchedColumns;
    public static final Match UNDEFINED = new Match(null, null, null);

    public Match(List<Argument> arguments, String location, List<Integer> matchedColumns) {
        this.arguments = arguments;
        this.location = location;
        this.matchedColumns = matchedColumns;
    }

    public Match(List<Argument> arguments, String location) {
        this(arguments, location, Collections.<Integer>emptyList());
    }
    
    public List<Argument> getArguments() {
        return arguments;
    }

    public String getLocation() {
        return location;
    }

    /**
     * If this match is from a Step that was created from a Step in a ScenarioOutline,
     * augmented by values from an Examples table, the result of this method is the
     * Examples table column indices. If this is a match created from a regular Scenario
     * (or Background) Step, then the result is null.
     *
     * @return the column indexes that this instance is related to.
     */
    public List<Integer> getMatchedColumns() {
        return matchedColumns;
    }

    public void replay(Reporter reporter) {
        reporter.match(this);
    }
}
