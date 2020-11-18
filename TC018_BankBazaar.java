package novJavaCodingChallenge;
//Created by: Ajfar Salim on 11-Nov-2020

/* Write a Selenium Java program for the below scenario:
 * 
 * 1. Launch Chrome Browser
 * 2. Got to https://www.bankbazaar.com/personal-loan.html
 * 3. Choose Salaried as employment type
 * 4. Type company name as 'INFOSYS' and select the second displayed option and click enter
 * 5. Click on the mid of the salary scale and click continue
 * 6. Enter pincode as 600001 and click continue
 * 7. Confirm that the mobile number screen is displayed
 * 8. Enter wrong phone number and print the waning message
 */
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC018_BankBazaar {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_86.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	
		driver.get("https://www.bankbazaar.com/personal-loan.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//span[text()='Salaried']")).click();
		driver.findElement(By.xpath("//input[@class='Select-input']")).sendKeys("Infosys");
		
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//div[@aria-label='INFOSYS LTD']"))).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		builder.dragAndDropBy(driver.findElement(By.xpath("//div[@class='rangeslider__handle']")), 300, 0).build().perform();
		driver.findElementByLinkText("Continue").click();
		driver.findElement(By.xpath("//input[@placeholder='PIN Code']")).sendKeys("600001");
		driver.findElementByLinkText("Continue").click();
		
	
		String titleText = driver.findElement(By.xpath("//span[@id='slideCustomTitle']")).getText();
		
		if(titleText.equalsIgnoreCase("Provide your mobile number"))
			System.out.println("The Mobile number screen is displayed");
		else
			System.out.println("The Mobile number screen is not displayed");
		
		driver.findElement(By.name("mobileNumber")).sendKeys("4545",Keys.TAB);
		System.out.println("The warning message is:"+ driver.findElement(By.xpath("//span[@class='errorMessage']")).getText());

		driver.quit();
	}

}
