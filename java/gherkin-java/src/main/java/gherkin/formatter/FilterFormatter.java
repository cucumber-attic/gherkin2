package gherkin.formatter;

import gherkin.formatter.model.Background;
import gherkin.formatter.model.BasicStatement;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Range;
import gherkin.formatter.model.Row;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class FilterFormatter implements Formatter {
    private final Formatter formatter;
    private final Filter filter;
    private List<Tag> featureTags;
    private List<Tag> featureElementTags;
    private List<Tag> examplesTags;

    private List<BasicStatement> featureEvents;
    private List<BasicStatement> backgroundEvents;
    private List<BasicStatement> featureElementEvents;
    private List<BasicStatement> examplesEvents;
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

        featureEvents = new ArrayList<BasicStatement>();
        backgroundEvents = new ArrayList<BasicStatement>();
        featureElementEvents = new ArrayList<BasicStatement>();
        examplesEvents = new ArrayList<BasicStatement>();
    }

    private Filter detectFilter(List filters) {
        Set<Class> filterClasses = new HashSet<Class>();
        for (Object filter : filters) {
            filterClasses.add(filter.getClass());
        }
        if(filterClasses.size() > 1) {
            throw new IllegalArgumentException("Inconsistent filters: " + filters + ". Only one type [line,name,tag] can be used at once.");
        }

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

    @Override
    public void uri(String uri) {
        formatter.uri(uri);
    }

    @Override
    public void feature(Feature feature) {
        featureTags = feature.getTags();
        featureName = feature.getName();
        featureEvents = new ArrayList<BasicStatement>();
        featureEvents.add(feature);
    }

    @Override
    public void background(Background background) {
        featureElementName = background.getName();
        featureElementRange = background.getLineRange();
        backgroundEvents = new ArrayList<BasicStatement>();
        backgroundEvents.add(background);
    }

    @Override
    public void scenario(Scenario scenario) {
        replay();
        featureElementTags = scenario.getTags();
        featureElementName = scenario.getName();
        featureElementRange = scenario.getLineRange();
        featureElementEvents = new ArrayList<BasicStatement>();
        featureElementEvents.add(scenario);
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        replay();
        featureElementTags = scenarioOutline.getTags();
        featureElementName = scenarioOutline.getName();
        featureElementRange = scenarioOutline.getLineRange();
        featureElementEvents = new ArrayList<BasicStatement>();
        featureElementEvents.add(scenarioOutline);
    }

    @Override
    public void examples(Examples examples) {
        replay();
        examplesTags = examples.getTags();
        examplesName = examples.getName();

        Range tableBodyRange;
        switch (examples.getRows().size()) {
            case 0:
                tableBodyRange = new Range(examples.getLineRange().getLast(), examples.getLineRange().getLast());
                break;
            case 1:
                tableBodyRange = new Range(examples.getRows().get(0).getLine(), examples.getRows().get(0).getLine());
                break;
            default:
                tableBodyRange = new Range(examples.getRows().get(1).getLine(), examples.getRows().get(examples.getRows().size() - 1).getLine());
        }
        examplesRange = new Range(examples.getLineRange().getFirst(), tableBodyRange.getLast());
        if (filter.eval(Collections.<Tag>emptyList(), Collections.<String>emptyList(), Collections.singletonList(tableBodyRange))) {
            examples.setRows(filter.filterTableBodyRows(examples.getRows()));
        }
        examplesEvents = new ArrayList<BasicStatement>();
        examplesEvents.add(examples);

    }

    @Override
    public void step(Step step) {
        if (!featureElementEvents.isEmpty()) {
            featureElementEvents.add(step);
        } else {
            backgroundEvents.add(step);
        }
        featureElementRange = new Range(featureElementRange.getFirst(), step.getLineRange().getLast());
    }

    public void table(List<Row> table) {
    }

    @Override
    public void eof() {
        replay();
        formatter.eof();
    }

    @Override
    public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void done() {
        formatter.done();
    }

    @Override
    public void close() {
        formatter.close();
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

        if (featureElementOk || examplesOk) {
            replayEvents(featureEvents);
            replayEvents(backgroundEvents);
            replayEvents(featureElementEvents);
            if (examplesOk) {
                replayEvents(examplesEvents);
            }
        }
        examplesEvents.clear();
        examplesTags.clear();
        examplesName = null;
        examplesRange = null;
    }

    private void replayEvents(List<BasicStatement> events) {
        for (BasicStatement event : events) {
            event.replay(formatter);
        }
        events.clear();
    }

}
