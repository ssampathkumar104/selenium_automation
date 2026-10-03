package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import annotations.IFrame;
import annotations.IFrames;
import pageFactory.FrameType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Homepages2 {
	WebDriver driver;
	
	@FindBy(xpath = "//h1")
	WebElement Header;
	
	@FindBy(xpath = "//*[@id='signupModalButton']")
	WebElement getStarted;
	
	@FindBy(how = How.XPATH, using = "//div[contains(.,'Down')]//child::span[%s]")
	public WebElement txtDown;


	public Homepages2(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
}