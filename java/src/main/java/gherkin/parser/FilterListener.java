package gherkin.parser;

import gherkin.Listener;

import java.util.*;

public class FilterListener implements Listener {

    private final List filters;
    private final Listener listener;
    private List<Event> metaBuffer = new ArrayList<Event>();
    private List<Event> featureBuffer = new ArrayList<Event>();
    private List<Event> scenarioBuffer = new ArrayList<Event>();
    private List<Event> examplesBuffer = new ArrayList<Event>();
    private List<Event> examplesRowsBuffer = new ArrayList<Event>();

    private List<String> featureTags = new ArrayList<String>();
    private List<String> scenarioTags = new ArrayList<String>();
    private List<String> exampleTags = new ArrayList<String>();

    private TableState tableState = TableState.STEP;
    private boolean featureOk = false;
    private boolean backgroundOk = false;
    private boolean scenarioOk = false;
    private boolean examplesOk = false;
    private FilterMethod filterMethod;

    @SuppressWarnings("unchecked")
    public FilterListener(Listener listener, List filters) {
        this.listener = listener;
        this.filters = filters;
        detectFilters(filters);
    }

    public void location(String uri, int offset) {
        listener.location(uri, offset);
    }

    public void tag(String tag, int line) {
        if (hasNoFilters()) {
            listener.tag(tag, line);
        } else {
            metaBuffer.add(new Event(Event.Type.TAG, tag, line));
        }
        replayBuffersIfAllOk();
    }

    public void comment(String comment, int line) {
        if (hasNoFilters()) {
            listener.comment(comment, line);
        } else {
            metaBuffer.add(new Event(Event.Type.COMMENT, comment, line));
        }
        replayBuffersIfAllOk();
    }

    public void feature(String keyword, String name, String description, int line) {
        if (hasNoFilters()) {
            listener.feature(keyword, name, description, line);
        } else {
            Event event = new Event(Event.Type.FEATURE, keyword, name, description, line);
            featureBuffer = metaBuffer;
            featureBuffer.add(event);
            featureTags = extractTags();
            metaBuffer = new ArrayList<Event>();
            if (filterMatch(event)) {
                featureOk = true;
            }
        }
        replayBuffersIfAllOk();
    }

    public void background(String keyword, String name, String description, int line) {
        if (hasNoFilters()) {
            listener.background(keyword, name, description, line);
        } else {
            Event event = new Event(Event.Type.BACKGROUND, keyword, name, description, line);
            featureBuffer.addAll(metaBuffer);
            featureBuffer.add(event);
            metaBuffer = new ArrayList<Event>();
            tableState = TableState.BACKGROUND;
            if (filterMatch(event)) {
                backgroundOk = true;
            }
        }
        replayBuffersIfAllOk();
    }

    public void scenario(String keyword, String name, String description, int line) {
        if (hasNoFilters()) {
            listener.scenario(keyword, name, description, line);
        } else {
            Event event = new Event(Event.Type.SCENARIO, keyword, name, description, line);
            replayExamplesRowsBuffer();
            scenarioBuffer = metaBuffer;
            scenarioBuffer.add(event);
            scenarioTags = extractTags();
            exampleTags = new ArrayList<String>();
            metaBuffer = new ArrayList<Event>();
            scenarioOk = filterMatch(scenarioBuffer) || tagMatch();
            examplesOk = false;
            backgroundOk = false;
            tableState = TableState.STEP;
        }
        replayBuffersIfAllOk();
    }

    public void scenarioOutline(String keyword, String name, String description, int line) {
        if (hasNoFilters()) {
            listener.scenarioOutline(keyword, name, description, line);
        } else {
            Event event = new Event(Event.Type.SCENARIO_OUTLINE, keyword, name, description, line);
            replayExamplesRowsBuffer();
            scenarioBuffer = metaBuffer;
            scenarioBuffer.add(event);
            scenarioTags = extractTags();
            exampleTags = new ArrayList<String>();
            metaBuffer = new ArrayList<Event>();
            scenarioOk = filterMatch(scenarioBuffer);
            examplesOk = false;
            backgroundOk = false;
            tableState = TableState.STEP;
        }
        replayBuffersIfAllOk();
    }

    public void examples(String keyword, String name, String description, int line) {
        if (hasNoFilters()) {
            listener.examples(keyword, name, description, line);
        } else {
            Event event = new Event(Event.Type.EXAMPLES, keyword, name, description, line);
            replayExamplesRowsBuffer();
            examplesBuffer = metaBuffer;
            examplesBuffer.add(event);
            exampleTags = extractTags();
            metaBuffer = new ArrayList<Event>();
            examplesRowsBuffer = new ArrayList<Event>();
            examplesOk = filterMatch(examplesBuffer) || tagMatch();
            tableState = TableState.EXAMPLES;
        }
        replayBuffersIfAllOk();
    }

