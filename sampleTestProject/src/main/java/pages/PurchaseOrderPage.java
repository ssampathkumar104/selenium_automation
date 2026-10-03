

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import annotations.IFrame;
import annotations.IFrames;
import pageFactory.FrameType;

public class PurchaseOrderPage extends LNCustomActions {

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[text()='Purchase Order'][contains(@id,'session')]")
	public WebElement lnPOSessionTab;
	
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.ID, using = "tdpur4100m900-button-std-file.new")
	public WebElement lnPONew;
	
	/* lnPOBusinessPartner -- TriggerInputField */
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "//input[@id='tdpur4100m900-tdpur400.otbp-n1-lookup-widget']")
	public WebElement lnPOBusinessPartner;
	
	/* lnPOBusinessPartnerAddress -- TriggerInputField */
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "//input[@id='tdpur4100m900-tdpur400.otad-n3-lookup-widget']")
	public WebElement lnPOBusinessPartnerAddress;
	
	/* lnPOOrderType -- TriggerInputField */
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "//input[@id='tdpur4100m900-tdpur400.cotp-n11-lookup-widget']")
	public WebElement lnPOOrderType;
	
	/* lnPOOffice -- TriggerInputField */
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "//input[@id='tdpur4100m900-tdpur400.cofc-n13-lookup-widget']")
	public WebElement lnPOOffice;
	
	/* lnPOSeries -- TriggerInputField */
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "//input[@id='tdpur4100m900-tdpur400.orno-n15-lookup-widget']")
	public WebElement lnPOSeries;
	
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4100m900-button-std-file.save']")
	public WebElement lnPOSave;
	
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "(//input[contains(@id, 'tdpur4100m900') and contains(@id, 'tdpur400.orno') and contains(@id, 'lookup-widget')])[last()-1]")
	public WebElement lnPONumber;
	
	@IFrames({
		@IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={}),
	})
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4100m900-button-std-file.save_and_close']")
	public WebElement lnPOSaveAndClose;
	
	@IFrames({
			@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'tdpur4101m000') and contains(@id,'button-std-file.new')]")
	public WebElement lnPONewOrderLine;

	/* linePosition -- DataCellElement */
	@IFrames({
			@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@id,'tdpur4101m000')][contains(@id,'tdpur401.pono')][contains(@class,'DataCell')])")
	public WebElement lnPOLinePosition;

	/* lineItem -- DataCellElement */
	@IFrames({
			@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@id,'tdpur4101m000')][contains(@id,'tdpur401.item.segment.2')][contains(@class,'DataCell')])")
	public WebElement lnPOItem;

	/* lineQuantity -- DataCellElement */
	@IFrames({
			@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@id,'tdpur4101m000')][contains(@id,'tdpur401.qoor')][contains(@class,'DataCell')])")
	public WebElement lnPOQuantity;

	/* lnPOPrice -- DataCellElement */
	@IFrames({
			@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@id,'tdpur4101m000')][contains(@id,'grid-n1-tdpur401.pric-')][contains(@class,'DataCell')])")
	public WebElement lnPOPrice;

	/* warehouse -- DataCellElement */
	@IFrames({
			@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@id,'tdpur4101m000')][contains(@id,'tdpur401.cwar')][contains(@class,'DataCell')])")
	public WebElement lnPOWarehouse;

	@IFrames({
			@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'tdpur4101m000')][contains(@id,'tdpur401.cwar')][contains(@id,'filter')]")
	public WebElement lnPOWarehouseFilter;
	
	/* popupOK - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'DialogWindow')]//label[text()='OK']")
	public WebElement popupOK;
	
	@IFrames({
		@IFrame(name = "LN_47fa453f-16c1-478f-86be-5d67f5b6fdf3", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	public By aa = By.xpath("//div[contains(@class,'DialogWindow')]//label[text()='OK']");
	
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'ColumnHeader')][not(contains(@class,'Row'))])[last()]")
	public WebElement columnCellsLast;
	
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'ColumnHeader')][not(contains(@class,'Row'))])[first()]")
	public WebElement columnCellsFirst;
	
	
	/* lnPOApprove -- WebElement */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'button-form-approve.order')][contains(@id,'tdpur4100m900')]")
	public WebElement lnPOApprove;
	
	/* moreButton -- WebElement */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'overflowButton')][contains(@id,'left')][contains(@id,'tdpur4100m900')]")
	public WebElement lnPOHeaderMoreButton;
	
	/* lnPOStatus -- WebElement */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[@id='tdpur4100m900-tdpur400.hdst-n19-label']")
	public WebElement lnPOStatus;
	
	/* loading - Button */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Loading')] ")
	public WebElement loading;
	
	/* lnPOSelectLine - CheckBox */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4101m000-grid-n1-select-n%s']")
	public WebElement lnPOSelectLine;
	
	/* lnPOOrderLineReferences - Button */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4101m000-button-std:reference'] ")
	public WebElement lnPOOrderLineReferences;

	/* lineStatus - Link */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[contains(@id,'tdpur4101m000')][contains(text(),'tatus')]")
	public WebElement lnPOlineStatus;
	
	/* lnPOExecute - Button */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4534m000-execute.next.step']")
	public WebElement lnPOExecute;
	
	/* lnPOPrint - Button */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4401m000-button-std-file.print']")
	public WebElement lnPOPrint;
	
	/* closePrintWindow -- Button */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[text()='Close']")
	public WebElement closePrintWindow;
	
	/* lnPOLineStatusSaveAndClose - Button */
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4534m000-button-std-file.save_and_close']")
	public WebElement lnPOLineStatusSaveAndClose;
	
	
	

	
	
	
	
	
	
}
