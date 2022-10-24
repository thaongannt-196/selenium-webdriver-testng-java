package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown_List {
	WebDriver driver;
	Select select;
	Random rand = new Random();
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Default_Dropdown() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();
		sleepInSecond(3);
		
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Nguyen");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Ngan");
		
		// khởi tạo select để thao tác với Day dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		// chọn item có text = bao nhiêu ???
		select.selectByVisibleText("1");
		
		// khởi tạo select để thao tác với Month dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("September");
		
		// khởi tạo select để thao tác với Year dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1996");	
		
		String email = "thaongan" + rand.nextInt(9999) + "@gmail.com";
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		
		driver.findElement(By.cssSelector("input#Company")).sendKeys("Unimob");
		
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button#register-button")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.button-1")).click();
		sleepInSecond(1);
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), "Nguyen");
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), "Ngan");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "September");
		
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1996");
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), "Unimob");
		
	}

	@Test
	public void TC_02_() {
		
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
		// Bước 6: Đóng browser
		driver.quit();
	}
}
