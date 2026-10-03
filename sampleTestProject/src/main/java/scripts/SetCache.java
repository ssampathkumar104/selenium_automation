package scripts;

import org.testng.annotations.Test;

import constants.LNCACHEVALUES;
import testBase.BaseClass;

public class SetCache extends BaseClass{

	
//	@BeforeMethod
//	public void aLogin()
//	{
//		
//		//Login to Cloud Suite 
//		setDataFolder(folder);
//		Map<String, Object> m = getDataLNCreateApprovePurchaseOrder.getData(testDataPropertyFile);
//		CSLoginContext loginContext = (CSLoginContext) m.get(SloginContextS);
//		Cloudsuite.login(loginContext);
//	}
	
	@Test
	public void setCache() throws Exception
	{
		//Create Purchase Order & process till Release in LN
		// setCache(LNCACHEVALUES.ADJUSTMENT_ORDERS, SSampath Kumar SS);
//		LNPurchaseOrderContext purchaseOrderContext = (LNPurchaseOrderContext) getDataLNCreateApprovePurchaseOrder.getData(testcaseDataPropertyFile).get(SpurchaseOrderContextS);
//		AppMenu.navigateToApplication(PRODUCTNAMES.LN);
//		LNPurchaseOrder.createAndProcessTillRelease(purchaseOrderContext);
	}
	
//	@AfterMethod
//	public void cLogout()
//	{
//		//Logout from Cloud Suite
//		Cloudsuite.logOut();
//	}

}
