package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		// swwitch to Alert (khi alert đang xuất hiện)
		alert = driver.switchTo().alert();
		
		// verify alert title trước khi accept 
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// accept alert
		alert.accept();
		
		// verify accept alert thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");	
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String keyword = "Selenium WebDriver";
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		alert.sendKeys(keyword);
		sleepInSecond(3);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + keyword);
	}
	
	@Test
	public void TC_04_Accept_Alert_Login() {
		driver.get("https://demo.guru99.com/v4");
				
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(3);
		
		alert = driver.switchTo().alert();
		
		// verify alert title
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		// accept alert
		alert.accept();
		sleepInSecond(2);
		
		// verify app url
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/index.php");
	}

	@Test
	public void TC_05_Authentication_Alert_1() {
		// pass hẳn user/ password vào url trước khi open nó ra
		// url: http://the-internet.herokuapp.com/basic_auth
		// pass: username/ password vào url
		// http://username:password@the-internet.herokuapp.com/basic_auth
		
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
			
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
	}
	
	@Test
	public void TC_06_Authentication_Alert_2() {
		driver.get("http://admin:admin@the-internet.herokuapp.com");
		
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getAuthenticationUrl(basicAuthenUrl, "admin", "admin"));
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText().contains("Congratulations! You must have the proper credentials."));
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
	
	public String getAuthenticationUrl(String basicAuthenUrl, String username, String password) {
		String[] authenUrlArray = basicAuthenUrl.split("//");
		basicAuthenUrl = authenUrlArray[0] + "//" + username + ":" + password + "@" + authenUrlArray[1];
		
		return basicAuthenUrl;
	}
	
	@AfterClass
	public void afterClass() {
		// Bước 6: Đóng browser
		driver.quit();
	}
}
