package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Wait_PII_Find_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");
	JavascriptExecutor jsExecutor;
	List<WebElement> elements;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		// implicit wait là 10 giây
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/reg/");
		
	}

	@Test
	public void TC_01_Find_Element() {
		// trả về 1 element
		
		// 3 trường hợp
		// Case 1: nếu tìm nhưng chỉ có 1 element được tìm thấy
		// FindElement sẽ apply cái timeout của implicitWWait
		// vì nó vào và tìm thấy element ngay nên ko cần phải chờ hết timeout là 10s
		driver.findElement(By.cssSelector("input[name='firstname']"));
		
		// Case 2: tìm thấy nhiều hơn 1 element
		// nó sẽ luôn lấy element đầu tiên để sử dụng
		// vì nó vào và tìm thấy element ngay nên ko cần phải chờ hết timeout là 10s
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys("automation");
		
		// Case 3: ko tìm thấy element nào hết
		// nó sẽ ko tìm thấy
		// tiếp tục tìm đi tìm lại
		// mỗi nửa giây tìm lại 1 lần
		// nếu tìm lại mà thấy thì như case 1-2 (ko cần chờ hết timeout còn lại)
		// nếu tìm lại mà ko thấy và hết timeout thì đánh fail testcase
		// throw ra 1 exception: NoSuchElement (ko có element nào hết) 
		driver.findElement(By.xpath("//div[text()=\"What's your name?\"]"));
	}

	@Test
	public void TC_02_Find_Elements() {
		// trả về nhiều element
		
		// 3 trường hợp
		// Case 1: nếu tìm nhưng chỉ có 1 element được tìm thấy
		// FindElements sẽ apply cái timeout của implicitWWait
		// vì nó vào và tìm thấy element ngay nên ko cần phải chờ hết timeout là 10s
		elements = driver.findElements(By.cssSelector("input[name='firstname']"));
		System.out.println("Case 1: Tìm thấy 1 element = " + elements.size());
		
		// Case 2: tìm thấy nhiều hơn 1 element
		// nó sẽ lấy ra hết tất cả các element được tìm thấy
		// vì nó vào và tìm thấy element ngay nên ko cần phải chờ hết timeout là 10s
		elements = driver.findElements(By.cssSelector("input[type='text']"));
		System.out.println("Case 2: Tìm thấy hơn 1 element = " + elements.size());
		
		// Case 3: ko tìm thấy element nào hết
		// nó sẽ ko tìm thấy
		// tiếp tục tìm đi tìm lại
		// mỗi nửa giây tìm lại 1 lần
		// nếu tìm lại mà thấy thì như case 1-2 (ko cần chờ hết timeout còn lại)
		// Nếu tìm lại mà ko thấy và hết timeout thì ko có đánh fail testcase
		// ko show exception nào hết
		// trả về 1 ;ist = 0 (empty)
		elements = driver.findElements(By.xpath("//div[text()=\"What's your name?\"]"));;
		System.out.println("Case 3: Ko tìm thấy element = " + elements.size());
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
