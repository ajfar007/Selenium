package TestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC012_Shiksha {

	public static void main(String[] args) throws InterruptedException {
// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		
		//	1 Go to https://studyabroad.shiksha.com/
		driver.manage().window().maximize();
		driver.get("https://studyabroad.shiksha.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions builder = new Actions(driver);
		
		//	2 Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
		builder.moveToElement(driver.findElement(By.xpath("(//label[text()='Colleges '])[1]"))).perform();
		driver.findElement(By.xpath("//a[text()='MS in Computer Science &Engg']")).click();
		
		//	3 Select GRE under Exam Accepted and Score 300 & Below 
		driver.findElement(By.xpath("//p[text()='GRE']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("examsScore[]")));
		Select score = new Select(driver.findElement(By.name("examsScore[]")));
		score.selectByVisibleText("300 & below");
		
		//	4 Max 10 Lakhs under 1st year Total fees, USA under countries
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Max 10 Lakhs']")));
		driver.findElement(By.xpath("//p[text()='Max 10 Lakhs']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='USA']//parent::p//preceding-sibling::span")).click();
		
		//	5 Select Sort By: Low to high 1st year total fees
		Thread.sleep(1000);
		Select sort = new Select(driver.findElement(By.id("categorySorter")));
		sort.selectByVisibleText("Low to high 1st year total fees");
		
		//	6 Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation facilities
		Thread.sleep(2000);
		List<WebElement> colleglist = driver.findElements(By.xpath("//p[text()='Public university']"));
		for(int i=0; i<colleglist.size(); i++) {
			String univStatus = driver.findElement(By.xpath("(//p[text()='Public university'])["+(i+1)+"]/child::span")).getAttribute("class");
			String scholarshipStatus = driver.findElement(By.xpath("(//p[text()='Scholarship'])["+(i+1)+"]/child::span")).getAttribute("class");
			String accommodationStatus = driver.findElement(By.xpath("(//p[text()='Accommodation'])["+(i+1)+"]/child::span")).getAttribute("class");

			if(univStatus.equalsIgnoreCase("tick-mark") && scholarshipStatus.equalsIgnoreCase("tick-mark") && accommodationStatus.equalsIgnoreCase("tick-mark"))
			{
				driver.findElement(By.xpath("(//p[text()='Add to compare'])["+(i+1)+"]//preceding-sibling::span")).click();
				break;
			}	}
			
		//	7 Select the first college under Compare with similar colleges 
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//a[@class='add-tag-title'])[1]"))));
		driver.findElement(By.xpath("(//a[@class='add-tag-title'])[1]")).click();
		
		//	8 Click on Compare College>
		driver.findElement(By.xpath("//strong[text()='Compare Colleges >']")).click();
		
		//	9 Select When to Study as 2021
		driver.findElement(By.xpath("//strong[text()='2021']")).click();
		
		//	10 Select Preferred Countries as USA
		driver.findElement(By.xpath("//div[@class='sp-frm selectCountryField signup-fld invalid ']")).click();
		driver.findElement(By.xpath("(//label[text()[normalize-space()='USA']])[2]")).click();
		
		//	11 Select Level of Study as Masters
		driver.findElement(By.xpath("//strong[text()='Masters']")).click();
		
		//	12 Select Preferred Course as MS
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='sp-frm selectField signup-fld invalid']")).click();
		driver.findElement(By.xpath("//li[text()='MS']")).click();
		
		//	13 Select Specialization as "Computer Science & Engineering"
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='All specializations']")).click();
		driver.findElement(By.xpath("//li[text()='Computer Science & Engineering']")).click();
		
		//	14 Click on Sign Up
		driver.findElement(By.id("signup")).click();
		
		//	15 Print all the warning messages displayed on the screen for missed mandatory fields
		List<WebElement> missedFields = driver.findElements(By.xpath("//div[contains(@id,'error')]/child::div[contains(text(),'Please')]"));
		System.out.println("The erorr messages for the missed fields are:");
		for (WebElement webElement : missedFields) {
			
			System.out.println(webElement.getText());
			
		}
		
		driver.quit();
	}

}
