package commons;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static testBase.TestData.getPropertyFile;

import org.openqa.selenium.support.ui.ExpectedConditions;

import contexts.CSLoginContext;
import dataUtils.PropertiesDataFile;
import pages.Homepages;
import testBase.BaseClass;
//import testBase.documetation.CaptureScreenshot;

public class Cloudsuite extends BaseClass {
	
	
	
	public static CSLoginContext getCSLoginDetailsContext(String propertyFile) {
		CSLoginContext loginContext = new CSLoginContext();
		if (isNotEmpty(getParameter("BASE_URL")) && isNotEmpty(getParameter("USER_NAME")) && isNotEmpty(getParameter("PASSWORD"))) {
			loginContext.url = getParameter("BASE_URL");
			loginContext.username = getParameter("USER_NAME");
			loginContext.password = getParameter("PASSWORD");
			log().info("INFO : Credentials are fetch from Test Plan ");
		} else {
			PropertiesDataFile data = getPropertyFile(propertyFile);
			loginContext.url = data.get("url");
			loginContext.username = data.get("userName");
			loginContext.password = data.get("password");
			log().info("INFO : Credentials are fetched from property file");
			
		}
		return loginContext;
	}

	public static void login(CSLoginContext loginContext) {
		Homepages homepage = initElements(Homepages.class);
		getDriver().get(loginContext.url);
		homepage.username.sendKeys(loginContext.username);
		homepage.password.sendKeys(loginContext.password);
		screenshot("Entered U and P");
//		CaptureScreenshot.screenShot("Entered U and P");
		homepage.submit.click();
		log().info("=========>>>>> Logged into Cloud Suite Successfully <<<<<=========");
	}
	
	public static void logOut() {
		Homepages homepage = initElements(Homepages.class);
		getDriver().switchTo().defaultContent();
		homepage.userIcon.click();
		forceClick(homepage.signOut);
		pause(15);
		log().info("=========>>>>> Signed Out from Cloud Suite Successfully <<<<<=========");
	}
	
	public static void navigateToApplication(String appName) {
		Homepages homepage = initElements(Homepages.class);
		await(10).until(ExpectedConditions.elementToBeClickable(homepage.appMenu)).click();
		forceClick(getDynamicElement(homepage.appName, appName));
	}

}
