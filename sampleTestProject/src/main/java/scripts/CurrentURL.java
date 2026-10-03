package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class CurrentURL extends BaseClass{

	
	@BeforeTest
	void startDockerGrid() throws Exception
	{
		Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
		Thread.sleep(30000);
	}
	
	
	@AfterTest
	 void stopDockerGrid() throws Exception
	 {
		Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
		Thread.sleep(15000);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");  //closes command prompt
	 }
	
	@Test
	public void sample1() throws Exception {
				WebDriver driver = getDriver();
				driver.get("https://google.co.in");
				String currenturl = getDriver().getCurrentUrl();
				System.out.println(currenturl);
	}

}
