package TestCases;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC003_MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		// 1 Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		//2 Click Hotels
		driver.findElement(By.xpath("//span[text()='Hotels']")).click();
		
		//3 Enter city as Goa, and choose Goa, India
		driver.findElement(By.xpath("//span[@data-cy='hotelCityLabel']")).click();
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']").sendKeys("Goa");
		driver.findElement(By.xpath("//p[text()='Goa, India']")).click();
		
		//4 Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElement(By.id("checkin")).click();
		driver.findElement(By.xpath("(//div[contains(text(),'15')])[2]")).click();
		driver.findElement(By.xpath("(//div[contains(text(),'20')])[2]")).click();
		
		//5 Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElement(By.id("guest")).click();
		driver.findElement(By.xpath("//li[@data-cy='adults-2']")).click();
		driver.findElement(By.xpath("//li[@data-cy='children-1']")).click();
		WebElement ageDropDown = driver.findElement(By.className("ageSelectBox"));
		Select ageList = new Select(ageDropDown);
		ageList.selectByIndex(11);
		driver.findElementByXPath("//button[text()='APPLY']").click();
		
		//6 Click Search button
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
		Thread.sleep(2000);
		if(driver.findElementByXPath("//div[contains(@class, 'mmBackdrop wholeBlack')]").isDisplayed())
		driver.findElementByXPath("//div[contains(@class, 'mmBackdrop wholeBlack')]").click();
		
		//7 Select locality as Baga
		driver.findElement(By.xpath("//label[text()='Baga']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		//8 Select 5 star in Star Category under Select Filters
		driver.findElement(By.xpath("//label[text()='5 Star']")).click();
		
		//9 Click on the first resulting hotel and go to the new window
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@id='hlistpg_hotel_name'])[1]")));
		driver.findElement(By.xpath("(//p[@id='hlistpg_hotel_name'])[1]")).click();
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> windowList = new ArrayList<String>(windows);
		driver.switchTo().window(windowList.get(1));
		
		//10 Print the Hotel Name
		System.out.println("The Holtel Name is: "+driver.findElement(By.xpath("//h1[@id='detpg_hotel_name']")).getText());
		
		//11 Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElement(By.xpath("//span[text()='MORE OPTIONS']")).click();
		driver.findElement(By.xpath("(//span[text()='SELECT'])[1]")).click();
		driver.findElement(By.xpath("//span[@class='close']")).click();
		//12 Click on BOOK THIS NOW
		driver.findElement(By.id("detpg_headerright_book_now")).click();
		
		//13 Print the Total Payable amount
		System.out.println("The total payable amount is: "+driver.findElement(By.id("revpg_total_payable_amt")).getText());
		
		//14 Close the browser
		driver.quit();
	}

}
