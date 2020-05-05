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
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC013_Zalando {

	public static void main(String[] args) throws InterruptedException {
	// TODO Auto-generated method stub

	ChromeOptions options = new ChromeOptions();
	options.addArguments("--disable-notifications");
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
	ChromeDriver driver = new ChromeDriver(options);
	WebDriverWait wait = new WebDriverWait(driver, 30);
	Actions builder = new Actions(driver);
	
	//1 Go to https://www.zalando.com/
	driver.manage().window().maximize();
	driver.get("https://www.zalando.com/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	//2 Get the Alert text and print it
	System.out.println("The Alert text is: "+driver.switchTo().alert().getText());
	
	//3 Close the Alert box and click on Zalando.uk
	driver.switchTo().alert().accept();
	driver.findElement(By.xpath("//a[text()='Zalando.uk']")).click();
	
	//4 Click Women--> Clothing and click Coat
	driver.findElement(By.xpath("//span[text()='Women']")).click();
	driver.findElement(By.xpath("//span[text()='Clothing']")).click();
	driver.findElement(By.xpath("(//a[text()='Coats'])[3]")).click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@id='uc-btn-accept-banner']"))));
	driver.findElement(By.xpath("//button[@id='uc-btn-accept-banner']")).click();
	
	//5 Choose Material as cotton (100% and Length as thigh-length
	driver.findElement(By.xpath("//span[text()='Material']")).click();
	driver.findElement(By.xpath("//span[text()='cotton (100%)']")).click();
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@aria-label='filter by Length']")).click();
	driver.findElement(By.xpath("//span[text()='thigh-length']")).click();
	driver.findElement(By.xpath("//button[text()='Save']")).click();
	
	//6 Click on Q/S designed by MANTEL - Parka coat
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[text()='Q/S designed by']//following-sibling::div[text()='MANTEL - Parka - navy']")).click();
	
	//7 Check the availability for Color as Olive and Size as 'M'
	driver.findElement(By.xpath("(//img[@alt='olive'])[2]")).click();
	driver.findElement(By.xpath("//button[@id='picker-trigger']")).click();
	driver.findElement(By.xpath("//span[text()='M']")).click();
	
	//8 If the previous preference is not available, check  availability for Color Navy and Size 'M'
	if(driver.findElement(By.xpath("//h2[text()='Out of stock']")).isDisplayed())
	{
	System.out.println("The Olive color is out of stock");
	driver.findElement(By.xpath("(//img[@alt='navy'])[2]")).click();
	driver.findElement(By.xpath("//button[@id='picker-trigger']")).click();
	driver.findElement(By.xpath("//span[text()='M']")).click();
	}

	
	//9 Add to bag only if Standard Delivery is free
	if(driver.findElement(By.xpath("(//span[text()='Standard delivery']//following-sibling::div//child::button)[1]//child::span[@class='AtOZbZ']")).getText()
			.equalsIgnoreCase("Free"))
	driver.findElement(By.xpath("//span[text()='Add to bag']")).click();
	//10 Mouse over on Your Bag and Click on "Go to Bag"
	Thread.sleep(3000);
	driver.findElement(By.xpath("//div[text()='Go to bag']")).click();
	
	//11 Capture the Estimated Deliver Date and print
	System.out.println(driver.findElement(By.xpath("//div[@data-id='delivery-estimation']//child::span")).getText());
	
	//12 Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
	builder.moveToElement(driver.findElementByXPath("//a[text()='Free delivery & returns*']")).perform();
	System.out.println("Tool Tip message: "+driver.findElementByXPath("//a[text()='Free delivery & returns*']//parent::span").getAttribute("title"));
	driver.findElementByXPath("//a[text()='Free delivery & returns*']").click();
	
	
	//13 Click on Start chat in the Start chat and go to the new window
	Thread.sleep(3000);
	driver.findElementByXPath("//span[text()='Start chat']").click();
	Set<String> windows = driver.getWindowHandles();
	List<String> windowList = new ArrayList<String>(windows);
	driver.switchTo().window(windowList.get(1));
	
	//14 Enter you first name and a dummy email and click Start Chat
	driver.findElementById("prechat_customer_name_id").sendKeys("A J");
	driver.findElement(By.id("prechat_customer_email_id")).sendKeys("asa@gmail.com");
	driver.findElement(By.id("prechat_submit")).click();
	
	//15 Type Hi, click Send and print thr reply message and close the chat window.
	Thread.sleep(3000);
	driver.findElementById("liveAgentChatTextArea").sendKeys("Hi");
	driver.findElement(By.xpath("//button[@title='Send']")).click();
	System.out.println(driver.findElement(By.xpath("(//span[@class='messageText'])[4]")).getText());
	driver.quit();
	}

}
