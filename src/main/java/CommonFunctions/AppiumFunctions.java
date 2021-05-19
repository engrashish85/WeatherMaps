package main.java.CommonFunctions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

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
        logger.info("Invoked Calendar application successfully");
        return driver;
    }

    protected void validateElementOnPage(String xPath) {
        List<AndroidElement> elements = driver.findElements(By.xpath(xPath));
        assertTrue("Element is displayed on page", elements.size() > 0);
    }

    protected void takeScreenshot() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = formatter.format(date);
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File directory = new File ("output/logs");
        String path = directory.getAbsolutePath();
        FileUtils.copyFile(file, new File(path + "/" + date1 + "/" + date1 + "_failure.jpg"));
    }

}
