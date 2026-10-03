package scripts;

import org.sikuli.script.Key;
//import org.openqa.selenium.Keys;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.Cloudsuite;
import contexts.CSLoginContext;
import desktop.outlook.screens.HomepagesPage;
import desktop.outlook.screens.LNPage;
import testBase.BaseClass;

/**
 * Test Sikuli Actions
 */
public class Desktop_ImagebasedTest extends BaseClass {
	public static String testDataPropertyFile = "LNSampleTestCase.properties";
	public static CSLoginContext context;

	@BeforeMethod
	public void login() {
		// Login to Cloud Suite
		context = Cloudsuite.getCSLoginDetailsContext(testDataPropertyFile);
		Cloudsuite.login(context);
	}

	@Test
	public void SikuliTest1() throws FindFailed, Exception {
		HomepagesPage hp = new HomepagesPage();
		Thread.sleep(20000);
		hp.appMenu.exists(15);
		hp.appMenu.click();
		hp.inforLN.wait(10);
		hp.inforLN.click();

		LNPage ln = new LNPage();
		if (ln.ok.exists()) {
			ln.ok.wait(20000);
			ln.ok.click();
		}
		Thread.sleep(20000);
		ln.options.wait(10);
		ln.options.click();
		ln.runprogram.wait(10);
		ln.runprogram.click();
		ln.open.exists(10);
		ln.open.click();
		ln.sessionID.wait(10);
		ln.sessionID.typeWithTab("tdpur4100m000");
		Thread.sleep(20000);
		ln.ok.click();
		ln.orderFilter.wait(10);
		ln.orderFilter.typeWithTab("ZPO024450");
		ln.drilldown.wait(10);
		ln.drilldown.click();
		Thread.sleep(20000);
		ln.orderID.click();
		String orderID = ln.orderID.getText();
		System.out.println("orderID : " + orderID + " ");
	}

	@AfterMethod
	public void logout() {
		// Logout from Cloud Suite
		Cloudsuite.logOut();
	}
}