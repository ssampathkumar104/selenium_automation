package scripts;

import static testBase.TestData.setDataFolder;

import java.net.URL;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commons.AppMenu;
import commons.Cloudsuite;
import constants.PRODUCTNAMES;
import contexts.CSLoginContext;
import contexts.LNPurchaseOrderContext;
import dataMapping.getDataLNCreateApprovePurchaseOrder;
import functions.LNPurchaseOrder;
import pages.Homepages;
import testBase.BaseClass;

public class TC_DockerSeleniumGrid_v3 extends BaseClass
{
	public static String folder= "test";
	public static String testDataPropertyFile = "LNSampleTestCase.properties";
	public static String testcaseDataPropertyFile = "TCLN_868.properties";
	public static LNPurchaseOrderContext purchaseOrderContext;
	
	@Test//(dataProvider="getData")
	public void localExecution(String browser,String version) throws Exception
	{
		setDataFolder(folder);
		Map<String, Object> m = getDataLNCreateApprovePurchaseOrder.getData(testDataPropertyFile);
//		DesiredCapabilities dc = new DesiredCapabilities();
//		dc.setBrowserName(browser);
//		dc.setVersion(version);
//		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		Homepages homepage = new Homepages();
		homepage.username.sendKeys(getParameter(""));
		homepage.password.sendKeys(getParameter(""));
		homepage.submit.click();
//		driver.get("https://your-environment-url.example.com");
//		System.out.println("browser--> "+browser+" version--> "+version+" Title is--> "+driver.getTitle());
//		driver.findElement(By.xpath("//input[@name='pf.username']")).sendKeys("test.user@example.com");
//		driver.findElement(By.xpath("//input[@name='pf.pass']")).sendKeys("your-password-here");
//		driver.findElement(By.xpath("//a[@title='Sign On']")).click();
//		Thread.sleep(50000);
//		System.out.println("Page Title2 is "+driver.getTitle());
//		driver.quit();
	}
	
//	@DataProvider(parallel=true)
//	public Object[][] getData()
//	{
//		return new Object[][]
//		{{"chrome","90.0.4430.85"},{"chrome","90.0.4430.85"},{"chrome","90.0.4430.85"},{"firefox","88.0"}}; //90.0.4430.85
//	}
	
}
