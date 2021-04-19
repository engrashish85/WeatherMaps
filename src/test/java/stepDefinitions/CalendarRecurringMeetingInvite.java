package test.java.stepDefinitions;

import com.sun.istack.logging.Logger;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.WeatherMaps.WeatherMaps;

public class CalendarRecurringMeetingInvite extends WeatherMaps {

    Logger logger = Logger.getLogger(this.getClass());

    @Given("^I have launched the Calendar App$")
    public void i_have_launched_the_Calendar_App() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("^It is a working Friday$")
    public void itIsAWorkingFriday() {
    }

    @And("^Meeting is between (\\d+):(\\d+)am to (\\d+):(\\d+)pm$")
    public void meetingIsBetweenAmToPm(int arg0, int arg1, int arg2, int arg3) {
    }

    @Then("^I want to book a meeting with the title “Workshop”$")
    public void iWantToBookAMeetingWithTheTitleWorkshop() {
    }

    @And("^I invite \"([^\"]*)\" of people$")
    public void iInviteOfPeople(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @And("^I save the meeting$")
    public void iSaveTheMeeting() {
    }

    @Then("^I Check if the meeting is created as expected$")
    public void iCheckIfTheMeetingIsCreatedAsExpected() {
    }

    @When("^It is a working Monday$")
    public void itIsAWorkingMonday() {
    }

    @And("^Meeting is is for (\\d+) minutes$")
    public void meetingIsIsForMinutes(int arg0) {
    }

    @Then("^I want to book a meeting with the title “Stand Up”$")
    public void iWantToBookAMeetingWithTheTitleStandUp() {
    }

}
