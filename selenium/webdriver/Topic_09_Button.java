package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fahasa() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(3);
		
		// switch qua iframe trước khi close pop-up
		driver.switchTo().frame("moe-onsite-campaign-6365e6241bb2b45645e41cb3");
		
		// close pop-up
		driver.findElement(By.cssSelector("button.close-icon>img")).click();
		
		// quay về trang trước đó chưa iframe
		driver.switchTo().defaultContent();
		
		// chuyển qua tab login
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		// verify 'Đăng nhập' button is disable
		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("tngan@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		
		// verify 'Đăng nhập' button is Enable
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		
		// verify 'Đăng nhập' button - background color: RED
		String rgbaColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		System.out.println("RGB color = " + rgbaColor);
		
		// rgbaColorert to Hexa color
		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		System.out.println("RGB color = " + hexaColor);
		
		// verify background color
		Assert.assertEquals(hexaColor, "#C92127");
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
		driver.quit();
	}
	
	
}
