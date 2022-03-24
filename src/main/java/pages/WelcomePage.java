package pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WelcomePage extends BasePage {

	By btn_get_started = By.xpath("//android.widget.Button[@resource-id='com.monefy.app.lite:id/buttonContinue']");
	By btn_amazing = By.xpath("//android.widget.Button[@resource-id='com.monefy.app.lite:id/buttonContinue'][contains(@text,'AMAZING')]");
	By btm_i_am_ready = By.xpath("//android.widget.Button[@resource-id='com.monefy.app.lite:id/buttonContinue'][contains(@text,'READY')]");
	By btn_close = By.id("com.monefy.app.lite:id/buttonClose");
	By welcome_page_header = By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewHeader']");
	By second_pageheader = By.xpath("//android.widget.TextView[@resource-id='com.monefy.app.lite:id/textViewHeader'][contains(@text,'Control your spend and start saving')]");


	public WelcomePage () {	
		super();
	}

	public void click_get_started() {
		if (isElementVisible(welcome_page_header)) {
			framework_ClickElement(btn_get_started);
		}
	}

	public void click_amazing() {
		if (isElementVisible(second_pageheader)) {
			framework_ClickElement(btn_amazing);
		}
	}

	public void click_i_am_ready() {
		framework_ClickElement(btm_i_am_ready);
	}

	public void click_close() {
		framework_ClickElement (btn_close);
	}

}
