package gherkin.formatter;

import gherkin.formatter.ansi.AnsiEscapes;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public 
class LocationMatcher extends TypeSafeMatcher<String> {
    @Override
    public boolean matchesSafely(String line) {
        return line.contains("#") && line.contains(AnsiEscapes.GREY.toString());
    }

    public void describeTo(Description description) {
        description.appendText("line contains location");
    }

    @Factory
    public static <T> Matcher<String> hasLocation() {
        return new LocationMatcher();
    }
}
