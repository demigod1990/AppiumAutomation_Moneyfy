package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BalancePage extends BasePage {

	By piegraph = By.id("com.monefy.app.lite:id/piegraph");
	By btn_income = By.id("com.monefy.app.lite:id/income_button");
	By btn_expense = By.id("com.monefy.app.lite:id/expense_button");
	By btn_numbers = By.xpath("//android.widget.Button");
	By btn_category = By.xpath("//android.widget.Button[contains(@text,'CATEGORY')]");
	By element_category = By.id("com.monefy.app.lite:id/textCategoryName");
	By txt_note = By.id("com.monefy.app.lite:id/textViewNote");
	By txt_amountText = By.id("com.monefy.app.lite:id/amount_text");
	By snacksbar_text = By.id("com.monefy.app.lite:id/snackbar_text");
	
	//com.monefy.app.lite:id/textCategoryName[contains(@text,'Deposits')]
	
	
	public BalancePage () {
		super();
	}
	
	public boolean ispiegraphDisplayed () {
		return isElementVisible(piegraph);
	}
	
	public void addBalances(String type, String category, String amount, String note) {
		
		if (type.equalsIgnoreCase("Income")) {	
			framework_ClickElement(btn_income);
			if (isDisplayed(txt_amountText) == false) {
				framework_ClickElement(btn_income);
			}
			enterAmount(amount);
			framework_FindElement(txt_note).sendKeys(note);
			framework_ClickElement(btn_category);
			selectategory(category);
		}
		
		else 
			if (type.equalsIgnoreCase("Expense")) {
				System.out.println("clicing expense button");
				framework_ClickElement(btn_expense);
				if (isDisplayed(txt_amountText) == false) {
					framework_ClickElement(btn_expense);
				}
				System.out.println("entering amount");
				enterAmount(amount);
				framework_FindElement(txt_note).sendKeys(note);
				framework_ClickElement(btn_category);
				selectategory(category);
			
		}
		
	}
	
	
	private void enterAmount(String amount) {
		List<WebElement> list_num = framework_FindElements(btn_numbers);
		char[] chararray = amount.toCharArray();
		
		for (char c: chararray) {
			String s = Character.toString(c);
			for (WebElement el : list_num) {
				if (el.getText().equalsIgnoreCase(s)) {
					System.out.println("entering " + el.getText());
					el.click();
					break;
				}
			}
			
		}

	}
	
	private void selectategory(String category) {
		
		List<WebElement> list_category = framework_FindElements(element_category);
		for(WebElement el : list_category) {
			if (el.getText().equalsIgnoreCase(category)) {
				el.click();
				break;
			}
		}
		
	}

	
	public String return_success_message() {
		String element_text = driver.findElement(snacksbar_text).getText();
		return element_text;
		
	}
	

}
