package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	String firstName, lastName, middleName, employeeID, editFirstName, editMiddleName, editLastName;
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		firstName = "Nguyen";
		lastName = "Ngan";
		middleName = "Thao";
		editFirstName = "Tran";
		editLastName = "Thao";
		editMiddleName = "Nguyen";
	}

	@Test
	public void TC_01_Textbox_Textarea() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		// nhập vào User/ password textbox
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
	
		// click login button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(5);
		
		// mở màn hình add employee	
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
		
		// nhập dữ liệu vào màn hình add employee
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys(middleName);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
		
		// lưu giá trị của employee id vào biến
		// lấy ra giá trị + gán vào biến
		// employeeID = driver.findElement(By.xpath("")).getAttribute("");
		
		// click save button
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		// verify the fields are enabled
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='firstName']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[name='middleName']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[name='lastName']")).isEnabled());
		
		// verify actual value equals expected value
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("data-v-844e87dc"), firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[name='middleName']")).getAttribute("data-v-844e87dc"), middleName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[name='lastName']")).getAttribute("data-v-844e87dc"), lastName);
		
		// tab: gõ ra 1 phần của hàm
		// enter: gõ ra 1 phần rồi enter -> lấy ra toàn bộ tên hàm
		// ctrl space: gợi ý code
	}

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
