package scripts;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import testBase.BaseClass;

public class SetupDockerGrid extends BaseClass{
	
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

}
