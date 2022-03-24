package moneyfyTest;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.testng.annotations.Test;

import pages.BalancePage;
import pages.WelcomePage;

public class Add_Income_Expense_test extends BaseTest{
	
	// To Verify that user is able to Launch the application
	
	@Test
	public void launchApp() throws InterruptedException {
		
		WelcomePage wp = new WelcomePage();
		wp.click_get_started();
		wp.click_amazing();
		wp.click_i_am_ready();
		wp.click_close();
		
		BalancePage bp = new BalancePage();
		boolean isDisplayed = bp.ispiegraphDisplayed();
		sa.assertTrue(isDisplayed);
		sa.assertAll();
		
	}

	// To verify  that user is able to add multiple  Income
	
	@Test (dataProvider="moneyfy-dataprovider", dependsOnMethods = "launchApp")
	public void addIncome(String type, String category, String amount, String note) {
		
		System.out.println(type + category + amount + note);
		
		
		BalancePage bp = new BalancePage();
		boolean isDisplayed = bp.ispiegraphDisplayed();
		bp.addBalances(type, category, amount, note);
		String success_message = bp.return_success_message();
		
		String[] liststr = success_message.split(" ");
		
		System.out.println(liststr[0]);
		System.out.println(liststr[1]);
		sa.assertTrue(liststr[0].contains(category));
		
		try {
			Number num = NumberFormat.getCurrencyInstance(Locale.US).parse(liststr[1]);
			Double expected_dollar = Double.valueOf(amount);
			sa.assertEquals(num, expected_dollar);
		} catch (Exception e) {
			e.getMessage();
		}

		
	}
	
	
}
