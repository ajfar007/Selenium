package TestCases;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC010_SnapDeal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions builder = new Actions(driver);
		
		//	1 Go to https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// 2 Mouse over on Toys, Kids' Fashion & more and click on Toys
		builder.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Toys, Kids')]"))).perform();
		//driver.findElement(By.xpath("//span[contains(text(),'Toys, Kids')]"));
		
		// 3 Click Educational Toys in Toys & Games
		driver.findElement(By.xpath("//span[contains(text(),'Educational Toys')]")).click();
		
		// 4 Click the Customer Rating 4 star and Up 
		driver.findElement(By.xpath("//label[@for='avgRating-4.0']")).click();
		
		// 5 Click the offer as 40-50
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='discount-40%20-%2050']")));
		driver.findElement(By.xpath("//label[@for='discount-40%20-%2050']")).click();
		
		// 6 Check the availability for the pincode
		driver.findElement(By.xpath("//input[@placeholder='Enter your pincode']")).sendKeys("600130");
		driver.findElement(By.xpath("//button[@class='pincode-check']")).click();
		
		// 7 Click the Quick View of the first product 
		Thread.sleep(2000);
		builder.moveToElement(driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"))).perform();
		driver.findElement(By.xpath("(//div[@class='center quick-view-bar  btn btn-theme-secondary  '])[1]")).click();
		
		// 8 Click on View Details
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class=' btn btn-theme-secondary prodDetailBtn']")));
		driver.findElement(By.xpath("//a[@class=' btn btn-theme-secondary prodDetailBtn']")).click();
		
		// 9 Capture the Price of the Product and Delivery Charge
		String itemprice = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		String delCharge= driver.findElement(By.xpath("(//span[@class='availCharges'])[2]")).getText();
		driver.findElement(By.xpath("//span[text()='add to cart']")).click();
		delCharge = delCharge.replaceAll("\\D", "");
		
		// 10 Validate the You Pay amount matches the sum of (price+deliver charge
		String toyTotal = driver.findElement(By.xpath("(//span[@class='price'])[2]")).getText();
		toyTotal = toyTotal.replaceAll("\\D", "");

		if((Integer.parseInt(itemprice) + Integer.parseInt(delCharge)) == Integer.parseInt(toyTotal))
		{
			System.out.println("The Total Amount matches sum of Item Price and Delivery Charge");
		}
		else
			System.out.println("Wrong Amount charged");
		
		// 11 Search for Sanitizer
		driver.findElement(By.id("inputValEnter")).sendKeys("Sanitizer",Keys.ENTER);
		//sdriver.findElement(By.xpath("(//span[text()='sanitizer'])[1]")).click();
		
		// 12 Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		driver.findElement(By.xpath("(//p[contains(text(),'BioAyurveda Neem Power  Hand Sanitizer')])[1]")).click();
		Set<String> windows = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windows);
		driver.switchTo().window(windowList.get(1));
		
		// 13 Capture the Price and Delivery Charge
		String sanitizerPrice = driver.findElement(By.xpath("//span[@itemprop='price']")).getText();
		System.out.println("Sanitizer Price is : "+sanitizerPrice);
		String sanitizerDelCharge = driver.findElement(By.xpath("(//span[@class='availCharges'])[2]")).getText();
		sanitizerDelCharge = sanitizerDelCharge.replaceAll("\\D","");
		System.out.println("Sanitizer Delivery Charge is: "+sanitizerDelCharge);
		
		// 14 Click on Add to Cart
		driver.findElement(By.xpath("(//div[@title='ADD TO CART'])[1]")).click();
		
		// 15 Click on Cart
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='cartTextSpan']")));
		driver.findElement(By.xpath("//span[@class='cartTextSpan']")).click();
		
		// 16 Validate the Proceed to Pay matches the total amount of both the products
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[contains(@value, 'PROCEED TO PAY')]"))));
		String CartTotal = driver.findElement(By.xpath("//input[contains(@value, 'PROCEED TO PAY')]")).getAttribute("value");
		System.out.println(CartTotal);
		CartTotal = CartTotal.replaceAll("\\D", "");
		System.out.println("Cart Total is: "+CartTotal);
		
		if(Integer.parseInt(sanitizerPrice)+Integer.parseInt(sanitizerDelCharge)+Integer.parseInt(toyTotal) == Integer.parseInt(CartTotal))
			System.out.println("The Total amount matches the sum of item prices and Delivery Charges");
		else
			System.out.println("The Pay amount is wrong");
		
		// 17 Close all the windows
		driver.quit();

	}

}
