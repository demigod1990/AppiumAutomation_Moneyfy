package moneyfyTest;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import Utils.DriverManager;
import Utils.PropertUtil;

public class BaseTest {

	SoftAssert sa = new SoftAssert();

	protected BaseTest() {

	}

	@DataProvider (name="moneyfy-dataprovider")
	public Object[][]  returndata(){
		
		Object[][] dataset = new Object [][] {
			{"Income", "Salary", "3520", "Salary of month"},
			{"Income", "Deposits", "250", "Deposit received"},
			{"Income", "Savings", "248", "Own savings"},
			{"Expense",	"Car", "34", "Car maintanance"},
			{"Expense",	"Health", "145", "Covid Medicine"},
			{"Expense",	"House", "1560", "Home Loan Repayment"}						
		};
		return dataset;
	}



	@BeforeTest
	protected void setUp() {

		String device_type = PropertUtil.get("mobile_device_type");

		if (device_type.equalsIgnoreCase("SauceLabs_RealDevice")) {
			try {
				DriverManager.createSession_SauceLabs();
			} catch (MalformedURLException e) {
				e.getMessage();
			}
		}
		else if (device_type.equalsIgnoreCase("Emulator")) {
			try {
				DriverManager.createSession_AndroidEmulator();
			} catch (MalformedURLException e) {
				e.getMessage();
			}
		}	

	}

	@AfterTest
	protected void tearDown() {
		DriverManager.quitDriver();

	}



}
