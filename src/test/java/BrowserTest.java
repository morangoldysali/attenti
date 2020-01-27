import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserTest {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.metric-conversions.org/");
		mainTest(driver, "Celsius to Fahrenheit", "32", "32°C= 89.60000°F");
		driver.navigate().to("https://www.metric-conversions.org/");
		mainTest(driver, "Meters to Feet", "10", "10m= 32ft 9.700788in");
		driver.navigate().to("https://www.metric-conversions.org/");
		weightTest(driver);
		driver.close();

		
	}
	
private static void mainTest(WebDriver driver, String requestedTest, String enterValue, String expectedResult) {
		
		String answer= "";
		try {
			driver.findElement(By.xpath("//a[text()='"+requestedTest+"']")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("argumentConv")).sendKeys(enterValue);
			Thread.sleep(1000);
			answer = driver.findElement(By.id("answer")).getText();

		}
		catch(Exception c){
			System.out.print("couldn't find this element");
			driver.close();
			return;
		}
		
		if (answer.equals(expectedResult))
			System.out.print(requestedTest +" is working. value is : " + answer+"\n");
		else
			System.out.print(requestedTest+ " doesnt return the right value, " + answer+"\n");
	}

private static void weightTest(WebDriver driver) {

	String answer= "";
	try {
		driver.findElement(By.xpath("//a[text()='Weight']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("queryFrom")).sendKeys("ounces");
		driver.findElement(By.id("queryTo")).sendKeys("grams");
		Thread.sleep(1000);
		driver.findElement(By.className("argument")).sendKeys("5");
		driver.findElement(By.xpath("//a[text()='Convert']")).click();
		Thread.sleep(1000);
		answer = driver.findElement(By.id("answer")).getText();

	}
	catch(Exception c){
		System.out.print("couldn't find this element");
		driver.close();
	}
	
	if (answer.equals("5oz= 141.7476g"))
		System.out.print("ounces to grams is working. value is : " + answer +"\n");
	else 
		System.out.print("ounces to grams doesnt return the right value, " + answer+ "\n");
}

}
