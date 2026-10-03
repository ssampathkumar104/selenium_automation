package scripts;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testBase.BaseClass;
/**
 * Unit test for simple App.
 */
public class Trail2  extends BaseClass{
    
	@BeforeMethod
	public void BeforeTestMethod() throws Exception{
		System.out.println("BeforeTestMethod");
		screenshot("BeforeTestMethod");
		getDriver().get("https://google.com");
		getDriver().findElement(By.xpath("sss"));
	}
	@Test
	public void AppTestMethod() throws Exception {
		getDriver().get("https://google.com");
		System.out.println("AppTestMethod");
		Thread.sleep(2000);
	}
	

}
