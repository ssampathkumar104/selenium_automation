package scripts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import functions.SampleWorkflow;
import testBase.BaseClass;

/**
 * Unit test for simple App.
 */
public class SampleTestWithPassSkipFail extends BaseClass {

//	@Test
//	public void sample1() throws Exception {
//		getDriver().get("https://google.com/");
//		log().info("sample1 before test");
//		screenshot("sample1 before");
//		SampleWorkflow.test();
//		System.out.println("sampl1test");
//		screenshot("sample1 after");
//		log().info("sample1 after test");
//	}

	@Test
	public void sample2() throws Exception {
		log().info("sample2 before test log");
		screenshot("sample2 before test1");
		getDriver().get("https://your-environment-url.example.com/");
		
		WebElement e = getDriver().findElement(By.name("username"));
		e.click();
		
		screenshot("sample2 before test2");
//		throw new SkipException("skip");
	}

//	@Test(enabled = true)
//	public void sample3() throws Exception {
//		getDriver().get("https://google.com/");
//		log().info("sample3 before test");
//		screenshot("sample3");
//		getDriver().findElement(By.xpath("tttt"));
//		assertEquals(100, 120);
//	}

}
