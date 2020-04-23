package TestCases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TC007_HondaTwoWheeler {
	
	public static void main(String[] args) throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Go to https://www.honda2wheelersindia.com/
		driver.get("https://www.honda2wheelersindia.com/");
		
		//warning message
		if(driver.findElement(By.xpath("//button[@class='close']")).isDisplayed())
		driver.findElement(By.xpath("//button[@class='close']")).click();
		
		//2 Click on scooters and click dio
		driver.findElement(By.xpath("(//a[text()='Scooter'])[1]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[contains(@src,'dio')])[1]")));
		driver.findElementByXPath("(//img[contains(@src,'dio')])[1]").click();
		
		//3 Click on Specifications and mouseover on ENGINE
		driver.findElement(By.xpath("(//a[text()='Specifications'])[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='ENGINE'])[1]")));
		driver.findElement(By.xpath("(//a[text()='ENGINE'])[1]")).click();
		
		//4 Get Displacement value
		String dioCC = driver.findElement(By.xpath("//span[text()='Displacement']/following-sibling::span")).getText();
		System.out.println("The Engine Capacity of Dio is: "+dioCC);
		
		//5 Go to Scooters and click Activa 125
		driver.findElementByXPath("(//a[text()='Scooter'])[1]").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[contains(@src,'activa')])[1]")));
		driver.findElementByXPath("(//img[contains(@src,'activa-125')])[1]").click();
		
		//6 Click on Specifications and mouseover on ENGINE
		driver.findElement(By.xpath("(//a[text()='Specifications'])[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='ENGINE'])[1]")));
		driver.findElement(By.xpath("(//a[text()='ENGINE'])[1]")).click();
		
		//7 Get Displacement value
		String activaCC = driver.findElement(By.xpath("//span[text()='Displacement']/following-sibling::span")).getText();
		System.out.println("The Engine Capacity of Activa is: "+activaCC);
		
		//8 Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		dioCC =  dioCC.replaceAll("[^0-9.]", "");
		activaCC = activaCC.replaceAll("[^0-9.]", "");
		double dioCCInt = Double.parseDouble(dioCC);
		double ActivaCCInt = Double.parseDouble(activaCC);

		
		if(dioCCInt>ActivaCCInt)
			System.out.println("Honda Dio has more Cubic Capacity than Activa");
		else
			System.out.println("Activa has more Cubic Capacity than Dio");
		
		//9 Click FAQ from Menu 
		driver.findElement(By.xpath("(//a[text()='FAQ'])[1]")).click();
		
		//10 Click Activa 125 BS-VI under Browse By Product
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Activa 125 BS-VI']")));
		driver.findElement(By.xpath("//a[text()='Activa 125 BS-VI']")).click();
		
		//11 Click  Vehicle Price 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=' Vehicle Price']")));
		driver.findElement(By.xpath("//a[text()=' Vehicle Price']")).click();
		
		//12 Make sure Activa 125 BS-VI selected and click submit
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//select[@id='ModelID6']"))));
		Select scooterModel = new Select(driver.findElement(By.xpath("//select[@id='ModelID6']")));
		String modelSlctd = scooterModel.getFirstSelectedOption().getText();
		
		if(modelSlctd.equals("Activa 125 BS-VI"))
			{	System.out.println("The model selected is Activa 125 BS-VI");
				driver.findElement(By.id("submit6")).click();
			}
		else
			System.out.println("Wrong Model Selected");
		
		//13 click the price link
		driver.findElement(By.partialLinkText("Click here to know the price of Activa")).click();
		
		//14  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> windows = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windows);
		driver.switchTo().window(winList.get(1));
		Select state = new Select(driver.findElement(By.id("StateID")));
		state.selectByVisibleText("Tamil Nadu");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='CityID']")));
		Select city = new Select(driver.findElement(By.xpath("//select[@name='CityID']")));
		city.selectByVisibleText(("Chennai"));

		//15 Click Search
		driver.findElement(By.xpath("//button[text()='Search']")).click();

		//16 Print all the 3 models and their prices
		String model = "";
		String price = "";
		Map<String, String> map1 =  new LinkedHashMap<String, String>();
		WebElement scooters = driver.findElementByXPath("//table[@id='gvshow']/tbody");
		List<WebElement> allRows = scooters.findElements(By.tagName("tr"));
		for(int i = 0; i < allRows.size(); i++) {
			List<WebElement> allCells = allRows.get(i).findElements(By.tagName("td"));
			if(i == 0) {
				model = allCells.get(1).getText();
				price = allCells.get(2).getText();
			}
			else {
				model = allCells.get(0).getText();
				price = allCells.get(1).getText();
			}
			map1.put(model, price);				
		}
		for (Entry<String,String> e : map1.entrySet()) {
			System.out.println("The Price for model "+e.getKey()+" is "+e.getValue());
		}
		
		//17 Close the Browser
		Thread.sleep(5000);
		driver.quit();
		
	}
		

}
