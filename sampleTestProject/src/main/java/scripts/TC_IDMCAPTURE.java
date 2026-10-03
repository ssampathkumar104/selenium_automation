package scripts;

import static testBase.TestData.setDataFolder;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commons.AppMenu;
import commons.Cloudsuite;
import constants.PRODUCTNAMES;
import contexts.CSLoginContext;
import contexts.LNPurchaseOrderContext;
import dataMapping.getDataLNCreateApprovePurchaseOrder;
import functions.LNPurchaseOrder;
import testBase.BaseClass;

public class TC_IDMCAPTURE extends BaseClass
{
	public static String folder= "test";
	public static String testDataPropertyFile = "LNSampleTestCase.properties";
	public static String testcaseDataPropertyFile = "TCLN_868.properties";
	public static LNPurchaseOrderContext purchaseOrderContext;
	
	@BeforeTest
	public void startDockerGrid() throws Exception
	{
		Runtime.getRuntime().exec("cmd /c start start_dockergrid.bat");
		Thread.sleep(30000);
	}
	
	
	@AfterTest
	 public void stopDockerGrid() throws Exception
	 {
		Runtime.getRuntime().exec("cmd /c start stop_dockergrid.bat");
		Thread.sleep(15000);
		Runtime.getRuntime().exec("taskkill /f /im cmd.exe");  //closes command prompt
	 }
	
	@BeforeMethod
	public void aLogin()
	{
		
		//Login to Cloud Suite 
		setDataFolder(folder);
		Map<String, Object> m = getDataLNCreateApprovePurchaseOrder.getData(testDataPropertyFile);
		CSLoginContext loginContext = (CSLoginContext) m.get("loginContext");
		Cloudsuite.login(loginContext);
	}

	@Test
	public void createPOTillRelease() throws Exception
	{
		//Create Purchase Order & process till Release in LN
		//LNPurchaseOrderContext purchaseOrderContext = (LNPurchaseOrderContext) getDataLNCreateApprovePurchaseOrder.getData(testcaseDataPropertyFile).get("purchaseOrderContext");
		AppMenu.navigateToApplication(PRODUCTNAMES.LN);
		Thread.sleep(10000);
		//LNPurchaseOrder.createAndProcessTillRelease(purchaseOrderContext);
	}
	
	@AfterMethod
	public void cLogout()
	{
		//Logout from Cloud Suite
		Cloudsuite.logOut();
	}

}