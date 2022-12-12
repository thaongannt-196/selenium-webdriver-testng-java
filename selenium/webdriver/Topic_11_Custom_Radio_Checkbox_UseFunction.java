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

public class Topic_10_Default_Radio_Checkbox_UseFunction {
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
		checkToCheckboxOrRadio("//input[@value='Cancer']");
		checkToCheckboxOrRadio("//input[@value='Fainting Spells']");
		
		// verify nó được chọn thành công
		Assert.assertTrue(isElementSelected("//input[@value='Cancer']"));
		Assert.assertTrue(isElementSelected("//input[@value='Fainting Spells']"));
		
		// chọn radio: 5+ days - 1-2 cups/day và verify nó được chọn thành công
		checkToCheckboxOrRadio("//input[@value='5+ days']");
		Assert.assertTrue(isElementSelected("//input[@value='5+ days']"));
		
		checkToCheckboxOrRadio("//input[@value='1-2 days']");
		Assert.assertTrue(isElementSelected("//input[@value='1-2 days']"));
				
		/* bỏ chọn thì chỉ cần click tiếp 1 lần nữa là nó bỏ chọn đi */
		// bỏ chọn checkbox: Cancer - Fainting Spells
		uncheckToCheckboxOrRadio("//input[@value='Cancer']");
		uncheckToCheckboxOrRadio("//input[@value='Fainting Spells']");
		
		// verify nó được bỏ chọn thành công
		Assert.assertFalse(isElementSelected("//input[@value='Cancer']"));
		Assert.assertFalse(isElementSelected("//input[@value='Fainting Spells']"));
		
		/* bỏ chọn thì chỉ cần click radio khác là nó bỏ radio đang chọn đi */
		// bỏ chọn radio: 5+ days - 1-2 cups/day và verify nó được bỏ chọn thành công
		checkToCheckboxOrRadio("//input[@value='5+ days']");
		Assert.assertFalse(isElementSelected("//input[@value='1-2 days']"));
		
		checkToCheckboxOrRadio("//input[@value='1-2 days']");
		Assert.assertFalse(isElementSelected("//input[@value='5+ days']"));
	}
	
	public void checkToCheckboxOrRadio(String xpathLocator) {
		if (!driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			driver.findElement(By.xpath(xpathLocator)).click();
		}
	}
	
	public void checkToCheckboxOrRadio(WebElement element) {
		if (!element.isSelected() && element.isEnabled()) {
			System.out.println("Click to element: " + element);
			element.click();
			Assert.assertTrue(isElementSelected(element));
		}
	}
	
	public void uncheckToCheckboxOrRadio(String xpathLocator) {
		if (driver.findElement(By.xpath(xpathLocator)).isSelected()) {
			driver.findElement(By.xpath(xpathLocator)).click();
		}
	}
	
	public void uncheckToCheckboxOrRadio(WebElement element) {
		if (element.isSelected() && element.isEnabled()) {
			element.click();
			Assert.assertFalse(isElementSelected(element));
		}
	}
	
	public boolean isElementSelected(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator)).isSelected();
	}

	public boolean isElementSelected(WebElement element) {
		return element.isSelected();
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
			checkToCheckboxOrRadio(checkbox);
			sleepInSecond(1);
		}
		
		// dùng vòng lặp để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertTrue(isElementSelected(checkbox));
		}
		
		// bỏ chọn hết 
		for (WebElement checkbox : allCheckboxes) {
			uncheckToCheckboxOrRadio(checkbox);
		}
		
		// dùng vòng lặp để duyệt qua và kiểm tra
		for (WebElement checkbox : allCheckboxes) {
			Assert.assertFalse(isElementSelected(checkbox));
		}
	}

	@Test
	public void TC_03_Select_All() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(5);
		
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[@id='example']//input[@type='checkbox']"));
		
		// vừa chọn vừa verify
		for (WebElement checkbox : allCheckboxes) {
			checkToCheckboxOrRadio(checkbox);
		}

		// vừa chọn vừa verify
		for (WebElement checkbox : allCheckboxes) {
			uncheckToCheckboxOrRadio(checkbox);
		}	
	}
	
	@Test
	public void TC_04_Select_All() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(5);
		
		checkToCheckboxOrRadio("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		Assert.assertTrue(isElementSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		uncheckToCheckboxOrRadio("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		Assert.assertFalse(isElementSelected("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		sleepInSecond(5);
		
		checkToCheckboxOrRadio("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		Assert.assertTrue(isElementSelected("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
	}
	
	@AfterClass
	public void afterClass() {
		// Bước 6: Đóng browser
		driver.quit();
	}
}
