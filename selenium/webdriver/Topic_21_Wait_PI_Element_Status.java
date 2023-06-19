package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Wait_PI_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// cho việc tìm element (findElement/ findElements) -> chung chung
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// cho trạng thái của element -> cụ thể
		explicitWait = new WebDriverWait(driver, 30);
		
	}

	
	public void TC_01_Visible_Displayed() {
		driver.get("https://www.facebook.com");
		
		// Điều kiện 1: Element có trên UI và có trong cây HTML
		
		// chờ cho Email textbox được hiển thị trước khi sendKeys vào nó
		// chờ trong khoảng time là 30s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		
		driver.findElement(By.cssSelector("input#email")).sendKeys("thaongan@gmail.com");
	}

	
	public void TC_02_Invisible_Undisplayed_Case_I() {
		// Điều kiện 2: Element ko có/ ko nhìn thấy trên UI nhưng vẫn có trong cây HTML
		
		driver.get("https://www.facebook.com");
		
		driver.findElement(By.cssSelector("a[data-testid=\"open-registration-form-button\"]")).click();
		
		// confirmation email textbox is undisplayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("thaongan@gmail.com");
		
		// confirmation email textbox is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}
	
	
	public void TC_02_Invisible_Undisplayed_Case_II() {
		// Điều kiện 3: Element ko có/ ko nhìn thấy trên UI và cũng ko có trong cây HTML
		
		driver.get("https://www.facebook.com");
		
		// confirmation email textbox is undisplayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_03_Presense_Case_I() {
		// Điều kiện 1: Element có trên UI và có trong cây HTML
		driver.get("https://www.facebook.com");
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#email")));
	}
	
	@Test
	public void TC_03_Presense_Case_II() {
		// Điều kiện 2: Element ko có/ ko nhìn thấy trên UI nhưng vẫn có trong cây HTML
		driver.get("https://www.facebook.com");
		
		driver.findElement(By.cssSelector("a[data-testid=\"open-registration-form-button\"]")).click();
		
		// confirmation email textbox is presense
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
	}
	
	@Test
	public void TC_04_Staleness() {
		// apply:  có trong HTML và sau đó apply điều kiện 3 
		driver.get("https://www.facebook.com");
		driver.findElement(By.cssSelector("a[data-testid=\"open-registration-form-button\"]")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
		// B1: Element phải có trong HTML
		WebElement confirmationEmailTextbox = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		
		// wait cho confirm email staleness
		explicitWait.until(ExpectedConditions.stalenessOf(confirmationEmailTextbox));
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
