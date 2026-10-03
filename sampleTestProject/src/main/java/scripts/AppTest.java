package scripts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testBase.BaseClass;
/**
 * Unit test for simple App.
 */
public class AppTest extends BaseClass {
    
	@BeforeMethod
	public void sampleLog() {
		log().info("====== [AppTest] Before Test Method ======");
		screenshot("[AppTest] Test 1");
	}
	@Test
	public void AppTest() throws Exception {
		screenshot("[AppTest] Test 2");
		log().info("====== [AppTest] Test Started ======");
		getDriver().get("https://google.com");
		Thread.sleep(2000);
		log().info("====== [AppTest] Test Finished ======");
		screenshot("[AppTest] Test 3");
//		SampleWorkflow.test();
//		TestData.setDataFolder("test");
//		getDriver().get("https://mail.google.com/mail/u/0/?tab=wm&ogbl");
////		SampleWorkflow.test();
//		TestData.setDataFolder("test");
//		System.out.println(ExcelFunctions.dataFromExcelSolution2("LN-IA-InputDataWorkBook.xlsx", "JPO", "TC01_JapanProcessPurchaseOrder"));
//		
//		System.out.println(ExcelFunctions.dataFromExcel("LN-IA-InputDataWorkBook.xlsx","Test"));
//		
//		TestData.setDataFolder("test2");
//		System.out.println(ExcelFunctions.dataFromExcelSolution2("LN-IA-InputDataWorkBook.xlsx", "JPO", "TC01_JapanProcessPurchaseOrder"));
//		
//		System.out.println(ExcelFunctions.dataFromExcel("LN-IA-InputDataWorkBook.xlsx","Test"));
		
	}
	

}
