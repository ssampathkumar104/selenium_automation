package scripts;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
 
import testBase.ArtefactBuilder;
import testBase.BaseClass;
 
//@Test(groups = { "CS1", "P1" })
public class TC_ContextPassing4 extends BaseClass {
	
 
	@Test
	public void TCLN_ContextPassing4() throws Exception {
		getDriver().get("https://www.google.com/");
		WebElement e = getDriver().findElement(By.name("q"));
		e.click();
//		click("asdfghjk", e);
		screenshot("TCLN_ContextPassing22");
//		screenshot("TCLN_ContextPassing22");
//		log().info("TCLN_ContextPassing2");
//		ArtefactBuilder.addSubHeader("Sub Header 1");
//		ArtefactBuilder.artefactSS("Artefact Statement 1", null);
//		ArtefactBuilder.artefactSS("Artefact Statement 2", e);
//		ArtefactBuilder.artefactSS("Artefact Statement 3", e);
//		ArtefactBuilder.artefactSS("Artefact Statement 4", e);
//		ArtefactBuilder.addSubHeader("Sub Header 2");
//		ArtefactBuilder.artefactSS("sdfawdawdghj", e);
//		ArtefactBuilder.addInfoNotes("Starting ChromeDriver 133.0.6943.141 (2a5d6da0d6165d7b107502095a937fe7704fcef6-refs/branch-heads/6943@{#1912}) on port 30547\r\n"
//				+ "====== Local execution =====");
//		ArtefactBuilder.artefactSS("Artefact Statement 5", e);
//		ArtefactBuilder.artefactSS("Artefact Statement 6", e);
//		ArtefactBuilder.addInfoNotes("After step no 6 in the document, we need to check that there is an active Promotion for the current Year. If the To date is not the Current year end date and if the status is not 20, then make sure to activate the Promotion by following the steps below. \r\n"
//				+ "1. Select the Promotion -> Go to Options -> Click Change\r\n"
//				+ "2. Enter Valid to Date as ‘Current year end date’\r\n"
//				+ "3. Enter Act End Date as ‘Current year end date’\r\n"
//				+ "4. Select the Status as ’20-Activated’\r\n"
//				+ "5. Click Next\r\n"
//				+ "6. Click Previous twice\r\n"
//				+ "");
//		ArtefactBuilder.addInfoNotes("At step 15: Verify the View MMS200 should be D12-OIS101.\r\n"
//				+ "If it is empty, please follow the below steps:\r\n"
//				+ "1.	Click on search lookup\r\n"
//				+ "2.	Enter view in the column and press enter\r\n"
//				+ "3.	Select the record\r\n"
//				+ "4.	Click on select");
//		ArtefactBuilder.addInfoNotes("Hi, Hello, How are you!");
//		ArtefactBuilder.addInfoNotes("Hi, Hello, How are you!");
//		ArtefactBuilder.addInfoNotes("Hi, Hello, How are you!");
//		ArtefactBuilder.addInfoNotes(
//				"After step no 6 in the document, we need to check that there is an active Promotion for the current Year. If the To date is not the Current year end date and if the status is not 20, then make sure to activate the Promotion by following the steps below. ");
//		ArtefactBuilder.addInfoNotes("INFO : After Step 30: we need to verify the stop code for the created order. If the stop code is not 9, we must change it to 9 by following these steps:\r\n"
//				+ "\r\n"
//				+ "1. Select the record.\r\n"
//				+ "2. Navigate to 'Related' -> Click on 'Stop CO'.\r\n"
//				+ "3. From the 'CO stop' dropdown, select '9-Man rel order'.\r\n"
//				+ "4. Click Next.");
//		ArtefactBuilder.addInfoNotes("At step 48 & 58 we need to enter the quantity based on the order quantity that was previously recorded in the predecessor document DI.05-7 when creating the order.\r\n"
//				+ "\r\n"
//				+ "For example, if in DI.05-7, we created an order with a quantity of 20, and in IP.05-3 we are splitting this into two package lines, we will divide the total quantity (20) by the number of packages (2), resulting in 10 for each package line.\r\n"
//				+ "\r\n"
//				+ "Therefore, for each package, we should enter a quantity of 10.");
//		ArtefactBuilder.addInfoNotes("Hi, Hello, How are you!");
//		ArtefactBuilder.artefactSS("Artefact Statement 8", e);
//		ArtefactBuilder.addInfoNotes("_Note: Starting ChromeDriver 133.0.6943.141 (2a5d6da0d6165d7b107502095a937fe7704fcef6-refs/branch-heads/6943@{#1912}) on port 30547\r\n"
//				+ "Only local connections are allowed.\r\n"
//				+ "[INFO ] 2025-03-05 11:06:36.009 - chrome browser is launched"
//				+ "%%%%%%%%%%%% Download directory is set to Local\r\n"
//				+ "====== Local execution =====");
//		ArtefactBuilder.artefactSS("Artefact Statement 9", e);
//		ArtefactBuilder.addSubHeader("Sub Header 3");
//		ArtefactBuilder.artefactSS("Artefact Statement 10", e);
//		ArtefactBuilder.artefactSS("Artefact Statement 11", e);
//		ArtefactBuilder.artefactSS("Artefact Statement 12", e);
//		ArtefactBuilder.artefactSS("Artefact Statement 13", e);
//		ArtefactBuilder.artefactSS("Artefact Statement 14", e);
//		ArtefactBuilder.addSubHeader("Sub Header 4");
		log().info("TCLN_ContextPassing2aaa");
	}
}
 