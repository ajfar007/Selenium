package TestCases;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TC006_BigBasket {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		//1 Go to https://www.bigbasket.com/
		driver.get("https://www.bigbasket.com/");
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@value='Change Location']")).click();
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		
		//2) mouse over on  Shop by Category 
		Actions builder = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='dropdown full-wid hvr-drop']")));
		builder.moveToElement(driver.findElement(By.xpath("//li[@class='dropdown full-wid hvr-drop']"))).perform();
		
		//3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Foodgrains, Oil & Masala'])[2]")));
		builder.moveToElement(driver.findElement(By.xpath("(//a[text()='Foodgrains, Oil & Masala'])[2]"))).perform();
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Foodgrains, Oil & Masala'])[2]")));
		builder.moveToElement(driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]")).build().perform();
		System.out.println("Foodgrains, Oil & Masala clicked");
		
		//4) Click on Boiled & Steam Rice
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@class='ng-scope']//a[text()='Rice & Rice Products'])[2]")));
		builder.moveToElement(driver.findElementByXPath("(//li[@class='ng-scope']//a[text()='Rice & Rice Products'])[2]")).click().build().perform();
	
				
		Thread.sleep(5000);
		//5) Choose the Brand as bb Royal
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='bb Royal'])[5]")));
		driver.findElement(By.xpath("(//span[text()='bb Royal'])[5]")).click();
		
		Thread.sleep(3000);
		//6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div[@class='btn-group btn-input clearfix ng-scope'])[1]"))));
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div[@class='btn-group btn-input clearfix ng-scope'])[1]").click(); 
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[contains(text(),'5 kg')])[1]").click();
		
		//7) print the price of Rice
		String ricePrice = driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[@class='discnt-price']/span)[1]").getText();
		int intRicePrice = Integer.parseInt(ricePrice);
		System.out.println("The price of the item is: "+ricePrice);
		
		
		//8) Click Add button
		driver.findElementByXPath("((//a[text()='Ponni Boiled Rice - Super Premium'])[1]/following::button[@class='btn btn-add col-xs-9'])[1]").click();
		
		
		//9) Verify the success message displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-title']")));
		String addToCartMsg= driver.findElementByXPath("//div[@class='toast-title']").getText();
		System.out.println(addToCartMsg);
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		
		//10) Type Dal in Search field and enter
		driver.findElementById("input").sendKeys("Dal", Keys.ENTER);
		
		//12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']")));
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[@class='btn btn-default dropdown-toggle form-control'])[1]").click();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[text()='2 kg'])[1]").click();
		WebElement toorDalQty = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[text()='Qty']/following-sibling::input)[1]");
		toorDalQty.clear();
		toorDalQty.sendKeys("2");
		
		Thread.sleep(3000);
		
		
		//13) Print the price of Dal
		String dalPrice = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[@class='discnt-price']/span)[1]").getText(); 
		int intDalPrice = Integer.parseInt(dalPrice.replaceAll("[^0-9]", ""));
		System.out.println("The price of toor dal is Rs."+intDalPrice);
		
		//14) Click Add button
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[contains(text(),'Add')])[1]").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-title']")));
		String dalSucssMsg= driver.findElementByXPath("//div[@class='toast-title']").getText();
		System.out.println(dalSucssMsg);
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		
		//15) Mouse hover on My Basket 
		WebElement eleBasket = driver.findElementByXPath("//span[@title='Your Basket']");
		builder.moveToElement(eleBasket).perform();
		Thread.sleep(3000);
		
		//16) Validate the Sub Total displayed for the selected items
		String subTotal = driver.findElementByXPath("//div[@class=\"row sub-cost ng-scope\"]/p/span/span").getText();
		int Total = intRicePrice + (intDalPrice * 2);
		System.out.println("Sub total is " + subTotal);
		System.out.println("Total is " + Total);
		int intSubTotal = Integer.parseInt(subTotal.replaceAll("[^1-9]", ""));
		Assert.assertEquals(Total, intSubTotal);
		System.out.println("Total Mached");
		
		//17) Reduce the Quantity of Dal as 1 
		driver.findElementByXPath("(//div[@class=\"btn-counter row\"]/button)[3]").click();
		
		//18) Validate the Sub Total for the current items
		Thread.sleep(2000);
		subTotal = driver.findElementByXPath("//div[@class=\"row sub-cost ng-scope\"]/p/span/span").getText();
		Total = intRicePrice + intDalPrice;
		System.out.println("Sub total is " + subTotal);
		System.out.println("Order Total is " + Total);
		intSubTotal = Integer.parseInt(subTotal.replaceAll("[^1-9]", ""));
		Assert.assertEquals(Total, intSubTotal);
		System.out.println("Order Total Matched");
		
		//19) Close the Browser
		Thread.sleep(5000);
		driver.quit();
	}

}
