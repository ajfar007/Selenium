package TestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC001_Myntra {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		//1 Open https://www.myntra.com/
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2 Mouse over on WOMEN
		WebElement women = driver.findElement(By.xpath("//a[text()='Women']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(women).perform();
		//3 Click Jackets & Coats
		driver.findElement(By.linkText("Jackets & Coats")).click();
		
		//4 Find the total count of item (top) -> getText -> String
		String items = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		int itemcount = Integer.parseInt(items.replaceAll("\\D", ""));
		
		//5 Validate the sum of categories count matches
		String jacketCnt = driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String coatCnt = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		jacketCnt =  jacketCnt.replaceAll("\\D", "");
		coatCnt =  coatCnt.replaceAll("\\D", "");
		int totalCnt = (Integer.parseInt(jacketCnt) + Integer.parseInt(coatCnt));
		if(itemcount == totalCnt)
			System.out.println("The total count matches");
		else
			System.out.println("The total count does not match");
		
		//6 Check Coats
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[2]")).click();
		
		//7 Click + More option under BRAND
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		
		//8 Type MANGO and click checkbox
		driver.findElement(By.xpath("//input[@class='FilterDirectory-searchInput']")).sendKeys("MANGO");
		driver.findElement(By.xpath("//label[@class=' common-customCheckbox']//div[1]")).click();
		
		//9 Close the pop-up x
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		Thread.sleep(2000);
		
		//10 Confirm all the Coats are of brand MANGO
		List<WebElement> coatList = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		int count = 0;
		System.out.println("The number of coats are "+coatList.size());
		for (WebElement webElement : coatList) {
			if(!webElement.getText().equalsIgnoreCase("MANGO"))
				count++;
		}
			if(count > 0)
				System.out.println("The are brands other than MANGO in the List");
			else
				System.out.println("All the Coats belongs to Brand MANGO");
		//11 Sort by Better Discount
		
		WebElement sortBy = driver.findElement(By.className("sort-sortBy"));
		
		System.out.println(sortBy.getText());
		Actions sortByOptns = new Actions(driver);
		sortByOptns.moveToElement(sortBy).perform();
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
		Thread.sleep(2000);
		
		//12 Find the price of first displayed item
		List<WebElement> priceList = driver.findElements(By.xpath("//span[@class='product-discountedPrice']"));
		String fistItemPrice = priceList.get(0).getText().replaceAll("\\D", "");
		System.out.println("The Price of the first displayed itm is:" +fistItemPrice);
		
		
		Thread.sleep(2000);
		
		//13 Mouse over on size of the first item
		builder.moveToElement(driver.findElement(By.xpath("(//div[@class='product-productMetaInfo'])[1]"))).perform();
		
		//14 Click on WishList Now
		driver.findElement(By.xpath("//span[text()='wishlist now']")).click();
		Thread.sleep(4000);
		
		driver.close();
		
	}

}
