package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Radio_Checkbox_UseFunction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Custom_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		// Case 1:
		// Thẻ input: bị ẩn nên ko click được
		// Thẻ input: dùng để verify được
		
		// click 
		// driver.findElement(By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input")).click();
		// verify
		//Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input")).isSelected());
		
		// Case 2:
		// Ko dùng được thẻ input để click - thay  thế bằng 1 thẻ đang hiển thị đại diện cho Checkbox/ Radio: span/div/ ..
		// các thẻ này lại ko verify được
		// driver.findElement(By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div")).click();
		// Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div")).isSelected());
		
		// Case 3:
		// thẻ div dùng để click
		// thẻ input dùng để verify
		// driver.findElement(By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div")).click();
		// Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input")).isSelected());
		
		// Case 4: work-around
		// thẻ input để clicl + verify
		// hàm click() của WebElement ko click vào element của hàm bị ẩn được
		// hàm click() của JavascriptExecutor để click: ko quan tâm element có bị ẩn hay ko
		By signinCheckbox = By.xpath("//div[text()='Đăng ký bản thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(signinCheckbox));
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(signinCheckbox).isSelected());
	}

	@Test
	public void TC_03_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By CanThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
		
		// verify trước khi click
		Assert.assertEquals(driver.findElement(CanThoRadio).getAttribute("aria-checked"), "false");
		
		checktoCheckbox("//div[@aria-label='Cần Thơ']");
		
		// verify sau khi click
		Assert.assertEquals(driver.findElement(CanThoRadio).getAttribute("aria-checked"), "true");
		
		unchecktoCheckbox("//div[@aria-label='Cần Thơ']");
		
		By MyQuangCheckbox = By.xpath("//div[@aria-label='Mì Quảng']");
		
		Assert.assertEquals(driver.findElement(MyQuangCheckbox).getAttribute("aria-checked"), "false");
		
		checktoCheckbox("//div[@aria-label='Mì Quảng']");
		
		Assert.assertEquals(driver.findElement(MyQuangCheckbox).getAttribute("aria-checked"), "true");
		
		unchecktoCheckbox("//div[@aria-label='Mì Quảng']");
	}
	
	public void checktoCheckbox(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.getAttribute("aria-checked").equals("false")) {
			element.click();
		}
	}
	
	public void unchecktoCheckbox(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.getAttribute("aria-checked").equals("true")) {
			element.click();
		}
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
