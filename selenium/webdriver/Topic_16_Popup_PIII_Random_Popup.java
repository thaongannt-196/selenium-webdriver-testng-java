package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_PIII_Random_Popup {


	// Khai báo 1 biến để đại diện cho thư viện Selenium WebDriver
	WebDriver driver;
	Select select;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;
	
	String emailAddress = "testdemo" + getRandomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
//		Map<String, Integer> prefs = new HashMap<String, Integer>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Yêu cầu:
	// Random popup nên nó có thể hiển thị 1 cách ngẫy nhiên hoặc ko hiển thị
	// nếu như nó hiển thị thì mình cần thao tác lên popup -> đóng nó đi để qua step tiếp theo
	// khi mà đóng nó lại thì có thể refresh trang để nó hiện lại hoặc mất (ko hiện lại nữa)
	// nếu nó không hiển thị thì qua step tiếp thao luôn 
	
    @Test
	public void TC_01_Radom_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(30);
		
		By lePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		
		// vì nó luôn có trong DOM nên có thể dùng hàm isDisplay() để kiểm tra
		if (driver.findElement(lePopup).isDisplayed()) {
			// Nhập Email vào
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
			sleepInSecond(1);
			driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
			sleepInSecond(5);
			// verify
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(), "Thank you!");
			
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
			// Đóng popup đi -> qua step tiếp theo
			// sau 5s sẽ tự đóng popup
			sleepInSecond(10);
			
			}
		
		String articlename = "Agile Testing Explained";
		
		// qua step này
		driver.findElement(By.cssSelector("input#search-input")).sendKeys(articlename);
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Agile Testing Explained']")).getText(), articlename);
		
	}

	@Test
	public void TC_02_Radom_In_DOM_VNK_EDU() {
		driver.get("https://vnk.edu.vn//");
		sleepInSecond(20);
		By popup = By.cssSelector("div#tve-p-scroller");
		
		// Hàm isDisplayed() chỉ check element có trong DOM
		if (driver.findElement(popup).isDisplayed()) {
			// close popup này đi hoặc là click vào link để join các Group Zalo
			driver.findElement(By.cssSelector("div#tve-p-scroller div.thrv_icon")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
		
	}

	@Test
	public void TC_03_Radom_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(20);
		
		By popup = By.cssSelector("div.popup-content");
		// findelement -> sẽ bị fail khi ko tìm thấy element -> Ném ra 1 ngoại lệ: NóuchElementException
		// findelements -> ko bị fail khi ko tìm thấy element -> trả về 1 list rỗng (empty)
		
		// isDisplayed()
		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("Thao Ngan");
			driver.findElement(By.id("popup-email")).sendKeys(emailAddress);
			driver.findElement(By.id("popup-phone")).sendKeys("0123456789");
			sleepInSecond(3);
			
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(3);
		}
		
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		
		String coursename = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.id("search-courses")).sendKeys(coursename);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);
		
		// Duy nhất 1 course hiển thị
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content>h4")).getText(), coursename);
		
	}
	
	public int getRandomNumber() {
		 return new Random().nextInt(99999);
		
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
