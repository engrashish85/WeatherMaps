package test.java.stepDefinitions;

import cucumber.api.java.en.When;
import main.java.CommonFunctions.ExcelFunctions;
import main.java.config.Globals;
import main.java.config.TestFileLocation;

public class CommonStepDefinitions {

    @When("^I retrieve HeroKUApp data into sheet$")
    public void retrieveDataIntoMap() {
        ExcelFunctions.myMap = new ExcelFunctions().retrieveDataIntoMap(Globals.TEST_DATA_LOCATION.toString() +
                TestFileLocation.HEROKUAPP_FACTS.toString());
        System.out.println(ExcelFunctions.myMap);
    }

}

