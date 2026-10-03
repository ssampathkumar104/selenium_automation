package scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.AppMenu;
import commons.Cloudsuite;
// import constants.LNSESSIONCODES;
import constants.PRODUCTNAMES;
import contexts.CSLoginContext;
import functions.LNCommon;
import testBase.BaseClass;

public class LNSampleTestCase extends BaseClass{
	
	public static String testDataPropertyFile = "LNSampleTestCase.properties";
	public static CSLoginContext context;
	
	@BeforeMethod
	public void aLogin()
	{	
		context = Cloudsuite.getCSLoginDetailsContext(testDataPropertyFile);
		Cloudsuite.login(context);
	}

	@Test
	public void bRunprogram() throws Exception
	{
		AppMenu.navigateToApplication(PRODUCTNAMES.LN);
		// LNCommon.runProgram(LNSESSIONCODES.PURCHASE_ORDER);
	}
	
	@AfterMethod
	public void cLogout()
	{
		Cloudsuite.logOut();
	}

}
