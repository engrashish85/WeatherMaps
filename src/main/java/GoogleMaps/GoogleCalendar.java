package main.java.GoogleMaps;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import main.java.CommonFunctions.AppiumFunctions;
import org.openqa.selenium.By;

import io.appium.java_client.touch.TapOptions;


import java.util.List;

import static io.appium.java_client.touch.offset.ElementOption.element;

public class GoogleCalendar extends AppiumFunctions {
    private static final String RIGHT_ARROW_NAVIGATION =
            "//android.widget.ImageView[@resource-id='com.google.android.calendar:id/right_arrow']";
    private static final String GOT_IT_BUTTON = "//android.widget.Button[@text='GOT IT']";
    private static final String ADD_NEW_EVENT_BUTTON =
            "//android.widget.ImageButton[@content-desc='Create new event and more']";
    private static final String EVENT_LINK = "//android.widget.TextView[@text='Event']";
    private static final String START_DATE_LINK =
            "//android.widget.TextView[@resource-id='com.google.android.calendar:id/value']";
    private static final String NEXT_MONTH = "//android.widget.ImageButton[@content-desc='Next month']";
    private static final String CALENDAR_DAY_TO_BE_CLICKED = "//android.view.View[@content-desc='Value']";
    private static final String OK_BUTTON = "//android.widget.Button[@text='OK']";
    private static final String START_TIME_VIEW =
            "//android.widget.TextView[@resource-id='com.google.android.calendar:id/value']";
    private static final String TIME_SELECTION = "android.widget.RadialTimePickerView$RadialPickerTouchHelper";
    private static final String MINUTES_SELECTION = "//*[@content-desc='Value']";
    private static final String TIME_DAY_NIGHT_SELECTION = "//android.widget.RadioButton[@text='value']";
    private static final String MEETING_TEXT = "//android.widget.EditText[contains(@resource-id,'calendar:id/input')]";
    private static final String DONE_BUTTON = "//android.widget.Button[@text='DONE']";
    private static final String INVITE_PEOPLE = "//android.widget.EditText[contains(@resource-id,'guest_input')]";
    private static final String SAVE_BUTTON = "//android.widget.Button[@text='SAVE']";
    private AndroidElement androidElement;
    private TouchAction touchAction;

    protected void navigateToCalendarMainPage() {
        AndroidElement androidElement;
        androidElement = returnElementByXPath(RIGHT_ARROW_NAVIGATION);
        for (int i = 0; i < 3; i++) {
            androidElement.click();
        }
        androidElement = returnElementByXPath(GOT_IT_BUTTON);
        androidElement.click();
    }

    protected AndroidElement returnElementByXPath(String xPath) {
        androidElement = driver.findElement(By.xpath(xPath));
        return androidElement;
    }

    protected void clickElementOnPage(String xPath) {
        returnElementByXPath(xPath);
        androidElement.click();
    }

    protected void selectDateFromCalendar(String date) {
        clickElementOnPage(ADD_NEW_EVENT_BUTTON);
        clickElementOnPage(EVENT_LINK);
        clickElementOnPage(START_DATE_LINK.replace("value", "start_date"));
        String dateFormat = date.replace("-", " ");
        List<AndroidElement> elementList = driver.findElements(
                By.xpath(CALENDAR_DAY_TO_BE_CLICKED.replace("Value", dateFormat)));
        if (elementList.size() == 0) {
            clickElementOnPage(NEXT_MONTH);
        }
        clickElementOnPage(CALENDAR_DAY_TO_BE_CLICKED.replace("Value", dateFormat));
        clickElementOnPage(OK_BUTTON);
    }

    protected void selectStartAndEndTimeFromCalendar(int startHours, int startMinutes, int endHours, int endMinutes) {
        clickElementOnPage(START_TIME_VIEW.replace("value", "start_time"));
        selectTimeFromCalendar(startHours, startMinutes, "am");
        clickElementOnPage(START_TIME_VIEW.replace("value", "end_time"));
        selectTimeFromCalendar(endHours, endMinutes, "pm");
    }

    private void selectTimeFromCalendar (int hours, int minutes, String timeTypeSelection) {
        List <AndroidElement> elements;
        touchAction = new TouchAction(driver);
        elements = driver.findElementsByClassName(TIME_SELECTION);
        touchAction.tap(TapOptions.tapOptions().withElement(element(elements.get(hours-1)))).perform();
        elements = driver.findElements(By.xpath(MINUTES_SELECTION.replace("Value", String.valueOf(minutes))));
        if (elements.size() > 0) {
            touchAction.tap(TapOptions.tapOptions().withElement(element(elements.get(0)))).perform();
        }
        clickElementOnPage(TIME_DAY_NIGHT_SELECTION.replace("value", timeTypeSelection.toUpperCase()));
        clickElementOnPage(OK_BUTTON);
    }

    protected void selectMeetingTitle() {
        androidElement = returnElementByXPath(MEETING_TEXT);
        androidElement.sendKeys("WORKSHOP");
    }

    protected void invitePeople() {
        androidElement = returnElementByXPath(INVITE_PEOPLE);
        androidElement.sendKeys("engr.ashish@gmail.com");
    }

    protected void clickSaveButton() {
        clickElementOnPage(SAVE_BUTTON);
    }
}
