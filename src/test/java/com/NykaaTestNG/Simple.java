package com.NykaaTestNG;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Simple {
    WebDriver driver;

    @BeforeSuite
    public void browserLaunch() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
}
	@BeforeTest
     public void launchUrl() {
		
		driver.get("https://www.nykaa.com/");
	   }
	
	@BeforeMethod
	public void imp_Wait() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@org.testng.annotations.Test( priority = 0, enabled = false)
	public void sing_up() throws InterruptedException {
		
		WebElement sing = driver.findElement(By.xpath("//button[text()='Sign in']"));
		sing.click();
		WebElement sing_in = driver.findElement(By.xpath("//button[text()='Sign in with Mobile / Email']"));
		sing_in.click();
		Thread.sleep(3000);
		WebElement number = driver.findElement(By.name("emailMobile"));
		number.sendKeys("9940567237");
		WebElement submit = driver.findElement(By.id("submitVerification"));
		submit.click();
		Thread.sleep(1000);
		WebElement verify = driver.findElement(By.xpath("//button[text()='verify']"));		
		verify.click();
	}
	
	@org.testng.annotations.Test( priority = 1)
	public void mom_baby() {
		
		WebElement cursor = driver.findElement(By.xpath("//a[text() = 'mom & baby']"));
	    
		Actions a = new Actions(driver);
		a.moveToElement(cursor).build().perform();
		
		WebElement powder = driver.findElement(By.linkText("Baby Powder"));
		powder.click();
	}
	
	@Test( priority = 2)
	public void	Baby_powder() 
	{
        Set<String> windowHandles = driver.getWindowHandles();
        for (String string : windowHandles) {
			driver.switchTo().window(string);
		}
		WebElement Himalaya = driver.findElement(By.className("css-xrzmfa"));
		Himalaya.click();
		
	}
	
	@Test(dependsOnMethods = "Baby_powder" )
	public void Himalaya_Babypowder(){
		Set<String> windowHandles = driver.getWindowHandles();
        for (String string : windowHandles) {
			driver.switchTo().window(string);
			}
		WebElement dropdown = driver.findElement(By.className("css-2t5nwu"));
		Select s = new Select(dropdown);
		s.selectByValue("3");
		WebElement Addto_cart = driver.findElement(By.className("btn-text"));
		Addto_cart.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
	}
	
	@Test(dependsOnMethods = "Himalaya_Babypowder" )
	public void cart() {
		
		
	    WebElement Bag = driver.findElement(By.xpath("//span[@class='cart-count']"));
		Bag.click();
		
		WebElement ele = driver.findElement(By.className("css-acpm4k"));
		ele.click();
		driver.switchTo().frame(ele);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebElement drop = driver.findElement(By.xpath("(//p[@color='#001325'])[4]"));
		drop.click();
		WebElement drop_options = driver.findElement(By.xpath("(//div[@label='4'])[1]"));
		drop_options.click();
	
		WebElement proceed = driver.findElement(By.xpath("//div[@class='css-207d6f e25lf6d6']"));
		proceed.click();

	}
	@AfterMethod
	public void S_Shot() throws IOException {
	
	TakesScreenshot ts =  (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	File dest = new File("C:\\Users\\Admin\\eclipse-workspace\\NykaaTestNG\\Screenshot\\one.png");
	FileHandler.copy(src, dest);
	}
	
	
}