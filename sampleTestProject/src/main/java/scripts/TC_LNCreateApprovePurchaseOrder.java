package scripts;

import static testBase.TestData.setDataFolder;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.AppMenu;
import commons.Cloudsuite;
import constants.PRODUCTNAMES;
import contexts.CSLoginContext;
import contexts.LNPurchaseOrderContext;
import dataMapping.getDataLNCreateApprovePurchaseOrder;
import functions.LNPurchaseOrder;
import testBase.BaseClass;

public class TC_LNCreateApprovePurchaseOrder extends BaseClass
{
	public static String folder= "test";
	public static String testDataPropertyFile = "LNSampleTestCase.properties";
	public static String testcaseDataPropertyFile = "TCLN_868.properties";
	public static LNPurchaseOrderContext purchaseOrderContext;
	
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
		LNPurchaseOrderContext purchaseOrderContext = (LNPurchaseOrderContext) getDataLNCreateApprovePurchaseOrder.getData(testcaseDataPropertyFile).get("purchaseOrderContext");
		AppMenu.navigateToApplication(PRODUCTNAMES.LN);
		LNPurchaseOrder.createAndProcessTillRelease(purchaseOrderContext);
	}
	
	@AfterMethod
	public void cLogout()
	{
		//Logout from Cloud Suite
		Cloudsuite.logOut();
	}

}