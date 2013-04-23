package gherkin.pr246;

import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.FileInputStream;

/**
 * Runs Cucumber tests.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber-html-report", "json-pretty:target/cucumber-json-report.json"})
public class RunCukesTest
{
}
