package test.java.testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "resources/features",
        glue = {"test/java/stepDefinitions"},
        tags = {"@listHeroKUAppFactsWithID"},
        plugin = {
//                "pretty",
//                "html:test-output/cucumber-html-report", "json:test-output/cucumber-json/report.json",
//                "pretty:test-output/cucumber-pretty/pretty.txt", "usage:test-output/cucumber-usage/usage-report.json",
                "com.cucumber.listener.ExtentCucumberFormatter:output/Extent-Report/Extent_Report.html",
                "junit:test-output/cucumber-junit-reports/junit-reports.xml"
        }
)

public class TestRunner {

    @BeforeClass
    public static void setup() {
        PropertyConfigurator.configure("log4j.properties");
    }

}
