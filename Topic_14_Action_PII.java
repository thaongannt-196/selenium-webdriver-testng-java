package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_PII {
//			- Bước 1: Mở browser lên
//			= Bước 2: Nhập vào Url
//			- Bước 3: Click vào My Account để mở trang login ra
//			- Bước 4: Click login
//			- Bước 5: Verify lỗi hiển thị
//			- Bước 6: Đóng browser

	// Khai báo 1 biến để đại diện cho thư viện Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		// Bước 1: Mở browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		// Bấm cho maximize browser lên
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// click chuột phải vào button
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		
		// hover chuột vào context menu: Paste
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		
		// verify Paste có trạng thái: hover/visible 
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste.context-menu-hover.context-menu-visible")).isDisplayed());
		
		// click vào Paste
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		
		// handle alert
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		
		// verify Paste có trạng thái: invisible
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());
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
