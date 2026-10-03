package scripts;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_DockerSeleniumGrid_v2 
{
	@Test(dataProvider="getData")
	public void localExecution(String browser,String version) throws Exception
	{
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browser);
		dc.setVersion(version);
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		driver.get("https://your-environment-url.example.com");
		System.out.println("browser--> "+browser+" version--> "+version+" Title is--> "+driver.getTitle());
		driver.findElement(By.xpath("//input[@name='pf.username']")).sendKeys("test.user@example.com");
		driver.findElement(By.xpath("//input[@name='pf.pass']")).sendKeys("your-password-here");
		driver.findElement(By.xpath("//a[@title='Sign On']")).click();
		Thread.sleep(50000);
		System.out.println("Page Title2 is "+driver.getTitle());
		driver.quit();
	}
	
	@DataProvider(parallel=true)
	public Object[][] getData()
	{
		return new Object[][]
		{{"chrome","90.0.4430.85"},{"chrome","90.0.4430.85"},{"chrome","90.0.4430.85"},{"firefox","88.0"}}; //90.0.4430.85
	}
	
}
