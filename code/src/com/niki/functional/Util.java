package com.niki.functional;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {
static String MobileNumber = "8886101415";

public static void insatllAPP(WebDriver driver)
{
	Scanner sc = new Scanner(System.in);
	String otp;
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
	WebElement otpText = driver.findElement(By
			.id("com.techbins.niki.beta:id/editTxtCode"));
	otpText.sendKeys(otp); 
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertTrue(driver.findElement(
			By.id("com.techbins.niki.beta:id/btn_skip")).isEnabled());
	driver.findElement(
			By.id("com.techbins.niki.beta:id/btn_skip")).click();
}

}
