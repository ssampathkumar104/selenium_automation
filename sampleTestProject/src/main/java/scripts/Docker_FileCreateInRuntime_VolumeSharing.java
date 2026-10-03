package scripts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import testBase.BaseClass;

public class Docker_FileCreateInRuntime_VolumeSharing extends BaseClass{
	
	
	@Test
	public void docker_file_upload() throws Exception
	{
		   // TODO Auto-generated method stub
		 
		   WebDriver driver = getDriver();
		   getDriver().get("https://www.monsterindia.com/seeker/registration"); //Testing webpage
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //for Implicit wait
		 
		   JavascriptExecutor js = (JavascriptExecutor)driver; //Scrolling using JavascriptExecutor
		   js.executeScript("window.scrollBy(0,380)");//Scroll Down to file upload button (+ve)
		   Thread.sleep(3000);
		 
		   // FILE UPLOADING USING SENDKEYS ....
		   
		   //New file is being created in runtime
		   FileUtils.copyFileToDirectory(new File("C:\\Users\\ssankati\\Downloads\\filelocation\\BC description.txt"), new File("C:\\Users\\ssankati\\Downloads\\filelocation\\BC description1.txt"));
		   WebElement browse = driver.findElement(By.xpath("//input[@id='file-upload']"));
		   //Upload a file created during runtime
		   browse.sendKeys("/home/seluser/filelocation/BC description1.txt"); //Uploading the file using sendKeys
		   System.out.println("File is Uploaded Successfully");
		 
		   }

}
