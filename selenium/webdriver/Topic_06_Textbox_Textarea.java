package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea {

	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	Random rand = new Random();
	String employeeID = String.valueOf(rand.nextInt(99999));
	String passportNumber = "45017-402-96-7202";
	String comment = "This is a \ncat";
	String username = "thaongan196";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Creat_New_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		// đăng nhập
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		
		// click button login
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(5);
		
		// mở trang add employee
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(3);
		
		// nhập thông tin 
		driver.findElement(By.name("firstName")).sendKeys("Nguyen");
		driver.findElement(By.name("lastName")).sendKeys("Ngan");
		
		WebElement webElementTextbox = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		
		webElementTextbox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		sleepInSecond(1);
		webElementTextbox.sendKeys(Keys.DELETE);
		sleepInSecond(2);
		webElementTextbox.sendKeys(employeeID);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("abcd" + username);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Abc123@@");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Abc123@@");
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(8);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Nguyen");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Ngan");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		sleepInSecond(6);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();	
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
		
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		sleepInSecond(3);
		
		driver.findElement(By.name("username")).sendKeys("abcd" + username);
		driver.findElement(By.name("password")).sendKeys("Abc123@@");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Nguyen");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "Ngan");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();	
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), comment);
			
}
	
	@Test
	public void TC_02_Confirm_New_Employee() {
		
	}
	
	// Sleep cứng (static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
