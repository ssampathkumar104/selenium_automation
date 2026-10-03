package desktop.outlook.screens;

import pageFactory.desktop.FindBy;
import pageFactory.desktop.FindByImageResourceLocation;
import testBase.BaseDesktopPage;
import testBase.SikuliElement;

@FindByImageResourceLocation("src\\main\\java\\desktop\\outlook\\screens\\images\\")
public class LNPage extends BaseDesktopPage<LNPage> {

	@FindBy(image = "runprogram.png", similarity = 70)
	public SikuliElement runprogram;
	
	@FindBy(image = "open.png", similarity = 70, x = -84, y = 5)
	public SikuliElement open;

	@FindBy(image = "sessionID.png", similarity = 70, x = -170, y = 1)
	public SikuliElement sessionID;

	@FindBy(image = "newIcon.png", similarity = 90)
	public SikuliElement newIcon;

	@FindBy(image = "ok.png")
	public SikuliElement ok;

	@FindBy(image = "options.png", similarity = 90, x = 94, y = -1)
	public SikuliElement options;
	
	@FindBy(image = "orderFilter.png", similarity = 99, x = -36, y = 11)
	public SikuliElement orderFilter;
	
	@FindBy(image = "drilldown.png", similarity = 90, x = 12, y = 3)
	public SikuliElement drilldown;
	
	@FindBy(image = "orderID.png", similarity = 70, x = -44, y = -1)
	public SikuliElement orderID;
	
	@FindBy(image = "status.png", similarity = 70, x = 8, y = 3)
	public SikuliElement status;

}