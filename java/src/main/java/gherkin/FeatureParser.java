package gherkin;

public interface FeatureParser {
    /**
     * Parses a Feature in some format.
     *
     * @param src    the "source code" of the feature
     * @param uri    where it came from (used for better parse error messages)
     * @param offset Line offset within the uri. Use 0 if it starts at the top of the file.
     */
    public void parse(String src, String uri, int offset);
}
