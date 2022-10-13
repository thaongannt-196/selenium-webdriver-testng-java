package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Exercise {

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
	public void TC_01_Is_Diplayed() {
		// có thể nhìn thấy 
		// có kích thước cụ thể (rộng x cao)
		// phạm vi áp dụng: tất cả các loại element (textbox/ textarea/ button/ radio/ link/ ...)
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
	
		// Email Textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
		
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing Textbox");
			System.out.println("Email textbox is displayed");
		} 	else {
			System.out.println("Email textbox is not displayed");
		}
		
		// Age Under 18 Radio Button
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age Under 18 radio is displayed");
		} else {
			System.out.println("Age Under 18 radio is not displayed");
		}
		
		// Education TextArea
		WebElement educationTextarea = driver.findElement(By.cssSelector("textarea#edu"));
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing TextArea");
			System.out.println("Education TextArea is displayed");
		} else {
			System.out.println("Education TextArea is not displayed");
		}
		
		// Image 5 (Undisplayed)
		WebElement image5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (image5.isDisplayed()) {
			System.out.println("Image 5 is displayed");
		} else {
			System.out.println("Image 5 is not displayed");
		}
		
		// nếu như element hiển thị thì hàm isDisplayed trả về giá trị là true
		// nếu như element ko hiển thị thì hàm isDisplayed trả về giá trị là false
	}

	@Test
	public void TC_02_Enabled() {
		// Có thể tương tác được = enabled -> true
		// Ko tương tác được = disabled -> false
		// phạm vi áp dụng: tất cả các loại element (textbox/ textarea/ button/ radio/ link/ ...)
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Email Textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email Textbox is enabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}
		
		// Password Textbox
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
		if (passwordTextbox.isEnabled()) {
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is disabled");
		}
		
		// Age Radio Button (under 18)
		WebElement ageUnder18 = driver.findElement(By.cssSelector("input#under_18"));
		if (ageUnder18.isEnabled()) {
			System.out.println("Age Under 18 is enabled");
		} else {
			System.out.println("Age Under 18 is disabled");
		}
		
		// Age Radio Button 
		WebElement ageRadioButton = driver.findElement(By.cssSelector("input#radio-disabled"));
		if (ageRadioButton.isEnabled()) {
			System.out.println("Radio Button is enabled");
		} else {
			System.out.println("Radio Button is disabled");
		}
		
		// Education Textarea
		WebElement EduTextarea = driver.findElement(By.cssSelector("textarea#edu"));
		if (EduTextarea.isEnabled()) {
			System.out.println("Education Textarea is enabled");
		} else {
			System.out.println("Education Textarea is disabled");
		}
		
		// Biography Textarea
		WebElement BioTextarea = driver.findElement(By.cssSelector("textarea#bio"));
		if (BioTextarea.isEnabled()) {
			System.out.println("Biography Textarea is enabled");
		} else {
			System.out.println("Biography Textarea is disabled");
		}
		
		// Job Role 1 
		WebElement job1Dropdown = driver.findElement(By.cssSelector("select#job1"));
		if (job1Dropdown.isEnabled()) {
			System.out.println("Job Role 1 is enabled");
		} else {
			System.out.println("Job Role 1 is disabled");
		}
		
		// Job Role 1 
		WebElement job2Dropdown = driver.findElement(By.cssSelector("select#job2"));
		if (job2Dropdown.isEnabled()) {
			System.out.println("Job Role 2 is enabled");
		} else {
			System.out.println("Job Role 2 is disabled");
		}
				
		// Job Role 1 
		WebElement job3Dropdown = driver.findElement(By.cssSelector("select#job3"));
		if (job3Dropdown.isEnabled()) {
			System.out.println("Job Role 3 is enabled");
		} else {
			System.out.println("Job Role 3 is disabled");
		}
		
		// Interests (Development) checkbox
		WebElement developmentCheckbox = driver.findElement(By.cssSelector("input#development"));
		if (developmentCheckbox.isEnabled()) {
			System.out.println("Development checkbox is enabled");
		} else {
			System.out.println("Development checkbox is disabled");
		}
		
		// Interests (Checkbox is disabled)
		WebElement interestCheckbox = driver.findElement(By.cssSelector("input#check-disbaled"));
		if (interestCheckbox.isEnabled()) {
			System.out.println("Interest checkbox is enabled");
		} else {
			System.out.println("Interest checkbox is disabled");
		}
		
		// Slider 1
		WebElement slider1 = driver.findElement(By.cssSelector("input#slider-1"));
		if (slider1.isEnabled()) {
			System.out.println("Slider 1 is enabled");
		} else {
			System.out.println("Slider 1 is disabled");
		}
		
		// Slider 2
		WebElement slider2 = driver.findElement(By.cssSelector("input#slider-2"));
		if (slider2.isEnabled()) {
				System.out.println("Slider 2 is enabled");
		} else {
				System.out.println("Slider 2 is disabled");
		}
		
	}

	@Test
	public void TC_03_Selected() {
		// Đã được chọn hay chưa =  selected -> true
		// chưa chọn = deselected -> false
		// phạm vi áp dụng: Radio Button/ Checkbox/ Dropdown (default)
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		ageUnder18Radio.click();
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age Under 18 is selected");
		} else {
			System.out.println("Age Under 18 is deselected");
		}
		
		// Java checkbox
		WebElement javaCheckbox = driver.findElement(By.cssSelector("input#java"));
		javaCheckbox.click();
		if (javaCheckbox.isSelected()) {
			System.out.println("Java Checkbox is selected");
		} else {
			System.out.println("Java Checkbox is deselected");
		}
	}

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		// Email/ Username Textbox
		driver.findElement(By.cssSelector("input#email")).sendKeys("thaongan@gmail.com");
		sleepInSecond(3);
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
		
		// check lowercase (viết thường)
		passwordTextbox.sendKeys("aaa");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// check uppercase (viết hoa)
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AAA");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// check number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// check special char
		passwordTextbox.clear();
		passwordTextbox.sendKeys(";#$%^");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// check 8 char
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345ghjj@@@");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		// check full
		passwordTextbox.clear();
		passwordTextbox.sendKeys("ABac12##$");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#new_password.av-password.success-check")).isDisplayed());
				
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
		driver.quit();
	}
	
	
}
