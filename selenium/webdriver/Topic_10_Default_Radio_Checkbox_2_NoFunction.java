package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Default_Radio_Checkbox_2_NoFunction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Jotform() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// chọn checkbox: Cancer - Fainting Spells
		// 1 - //input[@value='Cancer']
		// 2 - //label[contains(text(), 'Cancer')]/preceding-sibling::input
		driver.findElement(By.xpath("//input[@value='Cancer']")).click();
		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();
		
		// verify nó được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Cancer']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());
		
		// chọn radio: 5+ days - 1-2 cups/day
		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
		driver.findElement(By.xpath("//input[@value='1-2 days']")).click();
		
		// verify nó được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1-2 days']")).isSelected());
		
		/* bỏ chọn thì chỉ cần click tiếp 1 lần nữa là nó bỏ chọn đi */
		// bỏ chọn checkbox: Cancer - Fainting Spells
		driver.findElement(By.xpath("//input[@value='Cancer']")).click();
		driver.findElement(By.xpath("//input[@value='Fainting Spells']")).click();
		
		// verify nó được bỏ chọn thành công
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Cancer']")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@value='Fainting Spells']")).isSelected());
		
		/* bỏ chọn thì chỉ cần click radio khác là nó bỏ radio đang chọn đi */
		// bỏ chọn radio: 5+ days - 1-2 cups/day
		driver.findElement(By.xpath("//input[@value='5+ days']")).click();
		driver.findElement(By.xpath("//input[@value='1-2 days']")).click();
		
		// verify nó được chọn thành công
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='5+ days']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1-2 days']")).isSelected());
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
	
	@Test
	public void TC_02_Jotform_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		// dùng vòng lặp để duyệt qua và click chọn
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
			}
		}
		
		// dùng vòng lặp để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
	}

	@AfterClass
	public void afterClass() {
		// Bước 6: Đóng browser
		driver.quit();
	}
}
