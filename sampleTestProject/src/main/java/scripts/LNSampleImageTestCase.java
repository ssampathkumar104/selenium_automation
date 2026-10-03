package scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.Cloudsuite;
import contexts.CSLoginContext;
import functions.ExcelFunctions;
import testBase.BaseClass;

public class LNSampleImageTestCase extends BaseClass{
	
	public static String testDataPropertyFile = "LNSampleTestCase.properties";
	public static CSLoginContext context;
//	static String regexCompany = "Company\\s:\\s([A-Za-z0-9]+)";
	static String regexOrder = "Order:\\s([A-Za-z0-9]+)";
	
	@BeforeMethod
	public void aLogin(){	
		context = Cloudsuite.getCSLoginDetailsContext(testDataPropertyFile);
		Cloudsuite.login(context);
	}

	@Test
	public void bRunprogram() throws Exception{
//		AppMenu.navigateToApplication(PRODUCTNAMES.LN);
//		LNCommon.runProgram(LNSESSIONCODES.PLANNED_ORDERS);
		screenshot("Planned Orders");
//		String orderNumber = extractImageText(getScreenshotPath(), regexOrder);
//		System.out.println("====== Order number script :"+orderNumber +" ======");
//		extractImageText(regexOrder);
//		System.out.println("Order number from raw text"+extractImageText(regexOrder));
//		String orderNumber = ExcelFunctions.getOCRExtractedData("extracted_data.xls").get("Transferred To");
//		System.out.println("Order number is:"+orderNumber);

	}
	
	@AfterMethod
	public void cLogout(){
//		Cloudsuite.logOut();
	}
}