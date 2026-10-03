package scripts;

import org.testng.annotations.Test;

import constants.LNCACHEVALUES;
import testBase.BaseClass;

public class GetCache extends BaseClass{

	
//	@BeforeMethod
//	public void aLogin()
//	{
//		
//		//Login to Cloud Suite 
//		setDataFolder(folder);
//		Map<String, Object> m = getDataLNCreateApprovePurchaseOrder.getData(testDataPropertyFile);
//		CSLoginContext loginContext = (CSLoginContext) m.get("loginContext");
//		Cloudsuite.login(loginContext);
//	}
	
	@Test
	public void setCache() throws Exception
	{
		//Create Purchase Order & process till Release in LN
		String getCacheValue = (String) getCache(LNCACHEVALUES.ADJUSTMENT_ORDERS);
		log().info("====== value read from Cache is "+getCacheValue);
	}
	
//	@AfterMethod
//	public void cLogout()
//	{
//		//Logout from Cloud Suite
//		Cloudsuite.logOut();
//	}

}
