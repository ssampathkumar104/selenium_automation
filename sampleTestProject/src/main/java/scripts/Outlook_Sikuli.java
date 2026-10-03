package scripts;

import org.testng.annotations.Test;

import desktop.outlook.screens.OutlookPage;
import testBase.BaseClass;

public class Outlook_Sikuli extends BaseClass {

	@Test
	public void sample1() throws Exception {
		screenshot("Test 1");
		OutlookPage ss = new OutlookPage();
		ss.outlook.click();
		pause(3);
		screenshot("Test 2");
		getDriver().get("https://google.com");
		screenshot("Test 3");
	}

}
