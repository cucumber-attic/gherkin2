package gherkin.parser;

import gherkin.Listener;

import java.util.*;

public class FilterListener implements Listener {

    private final List filters;
    private final Listener listener;
    private List<Sexp> metaBuffer = new ArrayList<Sexp>();
    private List<Sexp> featureBuffer = new ArrayList<Sexp>();
    private List<Sexp> scenarioBuffer = new ArrayList<Sexp>();
    private List<Sexp> examplesBuffer = new ArrayList<Sexp>();
    private List<Sexp> examplesRowsBuffer = new ArrayList<Sexp>();

    private List<String> featureTags = new ArrayList<String>();
    private List<String> scenarioTags = new ArrayList<String>();
    private List<String> exampleTags = new ArrayList<String>();

    private boolean featureOk = false;
    private TableState tableState = TableState.STEP;
    private boolean scenarioOk = false;
    private boolean examplesOk = false;
    private IFilterMethod filterMethod;

    @SuppressWarnings("unchecked")
    public FilterListener(Listener listener, List filters) {
        this.listener = listener;
        this.filters = filters;
        detectFilters(filters);
    }

    public void tag(String name, int line) throws Exception {
        if (hasNoFilters()) {
            listener.tag(name, line);
        } else {
            metaBuffer.add(new Sexp(Sexp.Events.TAG, name, line));
        }
        replayBuffersIfAllOk();
    }

    public void comment(String content, int line) throws Exception {
        if (hasNoFilters()) {
            listener.comment(content, line);
        } else {
            metaBuffer.add(new Sexp(Sexp.Events.COMMENT, content, line));
        }
        replayBuffersIfAllOk();
    }

    public void feature(String keyword, String name, int line) throws Exception {
        if (hasNoFilters()) {
            listener.feature(keyword, name, line);
        } else {
            Sexp sexp = new Sexp(Sexp.Events.FEATURE, keyword, name, line);
            featureBuffer = metaBuffer;
            featureBuffer.add(sexp);
            featureTags = extractTags();
            metaBuffer = new ArrayList<Sexp>();
            if (filterMatch(sexp)) {
                featureOk = true;
            }
        }
        replayBuffersIfAllOk();
    }

    public void background(String keyword, String name, int line)
            throws Exception {
        if (hasNoFilters()) {
            listener.background(keyword, name, line);
        } else {
            Sexp sexp = new Sexp(Sexp.Events.BACKGROUND, keyword, name, line);
            featureBuffer.addAll(metaBuffer);
            featureBuffer.add(sexp);
            metaBuffer = new ArrayList<Sexp>();
            tableState = TableState.BACKGROUND;
            if (filterMatch(sexp)) {
                featureOk = true;
            }
        }
        replayBuffersIfAllOk();
    }

    public void scenario(String keyword, String name, int line)
            throws Exception {
        if (hasNoFilters()) {
            listener.scenario(keyword, name, line);
        } else {
            Sexp sexp = new Sexp(Sexp.Events.SCENARIO, keyword, name, line);
            replayExamplesRowsBuffer();
            scenarioBuffer = metaBuffer;
            scenarioBuffer.add(sexp);
            scenarioTags = extractTags();
            exampleTags = new ArrayList<String>();
            metaBuffer = new ArrayList<Sexp>();
            scenarioOk = filterMatch(scenarioBuffer) || tagMatch();
            examplesOk = false;
            tableState = TableState.STEP;
        }
        replayBuffersIfAllOk();
    }

    public void scenario_outline(String keyword, String name, int line)
            throws Exception {
        if (hasNoFilters()) {
            listener.scenario_outline(keyword, name, line);
        } else {
            Sexp sexp = new Sexp(Sexp.Events.SCENARIO_OUTLINE, keyword, name, line);
            replayExamplesRowsBuffer();
            scenarioBuffer = metaBuffer;
            scenarioBuffer.add(sexp);
            scenarioTags = extractTags();
            exampleTags = new ArrayList<String>();
            metaBuffer = new ArrayList<Sexp>();
            scenarioOk = filterMatch(scenarioBuffer);
            examplesOk = false;
            tableState = TableState.STEP;
        }
        replayBuffersIfAllOk();
    }

    public void examples(String keyword, String name, int line)
            throws Exception {
        if (hasNoFilters()) {
            listener.examples(keyword, name, line);
        } else {
            Sexp sexp = new Sexp(Sexp.Events.EXAMPLES, keyword, name, line);
            replayExamplesRowsBuffer();
            examplesBuffer = metaBuffer;
            examplesBuffer.add(sexp);
            exampleTags = extractTags();
            metaBuffer = new ArrayList<Sexp>();
            examplesRowsBuffer = new ArrayList<Sexp>();
            examplesOk = filterMatch(examplesBuffer) || tagMatch();
            tableState = TableState.EXAMPLES;
        }
        replayBuffersIfAllOk();
    }

