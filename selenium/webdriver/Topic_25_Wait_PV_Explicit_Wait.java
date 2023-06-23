package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_PV_Explicit_Wait {
	WebDriver driver;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	
	public void TC_01_Wait_For_Attribute_Contain_Value() {
		driver.get("http://live.techpanda.org/index.php/");
		expliciWait = new WebDriverWait(driver, 30);
		
		// Wait cho Search textbox chứa giá trị là 1 đoạn placeholder text
		// không lấy hết giá trị dùng contain
		expliciWait.until(ExpectedConditions.attributeContains(By.cssSelector("input#search"), "placeholder", "Search entire store"));
		
		// lấy giá trị tuyệt đối dùng ToBe
		expliciWait.until(ExpectedConditions.attributeToBe(By.cssSelector("input#search"), "placeholder", "earch entire store here..."));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#search")).getAttribute("placeholder"), "Search entire store here...");
	}
	
	
	public void TC_02_Wait_For_Element_Clickable() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		expliciWait = new WebDriverWait(driver, 10);
		
		// wait cho start button được ready trước khi click
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		driver.get("https://login.mailchimp.com/signup/");
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#create-account-enabled")));
		driver.findElement(By.cssSelector("button#create-account-enabled")).click();	
	}
	

	public void TC_03_Wait_For_Element_Selected() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		expliciWait = new WebDriverWait(driver, 10);
		
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input.form-checkbox"));
		
		// dùng vòng lặp để duyệt qua và click chọn
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
		}
		
		// dùng vòng lặp để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxes) {
			expliciWait.until(ExpectedConditions.elementToBeSelected(checkbox));
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	
	
	public void TC_04_Wait_For_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		expliciWait = new WebDriverWait(driver, 10);
		
		// switch vào frame
		expliciWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("login_page")));
		
		
		// thao tác với user id
		driver.findElement(By.name("fldLoginUserId")).sendKeys("kajinn");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
		driver.switchTo().defaultContent();
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("keyboard")));
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
	}
	
	@Test
	public void TC_05_Wait_For_GetText() {
		driver.get("http://live.techpanda.org/");
		expliciWait = new WebDriverWait(driver, 10);
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='footer']//a[@title='My Account']")));
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		expliciWait.until(ExpectedConditions.elementToBeClickable(By.id("send2")));
		driver.findElement(By.id("send2")).click();
		
		expliciWait.until(ExpectedConditions.textToBe(By.cssSelector("#advice-required-entry-email"), "This is a required field."));
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(), "This is a required field.");
		
		expliciWait.until(ExpectedConditions.textToBe(By.cssSelector("#advice-required-entry-pass"), "This is a required field."));
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
	
	public void TC_01_Timeout_Less_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		expliciWait = new WebDriverWait(driver, 3);
				
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

	
	public void TC_02_Timeout_Equal_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		expliciWait = new WebDriverWait(driver, 5);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
	}

	
	public void TC_03_Timeout_More_Than_5_Seconds() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		expliciWait = new WebDriverWait(driver, 1000);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
		
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
