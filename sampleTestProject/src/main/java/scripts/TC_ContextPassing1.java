package scripts;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.AppMenu;
import commons.Cloudsuite;
import constants.PRODUCTNAMES;
import contexts.CSLoginContext;
import testBase.BaseClass;

@Test(groups = { "CS1", "P2" })
public class TC_ContextPassing1 extends BaseClass {
	
	

	@Test
	public void TCLN_ContextPassing1() throws Exception {
//		AppMenu.navigateToApplication(PRODUCTNAMES.LN);
		getDriver().get("https://www.google.com/");
		WebElement e = getDriver().findElement(By.name("q"));
		e.click();
		click("TCLN_ContextPassing1", e);
		log().info("TCLN_ContextPassing1");
		screenshot("TCLN_ContextPassing1");
	}

	
}