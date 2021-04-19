package main.java.CommonFunctions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumFunctions {
    @Test
    public void testApplication() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver;
        driver = getAndroidDriver();

    }

    public AndroidDriver<AndroidElement> getAndroidDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "AshishEmulator");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                "com.google.android.calendar.AllInOneCalendarActivity");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        return driver;
    }
}
