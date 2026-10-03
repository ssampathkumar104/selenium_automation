package commons;

import pages.Homepages;
import testBase.BaseClass;
/**
 * This class contains methods related to AppMenu
 **/
public class AppMenu extends BaseClass{
	
	/**
	 * This method Navigates to required Application
	 **/
	/*
	 * This method need to updated as this should be reusable for all Elements
	 */
	public static void navigateToApplication(String AppName) throws Exception
	{
		Homepages homePg = initElements(Homepages.class);
		pause(2);
		homePg.appMenu.click();
		pause(2);
		forceClick(getDynamicElement(homePg.appName, AppName));
		pause(2);
		System.out.println("INFO : Switched to "+AppName+" application ");
	}
	
	public static void currentUrlIssue()
	{
		String urlIs = getDriver().getCurrentUrl();
		System.out.println("Current url is "+urlIs);
	}

}
