package TestCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC015_Ajio {

	public static void main(String[] args) throws InterruptedException {
		   // TODO Auto-generated method stub

	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	ChromeDriver driver = new ChromeDriver(options);
	WebDriverWait wait = new WebDriverWait(driver, 30);
	Actions builder = new Actions(driver);
	
   //1 Go to https://www.ajio.com/
	driver.manage().window().maximize();
	driver.get("https://www.ajio.com/shop/sale");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
   //2 Enter Bags in the Search field and Select Bags in Women Handbags
	driver.findElementByXPath("//input[@name='searchVal']").sendKeys("bags");
	builder.moveToElement(driver.findElementByXPath("//span[text()='Bags in ']")).click().build().perform();
	
   //3 Click on five grid and Select SORT BY as "What's New"
	driver.findElementByXPath("//div[@class='five-grid']").click();
	Select sortBy = new Select(driver.findElement(By.xpath("//select")));
	sortBy.selectByValue("newn");
	
   //4 Enter Price Range Min as 2000 and Max as 5000
	driver.findElementByXPath("//span[text()='price']").click();
	driver.findElement(By.xpath("//input[@name='minPrice']")).sendKeys("2000");
	driver.findElement(By.xpath("//input[@name='maxPrice']")).sendKeys("5000");
	driver.findElementByXPath("(//button[@type='submit'])[2]").click();
	
   //5 Click on the product "Puma Ferrari LS Shoulder Bag"
	Thread.sleep(3000);
	driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
	
	Set<String> windows = driver.getWindowHandles();
	List<String> windowList = new ArrayList<String>(windows);
	driver.switchTo().window(windowList.get(1));
	
   //6 Verify the Coupon code for the price above 2690 is applicable for your product, if applicable the get the Coupon Code and Calculate the discount price for the coupon
	Thread.sleep(2000);
	String couponCode = driver.findElementByXPath("//div[@class='promo-title']").getText().replace("Use Code", "");
	System.out.println("Coupon Code is: "+couponCode);
	String itemPrice = driver.findElementByXPath("//div[@class='prod-sp']").getText().replaceAll("Rs. ", "");
	System.out.println("Item Price is: "+itemPrice);
	String discountPrice = driver.findElementByXPath("//div[@class='promo-discounted-price']//child::span").getText();
	int discount = Integer.parseInt(itemPrice.replace(",", "")) - Integer.parseInt(discountPrice.replaceAll("\\D", ""));
	System.out.println("Applicable discount is: "+discount);
	
	if(Integer.parseInt(itemPrice.replace(",", "")) > 2690)
	{	System.out.println("The Coupon code is applicable to this product");

		}
	else
		System.out.println("Coupon code is not applicable to the product");
	

   //7 Check the availability of the product for pincode 560043, print the expected delivery date if it is available
	driver.findElementByXPath("//span[@class='edd-pincode-msg-details edd-pincode-msg-details-pointer edd-pincode-msg-details-text-color']").click();
	driver.findElementByXPath("//input[@name='pincode']").sendKeys("560043");
	driver.findElementByXPath("(//button[@type='submit'])[2]").click();
	if(driver.findElementByXPath("//li[text()='Expected Delivery: ']").isDisplayed())
		System.out.println("Delivery available to 560043");
	System.out.println("Expected Delivery Date is: "+driver.findElementByXPath("(//li[text()='Expected Delivery: '])//span").getText());
	
   //8 Click on Other Informations under Product Details and Print the Customer Care address, phone and email
	driver.findElementByXPath("//div[@class='other-info-toggle']").click();
	System.out.println(driver.findElementByXPath("//span[text()='Customer Care Address']//following-sibling::span[@class='other-info']").getText());
	
   //9 Click on ADD TO BAG and then GO TO BAG
	driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
	Thread.sleep(5000);
	wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='GO TO BAG']")));
	driver.findElementByXPath("//span[text()='GO TO BAG']").click();
	
   //10 Check the Order Total before apply coupon
	Thread.sleep(2000);
	String ordertotal = driver.findElementByXPath("//span[@class='price-value bold-font']").getText().replace("Rs. ", "");

   //11 Enter Coupon Code and Click Apply
	driver.findElementById("couponCodeInput").sendKeys("EPIC");
	driver.findElementByXPath("//button[text()='Apply']").click();
	
   //12 Verify the Coupon Savings amount(round off if it in decimal under Order Summary and the matches the amount calculated in Product details
	String couponSavings = driver.findElementByXPath("//span[@class='amount-savings']").getText();
	System.out.println("Discount applied: "+Math.round(Float.parseFloat(couponSavings.replace("Rs. ", ""))));
	if(discount==Math.round(Float.parseFloat(couponSavings.replace("Rs. ", ""))))
		System.out.println("The coupon savings matches");
	else
		System.out.println("Wrong discount applied");
	
   //13 Click on Delete and Delete the item from Bag
	Thread.sleep(2000);
	driver.findElementByXPath("//div[text()='Delete']").click();
	driver.findElementByXPath("(//div[@class='delete-btn'])[2]").click();
	
   //14 Close all the browsers
	Thread.sleep(2000);
	driver.quit();
	}

}
