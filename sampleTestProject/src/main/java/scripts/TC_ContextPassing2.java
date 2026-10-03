package scripts;
 
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
 
import testBase.ArtefactBuilder;
import testBase.BaseClass;
 
//@Test(groups = { "CS1", "P1" })
public class TC_ContextPassing2 extends BaseClass {
	
 
	@Test
	public void TCLN_ContextPassing2() throws Exception {
		getDriver().get("https://www.google.com/");
		WebElement e = getDriver().findElement(By.name("q"));
		e.click();
		click("TCLN_ContextPassing2", e);
		log().info("TCLN_ContextPassing2");
		screenshot("TCLN_ContextPassing2");
		fail("Failing TCLN_ContextPassing2");
	}
}
 