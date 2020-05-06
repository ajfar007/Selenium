package TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC014_AirBnB {

	public static void main(String[] args) throws InterruptedException {
				// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions builder = new Actions(driver);

		//1 Go to https://www.airbnb.co.in/
		driver.manage().window().maximize();
		driver.get("https://www.airbnb.co.in/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		

		//cookies
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[@class='optanon-allow-all accept-cookies-button']")));
		driver.findElement(By.xpath("//button[@class='optanon-allow-all accept-cookies-button']")).click();
		
		//2 Type Coorg in location and Select Coorg, Karnataka
		driver.findElement(By.id("bigsearch-query-attached-query")).sendKeys("coorg");
		builder.moveToElement(driver.findElement(By.xpath("//div[text()='Coorg, Karnataka']"))).click().build().perform();
		
		//3 Select the Start Date as June 1st and End Date as June 5th
		driver.findElement(By.xpath("(//table[@class='_cvkwaj']/following::table)[2]/tbody[1]/tr[1]/td[2]/div[1]/div[1]")).click();
		driver.findElement(By.xpath("(//table[@class='_cvkwaj']/following::table)[2]/tbody[1]/tr[1]/td[6]/div[1]/div[1]")).click();
		
		//4 Select guests as 6 adults, 3 child and Click Search
		driver.findElement(By.xpath("//div[text()='Add guests']")).click();
		int i=0;
		while(i<6)
			{
			driver.findElement(By.xpath("(//button[@aria-label='increase value'])[1]")).click();
			i++;
			}
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[2]")).click();
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[2]")).click();
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[2]")).click();
		driver.findElementByXPath("(//div[contains(text(),'guests')])[1]").click();
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//5 Click Cancellation flexibility and enable the filter and Save
		driver.findElement(By.xpath("(//span[text()='Cancellation flexibility'])[1]")).click();
		driver.findElement(By.xpath("//button[@id='filterItem-switch-flexible_cancellation-true']")).click();
		driver.findElement(By.xpath("//button[@id='filter-panel-save-button']")).click();
		
		//6 Select Type of Place as Entire Place and Save
		driver.findElementByXPath("//div[@id='menuItemButton-room_type']").click();
		driver.findElementByXPath("//input[@name='Entire place']//following-sibling::span").click();
		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		
		//7 Set Min price as 3000 and  max price as 5000
		driver.findElementByXPath("//span[@aria-label='Price']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys(Keys.CONTROL, "a");
		driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys(Keys.DELETE);
		driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys("3000");
		driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys(Keys.CONTROL, "a");
		driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys(Keys.DELETE);
		driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys("5000");
		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		
		//8 Click More Filters and set 3 Bedrooms and 3 Bathrooms
		driver.findElementByXPath("(//span[text()='More filters'])[1]").click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[2]")).click();
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[2]")).click();
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[2]")).click();
		
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[3]")).click();
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[3]")).click();
		driver.findElement(By.xpath("(//button[@aria-label='increase value'])[3]")).click();
		
		
		//9 Check the Amenities with Kitchen, Facilities with Free parking on premisses, Property as House and Host Language as English 
		driver.findElement(By.xpath("//input[@name='Kitchen']//following-sibling::span")).click();
		driver.findElementByXPath("//input[@name='Free parking on premises']//following-sibling::span").click();
		driver.findElementByXPath("//input[@name='House']//following-sibling::span").click();
		driver.findElementByXPath("//input[@name='English']//following-sibling::span").click();
		
		//  and click on Stays only when stays available
		Thread.sleep(2000);
		if(driver.findElementByXPath("(//button[contains(text(),'stay')])[2]").getText().equalsIgnoreCase("No places to stay available"))
		System.out.println("No Stays available");
		else
		driver.findElement(By.xpath("//button[contains(text(),'Show')]")).click();
		
		//10 Click Prahari Nivas, the complete house
		driver.findElementByXPath("(//a[@aria-label='Prahari Nivas, the complete house'])[1]").click();
		
		//11 Click on "Show all * amenities"
		Set<String> windows = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windows);
		driver.switchTo().window(windowList.get(1));
		
		Thread.sleep(4000);
		driver.findElementByXPath("(//*[contains(text(),'amenities')])[1]").click();
		//12 Print all the Not included amenities
		List<WebElement> notIncludedAmenities = driver.findElements(By.xpath("//*[text()='Not included']//following::del"));
		//div[text()='Not included']//following::del
		System.out.println("No of missing amenities: "+notIncludedAmenities.size());
		System.out.println("The missing amenities are:");
		for (WebElement webElement : notIncludedAmenities) {
			System.out.println(webElement.getText());
		}
		Thread.sleep(2000);
		driver.findElementByXPath("(//button[@aria-label='Close'])[2]").click();
		//13 Verify the Check-in date, Check-out date and Guests
			System.out.println("The Check in date is: "+driver.findElement(By.xpath("(//*[text()='Check-in'])[1]//following-sibling::div")).getAttribute("value"));
		System.out.println("The Check out date is: "+driver.findElement(By.xpath("(//*[text()='Checkout'])[1]//following-sibling::div")).getAttribute("value"));
		System.out.println("No of guests are:"+driver.findElementByXPath("//div[@class='guest-label']//child::span").getText());
		
		//14 Read all the Sleeping arrangements and Print
		System.out.println("The Sleeping arrangements are:");
		for(int j=0;j<4;j++)
			System.out.println("Bedroom "+(i=1)+":"+driver.findElement(By.xpath("//*[text()='Bedroom "+(i+1)+"']//following-sibling::div")).getText());
			
		
		//15 Close all the browsers
		driver.quit();

	}

}
