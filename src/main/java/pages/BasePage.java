package pages;


import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import Utils.DriverManager;
import Utils.PropertUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.driverscripts.ScriptOptions;


public class BasePage {
	
	AppiumDriver driver;
	FluentWait <WebDriver> wait;
	int timeout = 5;
	
	protected BasePage() {
		driver = DriverManager.getDriver();
		timeout = Integer.valueOf(PropertUtil.get("timeout"));
		wait = new FluentWait <WebDriver> (driver)
				.pollingEvery(Duration.ofSeconds(1))
				.withTimeout(Duration.ofSeconds(30))
				.ignoring(Exception.class);
	}
	
	
	protected boolean isDisplayed(By by) {		
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
	protected boolean isElementVisible(By by) {
		try {
			wait.until((driver) -> driver.findElement(by));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
		
	protected boolean isAllElementVisile(By by) {
		try {
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			return true;
		} catch (Exception e) {
			return false;
		}			
	}
	
	protected boolean isElementClickable(By by) {
		if(isElementVisible(by)) {
			try {
				String isClickable = wait.until((driver) -> driver.findElement(by).getDomAttribute("clickable"));		
				if (isClickable.equalsIgnoreCase("true")) {
					return true;
				}else {
					return false;
				}			
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	
	}
	
	
	protected void framework_ClickElement(By by) {
		if(isElementVisible(by)) {
			if(isElementClickable(by)) {
				driver.findElement(by).click();
				
			}	
		}

	}
	
	protected List<WebElement> framework_FindElements(By by) {
		if	(isAllElementVisile(by)) {
			return driver.findElements(by);
		}
		else {
			return null;
		}
	}
	
	protected WebElement framework_FindElement(By by) {
		if(isElementVisible(by)) {
			return driver.findElement(by);
		} else {
				return null;
			}
	}

	
	
	
	
	
	
	
	
	
	
}