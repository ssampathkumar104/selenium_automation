package desktop.outlook.screens;

import pageFactory.desktop.FindBy;
import pageFactory.desktop.FindByImageResourceLocation;
import testBase.BaseDesktopPage;
import testBase.SikuliElement;

@FindByImageResourceLocation("\\src\\main\\java\\desktop\\outlook\\screens\\images\\")
public class HomepagesPage extends BaseDesktopPage<HomepagesPage> {

	@FindBy(image = "appMenu.png", similarity = 90)
	public SikuliElement appMenu;

	@FindBy(image = "inforLN.png", similarity = 40)
	public SikuliElement inforLN;

}