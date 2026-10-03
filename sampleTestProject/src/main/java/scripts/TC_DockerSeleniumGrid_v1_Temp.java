package scripts;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_DockerSeleniumGrid_v1_Temp 
{
	@BeforeTest
	void startDockerGrid() throws Exception
	{
		Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
		Thread.sleep(30000);
	}
	
	@Test(dataProvider="getData")
	public void localExecution(String browser,String version) throws Exception
	{
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browser);
		dc.setVersion(version);
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		driver.get("https://google.co.in");
		Thread.sleep(10000);
		System.out.println("Page Title is "+driver.getTitle());
		driver.quit();
	}
	
	@AfterTest
	 void stopDockerGrid() throws Exception
	 {
		Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
		Thread.sleep(15000);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");  //closes command prompt
	 }
	
	@DataProvider(parallel=true)
	public Object[][] getData()
	{
		return new Object[][]
		{{"chrome","90.0.4430.85"},{"chrome","90.0.4430.85"},{"chrome","90.0.4430.85"},{"firefox","88.0"}}; 
	}
	
}
