package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Iframe_Frame {
	WebDriver driver;
	Select select;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.fanpage iframe")).isDisplayed());
		
		// Cách 1: switch vào fb iframe
		// driver.switchTo().frame(0);
		
		// cách 2: dùng id
		// driver.switchTo().frame("cs_chat_iframe");
		
		// cách 3: 
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K followers");
		
		// quay về trang trước đó (parent)
		driver.switchTo().defaultContent();
		// switch vào chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		
		// driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")));
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("thảo ngân");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0123456789");
		
		select = new Select(driver.findElement(By.cssSelector("select#serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("abcdef");
		
		driver.switchTo().defaultContent();
		
		String keyword = "Excel";
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(3);
		
		List<WebElement> courseNames = driver.findElements(By.cssSelector("div.content h4"));
		
		for (WebElement course : courseNames) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
		}
	}

	public void TC_02_Iframe() {
		// trang 1 chứa iframe 2
		driver.switchTo().frame("2");
		
		// trang 2 chưa iframe 3
		driver.switchTo().frame("3");
		
		// quay từ 3 về 2 (ko hỗ trợ từ 3 về 1)
		driver.switchTo().parentFrame();
		
		driver.switchTo().defaultContent();
	}

	@Test
	public void TC_03_frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// switch vào frame
		driver.switchTo().frame("login_page");
		
		// thao tác với user id
		driver.findElement(By.name("fldLoginUserId")).sendKeys("kajinn");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		driver.switchTo().defaultContent();
		
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
		
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
