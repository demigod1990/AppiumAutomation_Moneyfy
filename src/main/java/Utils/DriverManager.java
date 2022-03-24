package Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

	private DriverManager() {}

	private static AppiumDriver driver = null;
	public static AppiumDriver getDriver() {
		
		if(Objects.isNull(driver)) {
			System.out.println("Driver not initilized");
			return null;
		}
		else {
			return driver;
		}
		
	}

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


	public static void createSession_SauceLabs() throws MalformedURLException  {
		
		String url = "https://oauth-subhankar01.n26-ea4a0:91be8606-80ad-4bd8-995c-d83884730e33"
				+ "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
		URL remote_url = new URL(url);
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date();
        new Timestamp(date.getTime());
        String strTimestamp = DATE_FORMAT.format(timestamp);
		
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("appium:deviceName", PropertUtil.get("SAUCE_DEVICE_NAME"));
		caps.setCapability("platformName", PropertUtil.get("SAUCE_DEVICE_PLATFORM_NAME"));
		caps.setCapability("appium:platformVersion", PropertUtil.get("SAUCE_DEVICE_PLATFORM_VERSION"));
		caps.setCapability("appium:automationName", PropertUtil.get("SAUCE_AUTOMATION_NAME"));
		caps.setCapability("appium:appPackage", PropertUtil.get("SAUCE_APP_PACKAGE"));
		caps.setCapability("appium:appActivity", PropertUtil.get("SAUCE_APP_ACTIVITY"));
		caps.setCapability("appium:app", "storage:filename=" + PropertUtil.get("APP_FILENAME"));
		
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("username", PropertUtil.get("SAUCE_USERNAME"));
		sauceOptions.setCapability("accessKey", PropertUtil.get("SAUCE_ACCESS_KEY"));
		caps.setCapability("sauce:options", sauceOptions);
		
		driver = new AppiumDriver(remote_url, caps);
		((JavascriptExecutor)driver).executeScript("sauce:job-name=Test_Moneyfy_SauceLab_" + strTimestamp );		
		
	}
	
	
	public static void createSession_AndroidEmulator() throws MalformedURLException {
		String url = "http://127.0.0.1:4723/wd/hub";
		URL remote_url = new URL(url);
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "com.monefy.app.lite");
        capabilities.setCapability("appActivity", "com.monefy.activities.main.MainActivity_");

		driver = new AppiumDriver (remote_url,capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	
	public static void quitDriver() {
		
		if(Objects.nonNull(driver)) {
			driver.quit();
		}
		
	}


}
