package novJavaCodingChallenge;

//Created by: Ajfar Salim


/* Write a selenium program for the below scenario
 * 1. Launch the edge browser 
 * 2. Print the edge browser version
 * 3. Goto https://redbus.in
 * 4. Enter to as 'Bangalore (All Locations)' and shift tab
 * 5. Enter from as 'Chennai (All Locations)'
 * 6. Choose date as first monday of Next month
 * 7. Click search and print total number of displayed buses
 */
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyDownAction;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TC017_RedBus_Booking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.get("https://redbus.in");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		Actions builder = new Actions(driver);
		driver.findElement(By.xpath("//input[@id='dest']")).sendKeys("Bangalore (All Locations)");
		//builder.sendKeys("Bangalore (All Locations)").perform();
		Thread.sleep(2000);
		builder.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).perform();
		driver.findElement(By.xpath("//input[@id='src']")).sendKeys("Chennai (All Locations)");
		Thread.sleep(2000);
		driver.findElement(By.id("onward_cal")).click();
		driver.findElement(By.xpath("//button[text()='>']")).click();
		String a = driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']/table[1]/tbody[1]/tr[3]/td[1]")).getAttribute("class");
		System.out.println(a);
		if(a.equalsIgnoreCase("empty day"))
			{
			System.out.println("Empty Day");
			driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']/table[1]/tbody[1]/tr[4]/td[1]")).click();
			}
		else
		{
			System.out.println("Not Empty Day");
			driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']/table[1]/tbody[1]/tr[3]/td[1]")).click();
		}
		driver.findElement(By.id("search_btn")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(@class, 'close')]"))));
		driver.findElement(By.xpath("//div[contains(@class, 'close')]")).click();
		
		String nBuses = driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
		
		nBuses = nBuses.replaceAll("\\D", "");
		System.out.println("Number of buses: "+nBuses);
		
		driver.close();

	}

}
