package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
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

	@BeforeClass
	public void beforeClass() {
		// Bước 1: Mở browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Bấm cho maximize browser lên
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_() {
		// Bước 2: Nhập vào Url
		driver.get("https://www.facebook.com/");
		
		// ...
		// Xpath Format: //tagname[@attribute-name='attribute=value']
		
		// Css Format: tagname[attribute-name='attribute=value']
		
	}

	@Test
	public void TC_02_() {
		
		sleepInSecond(5);
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
