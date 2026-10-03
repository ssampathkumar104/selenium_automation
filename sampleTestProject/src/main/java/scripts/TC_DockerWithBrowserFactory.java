package scripts;

import static testBase.TestData.setDataFolder;

import java.util.Map;

import org.testng.annotations.Test;

import commons.Cloudsuite;
import contexts.CSLoginContext;
import contexts.LNPurchaseOrderContext;
import dataMapping.getDataLNCreateApprovePurchaseOrder;
import testBase.BaseClass;

public class TC_DockerWithBrowserFactory extends BaseClass{
	
	public static String folder= "test";
	public static String testDataPropertyFile = "LNSampleTestCase.properties";
	public static String testcaseDataPropertyFile = "TCLN_868.properties";
	public static LNPurchaseOrderContext purchaseOrderContext;
	
	@Test
	public void dockerBrowserFactory()
	{
		//Login to Cloud Suite
		setDataFolder(folder);
		Map<String, Object> m = getDataLNCreateApprovePurchaseOrder.getData(testDataPropertyFile);
		CSLoginContext loginContext = (CSLoginContext) m.get("loginContext");
		Cloudsuite.login(loginContext);
	}

}
