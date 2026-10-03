package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import annotations.IFrame;
import annotations.IFrames;
import pageFactory.FrameType;

public class Homepages extends LNCustomActions {

	@FindBy(how = How.NAME, using = "username")
	public WebElement username;

	@FindBy(how = How.NAME, using = "pass")
	public WebElement password;
	
	@FindBy(how = How.NAME, using = "%s")
	public WebElement qq;
	
	@FindBy(how = How.NAME, using = "%s")
	public WebElement q;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Down')]//child::span[%s]")
	public WebElement txtDown;

	
	@FindBy(how = How.XPATH, using = "//*[@id='submitButton' or @id='submit' or contains(@title,'Sign On') or @type='submit']")
	public WebElement submit;

	@FindBy(how = How.XPATH, using = "//button[@id='rNavUsrBtn']")
	public WebElement userIcon;

	@FindBy(how = How.XPATH, using = "//a[@id='usrSignOut']")
	public WebElement signOut;

	@FindBy(how = How.XPATH, using = "//*[@id='mhdrAppBtn']")
	public WebElement appMenu;

	@FindBy(how = How.XPATH, using = "//a[@title='%s']")
	public WebElement appName;
	
	@FindBy(how = How.XPATH, using = "//a[@title='LN']")
	public WebElement ln;

	/* popupOK - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'DialogWindow')]//label[text()='OK']")
	public WebElement popupOK;
	
	/* menuIcon - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//label[text()='Yes'])[last()]")
	public WebElement YesButton;
	
	/* menuIcon - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[text()='No']")
	public WebElement NoButton;

	/* popupText - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'DialogWindow')]//label/li")
	public WebElement popupText;

	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'ColumnHeader')][not(contains(@class,'Row'))])[last()]")
	public WebElement columnCells;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='sysmesdialog-button-n0']") 
//	@FindBy(how = How.XPATH, using = "//label[text()='OK']")
//	@FindBy(how = How.CLASS_NAME, using = "DialogWindowFooter WindowFooter")sysmesdialog-button-n0

	public WebElement lnSystemMessage;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='button-menu']")
	public WebElement lnMenu;

	@FindBy(how = How.XPATH, using = "//button[@id='rNavECT']/parent::li")
	public WebElement lnContextMenu;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='side-navigation']/parent::div")
	public WebElement lnSideNavigate;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[@id='node-options-label']")
	public WebElement lnOptions;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[@id='node-run_program-label']")
	public WebElement lnRunprogram;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//input[@id='dlg-run_program-input-control-widget-label']")
	public WebElement lnRunprogramInput;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//input[@id='dlg-run_program-input-panel-content-searchField-widget']")
	public WebElement lnSessionInput;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='dlg-run_program-button-n0']")
	public WebElement lnRunprogramOK;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[text()='Purchase Order'][contains(@id,'session')]")
	public WebElement lnPOSessionTab;

	/**
	 * Purchase Order
	 */

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@id='tdpur4100m900-button-std-file.new']")
	public WebElement lnPONew;

	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//input[@id='tdpur4100m900-tdpur400.otbp-1-lookup-widget']")
	public WebElement lnPOBusinessPartner;

	/* scrollBarParent - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'gwt-SplitLayoutPanel-VDragger') or contains(@class,'splitter')]/parent::div")
	public WebElement scrollBarParent;
	
	/* menuIcon - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@aria-hidden='false']/div[@class='DetailXForm']//input[contains(@id,'ttstpsplopen')][contains(@id,'lookup-widget')][contains(@id,'devc')]")
	public WebElement device;

	/* menuIcon - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//div[@aria-hidden='false']/div[@class='DetailXForm']//input[contains(@id,'ttstpsplopen')][contains(@id,'lookup-widget')][contains(@id,'devc')]")
	public WebElement deviceField;

	/* menuIcon - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[contains(@id,'ttstpsplopen') and contains(@id,'pages-bar-page') and text()='Display']")
	public WebElement display;

	/* menuIcon - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'homepages_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[contains(@id,'ttstpsplopen') and contains(@id,'pages-bar-page') and text()='Printer']")
	public WebElement printer;

	/* selectDevice - TriggerInputField */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'homepages_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "(//input[contains(@id,'ttstpsplopen') and contains(@id,'-devc') and contains(@id,'lookup')])[last()-1]")
	public WebElement selectDevice;

	/* continueIcon - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[contains(@id,'ttstpsplopen') and contains(@id,'-form-exec.cont.process-label') and text()='Continue']")
	public WebElement continueIcon;
	
	/* closeProcess - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[@class='Label' and text()='Close'][contains(@id,'tsspc2201m000')]")
	public WebElement closeProcess;
	
	@IFrames({
		@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[contains(@id,'session') and text()='%s']")
	public WebElement currentTab;
	
	/* closeTab - Button */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Close session')]")
	public WebElement closeTab;
	
	/* closeSessionTab - DynamicLink */
	@FindBy(how = How.XPATH, using = "//label[contains(@id,'session') and text()='%s']")
	public WebElement closeSessionTab;
	
	/* closeAllSessions - Text */
	@IFrames({
			@IFrame(name = "", frameType = FrameType.IFRAME, xpath = "//iframe[contains(@name,'LN_')]", attributes = {}), })
	@FindBy(how = How.XPATH, using = "//label[text()='Close all sessions']")
	public WebElement closeAllSessions;



}
