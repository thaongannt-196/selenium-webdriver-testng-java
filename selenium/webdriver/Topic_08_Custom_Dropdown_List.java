package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.util.preventers.SecurityProviderLeakPreventer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_List {
	
	// khai báo
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExcutor;
	
	// khai báo và khởi tạo
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("osName");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// khởi tạo driver
		driver = new FirefoxDriver();
		
		// khởi tạo
		jsExcutor = (JavascriptExecutor) driver;
		
		driver.manage().window().setSize(new Dimension(1600, 1200));
		
		// khởi tạo wait
		expliciWait = new WebDriverWait(driver, 30);		
		
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

		
	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		// gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 Class
		
		/* Speed dropdown */
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "7");
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "5");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
		
		/* File dropdown */
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Fast");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slower");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		
		/* Title dropdown */
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu li", "Dr.");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Dr.");
		
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu li", "Mr.");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Mr.");
	}

	@Test
	public void TC_02_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		
		scrollToElement("div.carousel-item");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("button#selectize-input", "button#selectize-input+div>a", "CITY G");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY G");
		
		scrollToElement("div.container");
		sleepInSecond(3);
		
		Select select = new Select(driver.findElement(By.cssSelector("select.province")));
		select.selectByVisibleText("Đà Nẵng");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Đà Nẵng");
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");
	}
	
	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Elliot Fu");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
	}
	
	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
	}
	
	@Test
	public void TC_05_React_Selectable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Algeria");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
	}
	
	@Test
	public void TC_06_React_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterItemInCustomDropdown("input.search", "div.menu span.text", "Albania");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");
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
		// driver.quit();
	}
	
	public void scrollToElement(String cssLocator) {
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
	}
	
	// ko dùng cho bất kì 1 dropdown nào cụ thể
	// dùng cho các dropdown chung/ ko cụ thể của 1 dự án
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		// 1 - click vào 1 phần tử nào đó thuộc dropdown để cho nó xổ ra
		driver.findElement(By.cssSelector(parentLocator)).click();
		
		// 2 - chờ cho tát cả các item trong dropdown được load ra xong
		// lưu ý: ko dùng sleep cứng được
		// phải có 1 hàm wait nào để nó linh động:
		// - nếu như chưa tìm thấy thì sẽ tìm lại trong khoảng time được set
		// - nếu như tìm thấy rồi thì ko cần phải chờ hết khoảng time này
		// bắt được 1 locator để đại diện cho tất cả các item
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		// 3.1 - Nếu item cần chọn đang hiển thị
		// 3.2 - Nếu item cần chọn ko hiển thị thì cần cuộn chuột xuống - scroll down
		// 4 - Ktra text của item - nếu đúng với cái mình cần thì click vào
		// viết code để duyệt qua từng item và ktra theo điều kiện
						
		// lưu trữ tất cả các item lại thì mới duyệt qua được
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		// duyệt qua từng item 
		// vòng lặp foreach
		for (WebElement item : allItems) {
			// dùng bến item để thao tác trong vòng lặp for
			
			// lấy ra text 
			String textItem = item.getText();

			// ktra nếu nó = với text mình mong muốn	
			if (textItem.equals(textExpectedItem)) {
				// nó sẽ nhận vào 1 điều kiện là boolean (true/ false)
				// nếu điều kiện đúng thì mới vào trong hàm if
				// điều kiện mà sai thì bỏ qua
				
				// thì click vào
				item.click();
				
				// khi đã tìm thấy và thỏa mãn điều kiện rồi thì ko cần duyệt tiếp nữa
				break;
			}
		}
	}
	
	public void enterItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);;
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		for (WebElement item : allItems) {
			String textItem = item.getText();	
			if (textItem.equals(textExpectedItem)) {
				item.click();
				break;
			}
		}
	}
	
}
