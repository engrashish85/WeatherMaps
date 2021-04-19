package test.java.testRunner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
		features="resources/features", 
		glue = {"test/java/stepDefinitions"},
		tags = {"@WeatherMapsRegression"},
		plugin = {"html:test-output/cucumber-html-report", "json:test-output/cucumber-json/report.json",
				//"pretty:test-output/cucumber-pretty/pretty.txt", "usage:test-output/cucumber-usage/usage-report.json",
				//"junit:test-output/cucumber-junit-reports/jnit-reports.xml", 
				}
		)

public class TestRunner {

	@BeforeClass
	public static void setup () {
		
		System.out.println("sClassName - tag2");
	}

}
