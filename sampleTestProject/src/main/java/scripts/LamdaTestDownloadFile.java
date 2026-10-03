package scripts;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import testBase.BaseClass;
import testBase.TestData;

public class LamdaTestDownloadFile extends BaseClass {

	@Test
	public void fileDownload() throws AWTException, InterruptedException {

		getDriver().get("https://chromedriver.storage.googleapis.com/index.html?path=79.0.3945.36/");
		Thread.sleep(2000);
		WebElement btnDownload = getDriver().findElement(By.xpath(".//a[text()='chromedriver_win32.zip']"));
		btnDownload.click();

		Thread.sleep(20000);

				    
//		String directoryPath = TestData.getDataFolderToUpload(); // Specify the path to your directory
//		File f = new File(System.getProperty("user.dir") + File.separator + "downloads" + File.separator +"chromedriver_win32.zip");
		
		
		File f = new File(TestData.getDownloadDirectoryPath()+ File.separator +"chromedriver_win32.zip");
		System.out.println(System.getProperty("user.dir") + File.separator + "downloads" + File.separator +"chromedriver_win32.zip");
		
		
		System.out.println("asasas : "+ f.exists());
				    
//		try {
//			Files.walk(Paths.get(directoryPath)).forEach(System.out::println);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

}