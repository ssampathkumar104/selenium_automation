package functions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import contexts.LNPurchaseOrderContext;
import contexts.WMSAdjustmentOrderContext;
import pages.Homepages;
import pages.WMSHomepage;
import testBase.BaseClass;

public class WMSFunctions extends BaseClass{

	public static void navigateToFacility(LNPurchaseOrderContext purchaseOrderContext) throws Exception 
	{
		WMSHomepage homePg = initElements(WMSHomepage.class);
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.facility));
		homePg.facility.click();
		await(100).until(ExpectedConditions.visibilityOf(getDynamicElement(homePg.warehouse, purchaseOrderContext.warehouse)));
		getDynamicElement(homePg.warehouse, purchaseOrderContext.warehouse).click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.wmsMenu));
		System.out.println("=========>>>>> Navigated to WMS Facility Successfully <<<<<=========");
	}
	
	public static void verifyPO(LNPurchaseOrderContext purchaseOrderContext) throws Exception
	{
		WMSHomepage homePg = initElements(WMSHomepage.class);
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.wmsMenu));
		homePg.wmsMenu.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.inboundMenu));
		homePg.inboundMenu.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.purchaseOrder));
		homePg.purchaseOrder.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.purchaseOrderFilter));
		homePg.purchaseOrderFilter.sendKeys(purchaseOrderContext.returnPurchaseOrder,Keys.TAB);
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.filterIcon));
		homePg.filterIcon.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.OrderNumber));
		String returnPONumber = homePg.OrderNumber.getText();
		Assert.assertEquals(returnPONumber,purchaseOrderContext.returnPurchaseOrder);
		log().info("Purchase Order: " + returnPONumber + " verified Successfully");
		screenshot("Purchase Order: " + returnPONumber + " verified Successfully");
	}
	
	/*--------------------------------------------------------------------------------------
	 * Purpose: Create Inventory Adjustment in WMS
	 *-------------------------------------------------------------------------------------*/
	public static void createAdjustment(WMSAdjustmentOrderContext orderContext) throws Exception
	{
		WMSHomepage homePg = initElements(WMSHomepage.class);
		
		//Navigation to Adjustments
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.wmsMenu));
		homePg.wmsMenu.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.executionMenu));
		homePg.executionMenu.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.inventoryMenu));
		homePg.inventoryMenu.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.adjustments));
		homePg.adjustments.click();
		
		//Create Order
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.newButton));
		homePg.newButton.click();
		await(100).until(ExpectedConditions.elementToBeClickable(homePg.adjustments));
		
		homePg.purchaseOrderFilter.sendKeys(orderContext.getReturnPurchaseOrder(),Keys.TAB);
		pause(2);
		homePg.filterIcon.click();
		pause(5);
		String returnPONumber = homePg.OrderNumber.getText();
		Assert.assertEquals(returnPONumber,orderContext.getReturnPurchaseOrder());
		pause(5);
		System.out.println("=========>>>>> Adjustment Order: "+returnPONumber+" verified Successfully <<<<<=========");
	}
}