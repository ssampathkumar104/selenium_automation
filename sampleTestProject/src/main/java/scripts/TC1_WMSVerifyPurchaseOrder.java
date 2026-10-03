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
import functions.WMSFunctions;
import testBase.BaseClass;

/**
 * @author anonymous
 *	Purpose - To verify Purchase Order flow from LN to WMS to test WMS-Inbound
 */
public class TC1_WMSVerifyPurchaseOrder extends BaseClass
{	
	public static String folder= "WMS";
	public static String propertyFile = "TCLN-1.properties";
	
	@BeforeMethod
	public void login()
	{
		//Login to Cloud Suite
		setDataFolder(folder);
		Map<String, Object> m = getDataLNCreateApprovePurchaseOrder.getData(propertyFile);
		CSLoginContext loginContext = (CSLoginContext) m.get("loginContext");
		Cloudsuite.login(loginContext);
	}

	@Test
	public void verifyWMSInbound() throws Exception
	{
		LNPurchaseOrderContext purchaseOrderContext = (LNPurchaseOrderContext) getDataLNCreateApprovePurchaseOrder.getData(propertyFile).get("purchaseOrderContext");
		
		//Create Purchase Order in LN
		AppMenu.navigateToApplication(PRODUCTNAMES.LN);
		LNPurchaseOrder.createAndProcessTillRelease(purchaseOrderContext);
		
		//Verify Purchase order in WMS
		AppMenu.navigateToApplication(PRODUCTNAMES.WMS);
		WMSFunctions.navigateToFacility(purchaseOrderContext);
		WMSFunctions.verifyPO(purchaseOrderContext);
		System.out.println("=========>>>>> WMS Inbound flow Successful <<<<<=========");
	}
	
	@AfterMethod
	public void logout()
	{
		//Logout from Cloud Suite
		Cloudsuite.logOut();
	}
}