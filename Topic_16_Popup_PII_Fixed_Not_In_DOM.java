package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_PII_Fixed_Not_In_DOM {


	// Khai báo 1 biến để đại diện cho thư viện Selenium WebDriver
	WebDriver driver;
	Select select;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
//		Map<String, Integer> prefs = new HashMap<String, Integer>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Fixed_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		
		// By: Nó chưa có đi tìm element
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		// WebElement: chưa kịp mở popup đi tìm element -> nó ko có
		
		// verify nó chưa hiển thị khi chưa click vào login button
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		
		// click cho bật login popup
		driver.findElement(By.cssSelector("div[data-view-id*='header_account']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(loginPopup).size(), 1);
		
//		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed()); (ko dùng, bị false)
		
		driver.findElement(By.cssSelector("input[name='tel']")).sendKeys("0352080809");
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(2);
		
		// veriy error message displayed
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
		sleepInSecond(2);
		
		
		// close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(2);
		
		
	}

	@Test
	public void TC_02_Fixed_Not_In_DOM__Facebook() {
		driver.get("https://www.facebook.com/");
		
		By createAccountPopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
		
		// verify popup không hiển thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
		
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(2);
		
		// verify popup hiển thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 1);
		
		driver.findElement(By.name("firstname")).sendKeys("Automation");
		driver.findElement(By.name("lastname")).sendKeys("FC");
		driver.findElement(By.name("reg_email__")).sendKeys("0352080809");
		driver.findElement(By.name("reg_passwd__")).sendKeys("123456789");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Sep");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1996");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		// verify popup không hiển thị
		Assert.assertEquals(driver.findElements(createAccountPopup).size(), 0);
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
