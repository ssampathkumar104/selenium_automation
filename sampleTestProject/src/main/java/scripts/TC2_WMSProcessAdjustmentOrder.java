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
import contexts.WMSAdjustmentOrderContext;
import dataMapping.getDataLNCreateApprovePurchaseOrder;
import functions.WMSFunctions;
import testBase.BaseClass;

public class TC2_WMSProcessAdjustmentOrder extends BaseClass
{	
	public static String folder= "WMS";
	public static String testDataPropertyFile = "TCLN-2.properties";
	
	@BeforeMethod
	public void login()
	{
		//Login to Cloud Suite
		setDataFolder(folder);
		Map<String, Object> m = getDataLNCreateApprovePurchaseOrder.getData(testDataPropertyFile);
		CSLoginContext loginContext = (CSLoginContext) m.get("loginContext");
		Cloudsuite.login(loginContext);
	}

	@Test
	public void verifyWMSOutbound() throws Exception
	{
		WMSAdjustmentOrderContext orderContext = (WMSAdjustmentOrderContext) getDataLNCreateApprovePurchaseOrder.getData(testDataPropertyFile);
		
		//Create Adjustment Order in WMS
		AppMenu.navigateToApplication(PRODUCTNAMES.WMS);
		WMSFunctions.createAdjustment(orderContext);
		
		//Verify Adjustment order in LN
		AppMenu.navigateToApplication(PRODUCTNAMES.LN);

		System.out.println("=========>>>>> Adjustment Order flowed Successfully from WMS to LN <<<<<=========");
		System.out.println("=========>>>>> WMS Outbound flow Successful <<<<<=========");
	}
	
	@AfterMethod
	public void logout()
	{
		//Logout from Cloud Suite
		Cloudsuite.logOut();
	}
}