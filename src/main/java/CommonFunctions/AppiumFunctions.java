package main.java.CommonFunctions;

import com.sun.istack.logging.Logger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppiumFunctions {

    public static AndroidDriver<AndroidElement> driver;
    Logger logger = Logger.getLogger(AppiumFunctions.class);

    protected AndroidDriver<AndroidElement> getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "NEXUS_4_API_28");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.calendar");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                "com.android.calendar.AllInOneActivity");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        return driver;
    }

    protected void validateElementOnPage(String xPath) {
        List<AndroidElement> elements = driver.findElements(By.xpath(xPath));
        if (elements.size() > 0) {
            logger.info("Element is displayed on the page");
        } else {
            logger.severe("Element is not displayed on the page");
        }
    }

}
