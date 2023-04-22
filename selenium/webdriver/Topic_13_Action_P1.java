package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_P1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		// hover vào element
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

//	@Test
//	public void TC_02_Hover() {
//		driver.get("http://www.myntra.com/");
		
//		action.moveToElement(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"))).perform();
//		sleepInSecond(3);
		
//		action.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']"))).perform();
		
//		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "kids home bath");
//	}

	@Test
	public void TC_03_Hover() {
		driver.get("https://www.fahasa.com/");
		
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Kỹ Năng Sống']")).click();
		
		// hàm getText() sẽ get text ở trên UI mà user nhìn thấy
		Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li/strong")).getText(), "KỸ NĂNG SỐNG");
		
		// hàm isDisplayed() sẽ dùng text ở dưới html
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li/strong[text()='Kỹ năng sống']")).isDisplayed());
	}
	
	@Test
	public void TC_04_Click_And_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// đang cần thao tác với cả 12 số
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tổng số lượng number = " + listNumbers.size());
		
		// click vào số bắt đầu và giữ chuột trái (chưa nhả chuột ra)
		// di chuột đến số kết thúc
		// nhả chuột trái ra
		action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(9)).release().perform();
		sleepInSecond(5);
		
		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		System.out.println("Tổng số lượng number đã chọn = " + listNumberSelected.size());
		
		// verify về số lượng element đã chọn
		Assert.assertEquals(listNumberSelected.size(), 6);
		
		
		// thuộc về phần framework (nên ko cần thiết code trong bài này)
		// verify text đã chọn của element
		// define ra 1 mảng chứa các text mình cần verify
		String[] listNumberSelectedActual = {"1", "2", "5", "6", "9", "10"};
		
		// khai báo ra 1 arratlist để lưu lại các giá trị được get trong list bên trên
		ArrayList<String> listNumberSelectedExpected = new ArrayList<String>();
		
		// vòng lặp để duyệt qua list đã chọn ở trên
		for (WebElement number : listNumberSelected) {
			listNumberSelectedExpected.add(number.getText());
		}
		
		// ép kiểu array qua list
		Assert.assertEquals(listNumberSelectedExpected, Arrays.asList(listNumberSelectedActual));
		
	}
	
	@Test
	public void TC_05_Click_And_Hover_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// đang cần thao tác với cả 12 số
		List<WebElement> listNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		System.out.println("Tổng số lượng number = " + listNumbers.size());
		
		// 2 4 5 7 11
		
		// nhấn phím ctrl xuống (chưa nhả ra)
		action.keyDown(Keys.CONTROL).perform();
		
		// click vào các số cần chọn
		action.click(listNumbers.get(1))
		.click(listNumbers.get(3))
		.click(listNumbers.get(4))
		.click(listNumbers.get(6))
		.click(listNumbers.get(10)).perform();
		
		// bỏ phím ctrl ra
		action.keyDown(Keys.CONTROL).perform();
		
		List<WebElement> listNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		System.out.println("Tổng số lượng number đã chọn = " + listNumberSelected.size());
		
		// verify về số lượng element đã chọn
		Assert.assertEquals(listNumberSelected.size(), 5);
	}

	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// scroll tới element trước
		jsExecutor.executeScript("argument[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(3);
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")), "Hello Automation Guys!");
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
