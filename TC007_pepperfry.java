package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC007_pepperfry {

	public static void main(String[] args) throws InterruptedException, WebDriverException, IOException {
	
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	ChromeDriver driver = new ChromeDriver(options);
	
	//1 Go to https://www.pepperfry.com/
	driver.get("https://www.pepperfry.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	//2 Mouseover on Furniture and click Office Chairs under Chairs
	Actions builder = new Actions(driver);
	builder.moveToElement(driver.findElement(By.xpath("(//a[text()='Furniture'])[1]"))).perform();
	wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("(//a[text()='Office Chairs'])[1]"))));
	builder.moveToElement(driver.findElement(By.xpath("(//a[text()='Office Chairs'])[1]"))).click().perform();
	Thread.sleep(2000);

	//3 click Executive Chairs
	driver.findElement(By.xpath("//h5[text()='Executive Chairs']")).click();

	//4 Change the minimum Height as 50 in under Dimensions
	driver.findElement(By.xpath("(//input[@class='clipFilterDimensionHeightValue'])[1]")).clear();
	driver.findElement(By.xpath("(//input[@class='clipFilterDimensionHeightValue'])[1]")).sendKeys("50", Keys.ENTER);
	
	//5 Add "Poise Executive Chair in Black Colour" chair to Wishlist
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-productname='Poise Executive Chair in Black Colour']")));
	driver.findElement(By.xpath("//a[@data-productname='Poise Executive Chair in Black Colour']")).click();
	
	
	//handle sign in popup
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")));
	driver.findElement(By.xpath("//div[@id='regPopUp']//a[@class='popup-close']")).click();

	
	//6 Mouseover on Homeware and Click Pressure Cookers under Cookware
	builder.moveToElement(driver.findElement(By.xpath("(//a[text()='Homeware'])[1]"))).perform();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Pressure Cookers']")));
	builder.moveToElement(driver.findElement(By.xpath("//a[text()='Pressure Cookers']"))).click().perform();
	
	//7 Select Prestige as Brand
	driver.findElement(By.xpath("//label[text()='Prestige']")).click();
	
	//8 Select Capacity as 1-3 Ltr
	Thread.sleep(2000);
	driver.findElement(By.xpath("//label[@for='capacity_db1_Ltr_-_3_Ltr']")).click();

	//9 Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")));
	driver.findElement(By.xpath("//a[@data-productname='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']")).click();
	
	
	//10 Verify the number of items in Wishlist
	Thread.sleep(2000);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(("//span[@class='count_alert'])[2]"))));
	System.out.println("Number of items in wishlist: "+driver.findElement(By.xpath("(//span[@class='count_alert'])[2]")).getText());
	
	//11 Navigate to Wishlist
	driver.findElement(By.xpath("//a[@class='pf-icon pf-icon-heart header_tab wistlist_img pending_alert']")).click();

	//12 Move Pressure Cooker only to Cart from Wishlist
	driver.findElement(By.xpath("//a[contains(text(),'Nakshatra Cute Metallic Red')]//ancestor::div[@class='item_details']//a[text()='Add to Cart']")).click();
	
	//13 Check for the availability for Pincode 600128
	driver.findElement(By.className("srvc_pin_text")).sendKeys("600128");
	driver.findElement(By.className("check_available")).click();
	
	//14 Click Proceed to Pay Securely
	Thread.sleep(2000);
	driver.findElement(By.xpath("//a[text()='Proceed to pay securely ']")).click();
	
	//15 Click Place order
	driver.findElement(By.xpath("(//a[text()='PLACE ORDER'])[1]")).click();
	
	//16 Capture the screenshot of the item under Order Item
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='nCheckout__accrodian-header-right'])[1]")));
	driver.findElement(By.xpath("(//div[@class='nCheckout__accrodian-header-right'])[1]")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'nakshatra')]")));
	WebElement cartItem = driver.findElement(By.xpath("//img[contains(@src,'nakshatra')]"));
	File src = cartItem.getScreenshotAs(OutputType.FILE);
	File dst = new File("./snaps/TC007_pepperfry_img.jpg");
	FileUtils.copyFile(src, dst);
	
	//17 Close the browser
	driver.quit();

	}

}
