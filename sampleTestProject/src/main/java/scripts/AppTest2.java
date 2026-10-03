package scripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import testBase.BaseClass;
import testBase.TestData;
/**
 * Unit test for simple App.
 */

public class AppTest2 extends BaseClass {
    
	@Test
	public void AppTest2() throws Exception {
		
		log().info("====== [AppTest2] Test Started ======");
		getDriver().get("https://www.learningcontainer.com/sample-pdf-files-for-ting");
		Thread.sleep(5000);
		screenshot("[AppTest] Test");
		actions().moveToElement(getDriver().findElement(By.xpath("//a[text()='Download']"))).build().perform();
		getDriver().findElement(By.xpath("//a[text()='Download']")).click();;
		Thread.sleep(5000);
//		SampleWorkflow.test();
		TestData.setDataFolder("test");
		log().info("====== [AppTest2] Test Finished ======");
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