    public void step(String keyword, String name, int line) {
        if (hasNoFilters()) {
            listener.step(keyword, name, line);
        } else {
            Event event = new Event(Event.Type.STEP, keyword, name, line);
            if (tableState.equals(TableState.BACKGROUND)) {
                featureBuffer.addAll(metaBuffer);
                featureBuffer.add(event);
                metaBuffer = new ArrayList<Event>();
                if (filterMatch(event)) {
                    backgroundOk = true;
                }
            } else {
                scenarioBuffer.add(event);
                scenarioOk |= filterMatch(scenarioBuffer);
                tableState = TableState.STEP;
            }
        }
        replayBuffersIfAllOk();
    }

    public void row(List<String> cells, int line) {
        if (hasNoFilters()) {
            listener.row(cells, line);
        } else {
            Event event = new Event(Event.Type.ROW, cells, line);
            if (tableState.equals(TableState.EXAMPLES)) {
                if (!headerRowAllreadyBuffered()) {
                    examplesBuffer.add(event);
                    if (filterMatch(examplesBuffer)) {
                        examplesOk = true;
                    }
                } else {
                    if (scenarioOk || examplesOk || featureOk || filterMatch(event)) {
                        examplesRowsBuffer.add(event);
                    }
                }
            } else if (tableState.equals(TableState.STEP)) {
                scenarioBuffer.add(event);
                scenarioOk |= filterMatch(scenarioBuffer);
            } else if (tableState.equals(TableState.BACKGROUND)) {
                featureBuffer.addAll(metaBuffer);
                featureBuffer.add(event);
                metaBuffer = new ArrayList<Event>();
            } else {
                throw new RuntimeException("Bad table_state:" + tableState);
            }
        }
        replayBuffersIfAllOk();
    }

    public void pyString(String string, int line) {
        if (hasNoFilters()) {
            listener.pyString(string, line);
        } else {
            if (tableState.equals(TableState.BACKGROUND)) {
                featureBuffer.add(new Event(Event.Type.PY_STRING, string, line));
                featureOk |= filterMatch(featureBuffer);
            } else {
                scenarioBuffer.add(new Event(Event.Type.PY_STRING, string, line));
                scenarioOk |= filterMatch(scenarioBuffer);
            }
        }
        replayBuffersIfAllOk();
    }

    public void eof() {
        replayExamplesRowsBuffer();
        listener.eof();
    }

    public void syntaxError(String state, String event,
                             List<String> legalEvents, int line) {
    }

    private void detectFilters(List filters) {
        if (hasNoFilters()) {
            return;
        }
        checkIfMoreThanOneFilterType(filters);
        this.filterMethod = new FilterMethodFactory().getFilterMethod(filters);
    }

    private void checkIfMoreThanOneFilterType(List filters) {
        Set<Class> filterTypes = new HashSet<Class>();
        for (Object object : filters) {
            filterTypes.add(object.getClass());
        }
        if (filterTypes.size() > 1) {
            throw new RuntimeException("Bad Filter: " + filterTypes);
        }
    }

    private boolean hasNoFilters() {
        return filters.size() == 0;
    }

    private boolean headerRowAllreadyBuffered() {
        return !examplesBuffer.isEmpty() && examplesBuffer.get(examplesBuffer.size() - 1).getType() == Event.Type.ROW;
    }

    private boolean filterMatch(Event event) {
        return filterMatch(Arrays.asList(event));
    }

    private boolean filterMatch(List<Event> buffer) {
        for (Event event : buffer) {
            if (filterMethod.filter(event)) {
                return true;
            }
        }
        return false;
    }

    private boolean tagMatch() {
        return filterMethod.getClass() == TagFilterMethod.class && filterMethod.filterTags(currentTags());
    }

    private void replayBuffers() {
        List<Event> allItems = new ArrayList<Event>();
        allItems.addAll(featureBuffer);
        allItems.addAll(scenarioBuffer);
        for (Event item : allItems) {
            item.replay(listener);
        }
        featureBuffer.clear();
        scenarioBuffer.clear();
    }

    private List<String> extractTags() {
        List<String> retVal = new ArrayList<String>();
        for (Event event : metaBuffer) {
            if (event.getType() == Event.Type.TAG) {
                retVal.add(event.getName());
            }
        }
        return retVal;
    }

    private void replayExamplesRowsBuffer() {
        if (!examplesRowsBuffer.isEmpty()) {
            replayBuffers();
            List<Event> exampleItems = new ArrayList<Event>();
            exampleItems.addAll(examplesBuffer);
            exampleItems.addAll(examplesRowsBuffer);
            for (Event event : exampleItems) {
                event.replay(listener);
            }
            examplesRowsBuffer.clear();
        }
    }

    private void replayBuffersIfAllOk() {
        if (scenarioOk || examplesOk || featureOk || backgroundOk) {
            replayBuffers();
        }
    }

    private List<String> currentTags() {
        List<String> retVal = new ArrayList<String>();
        retVal.addAll(featureTags);
        retVal.addAll(scenarioTags);
        retVal.addAll(exampleTags);
        return retVal;
    }

}
