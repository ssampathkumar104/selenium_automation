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

public class Docker_FileUpload extends BaseClass{
	
	
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
		   
		   WebElement browse = driver.findElement(By.xpath("//input[@id='file-upload']"));
		   //click on ‘Choose file’ to upload the desired file
		  // ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		   browse.sendKeys("/home/seluser/filelocation/BC description.txt"); //Uploading the file using sendKeys
		   System.out.println("File is Uploaded Successfully");
		 
		   }

}