    public void step(String keyword, String name, int line) throws Exception {
        if (hasNoFilters()) {
            listener.step(keyword, name, line);
        } else {
            Sexp sexp = new Sexp(Sexp.Events.STEP, keyword, name, line);
            if (tableState.equals(TableState.BACKGROUND)) {
                featureBuffer.addAll(metaBuffer);
                featureBuffer.add(sexp);
                metaBuffer = new ArrayList<Sexp>();
                if (filterMatch(sexp)) {
                    featureOk = true;
                }
            } else {
                scenarioBuffer.add(sexp);
                scenarioOk |= filterMatch(scenarioBuffer);
                tableState = TableState.STEP;
            }
        }
        replayBuffersIfAllOk();
    }

    public void row(List<String> row, int line) throws Exception {
        if (hasNoFilters()) {
            listener.row(row, line);
        } else {
            Sexp sexp = new Sexp(Sexp.Events.ROW, row, line);
            if (tableState.equals(TableState.EXAMPLES)) {
                if (!headerRowAllreadyBuffered()) {
                    examplesBuffer.add(sexp);
                    if (filterMatch(examplesBuffer)) {
                        examplesOk = true;
                    }
                } else {
                    if (scenarioOk || examplesOk || featureOk || filterMatch(sexp)) {
                        examplesRowsBuffer.add(sexp);
                    }
                }
            } else if (tableState.equals(TableState.STEP)) {
                scenarioBuffer.add(sexp);
                scenarioOk |= filterMatch(scenarioBuffer);
            } else if (tableState.equals(TableState.BACKGROUND)) {
                featureBuffer.addAll(metaBuffer);
                featureBuffer.add(sexp);
                metaBuffer = new ArrayList<Sexp>();
            } else {
                throw new RuntimeException("Bad table_state:" + tableState);
            }
        }
        replayBuffersIfAllOk();
    }

    public void py_string(String string, int line) throws Exception {
        if (hasNoFilters()) {
            listener.py_string(string, line);
        } else {
            if(tableState.equals(TableState.BACKGROUND)) {
                featureBuffer.add(new Sexp(Sexp.Events.PY_STRING, string, line));
                featureOk |= filterMatch(featureBuffer);
            } else {
                scenarioBuffer.add(new Sexp(Sexp.Events.PY_STRING, string, line));
                scenarioOk |= filterMatch(scenarioBuffer);
            }
        }
        replayBuffersIfAllOk();
    }

    public void eof() throws Exception {
        replayExamplesRowsBuffer();
        listener.eof();
    }

    public void syntax_error(String state, String event,
                             List<String> legalEvents, int line) throws Exception {
    }

    private void detectFilters(List filters) {
        if (hasNoFilters()) {
            return;
        }
        checkIfMoreThanOneFilterType(filters);
        this.filterMethod = new FilterMethodFactory().getFilterMethod(filters.get(0).getClass());
        this.filterMethod.setFilters(filters);
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
        return !examplesBuffer.isEmpty() && examplesBuffer.get(examplesBuffer.size() - 1).getEvent() == Sexp.Events.ROW;
    }

    private boolean filterMatch(Sexp sexp) {
        return filterMatch(Arrays.asList(sexp));
    }

    private boolean filterMatch(List<Sexp> buffer) {
        for (Sexp sexp : buffer) {
            if (filterMethod.filter(sexp)) {
                return true;
            }
        }
        return false;
    }

    private boolean tagMatch() {
        return filterMethod.getClass() == TagFilterMethod.class && filterMethod.filterTags(currentTags());
    }

    private void replayBuffers() throws Exception {
        List<Sexp> allItems = new ArrayList<Sexp>();
        allItems.addAll(featureBuffer);
        allItems.addAll(scenarioBuffer);
        for (Sexp item : allItems) {
            item.replay(listener);
        }
        featureBuffer.clear();
        scenarioBuffer.clear();
    }

    private List<String> extractTags() {
        List<String> retVal = new ArrayList<String>();
        for (Sexp sexp : metaBuffer) {
            if (sexp.getEvent() == Sexp.Events.TAG) {
                retVal.add(sexp.getName());
            }
        }
        return retVal;
    }

    private void replayExamplesRowsBuffer() throws Exception {
        if (!examplesRowsBuffer.isEmpty()) {
            replayBuffers();
            List<Sexp> exampleItems = new ArrayList<Sexp>();
            exampleItems.addAll(examplesBuffer);
            exampleItems.addAll(examplesRowsBuffer);
            for (Sexp sexp : exampleItems) {
                sexp.replay(listener);
            }
            examplesRowsBuffer.clear();
        }
    }

    private void replayBuffersIfAllOk() throws Exception {
        if (scenarioOk || examplesOk || featureOk) {
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
