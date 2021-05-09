package test.java.stepDefinitions;

import com.sun.istack.logging.Logger;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.WeatherMaps.Top10Beaches;
import main.java.WeatherMaps.WeatherMaps;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BeachSurfingSelection extends WeatherMaps {

    Logger logger = Logger.getLogger(this.getClass());

    @And("I only like to surf on <Monday & Friday> in next 16 days")
    public void iOnlyLikeToSurfOnMondayFridayInNextDays() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 16; i++) {
            int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if ((dayOfTheWeek == Calendar.MONDAY) || (dayOfTheWeek == Calendar.THURSDAY)) {
                Date dateTime = calendar.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(dateTime);
                dates.add(formattedDate);
            }
            calendar.add(Calendar.DATE, 1);
        }
        logger.info("The dates for Mondays and Fridays in the next 16 days are - "+dates);
    }


    @Given("^I like to surf in any of 2 beaches \"([^\"]*)\" of Sydney$")
    public void iLikeToSurfInAnyOfBeachesOfSydney(String beachName) {
        // Write code here that turns the phrase above into concrete actions
        beachEnum = Top10Beaches.valueOf(beachName);
        logger.info("The beach selected is - "+beachEnum.returnBeachName());
    }

    @When("^I look up the the weather forecast for the next 16 days with POSTAL CODES$")
    public void iLookUpTheTheWeatherForecastForTheNextDaysWithPOSTALCODES() throws UnknownHostException {
        restParameters.put("postal_code", beachEnum.returnPostalCodeOfBeach());
        response = getAPIRequest(restParameters);
        logger.info("The data returned by API request is - "+ response);
        returnDataFromResponse(response);
    }

    @Then("^I check to if see the temperature is between <(\\d+)℃ and (\\d+)℃>$")
    public void iCheckToIfSeeTheTemperatureIsBetweenAnd(int lowTemperature, int highTemperature) {
        validateTemperatureRange(lowTemperature, highTemperature);
    }

    @And("^I check wind speed to be between (\\d+) and (\\d+)$")
    public void iCheckWindSpeedToBeBetweenAnd(int lowSpeed, int highSpeed) {
        if (isBeachFavorable.equals(true)) {
            validateWindSpeedRange(lowSpeed, highSpeed);
        }
    }

    @And("^I check to see if UV index is <= (\\d+)$")
    public void iCheckToSeeIfUVIndexIs(int uvIndex) {
        if (isBeachFavorable.equals(true)) {
            validateUVIndex(uvIndex);
        }
    }

    @And("^I Pick \\(Display in logs/report\\) the best suitable (\\d+) spots out of top (\\d+) spots, based upon suitable weather forecast for the day$")
    public void iPickDisplayInLogsReportTheBestSuitableSpotsOutOfTopSpotsBasedUponSuitableWeatherForecastForTheDay(int arg0, int arg1) throws IOException {
        initializeExcel();
        appendDataToSheet();
        closeWorkBook();
    }

}
