import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeHrm {

	public static void main(String[] args) throws InterruptedException {
		String[] firstname = { "Suip", "Sam", "Samual" };
		String[] lastname = { "hazra", "John", "Angel" };
		String[] employeeid = { "15000", "15001", "15002" };
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\SUDEEP\\OneDrive\\Documents\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		common(driver);
		String[] username = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--p'][1]")).getText().split(":");
		String username1 = username[1].trim();
		String[] password = driver.findElement(By.xpath("//*[@class='oxd-text oxd-text--p'][2]")).getText().split(":");
		String password1 = password[1].trim();
		driver.findElement(By.xpath("//*[@placeholder='Username']")).sendKeys(username1);
		driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys(password1);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		// Logged in to the Dashboard
		driver.findElement(By.linkText("PIM")).click();
		// Navigated to PIM page
		driver.findElement(By.cssSelector("[class='oxd-button oxd-button--medium oxd-button--secondary']")).click();
		// Creating 1st Employee
		driver.findElement(By.cssSelector("[name='firstName']")).sendKeys(firstname[0]);
		driver.findElement(By.cssSelector("[name='lastName']")).sendKeys(lastname[0]);
		driver.findElement(By.xpath("((//*[@class='oxd-input oxd-input--active'])[2])")).sendKeys(employeeid[0]);
		driver.findElement(By.cssSelector("[type='lastname']")).click();
		Thread.sleep(5000);
		String firstuser = driver.findElement(By.cssSelector("[class='orangehrm-edit-employee-name']")).getText();
		if (firstuser.contains(firstname[0])) {
			driver.findElement(By.linkText("Add Employee")).click();
		} else {
			System.out.println("Employee did not Added");

		}
		// Creating 2nd Employee
		driver.findElement(By.cssSelector("[name='firstName']")).sendKeys(firstname[1]);
		driver.findElement(By.cssSelector("[name='lastName']")).sendKeys(lastname[1]);
		driver.findElement(By.xpath("((//*[@class='oxd-input oxd-input--active'])[2])")).sendKeys(employeeid[1]);
		driver.findElement(By.cssSelector("[type='lastname']")).click();
		Thread.sleep(5000);
		String seconduser = driver.findElement(By.cssSelector("[class='orangehrm-edit-employee-name']")).getText();
		if (seconduser.contains(firstname[1])) {
			driver.findElement(By.linkText("Add Employee")).click();
		} else {
			System.out.println("Employee did not Added");

		}
		// Creating 3rd Employee
		driver.findElement(By.cssSelector("[name='firstName']")).sendKeys(firstname[2]);
		driver.findElement(By.cssSelector("[name='lastName']")).sendKeys(lastname[2]);
		driver.findElement(By.xpath("((//*[@class='oxd-input oxd-input--active'])[2])")).sendKeys(employeeid[2]);
		driver.findElement(By.cssSelector("[type='lastname']")).click();
		Thread.sleep(5000);
		String thirduser = driver.findElement(By.cssSelector("[class='orangehrm-edit-employee-name']")).getText();
		if (thirduser.contains(firstname[2])) {
			driver.findElement(By.linkText("Add Employee")).click();
		} else {
			System.out.println("Employee did not Added");

		}

		// Navigate to Employee list
		driver.findElement(By.cssSelector("[class='oxd-topbar-body-nav-tab --visited']")).click();
		//Scroll down to the Page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		//Pulling all the Employee names as a list
		List<WebElement> Ids = driver.findElements(By.xpath("//div[@role='row']"));
		//Putting condition to compare pulled employee list & added list
		for (int i = 0; i < Ids.size(); i++) {
			String Ids1 = Ids.get(i).getText();
			Thread.sleep(3000);
			List<String> listemployee = Arrays.asList(employeeid);
			if (listemployee.contains(Ids1)) {
				System.out.println("Name Verified");
			}
		}
		//Navigating to the Dashboard
		driver.findElement(By.xpath("(//*[@class='oxd-main-menu-item-wrapper'])[8]")).click();
		driver.findElement(By.xpath("//*[@class='oxd-userdropdown-tab']")).click();
		driver.findElement(By.linkText("Logout")).click();
		//Logging out from the account
		String loginpage = driver.findElement(By.xpath("[class='oxd-text oxd-text--h5 orangehrm-login-title']")).getText();
		if(loginpage.contains("Login")) {
			System.out.print("Page Successfully logged out");
		}
	}

	public static void common(WebDriver driver) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

}
