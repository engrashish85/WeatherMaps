package test.java.testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
		features="resources/features", 
		glue = {"test/java/stepDefinitions"},
		tags = {"@WeatherMapsRegression1"},
		plugin = {"html:test-output/cucumber-html-report", "json:test-output/cucumber-json/report.json",
				"pretty:test-output/cucumber-pretty/pretty.txt", "usage:test-output/cucumber-usage/usage-report.json",
				"junit:test-output/cucumber-junit-reports/jnit-reports.xml",
				}
		)

public class TestRunner {

	@BeforeClass
	public static void setup () {
		PropertyConfigurator.configure("log4j.properties");
	}

}
