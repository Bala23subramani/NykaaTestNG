package com.NykaaTestNG.base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Baseclass {
	static WebDriver driver;
	public Baseclass() {
		PageFactory.initElements(driver, this);
	}
	public static void browser_Launch() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\Selenium\\Driver\\chromedriver114.exe");
		ChromeOptions ch = new ChromeOptions();
		ch.addArguments("Incognito");
		driver = new ChromeDriver(ch);
		driver.manage().window().maximize();
		
	}
	
	public static void launchUrl(String url) {
		
		driver.get(url);
	}
	
	public static void clickOnElement(WebElement element) {
		element.click();
		
	}
	
	public static void screenshot(String filename) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\Admin\\eclipse-workspace\\NykaaMVN\\Screenshot\\"+filename+".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {		
			e.printStackTrace();
		}
}
	public static void dropdownselect(WebElement element, String option, String value) {
		Select s = new Select(element);
		
		if(option.equals("value")) {
			s.selectByValue(value);
		}
		else if (option.equals("text")) {
			s.selectByVisibleText(value);
		}
		else if (option.equals("index")) {
			int parseInt = Integer.parseInt(value);
			s.selectByIndex(parseInt);
		}
	}
	
	public static void javascriptexe(WebElement element, String option) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if(option.equals("View")) {
		js.executeScript("arguments[0].scrollIntoView(),"+element+"");
	}
		else if(option.equals("click")) {
		js.executeScript("arguments[0].click(),"+element+"");
		}
		else if(option.equals("scroll")) {
			int number = Integer.parseInt(option);
			js.executeScript("window.ScrollBy(0,"+number+")");	
		}
	}
	public static void waitMethod(WebElement element, int sec, String option) {
		if(option.equals("implicit")) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
}
		else if (option.equals("explicit")) {
			WebDriverWait wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
}
}
