package scripts;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testBase.BaseClass;
import testBase.listners.ExtentReportListener;

/**
 * Unit test for simple App.
 */
public class Trail extends BaseClass {
	
	@Test
	public void StageTest() throws Exception {
		screenshot("Stage Test 1");
		log().info("Stage Test 1");
		getDriver().get("https://your-environment-url.example.com");
		screenshot("Stage Test 2");
		log().info("Stage Test 2");
		getDriver().findElement(By.xpath("ssaaa"));
	}
	
	@Test
	public void googleTest() throws Exception {
		getDriver().get("https://google.com/");
		screenshot("google Test");
		log().info("google Test");
		getDriver().findElement(By.xpath("tttt"));
	}

}
