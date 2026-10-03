package functions;

import org.openqa.selenium.StaleElementReferenceException;

import pages.Homepages;
import pages.PurchaseOrderPage;
import testBase.BaseClass;

public class LNSessionTabActions extends BaseClass
{
	//To close the Tab in LN
		public static void closeTab(String tabName)
		{
			Homepages homePg = initElements(Homepages.class);
			int MAX_TRY=10;
			int i=0;
			while (i<MAX_TRY) {
				try {
					 // if pause is not given,click tab action is not performed
			        pause(2);
					actions().contextClick(getDynamicElement(homePg.currentTab, tabName)).build().perform();
					forceClick(homePg.closeTab);
					i=MAX_TRY;
				} catch (StaleElementReferenceException e) {
					pause(1);
					i++;
					System.out.println("=========== Stale Element Reference Exception ========");
				}
			}
		}

		public static void closeAllTabs(String tabName){
			Homepages homePg = initElements(Homepages.class);
			int MAX_TRY=10;
			int i=0;
			try {
				pause(2);
				actions().contextClick(getDynamicElement(homePg.closeSessionTab, tabName)).build().perform();
				forceClick(homePg.closeAllSessions);
				i = MAX_TRY;
			} catch (StaleElementReferenceException e) {
				pause(1);
				i++;
				log().info("=========== Stale Element Reference Exception ========");
			}
		}
}
