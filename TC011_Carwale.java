package TestCases;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC011_Carwale {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//1 Go to https://www.carwale.com/
		driver.manage().window().maximize();
		driver.get("https://www.carwale.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		//2 Click on Used
		driver.findElement(By.xpath("//li[@data-tabs='usedCars']")).click();
		
		//3 Select the City as Chennai
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usedCarsList")));
		driver.findElement(By.id("usedCarsList")).sendKeys("Chennai");
		driver.findElement(By.xpath("//a[@cityname='chennai,tamilnadu']")).click();
		
		//4 Select budget min (8L and max(12L and Click Search
		
		driver.findElement(By.id("minInput")).sendKeys("8",Keys.TAB);
		driver.findElement(By.id("maxInput")).sendKeys("12",Keys.TAB);
		driver.findElement(By.id("btnFindCar")).click();
		
		//5 Select Cars with Photos under Only Show Cars With
		Thread.sleep(3000);
		WebElement photos = driver.findElement(By.xpath("//span[text()='Cars with Photos']"));
		js.executeScript("arguments[0].click()", photos);		
		
		
		//6 Select Manufacturer as "Hyundai" --> Creta
		Thread.sleep(2000);
		WebElement brand = driver.findElementByXPath("//ul[@id='makesList']//span[contains(text(),'Hyundai')]");
		js.executeScript("arguments[0].click()", brand);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Creta']"))));
		WebElement model = 	driver.findElement(By.xpath("//span[text()='Creta']"));
		js.executeScript("arguments[0].click()", model);
		
		//7 Select Fuel Type as Petrol
		WebElement fuel = driver.findElement(By.xpath("(//span[text()='Petrol'])[1]"));
		js.executeScript("arguments[0].click()", fuel);
		
		//8 Select Best Match as "KM: Low to High"
		Select sortBy = new Select(driver.findElement(By.id("sort")));
		sortBy.selectByVisibleText("KM: Low to High");
		
		//9 Validate the Cars are listed with KMs Low to High
		Thread.sleep(2000);
		List<WebElement> vehicleKmselement = driver.findElements(By.xpath("//span[@class='slkms vehicle-data__item']"));
		List<Integer> VehicleKms = new ArrayList<Integer>();
		List<Integer> sortedvehicleKms = new ArrayList<Integer>();
		
		for (WebElement webElement : vehicleKmselement) {
			
			VehicleKms.add(Integer.parseInt(webElement.getText().replaceAll("\\D", "")));
			sortedvehicleKms.add(Integer.parseInt(webElement.getText().replaceAll("\\D", "")));
		}
		Collections.sort(sortedvehicleKms);
		if(VehicleKms.equals(sortedvehicleKms)== true)
		System.out.println("The Vehicles are sorted in ascending order of Kms driven");
		else
			System.out.println("The Vehicles are not sorted in ascending order of Kms driven");
			
		Thread.sleep(2000);
		//10 Add the least KM ran car to Wishlist
		for (int i=0; i<vehicleKmselement.size();i++)
		{
			if(Integer.parseInt(vehicleKmselement.get(i).getText().replaceAll("\\D", "")) == sortedvehicleKms.get(0))
			{
				WebElement leastDriven = driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]");
				js.executeScript("arguments[0].click()", leastDriven);
				break;
			}
			
			
		}
		
		//11 Go to Wishlist and Click on More Details
		driver.findElementByXPath("//li[contains(text(),'Compare')]").click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='More details »']")));
		driver.findElementByXPath("//a[text()='More details »']").click();
		
		Set<String> windows = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windows);
		driver.switchTo().window(winList.get(1));
		
		//12 Print all the details under Overview 
		List<WebElement> specNames = driver.findElements(By.xpath("//div[@id='overview']//li//div[@class='equal-width text-light-grey']"));
		//List<WebElement> specValues = driver.findElements(By.xpath("//div[@id='overview']//li//div[@class='equal-width dark-text']"));
		Map<String, String> specs = new LinkedHashMap<String, String>();
		for (int i = 1; i <= specNames.size(); i++) 
		{
			String feature = driver.findElementByXPath("//div[@id='overview']//li["+i+"]/div[@class='equal-width text-light-grey']").getText().trim();
			String value = driver.findElementByXPath("//div[@id='overview']//li["+i+"]/div[@class='equal-width dark-text']").getText().trim();
			specs.put(feature, value);

		}
		
		for (Map.Entry<String, String> eachEntry : specs.entrySet()) 
		{
			System.out.println(eachEntry.getKey()+": "+eachEntry.getValue());
		}
		//13 Close the browser.
		driver.quit();

	}

}
