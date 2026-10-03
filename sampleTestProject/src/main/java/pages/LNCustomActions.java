package pages;

import static testBase.BaseClass.await;
import static testBase.BaseClass.getXpathString;
import static testBase.BaseClass.pause;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testBase.ArtefactBuilder;
import testBase.BasePageObject;

/**--------------------------------------------------------------------------------------------------------------
 * Author		: 
 * Purpose		: This Class contains custom actions 
 *--------------------------------------------------------------------------------------------------------------*/
public class LNCustomActions extends BasePageObject<TestPage> {
	
	/**
     * The method is used to perform custom actions on LN Table fields Input fields
     **/
	public void DataCellElement(WebElement we, int position, String data) {
		ArtefactBuilder.setCustAct(false);
		
		System.out.println("INFO : DataCell element is implemented for" + we);
		String extractedXpath = getXpathString(we);
		int position2 = position + 1;
		String xpath = extractedXpath + "[" + position2 + "]";
		System.out.println("INFO : Final Xpath is " + xpath);

		WebElement elementXpath = getDriver().findElement(By.xpath(xpath));
		pause(2);
		elementXpath.click();
		pause(4);
		WebElement parent = elementXpath.findElement(By.xpath("./child::div"));
		await(60).until(ExpectedConditions.elementToBeClickable(parent));
		if ((!(parent.getAttribute("class").contains("focus")))) {
			elementXpath.click();
		}
		pause(5);
		WebElement grandChild = elementXpath.findElement(By.xpath("./child::div/input"));
		grandChild.clear();
		grandChild.sendKeys(data, Keys.TAB);
		
		ArtefactBuilder.setCustAct(true);
		ArtefactBuilder.artefactSS("Enter " + data + "in highlighted field", we);
	}
	
	/**
     * The method is used to perform custom actions on LN Input fields with Lookup
     **/
	public void TriggerInputField(WebElement we, String data){
		ArtefactBuilder.setCustAct(false);
		
		await(10).until(ExpectedConditions.elementToBeClickable(we)).click();
		await(2);
//			System.out.println("INFO : PageObject locator is "+we);
		WebElement parent = we.findElement(By.xpath("./parent::div"));
//			System.out.println("INFO : Parent locator after appending is "+parent);
		if ((!(parent.getAttribute("class").contains("TriggerInputField-focus")))) {
			we.click();
		}
		await(60).until(ExpectedConditions.attributeContains(parent, "class", "focus"));
		we.clear();
		we.sendKeys(data, Keys.TAB);
		
		ArtefactBuilder.artefactSS("Enter " + data + "in highlighted field", we);
		ArtefactBuilder.setCustAct(true);
		
	}
	
	/**
     * The method is used to perform custom actions on LN Input fields
     **/
	public void DecoratorInputField(WebElement we, String data) {

		ArtefactBuilder.setCustAct(false);

		we.click();
		await(2);
//		System.out.println("INFO : PageObject locator is "+we);
		WebElement parent = we.findElement(By.xpath("./parent::div"));
//		System.out.println("INFO : Parent locator after appending is "+parent);
		if ((!(parent.getAttribute("class").contains("focus")))) {
			we.click();
		}
		await(60).until(ExpectedConditions.attributeContains(parent, "class", "focus"));
		we.click();
		we.sendKeys(data, Keys.TAB);

		ArtefactBuilder.setCustAct(true);
		ArtefactBuilder.artefactSS("Enter " + data + "in highlighted field", we);
	}
	
}
