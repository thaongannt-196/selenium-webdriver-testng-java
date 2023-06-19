package webdriver;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;
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

public class Topic_18_Window_Tab {
	WebDriver driver;
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


	public void TC_01_Github_With_Two_Window_Tab() {
		// driver đang tại trang github
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// trả về id của driver đang đứng tại đó (1 id)
		String githubID = driver.getWindowHandle();
		System.out.println("Github ID = " + githubID);
		System.out.println("Page Title = " + driver.getTitle());
		
		// click vào google link -> theo business nó sẽ mở ra trang Google
		// driver vẫn đang ở trang github
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(1);
		
		System.out.println("Page Title - Github = " + driver.getTitle());
		
		switchToWindowByID(githubID);
		
		// driver đang tại trang google
		System.out.println("Page Title - Google = " + driver.getTitle());
		
		// quay lại trang github
		// trả về id của driver đang đứng tại đó
		String googleID = driver.getWindowHandle();
		System.out.println("Google ID = " + googleID);
		
		switchToWindowByID(googleID);
		System.out.println("Page Title - Github = " + driver.getTitle());
		
	}


	public void TC_02_Github_Greater_Two_Window_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String githubID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Google");
		System.out.println("Page Title - Google = " + driver.getTitle());
		switchToWindowByTitle("Selenium WebDriver");
		System.out.println("Page Title - Github = " + driver.getTitle());
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Facebook – log in or sign up" );
		System.out.println("Page Title - Facebook = " + driver.getTitle());
		switchToWindowByTitle("Selenium WebDriver");
		System.out.println("Page Title - Github = " + driver.getTitle());
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("Page Title - Tiki = " + driver.getTitle());
		
		closeAllWindowWithoutParentID(githubID);
		sleepInSecond(3);
	}


	public void TC_03_TechPanda() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'The product Sony Xperia')]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'The product Samsung Galaxy')]")).isDisplayed());
		
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		System.out.println("Page title = " + driver.getTitle());
		
		// sau khi click close về mặt business nó đã close window này rồi
		// nhưng driver vẫn đang đứng ở trang Products Comparison List
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		sleepInSecond(3);
		
		switchToWindowByTitle("Mobile");
		System.out.println("Page title = " + driver.getTitle());
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
	}
	
	@Test
	public void TC_04_Cambridge() {
		driver.get("https://dictionary.cambridge.org/vi/");
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//span[text()='Đăng nhập']/parent::span")).click();
		sleepInSecond(5);
		
		switchToWindowByTitle("Login");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='username']/following-sibling::span[text()='This field is required']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='password']/following-sibling::span[text()='This field is required']")).isDisplayed());
		sleepInSecond(3);
		
		closeAllWindowWithoutParentID(parentID);
		
		driver.findElement(By.id("searchword")).sendKeys("automation");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		List<WebElement> words = driver.findElements(By.xpath("//div[@class='di-title']//span[text()='automation']"));
		for (WebElement word : words) {
			System.out.println(word.getText());
			Assert.assertTrue(word.getText().contains("automation"));
		}
		
	}
	
	// dùng cho case duy nhất là 2 tab/window
	public void switchToWindowByID(String pageID) {
		// lấy ra tất cả id của các tab/window hiện có
		Set<String> allIDs = driver.getWindowHandles();
		
		// dùng vòng lặp để duyệt qua từng item trong Set
		for (String id : allIDs) {
			
			// trong quá trình duyệt từng item sẽ kiểm tra
			// mếu item nào khác với id truyền vào
			if (!id.equals(pageID)) {
				
				// thì switch vào
				driver.switchTo().window(id);
				sleepInSecond(1);
			}
		}
	}
	
	// dùng cho cả từ 2 tab/window trở lên
	public void switchToWindowByTitle(String pageTitle) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			// switch trước để lấy title
			driver.switchTo().window(id);
			sleepInSecond(1);
			
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTitle)) {
				// thoát khỏi vòng lặp ko duyệt item tiếp theo nữa
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParentID(String parentID) {
		Set<String> allIDs = driver.getWindowHandles();
		
		for (String id : allIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		
		driver.switchTo().window(parentID);
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
