package TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC002_Nykaa {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		//1 Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2 Mouseover on Brands and Mouseover on Popular
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='brandHeadingbox '])[1]")));
		driver.findElement(By.xpath("(//a[@class='brandHeadingbox '])[1]")).click();
	
		//3 Click L'Oreal Paris
		driver.findElement(By.xpath("(//li[@class='brand-logo menu-links'])[5]")).click();
		
		//4 Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windows = driver.getWindowHandles();
		List<String> winList =new ArrayList<String>(windows);
		driver.switchTo().window(winList.get(1));
		System.out.println("The second window title is: "+driver.getTitle());
		
		//5 Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[@title='POPULARITY']")).click();
		driver.findElement(By.xpath("(//div[@class='control__indicator radio'])[4]")).click();
		Thread.sleep(5000);
		//6 Click Category and click Shampoo
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//span[contains(text(),'Shampoo (')])[1]")).click();
		
		//7 check whether the Filter is applied with Shampoo
		if((driver.findElement(By.xpath("//ul[@class='pull-left applied-filter-lists']/child::li")).getText()).contains("Shampoo"))
			System.out.println("Shampoo filter applied successfully");
		else
			System.out.println("Error in applying filter");
		
		//8 Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//span[contains(text(),'Paris Colour Protect Shampoo')]")).click();
		
		//9 GO to the new window and select size as 175ml
		windows = driver.getWindowHandles();
		winList =new ArrayList<String>(windows);
		driver.switchTo().window(winList.get(2));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='size-pallets'])[2]")));
		driver.findElement(By.xpath("(//span[@class='size-pallets'])[2]")).click();
		
		//10 Print the MRP of the product
		String price = driver.findElement(By.xpath("(//span[@class='post-card__content-price-offer'])[1]")).getText();
		System.out.println("The Price of the Shampoo selected is: "+price);
			
		//11 Click on ADD to BAG
		driver.findElement(By.xpath("(//button[text()='ADD TO BAG'])[1]")).click();
		
		//12 Go to Shopping Bag 
		driver.findElement(By.xpath("//div[@class='AddBagIcon']")).click();
		
		//13 Print the Grand Total amount
		System.out.println("The Grand Total is" +driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText());
		
		//14 Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		
		//15 Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
	
		//16 Print the warning message (delay in shipment)
		System.out.println("Warning Message: "+driver.findElement(By.xpath("//div[@class='message']")).getText());
		
		//17 Close all windows
		driver.quit();
	}

}
