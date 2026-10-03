package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import annotations.IFrame;
import annotations.IFrames;
import pageFactory.FrameType;
import testBase.BasePageObject;

public class TestPage extends BasePageObject<TestPage> {

	@FindBy(how = How.NAME, using = "q")
	public WebElement identifier;
	
	@FindBy(how = How.NAME, using = "%s")
	public WebElement googleInput;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'LN_')]", frameType = FrameType.IFRAME, attributes = {
			"class=m-app-frame", "name=LN_4bf34403-4d45-419d-868c-988e5cffe2b5",
			"src=https://your-environment-url.example.com",
			"onload=onAppFrameLoad(this)", "onerror=onAppFrameError(e);",
			"allow=geolocation; microphone; camera", }), })
	@FindBy(how = How.ID, using = "icon-menu")
	public WebElement menuIcon;

	@IFrames({ @IFrame(xpath = "//iframe[contains(@name,'LN_')]", frameType = FrameType.IFRAME, attributes = {
			"class=m-app-frame", "name=LN_4bf34403-4d45-419d-868c-988e5cffe2b5",
			"src=https://your-environment-url.example.com",
			"onload=onAppFrameLoad(this)", "onerror=onAppFrameError(e);",
			"allow=geolocation; microphone; camera", }), })
	@FindBy(how = How.XPATH, using = "//span[text()='%s']/..")
	public WebElement menuIcon1;

//	@IFrames({
//        @IFrame(xpath="//iframe[contains(@name,'LN_')]" , frameType=FrameType.IFRAME  , attributes={"class=m-app-frame", "name=LN_4bf34403-4d45-419d-868c-988e5cffe2b5", "src=https://your-environment-url.example.com", "onload=onAppFrameLoad(this)", "onerror=onAppFrameError(e);", "allow=geolocation; microphone; camera", }),
//    })
	@FindBy(how = How.XPATH, using = "//span[text()='Next']/..")
	public WebElement menuIcon2;

}
