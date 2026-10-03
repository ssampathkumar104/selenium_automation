package testBase.listners;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import testBase.ArtefactBuilder;

/**
*
* Implementation of {@link WebDriverListener} interface that handles events triggered by the WebDriver.
*/
public class EventHandler extends ArtefactBuilder implements WebDriverListener  {
	
	/**
	 * Actions to be performed after getting the URL.
	 *
	 * @param driver  the WebDriver instance
	 * @param url     the URL that was retrieved
	 */
	@Override
	public void afterGet(WebDriver driver, String url) {
//		ThreadUtils.setBaseUrl(url);
	 }
	
	/**
	 * Actions to be performed before clicking on an element.
	 *
	 * @param element  the element that is being clicked
	 */
	@Override
	public void beforeClick(WebElement element) {
		String stmt = "Click highlighted " ;
		String innerText = "";
		try {
			innerText = element.getAttribute("innerText").trim();
		} catch (NullPointerException e) {
			System.err.println(" InnerText is empty");
		}
		String tagName = element.getTagName();
		String className = element.getAttribute("class");
		
		if(StringUtils.isNoneBlank(innerText)) 
			stmt = "Click '" + innerText + "' ";
		
		if(tagName.equalsIgnoreCase("button") || className.toLowerCase().contains("button")) {
			if(StringUtils.isNoneBlank(innerText))
				stmt = "Click '" + innerText + "' button";
			if(StringUtils.isNoneBlank(element.getAttribute("title")))
				stmt = "Click '" + element.getAttribute("title") + "' button";
		}
		
		boolean linkOrNot =false;
		try {
//			System.out.println("****** "+element.getAttribute("href").replace("javascript:void(0)", ""));
			linkOrNot = StringUtils.isNotBlank(element.getAttribute("href").replace("javascript:void(0)", ""));
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		if(tagName.equalsIgnoreCase("a") && linkOrNot)
			stmt = stmt + "link";
		
		takeArtefact(stmt, element);
	}

	/**
	 * Actions to be performed after sending keys to an element.
	 *
	 * @param element       the element that received the keys
	 * @param keysToSend    the keys that were sent
	 */
	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		StringBuilder sb = new StringBuilder();
		for (CharSequence str : keysToSend)
			sb.append(str + " ");

		String s = null;
		int lenOfPassWord = 0;

		try {
			if (element.getAttribute("type") != null && element.getAttribute("type").trim().equalsIgnoreCase("password")) {
				s = "password";
				lenOfPassWord = keysToSend[0].length();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (Objects.isNull(s))
			takeArtefact("Enter '" + sb.toString() + "' in highlighted field", element);
		if (Objects.nonNull(s) && s.equalsIgnoreCase("password"))
			takeArtefact("Enter '" + StringUtils.repeat("*", lenOfPassWord) + "' in highlighted field", element);
	}

	/**
	 * Actions to be performed after getting the text of an element.
	 *
	 * @param element  the element from which the text was retrieved
	 * @param result   the text that was retrieved
	 */
	@Override
	public void afterGetText(WebElement element, String result) {
//		takeArtefact("Get/Save value from the highlighted field", element);
	}

	/**
	 * Actions to be performed after getting the value of an attribute of an element.
	 *
	 * @param element  the element from which the attribute value was retrieved
	 * @param name     the name of the attribute
	 * @param result   the value of the attribute
	 */
	@Override
	public void afterGetAttribute(WebElement element, String name, String result) {
//		if (name.equalsIgnoreCase("value"))
//			takeArtefact("Get/Save " + name + " from the highlighted field", element);
	}
	
	/**
	 * Actions to be performed before clearing the text of an element.
	 *
	 * @param element  the element from which the text will be cleared
	 */
	@Override
	public void  beforeClear (WebElement element){
		takeArtefact("Clear the text from the highlighted field", element);
	}


}
