package com.template;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import pageObjects.User_HomePageObject;
import pageObjects.User_LogInPageObject;
import pageObjects.PageGeneratorManager;
import utilitiesConfig.DataFacker;

public class TC_01_LogIn extends BaseTest{
	WebDriver driver;
	User_HomePageObject homePage;
	User_LogInPageObject logInPage;
	String invalidEmail,invalidPassword;
	DataFacker faker = DataFacker.getData();
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserName(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		homePage.clickOnHeaderLink(driver, "Log in");
		logInPage = PageGeneratorManager.getUserLogInPage(driver);
		invalidEmail="1234";
		invalidPassword="1234";
	}
	
	@BeforeMethod
	public void navigateBrowser() {
		logInPage.navigateToUrl(driver,"https://demo.nopcommerce.com/login?returnUrl=%2F");
	}

	@Description("LogIn - LogIn with empty data")
	@Step("LogIn - LogIn with empty data")
	@Test
	public void TC01_LogIn_EmptyData(){
		log.info("Click on Log In button");
		logInPage.clickOnButton(driver, "Log in");
		
		log.info("Get error message on Email text box");
		verifyEquals(logInPage.getTextOfMessageError(driver,"Email"),"Please enter your email");
	}
	
	@AfterClass
	public void afterClass() {
		log.info("Close browser");
		closeBrowserAndDriver(driver);
	}
}

