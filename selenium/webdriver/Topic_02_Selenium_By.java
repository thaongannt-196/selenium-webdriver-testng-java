package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_By {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		// ID - Eemail Textbox
		driver.findElement(By.id("email"));
		
		// Class - New User form
		driver.findElement(By.className("new-users"));
		
		// Name - Email Textbox
		driver.findElement(By.name("login[username]"));
		
		// Tagname - Tìm xem có bao nhiêu element/ page
		driver.findElement(By.tagName("a"));
		
		// LinkText (Link)
		driver.findElement(By.linkText("SEARCH TERMS"));
		
		// Partial LinkText (link) - text tương đối/ tuyệt đối
		driver.findElement(By.partialLinkText("SEARCH TERMS"));
		driver.findElement(By.partialLinkText("RCH TER"));
		driver.findElement(By.partialLinkText("SEARCH"));
		driver.findElement(By.partialLinkText("TERMS"));
		
		// Css - Cover được hết 6 loại trên
		driver.findElement(By.cssSelector("input[name='login[username]']"));
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input[title='Email Address']"));
		
		//Xpath
		driver.findElement(By.xpath("//input[@name='login[username]']"));
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@title='Email Address']"));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}
