package test.java.stepDefinitions;

import com.cucumber.listener.Reporter;
import com.sun.istack.logging.Logger;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import main.java.CommonFunctions.ExcelFunctions;
import main.java.CommonFunctions.IOFunctions;
import main.java.CommonFunctions.RestAssuredCommonFunctions;
import main.java.config.ChildURLs;
import main.java.config.Globals;
import org.bson.assertions.Assertions;
import org.junit.Assert;
import org.junit.rules.ErrorCollector;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

public class HeroKUAppFacts {

    Logger logger = Logger.getLogger(this.getClass());
//    public ErrorCollector errorCollector = new ErrorCollector();
//    List <Exception> errors;


    @And("^I retrieve all the facts stored in the database$")
    public void iRetrieveAllTheFactsStoredInTheDatabaseWithIdListedInExcelSheet() throws IOException {
        String response = RestAssuredCommonFunctions.getAPIRequest(new IOFunctions().
                        readValueFromPropertiesFile(Globals.PROPERTIES_FILE_LOCATION.toString(), "CAT_HEROKUAPP_URL"),
                ChildURLs.GET_FACTS.toString(), null);
        logger.info("The data returned by API request is - "+ response);
        Reporter.addStepLog("The data returned by API request is - "+ response);
        new RestAssuredCommonFunctions().populateJsonArrayResponsesToList(response);
    }

    @And("^I retrieve all the facts stored in the database with ids listed in Excel sheet$")
    public void retrieveFactsInDataSheetWithID() throws IOException {
        RestAssuredCommonFunctions restAssuredCommonFunctions = new RestAssuredCommonFunctions();
        List<String> userIdList = ExcelFunctions.myMap.get("_id");
        try {
            for (int i = 0; i < userIdList.size(); i++) {
                String response = RestAssuredCommonFunctions.getAPIRequest(new IOFunctions().
                                readValueFromPropertiesFile(Globals.PROPERTIES_FILE_LOCATION.toString(), "CAT_HEROKUAPP_URL"),
                        ChildURLs.GET_FACTS_ID.toString().replace("{id}", userIdList.get(i)), null);
                logger.info("The data returned by API request is - " + response);
                Reporter.addStepLog("The data returned is - "+response);
                if (!(response.contains("Fact not found"))) {
                    List<String> jsonResponses = restAssuredCommonFunctions.populateJsonArrayResponsesToList(response);
                    Map<Object, Object> dataMap = restAssuredCommonFunctions.returnValuesFromResponse(jsonResponses.get(0));
                    Assert.assertEquals("Validating that user id is matched with user id in response", userIdList.get(i), dataMap.get("_id").toString());
                    Map<String, String> user = (Map<String, String>) dataMap.get("user");
                    String id = user.get("_id").toString();
                    Assert.assertEquals("Validating that user is matched with user value in response", ExcelFunctions.myMap.get("user").get(i), is(id));
                    Assert.assertEquals("Validating that text is matched with text value in response", ExcelFunctions.myMap.get("text").get(i), is(dataMap.get("text").toString()));
                    Map<String, Map<String, String>> firstLastName = (Map<String, Map<String, String>>) dataMap.get("user");
                    Assert.assertEquals("Validating that first name is matched with user first name value in response", ExcelFunctions.myMap.get("user.name.first").get(i), is(firstLastName.get("name").get("first")));
                    Assert.assertEquals("Validating that last name is matched with user last name value in response", ExcelFunctions.myMap.get("user.name.last").get(i), is(firstLastName.get("name").get("last")));
                    Assert.assertEquals("Validating that type is matched with type value in response", ExcelFunctions.myMap.get("type").get(i), is(dataMap.get("type").toString()));
                } else {
                    Reporter.addStepLog("User id - " + userIdList.get(i) + " is not in database");
                    logger.severe("User id - " + userIdList.get(i) + " is not in database");
                }
            }
        } catch (Throwable error) {
            Reporter.addStepLog(error.getMessage());
            logger.severe(error.getMessage());
        }

    }

//    @After
//    public void tearDown() {
//        if (errorCollector != null) {
//            throw new AssertionError(errorCollector);
//        }
//    }
}
