package desktop.outlook.screens;

import pageFactory.desktop.FindBy;
import pageFactory.desktop.FindByImageResourceLocation;
import testBase.BaseDesktopPage;
import testBase.SikuliElement;

@FindByImageResourceLocation("src\\main\\java\\desktop\\outlook\\screens\\images\\")
public class LocallyPage extends BaseDesktopPage<LocallyPage>{

	@FindBy(image="outlook.png", similarity = 90)
	public SikuliElement outlook;

	@FindBy(image="inbox.png", similarity = 90)
	public SikuliElement inbox;
	
	@FindBy(image="start.png", similarity = 90)
	public SikuliElement start;
	
	@FindBy(image="startIcon_1.png", similarity = 90)
	public SikuliElement startIcon_1;
	
	@FindBy(image="searchMail.png", similarity = 90)
	public SikuliElement searchMail;

}