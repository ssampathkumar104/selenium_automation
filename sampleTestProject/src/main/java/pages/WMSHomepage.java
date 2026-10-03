package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import annotations.IFrame;
import annotations.IFrames;
import pageFactory.FrameType;

public class WMSHomepage extends LNCustomActions {
	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Facility')]//following::td[1]")
	public WebElement facility;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'%s')])[last()]")
	public WebElement warehouse;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//td/span[text()='WMS']")
	public WebElement wmsMenu;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//td/div[text()='Inbound']")
	public WebElement inboundMenu;
	
	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//td/div[text()='Execution'])[1]")
	public WebElement executionMenu;
			
	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//td/div[text()='Inventory']")
	public WebElement inventoryMenu;
	
	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//td/div/ul[text()='Adjustments']")
	public WebElement adjustments;
	
	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@accesskey='N']")
	public WebElement newButton;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//td/div/ul[text()='Purchase Order']")
	public WebElement purchaseOrder;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//input[@id='Inatifq']")
	public WebElement purchaseOrderFilter;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//tr[@class='listfilter']/td[1]/input")
	public WebElement filterIcon;
	
	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'sce_')]", frameType = FrameType.IFRAME, attributes = {}), })
	@FindBy(how = How.XPATH, using = "//td/span[contains(@id,'_cell_0_1_span')]")
	public WebElement OrderNumber;

}