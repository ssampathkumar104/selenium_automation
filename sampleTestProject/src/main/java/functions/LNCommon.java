package functions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import constants.COMMONS;
import pages.Homepages;
import pages.PurchaseOrderPage;
import testBase.BaseClass;

/**
 * Purpose: This class contains reusable methods for LN UI
 *
 **/
public class LNCommon extends BaseClass{
	
	/**----------------------------------------------------------------------------------
	 * Purpose		: This Method navigates to LN Screens through Session ID
	 * Variables	: Session ID
	 *----------------------------------------------------------------------------------
	 * @throws Exception */
	public static void runProgram(String sessionID) throws Exception
	{
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Homepages homepage = initElements(Homepages.class);
		pause(5);
//		await(500).until(ExpectedConditions.elementToBeClickable(homepage.lnSystemMessage));
		if(isElementPresent(homepage.lnSystemMessage)==true)
		{
			homepage.lnSystemMessage.click();
		}
		await(2);
		if(homepage.lnSideNavigate.getAttribute("style").contains("width: 0px"))
		{
			System.out.println("INFO : ========>>>>> LN Menu is Closed <<<<<=========");
			homepage.lnMenu.click();
			System.out.println("INFO : ========>>>>> LN Menu is Opened <<<<<=========");
		}else
		{
			System.out.println("INFO : ========>>>>> LN Menu is Opened <<<<<=========");
		}
		if(homepage.lnContextMenu.getAttribute("class").contains("expanded"))
		{
			System.out.println("INFO : ========>>>>> InContext pane is Opened <<<<<=========");
			homepage.lnContextMenu.click();
			System.out.println("INFO : ========>>>>> InContext pane is Closed <<<<<=========");
		}else
		{
			System.out.println("INFO : ========>>>>> InContext pane is Closed <<<<<=========");
		}
		await(2);
		homepage.lnOptions.click();
		Thread.sleep(2000);
		homepage.lnRunprogram.click();
		await(3).until(ExpectedConditions.elementToBeClickable(homepage.lnRunprogramInput));
		forceType(homepage.lnRunprogramInput, sessionID);
		getDriver().switchTo().activeElement().sendKeys(Keys.TAB);
		homepage.lnRunprogramOK.click();
		System.out.println("INFO : ========>>>>> Navigated to "+sessionID+" Session <<<<<=========");
	}
	
	public static void handleScrollBar()
	{
		Homepages homePg =  initElements(Homepages.class);
		System.out.println("INFO : ========>>>>> style attirbute is"+homePg.scrollBarParent.getAttribute("style")+" <<<<<=========");
		String styleTop = homePg.scrollBarParent.getAttribute("style").subSequence(54,57).toString().replaceAll("[^0-9]","");
		System.out.println("INFO : ========>>>>> style top value is"+styleTop+" <<<<<=========");
		int styleTopInt = Integer.parseInt(styleTop);
		if(styleTopInt>150)
				{
					int up = styleTopInt-150;
					System.out.println("INFO : ========>>>>> Move the ScrollBar to Top By a value of "+up+" <<<<<=========");
					actions().dragAndDropBy(homePg.scrollBarParent, 0, -up).build().perform();
					String adjustedLocation = homePg.scrollBarParent.getAttribute("style").subSequence(54,59).toString();
					System.out.println("INFO : ========>>>>> ScrollBar location after handle is "+adjustedLocation+" <<<<<========="); 
				} else if(styleTopInt<150)
				{
					int down = 150-styleTopInt;
					System.out.println("INFO : ========>>>>> Move the ScrollBar to Down By a value of "+down+" <<<<<=========");
					actions().dragAndDropBy(homePg.scrollBarParent, 0,down).build().perform();
					String adjustedLocation = homePg.scrollBarParent.getAttribute("style").subSequence(54,59).toString();
					System.out.println("INFO : ========>>>>> ScrollBar location after handle is "+adjustedLocation+" <<<<<========="); 
				}
	}
	
	/*----------------------------------------------- 
	 * Objective 	: Handles the device window
	 *-----------------------------------------------*/
	public static void handleDevice()
	{
		Homepages homePg = initElements(Homepages.class);
		if(isElementPresent(homePg.deviceField)==true)
		{
			homePg.display.click();
			waitForJQueryAndJSToLoad(100);
			pause(1);
			homePg.TriggerInputField(homePg.device, COMMONS.DEVICE_D);
			waitForJQueryAndJSToLoad(100);
			homePg.continueIcon.click();
			waitForJQueryAndJSToLoad(100);
			handlePopUp();
			if(isElementPresent(homePg.closeProcess))
			{
				Homepages homePg2 = new Homepages();
				homePg2.closeProcess.click();
				waitForJQueryAndJSToLoad(100);
			}
			pause(1);
		}
	}
	
	public static void handlePopUp()
	{
		PurchaseOrderPage poPg =  initElements(PurchaseOrderPage.class);
		if(isElementPresent(poPg.popupOK)==true)
		{
			poPg.popupOK.click();
		}
	}
	
	
}
