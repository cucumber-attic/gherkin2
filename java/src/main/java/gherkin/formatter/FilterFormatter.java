package gherkin.formatter;

import gherkin.formatter.events.*;
import gherkin.formatter.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class FilterFormatter implements Formatter {
    private final Formatter formatter;
    private final Filter filter;
    private  List<Tag> featureTags;
    private List<Tag> featureElementTags;
    private List<Tag> examplesTags;

    private List<AbstractEvent> featureEvents;
    private List<AbstractEvent> backgroundEvents;
    private List<AbstractEvent> featureElementEvents;
    private List<AbstractEvent> examplesEvents;
    private String featureName;
    private String featureElementName;
    private String examplesName;
    private Range featureElementRange;
    private Range examplesRange;

    public FilterFormatter(Formatter formatter, List filters) {
        this.formatter = formatter;
        this.filter = detectFilter(filters);

        featureTags = new ArrayList<Tag>();
        featureElementTags = new ArrayList<Tag>();
        examplesTags = new ArrayList<Tag>();

        featureEvents = new ArrayList<AbstractEvent>();
        backgroundEvents = new ArrayList<AbstractEvent>();
        featureElementEvents = new ArrayList<AbstractEvent>();
        examplesEvents = new ArrayList<AbstractEvent>();
    }

    private Filter detectFilter(List filters) {
        Class<?> typeOfFilter = filters.get(0).getClass();
        if (String.class.isAssignableFrom(typeOfFilter)) {
            return new TagFilter(filters);
        } else if (Number.class.isAssignableFrom(typeOfFilter)) {
            return new LineFilter(filters);
        } else if (Pattern.class.isAssignableFrom(typeOfFilter)) {
            return new PatternFilter(filters);
        } else {
            throw new RuntimeException("Could not create filter method for unknown filter of type: " + typeOfFilter);
        }
    }

    public void feature(Statement statement, String uri) {
        featureTags = statement.getTags();
        featureName = statement.getName();
        featureEvents = new ArrayList<AbstractEvent>();
        featureEvents.add(new FeatureEvent(statement, uri));
    }

    public void background(Statement statement) {
        featureElementName = statement.getName();
        featureElementRange = statement.getLineRange();
        backgroundEvents = new ArrayList<AbstractEvent>();
        backgroundEvents.add(new BackgroundEvent(statement));
    }

    public void scenario(Statement statement) {
        replay();
        featureElementTags = statement.getTags();
        featureElementName = statement.getName();
        featureElementRange = statement.getLineRange();
        featureElementEvents = new ArrayList<AbstractEvent>();
        featureElementEvents.add(new ScenarioEvent(statement));
    }

    public void scenarioOutline(Statement statement) {
        replay();
        featureElementTags = statement.getTags();
        featureElementName = statement.getName();
        featureElementRange = statement.getLineRange();
        featureElementEvents = new ArrayList<AbstractEvent>();
        featureElementEvents.add(new ScenarioOutlineEvent(statement));
    }

    public void examples(Statement statement, List<Row> exampleRows) {
        replay();
        examplesTags = statement.getTags();
        examplesName = statement.getName();

        Range tableBodyRange = new Range(exampleRows.get(1).getLine(), exampleRows.get(exampleRows.size()-1).getLine());
        examplesRange = new Range(statement.getLineRange().getFirst(), tableBodyRange.getLast());
        if(filter.eval(Collections.<Tag>emptyList(), Collections.<String>emptyList(), Collections.singletonList(tableBodyRange))) {
            exampleRows = filter.filterTableBodyRows(exampleRows);
        }
        examplesEvents = new ArrayList<AbstractEvent>();
        examplesEvents.add(new ExamplesEvent(statement, exampleRows));

    }

    public void step(Statement statement, List<Row> stepTable, Result result) {
        addStep(new StepEvent(statement, stepTable, result));
        if(stepTable != null && stepTable.size() > 0) {
            featureElementRange = new Range(featureElementRange.getFirst(), stepTable.get(stepTable.size() - 1).getLine());
        } else {
            featureElementRange = new Range(featureElementRange.getFirst(), statement.getLine());
        }
    }

    public void step(Statement statement, PyString pyString, Result result) {
        addStep(new StepEvent(statement, pyString, result));
        if(pyString != null) {
            featureElementRange = new Range(featureElementRange.getFirst(), pyString.getLineRange().getLast());
        } else {
            featureElementRange = new Range(featureElementRange.getFirst(), statement.getLine());
        }
    }

    private void addStep(AbstractEvent event) {
        if(!featureElementEvents.isEmpty()) {
            featureElementEvents.add(event);
        } else {
            backgroundEvents.add(event);
        }
    }

    public void eof() {
        replay();
        formatter.eof();
    }

    public void table(List<Row> rows) {
        throw new UnsupportedOperationException("TABLE");
    }

    public void syntaxError(String state, String event, List<String> legalEvents, String uri, int line) {
        throw new UnsupportedOperationException("SYNTAX ERROR");
    }


    private void replay() {
        List<Tag> feTags = new ArrayList<Tag>(featureTags);
        feTags.addAll(featureElementTags);
        List<String> feNames = Arrays.asList(featureName, featureElementName);
        List<Range> feRanges = Arrays.asList(featureElementRange);
        boolean featureElementOk = filter.eval(feTags, feNames, feRanges);

        List<Tag> exTags = new ArrayList<Tag>(feTags);
        exTags.addAll(examplesTags);
        List<String> exNames = new ArrayList<String>(feNames);
        exNames.add(examplesName);
        List<Range> exRanges = new ArrayList<Range>(feRanges);
        exRanges.add(examplesRange);
        boolean examplesOk = filter.eval(exTags, exNames, exRanges);

        if(featureElementOk || examplesOk) {
            replayEvents(featureEvents);
            replayEvents(backgroundEvents);
            replayEvents(featureElementEvents);
            if(examplesOk) {
                replayEvents(examplesEvents);
            }
        }
        examplesEvents.clear();
        examplesTags.clear();
        examplesName = null;
    }

    private void replayEvents(List<AbstractEvent> events) {
        for (AbstractEvent event: events) {
            event.replay(formatter);
        }
        events.clear();
    }

}
