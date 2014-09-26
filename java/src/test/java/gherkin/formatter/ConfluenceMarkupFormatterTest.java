package gherkin.formatter;


import gherkin.parser.Parser;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static gherkin.util.FixJava.readResource;
import static org.junit.Assert.assertEquals;

public class ConfluenceMarkupFormatterTest {

    public static final String RESOURCES_PATH = "/gherkin/formatter/confluencemarkup/";

    @Test
    public void completeFeatureShouldBeFormattedAsDesiredWithTags() throws IOException {

        String basicFeatureDescription = readFeature("completeFeatureDescription");
        String[] expectedLines = readExpectedMarkup("completeFeatureDescriptionWithTags");

        List<String> formatterOutput = doFormatter(basicFeatureDescription, true);

        // looping through the collection to get junit to provide helpful output
        for (String line : expectedLines) {
            assertEquals(line, formatterOutput.remove(0));
        }
    }

    @Test
    public void completeFeatureShouldBeFormattedAsDesiredWithoutTags() throws IOException {

        String basicFeatureDescription = readFeature("completeFeatureDescription");
        String[] expectedLines = readExpectedMarkup("completeFeatureDescriptionWithoutTags");

        List<String> formatterOutput = doFormatter(basicFeatureDescription, false);

        // looping through the collection to get junit to provide helpful output
        for (String expectedLine : expectedLines) {
            assertEquals(expectedLine, formatterOutput.remove(0));
        }
    }

    private String readFeature(final String feature) {
        return readResource(RESOURCES_PATH + feature + ".feature");
    }

    private String[] readExpectedMarkup(final String featureName) {
        String basicFeatureDescriptionMarkup = readResource(RESOURCES_PATH + featureName + ".markup");
        return basicFeatureDescriptionMarkup.split(System.getProperty("line.separator"));
    }

    private List<String> doFormatter(String feature, boolean renderTags) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArrayOutputStream);

        Formatter formatter;
        formatter = new ConfluenceMarkupFormatter(out, new ConfluenceMarkupFormatter.Options(renderTags));
        Parser parser = new Parser(formatter);
        parser.parse(feature, "", 0);
        formatter.close();

        return extractLines(byteArrayOutputStream);
    }

    private List<String> extractLines(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));

        String line;
        List<String> lines = new ArrayList<String>();
        int lineNumber = 0;

        while ((line = br.readLine()) != null) {
            System.out.println(lineNumber + ":" + line);
            lineNumber++;
            lines.add(line);
        }
        return lines;
    }
}