package test.java.stepDefinitions;

import com.sun.istack.logging.Logger;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import main.java.GoogleMaps.GoogleCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarRecurringMeetingInvite extends GoogleCalendar {

    Logger logger = Logger.getLogger(this.getClass());
    GoogleCalendar googleCalendar = new GoogleCalendar();
    private String dateToBeReturned;

    @Given("^I have launched the Calendar App$")
    public void i_have_launched_the_Calendar_App() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver = getAndroidDriver();
        navigateToCalendarMainPage();
        logger.info("Navigated to the calendar main page");
    }

    @When("^It is a working Friday$")
    public void itIsAWorkingFriday() {
        Date expectedDate = returnWorkingDay("Fri");
        logger.info("Date of next working Friday is - "+expectedDate);
        dateToBeReturned = returnDateInTheCorrespondingFormat(expectedDate, "dd-MMMM-yyyy");
        logger.info("Date after conversion is - "+dateToBeReturned);
        selectDateFromCalendar(dateToBeReturned);
    }

    @And("^Meeting is between (\\d+):(\\d+)am to (\\d+):(\\d+)pm$")
    public void meetingIsBetweenAmToPm(int startHour, int startMinute, int endHour, int endMinute) {
        selectStartAndEndTimeFromCalendar (startHour, startMinute, endHour, endMinute);
    }

    @Then("^I want to book a meeting with the title “Workshop”$")
    public void iWantToBookAMeetingWithTheTitleWorkshop() {
        selectMeetingTitle();
    }

    @And("^I invite \"([^\"]*)\" of people$")
    public void iInviteOfPeople(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        invitePeople();
    }

    @And("^I save the meeting$")
    public void iSaveTheMeeting() {
        clickSaveButton();
    }

    @Then("^I Check if the meeting is created as expected$")
    public void iCheckIfTheMeetingIsCreatedAsExpected() {
        validateMeetingInvite();
    }

    private Date returnWorkingDay(String expectedWeekday) {
        Calendar now = Calendar.getInstance();
        Date dateTime = null;
        String weekDay = now.getTime().toString();
        weekDay = weekDay.substring(0, 3).trim();
        while (!(weekDay.equals(expectedWeekday))) {
            now.add(Calendar.DATE, 1);
            dateTime = now.getTime();
            weekDay = now.getTime().toString().substring(0,3).trim();
        }
        return dateTime;
    }

    private String returnDateInTheCorrespondingFormat(Date expectedDate, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String formattedDate = formatter.format(expectedDate);
        return formattedDate;
    }

}
