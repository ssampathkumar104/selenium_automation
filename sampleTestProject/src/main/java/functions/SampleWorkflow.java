package functions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import dataUtils.RuntimeData;
import pages.TestPage;
import testBase.BaseClass;

public class SampleWorkflow extends BaseClass{

	public static void test() {
		TestPage page = initElements(TestPage.class);
		await(100).until(ExpectedConditions.elementToBeClickable(page.identifier));
		page.identifier.click();
		System.out.println("&&&&&&&&  "+RuntimeData.getDateParam("dd-MM-yyyy hh:mm:sss"));
		getDynamicElement(page.googleInput, "q").sendKeys("ssanapathi1");
		System.out.println("&&&&&&&&  "+RuntimeData.getDateParam("dd-MM-yyyy hh:mm:sss"));
		page.identifier.click();
		
	}
}
