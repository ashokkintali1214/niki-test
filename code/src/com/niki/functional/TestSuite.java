package com.niki.functional;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestSuite {
	WebDriver driver;
	DesiredCapabilities caps = new DesiredCapabilities();
	Scanner sc = new Scanner(System.in);
	String otp;

	@Test(priority=1)
	public void validLogin() {
		WebElement mobileNumber = driver.findElement(By
				.id("com.techbins.niki.beta:id/edtTxtPhone"));
		mobileNumber.sendKeys(Util.MobileNumber);
		//driver.navigate().back();
		WebElement submit = driver.findElement(By
				.id("com.techbins.niki.beta:id/btnSubmit"));
		submit.click();
		/* if used mobile number is in aniother moile elable this code*/
		System.out.println("Enter OTP: ");
	otp = sc.next();
		WebElement otpText = driver.findElement(By.id("com.techbins.niki.beta:id/editTxtCode"));
		otpText.sendKeys(otp);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(
				By.id("com.techbins.niki.beta:id/btn_skip")).isEnabled());
		for (int i = 1; i <= 6; i++) {
			driver.findElement(By.id("com.techbins.niki.beta:id/btn_next"))
					.click();
		}
		Assert.assertTrue("Home Page ", driver.findElement(By.name("Niki")).isDisplayed());

	}
	@Test(priority=2)
	public void menuItemsVerification()
	{ 
		Util.insatllAPP(driver);
		
		Assert.assertTrue("Home Page ", driver.findElement(By.name("Niki")).isDisplayed());
		WebElement menuButton = driver.findElement(By.id("com.techbins.niki.beta:id/nikiMenuBlink"));
		menuButton.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement myOrders = driver.findElement(By.name("My Orders"));
		Assert.assertTrue(myOrders.isDisplayed());
		WebElement Improve_My_Experience = driver.findElement(By.name("Improve My Experience"));
		Assert.assertTrue(Improve_My_Experience.isDisplayed());
		WebElement Notifications = driver.findElement(By.name("Notifications"));
		Assert.assertTrue(Notifications.isDisplayed());
		
	}
	
	@Test(priority=3)
	public void nikiServiceIcon()
	{
		Util.insatllAPP(driver);
		WebElement helpMenu = driver.findElement(By.id("com.techbins.niki.beta:id/btnHelp"));
		helpMenu.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement phoneRecharge = driver.findElement(By.name("Recharge my phone"));
		Assert.assertTrue(phoneRecharge.isDisplayed());
		
		
	}
	@Test(priority=4)
	public void nikiReplay()
	{
		String str = "Hi ! Let me assist you with prepaid recharge. You can type cancel at any point to exit this conversation";
		Util.insatllAPP(driver);
		WebElement nikiTextbox = driver.findElement(By.name("How can I help you?"));
		nikiTextbox.sendKeys("Rechage my phone");
		WebElement sendBtn = driver.findElement(By.id("com.techbins.niki.beta:id/btnSend"));
		sendBtn.click();
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebElement nikireply = driver.findElement(By.name(str));
		Assert.assertEquals(str, nikireply.getText());
		
		
	}
	
	@BeforeMethod
	public void beforeTest() throws MalformedURLException {
		File app = new File(
				"E:\\ashok\\appium\\appium\\NikiBeta_test\\ExternalFiles\\app-test-apk.apk");
		caps.setCapability(MobileCapabilityType.APP, app);
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "BH90AD1C0G");
		caps.setCapability("appPackage", "com.techbins.niki.beta");
		caps.setCapability("appActivity", "com.techbins.niki.SplashActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 300);
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("com.techbins.niki.beta:id/edtTxtPhone")));
		 
	}

	@AfterMethod
	public void afterTest() {
		driver.quit();
	}

}
