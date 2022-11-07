package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Tương tác với Browser thì sẽ thông qua biến WebDriver driver
		// Tương tác với Element thì sẽ thông qua biến WebElement element
	}

	@Test
	public void TC_01_Browser() {
		// Các hàm tương tác với Browser sẽ thông qua biến driver
		
		// Đóng tab/ window đang active
		driver.close(); // **
		
		// Đóng browser
		driver.quit(); // **
		
		// Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector("")); // **
		
		// Tìm ra nhiều element (multiple)
		List<WebElement> links = driver.findElements(By.cssSelector("")); // **
		
		// Mở ra cái Url truyền vào
		driver.get("https://www.facebook.com/"); // **
		
		// Trả về 1 Url tại page đang đứng
		String gamePageUrl = driver.getCurrentUrl();
		
		String gamePageTitle = driver.getTitle();
		
		// Source code của page hiện tại (ít dùng)
		String gamePageSource = driver.getPageSource();
		
		// Lấy ra ID của tab/ window đang đứng/ active (windows/tab)
		driver.getWindowHandle(); // 1 // **
		driver.getWindowHandles(); // tất cả // **
		
		driver.manage().getCookies(); // Cookies (Framework) // **
		driver.manage().logs().getAvailableLogTypes(); // Log (Framework)
		
		driver.manage().window().maximize();
		driver.manage().window().fullscreen(); // **
		
		// Tesst GUI ̣(Graphic User Interface)
		// Font/ Size/ Color/ Position/ Location/ ...
		// Ưu tiên làm chức năng trước (Function UI)
		// Ưu tiên làm giao diện sau (Graphic UI)
		
		// Chờ cho element được tìm thấy trong khoảng time xx giây (WebDriverWait)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // **
		
		// Chờ cho page load thành công sau xx giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Chờ cho script được Inject thành công vào browser/ element  sau xx giây (JavaScriptExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		
		// Alert/ Frame (IFrame)/ Window (Tab)
		driver.switchTo().alert(); // **
		
		driver.switchTo().frame(0); // **
		
		driver.switchTo().window(""); // **
	}

	@Test
	public void TC_02_Elements() {
		
		// có 2 cách thao tác
		// khai báo biến và dùng lại
		// dùng đi dùng lại nhiều lần - ít nhất là 2 lần thì mới cần khai báo biến
		
		// khai báo biến cùng với kiểu dữ liệu trả về của hàm findElement
		WebElement emailAddressTextbox = driver.findElement(By.id("email"));
		emailAddressTextbox.clear();
		emailAddressTextbox.sendKeys("thaongan@gmail.com");
				
		String facebookTitle = driver.getTitle();
		
		// dùng trực tiếp - dùng 1 lần
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("thaongan@gmail.com");
		
		// Các hàm tương tác với Element sẽ thông qua class WebElement (biến nào đó)
		
		// xóa dữ liệu trong 1 field dạng editable (có thể nhập)
		// Textbox/ Area/ Edittable Dropdown
		element.clear();
		
		// nhập dữ liệu vào field dạng editable
		element.sendKeys("thaongan@gmail.com");
		element.sendKeys(Keys.ENTER);
		
		// cho click vào những element: button/ links/ checkbox/ radio/ image/ ...
		element.click();
		
		// trả về giá trị nằm trong cái attribute của element
		element.getAttribute("placeholder"); // trả về -> Email address or phone number
		
		driver.findElement(By.id("firstname")).getAttribute("value");
		
		// trả về thuộc tính Css của element
		// font/ size/ color
		element.getCssValue(facebookTitle);
		
		// trả về màu nền của element
		element.getCssValue("backgroud-color");
		
		// trả về font size của element
		element.getCssValue("font-size");
		
		// Test GUI: point/ rectangle/ size (visualize testing)
		element.getLocation();
		element.getRect();
		element.getSize();
		
		// chụp hình và attach vào HTML Report
		element.getScreenshotAs(OutputType.FILE);
		
		// trả về thẻ HTML của element
		element.getTagName();
		
		// trả về text của 1 element (Link/ Header/ Message lỗi/ Message success )
		element.getText();
		
		// trả về giá trị đúng or sai của 1 element có hiển thị hoặc ko
		element.isDisplayed();
		// hiển thị: true
		// ko hiển thị: false
		
		// trả về giá trị đúng or sai của 1 element có thao tác được hay ko
		// có bị disable ko
		element.isEnabled();
		// bị disable: false
		// enable: true
		
		// trả về giá trị đúng or sai của 1 element đã được chọn rồi hay chưa
		// checkbox/ radio
		// dropdown: có 1 thư viện riêng để xử lí (select)
		element.isSelected();
		// được chọn: true
		// chưa chọn: false
		
		// chỉ làm việc được với form (đăng kí/ login/ search/ ..)
		// Submit = ENTER ở 1 field nào đó
		// submit vào 1 field nào đó trong form
		element.submit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
}
