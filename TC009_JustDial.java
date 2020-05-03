package TestCases;



import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TC009_JustDial {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);

		//1 https://www.justdial.com/
		driver.get("https://www.justdial.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2 Set the Location as Chennai
		Thread.sleep(3000);
		
		//3 Click Auto Care in the left menu
		driver.findElement(By.linkText("Auto care")).click();
		
		//4 Click Car Repair
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[text()='Car Repair']")).click();
		
		//5 Click Car Brand as Hyundai
		Thread.sleep(500);
		driver.findElement(By.xpath("(//span[text()='Hyundai'])[1]")).click();
		
		//6 Click Make as Hyundai I20
		Thread.sleep(500);
		driver.findElement(By.xpath("(//span[text()='Hyundai I20'])[1]")).click();
		
		//7 Identify all the Service Center having Ratings >=4.2 and Votes >=50
		List<WebElement> ratingList = driver.findElements(By.xpath("//span[@class='green-box']"));
		List<WebElement> voteList = driver.findElements(By.xpath("//span[@class='green-box']/following-sibling::span[@class='rt_count lng_vote']"));

		String[][] arr1 = new String[ratingList.size()][2]; 
		int i=0;
		
			for(i=0;i<ratingList.size(); i++)
			{
				arr1[i][0] = ratingList.get(i).getText().replaceAll("[a-zA-Z\\s]", "");
				arr1[i][1] = voteList.get(i).getText().replaceAll("\\D", "");
			System.out.println("Rating: "+arr1[i][0]+" Votes: "+arr1[i][1]);
			System.out.println();

		}
		Map<String, Character> phoneCodeValueMap = new LinkedHashMap<String, Character>();

		phoneCodeValueMap.put("mobilesv icon-dc", '+');
		phoneCodeValueMap.put("mobilesv icon-fe", '(');
		phoneCodeValueMap.put("mobilesv icon-hg",')');
		phoneCodeValueMap.put("mobilesv icon-ba", '-');
		phoneCodeValueMap.put("mobilesv icon-acb",'0');
		phoneCodeValueMap.put("mobilesv icon-yz",'1');
		phoneCodeValueMap.put("mobilesv icon-wx",'2');
		phoneCodeValueMap.put("mobilesv icon-vu",'3');
		phoneCodeValueMap.put("mobilesv icon-ts",'4');
		phoneCodeValueMap.put("mobilesv icon-rq",'5');
		phoneCodeValueMap.put("mobilesv icon-po",'6');
		phoneCodeValueMap.put("mobilesv icon-nm",'7');
		phoneCodeValueMap.put("mobilesv icon-lk",'8');
	    phoneCodeValueMap.put("mobilesv icon-ji",'9');
		
	    		
		for (i=0;i<ratingList.size(); i++)
		
			if(Double.parseDouble(arr1[i][0]) >= 4.2 && Integer.parseInt(arr1[i][1]) >= 50)
			{	System.out.println("Rating is: "+arr1[i][0]+" and votes are: "+arr1[i][1]);
				String centreName ="";
				centreName = driver.findElement(By.xpath("(//span[@class='lng_cont_name'])["+(i+1)+"]")).getText();
		
			List<WebElement> phoneDigitsele = driver.findElements(By.xpath("(//p[@class='contact-info '])["+(i+1)+"]//child::span//child::span"));
			List<Character> digitCodes = new ArrayList<Character>();
		
			for (WebElement webElement : phoneDigitsele) {	
				for(Entry<String, Character> eachEntry: phoneCodeValueMap.entrySet())
				{
					if(eachEntry.getKey().equals((webElement.getAttribute("class"))))			
						digitCodes.add(eachEntry.getValue());
				} 		
														}
		//8 Print all the Service Center name and Phone number matching the above condition
			System.out.println("The phone number of Service Centre '"+centreName+"' is: ");
				for (Character character : digitCodes) {
					System.out.print(character.charValue());
				}
		}
		
		//9 Close the browser
		driver.quit();
	}

}
