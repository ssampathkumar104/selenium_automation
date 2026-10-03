package functions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import constants.COMMONS;
// import constants.LNSESSIONCODES;
// import constants.LNSESSIONTABS;
import contexts.LNPurchaseOrderContext;
import pages.Homepages;
import pages.PurchaseOrderPage;
import testBase.BaseClass;

public class LNPurchaseOrder extends BaseClass{
	
	public static void createAndProcessTillRelease(LNPurchaseOrderContext purchaseOrderContext) throws Exception
	{
		screenshot("Switched to LN");
		// LNCommon.runProgram(LNSESSIONCODES.PURCHASE_ORDER);
		PurchaseOrderPage poPg = initElements(PurchaseOrderPage.class);
		pause(5);
		LNCommon.handlePopUp();
		LNCommon.handleScrollBar();
		pause(2);
		screenshot("Switched to Purchase Order Session");
		await(100).until(ExpectedConditions.elementToBeClickable(poPg.lnPONew));
		poPg.lnPONew.click();
		poPg.TriggerInputField(poPg.lnPOBusinessPartner, purchaseOrderContext.businessPartner);
		poPg.TriggerInputField(poPg.lnPOBusinessPartnerAddress, purchaseOrderContext.businessPartnerAddress);
		poPg.TriggerInputField(poPg.lnPOOrderType, purchaseOrderContext.purchaseOrderType);
		poPg.TriggerInputField(poPg.lnPOOffice, purchaseOrderContext.purchaseOffice);
		poPg.TriggerInputField(poPg.lnPOSeries, purchaseOrderContext.purchaseSeries);
		pause(2);
		poPg.lnPOSave.click();
		pause(15);
		String returnPONumber = poPg.lnPONumber.getAttribute("value");
		purchaseOrderContext.returnPurchaseOrder =poPg.lnPONumber.getAttribute("value");
		log().info("Created Purchase Order Number is "+purchaseOrderContext.returnPurchaseOrder);
		Assert.assertTrue(!returnPONumber.isEmpty());
		pause(5);
		log().info("=========>>>>> Purchase Order Header created Successfully <<<<<=========");
		pause(4);
		
		/*
		 * PO Orderline creation
		 */
		LNCommon.handleScrollBar();
		poPg.lnPONewOrderLine.click();
		actions().moveToElement(poPg.columnCellsLast).build().perform();
		poPg.DataCellElement(poPg.lnPOLinePosition, 0, purchaseOrderContext.position);
		poPg.DataCellElement(poPg.lnPOItem, 0, purchaseOrderContext.item);
		LNCommon.handlePopUp();
		poPg.DataCellElement(poPg.lnPOQuantity, 0, purchaseOrderContext.quantity);
		poPg.DataCellElement(poPg.lnPOPrice, 0, purchaseOrderContext.price);
		pause(2);
		poPg.lnPOSave.click();
		screenshot("Purchase Order"+purchaseOrderContext.returnPurchaseOrder);
				
		/*
		 * Approve Purchase Order
		 */
		if(isElementPresent(poPg.lnPOApprove)==false)
		{
			poPg.lnPOHeaderMoreButton.click();
		}
		pause(2);
		poPg.lnPOApprove.click();
		pause(5);
		String status = poPg.lnPOStatus.getText();
		log().info("Purchase order status is " + status);
		screenshot("Purchase order status is " + status);
		Assert.assertEquals(status, COMMONS.APPROVED, "Purchase Order is not approved");
		
		/*
		 * Print & Release Purchase Order
		 */
		getDynamicElement(poPg.lnPOSelectLine, "0").click();
		poPg.lnPOOrderLineReferences.click();
		pause(2);
		poPg.lnPOlineStatus.click();
		pause(3);
		forceClick(poPg.lnPOExecute);
		poPg.lnPOPrint.click();
		LNCommon.handleDevice();
		// LNSessionTabActions.closeTab(LNSESSIONTABS.PRINT_PURCHASE_ORDER);
		pause(2);
		poPg.closePrintWindow.click();
		pause(1);
		poPg.lnPOExecute.click();
		poPg.lnPOLineStatusSaveAndClose.click();
		pause(3);
		String InprocessStatus = poPg.lnPOStatus.getText();
		Assert.assertEquals(InprocessStatus, COMMONS.INPROCESS, "Purchase Order is not In-Process");
		screenshot("Purchase order status is " +InprocessStatus);
		log().info("Purchase order status is " +InprocessStatus);
		poPg.lnPOSaveAndClose.click();
	}
}