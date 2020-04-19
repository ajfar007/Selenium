package TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC004_HPStore {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//1 Go to https://store.hp.com/in-en/
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://store.hp.com/in-en/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		//2 Mouse over on Laptops menu and click on Pavilion
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("(//span[text()='Laptops'])[1]"))).perform();
		driver.findElement(By.xpath("(//span[text()='Pavilion'])[1]")).click();
		
		//Accept Cookies
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Accept Cookies']")));
		driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();
			
		
		//3 Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElement(By.xpath("(//span[text()='Processor'])[2]")).click();
		driver.findElement(By.xpath("//span[text()='Intel Core i7']")).click();
		
		//4 Hard Drive Capacity -->More than 1TB
		Thread.sleep(3000);
		
					
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("\"//span[text()='More than 1 TB']")));
		driver.findElement(By.xpath("//span[text()='More than 1 TB']")).click();
		//5 Select Sort By: Price: Low to High
		WebElement sortBy = driver.findElement(By.xpath("(//Select[@id='sorter'])[1]"));
		Select sortByOptions = new Select(sortBy);
		sortByOptions.selectByIndex(1);
		
		//6 Print the First resulting Product Name and Price
		System.out.println("The first product listed is: "+driver.findElement(By.xpath("(//a[@class='product-item-link'])[1]")).getText());
		System.out.println("The Price of the product is: "+driver.findElement(By.xpath("(//span[@class='price'])[2]")).getText());
		
		Thread.sleep(5000);
		//7 Click on Add to Cart
		driver.findElement(By.xpath("(//span[text()='Add To Cart'])[1]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inside_closeButton fonticon icon-hclose']")));
		driver.findElement(By.xpath("//div[@class='inside_closeButton fonticon icon-hclose']")).click();
		
		//8 Click on Shopping Cart icon --> Click on View and Edit Cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='My Cart'])[1]")));
		driver.findElement(By.xpath("(//a[@title='Shopping Cart'])[1]")).click();
		
		driver.findElement(By.xpath("//span[text()='View and edit cart']")).click();
		
		//9 Check the Shipping Option --> Check availability at Pincode
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='pincode']")));
		driver.findElement(By.xpath("//input[@name='pincode']")).sendKeys("600130");
		driver.findElement(By.xpath("//button[text()='check']")).click();
		if((driver.findElement(By.xpath("//span[@class='available']")).getText()).contentEquals("In stock"))
		System.out.println("The Product is can be shipped to the selected PINCODE");
		
		//10 Verify the order Total against the product price
		String orderTotal = driver.findElement(By.xpath("//span[@class='price-wrapper']//child::span")).getText();
		String originalPrice = driver.findElement(By.xpath("//span[@class='original-price']//child::span")).getText();
		if(orderTotal.equalsIgnoreCase(originalPrice))
		{	System.out.println("The price is same and no shipping charges applied");
		//11 Proceed to Checkout if Order Total and Product Price matches
				driver.findElement(By.xpath("(//span[text()='Proceed to Checkout'])[1]")).click();
		}
		else
			System.out.println("Shipping charges applicable");
		
		//12 Click on Place Order
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='Place Order'])[3]")));
		driver.findElement(By.xpath("(//span[text()='Place Order'])[3]")).click();
		
		//13 Capture the Error message and Print
		System.out.println("Error Message: "+driver.findElement(By.xpath("//div[@class='mage-error']")).getText());
		
		//14 Close Browser
		driver.quit();
	}

}
