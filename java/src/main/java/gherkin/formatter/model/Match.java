package gherkin.formatter.model;

import gherkin.formatter.Argument;
import gherkin.formatter.Formatter;
import gherkin.formatter.Mappable;

import java.util.List;

public class Match extends Mappable {
    private final List<Argument> arguments;
    private final String location;

    public Match(List<Argument> arguments, String location) {
        this.arguments = arguments;
        this.location = location;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getLocation() {
        return location;
    }

    public void replay(Formatter formatter) {
        formatter.match(this);
    }
}
