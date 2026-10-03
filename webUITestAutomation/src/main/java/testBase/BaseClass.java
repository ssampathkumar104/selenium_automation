package testBase;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import static testBase.ThreadUtils.getITestContext;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.xml.XmlTest;

//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.textract.AmazonTextract;
//import com.amazonaws.services.textract.AmazonTextractClientBuilder;
//import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
//import com.amazonaws.services.textract.model.DetectDocumentTextResult;
//import com.amazonaws.services.textract.model.Document;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import annotations.PopUp;
import dataUtils.RuntimeData;
import pageFactory.PageFactory;
import testBase.documetation.PDFReportObject;
import testBase.listners.API_Call;
import testBase.listners.ExtentReportListener;
import testBase.listners.LogFormatter;
import testReportingAPI.TestResultsAPI;

/**
 * This class is the base class for all test cases.
 */
@Listeners(ExtentReportListener.class)
public class BaseClass {
	
	private static final String IMPLICIT_WAIT_TIME ="implicitlyWaitTime";   
	private static final String DOWNLOAD ="download";   
//	private static AmazonTextractClientBuilder clientBuilder = AmazonTextractClientBuilder.standard().withRegion(Regions.AP_SOUTH_1);
	/**
	 * This method is invoked before the test class is executed to launch the browser.
	 * @param browserName The name of the browser to be launched (optional, default is 'chrome').
	 */
	@BeforeClass(alwaysRun = true)
	@Parameters({ "browserName" })
	public void launchBrowser(@Optional("chrome") String browserName) {
		try {
			String testCaseName = substringAfterLast(Reporter.getCurrentTestResult().getTestClass().getXmlClass().getName().trim(), ".");
			LogFormatter.createDownloadLogScreenshotDirectories(testCaseName);
			LogFormatter.initLogFormatter(testCaseName);

			ArtefactBuilder.initArtefactBuilder();
			Driver.initDriverForWeb(browserName);
			
			getDriver().manage().window().maximize();
			getDriver().manage().deleteAllCookies();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(getParameter(IMPLICIT_WAIT_TIME, "10"))));
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(getParameter("pageLoadTimeout", "120"))));
			getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(Integer.parseInt(getParameter("scriptTimeout", "120"))));
			log().info(browserName + " browser is launched");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is invoked after the test class is executed to close the browser and generate artefacts.
	 * @throws IOException If an I/O error occurs.
	 */
	@AfterClass(alwaysRun = true)
	public void endBrowser() throws IOException {
		try {
			Driver.quitDriver();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		String endTime =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a"));
		String startTime = ThreadUtils.getPDFReportObj().getStartTime();
		
		ITestResult result = ThreadUtils.getPDFReportObj().getResult();
		String strResult = result.getStatus()== ITestResult.SUCCESS ? "Passed" : (result.getStatus() == ITestResult.FAILURE ? "Failed" : "Skipped");
		
		XmlTest xmlTest = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();
		String testCaseName = xmlTest.getName();

		String testClassName = "";
		String className = Reporter.getCurrentTestResult().getTestClass().getXmlClass().getName().trim();
		String lastClassName = xmlTest.getXmlClasses().get(xmlTest.getClasses().size() - 1).getName().trim();
		
		if (xmlTest.getClasses().size() == 1)
			testClassName = substringAfterLast(xmlTest.getClasses().get(0).getName().trim(), ".");
		else if (className.trim().equalsIgnoreCase(substringAfterLast(lastClassName, "."))) {
			testClassName = substringAfterLast(lastClassName, ".");
		} else {
			testClassName = substringAfterLast(className, ".");
		}
		
		ArtefactBuilder.artefactDocxBuilder(testClassName, startTime, endTime, strResult);
		ArtefactBuilder.artefactPDFBuilder(testClassName, startTime, endTime, strResult);
		ArtefactBuilder.generateXLS(testClassName, startTime, endTime, strResult);
		
		API_Call.publishToApi(result, testClassName, startTime, endTime);
		
		new TestResultsAPI().processTestScript(result);
	}
	
	/**
	 * Returns the WebDriver instance associated with the current thread.
	 * @return The WebDriver instance.
	 */
	public static WebDriver getDriver() {
		return ThreadUtils.getDriverRef();
	}

	/**
	 * Returns the Sikuli Screen instance associated with the current thread.
	 * @return The Sikuli Screen instance.
	 */
	public static Screen getScreen() {
		return ThreadUtils.getScreenRef();
	}

	/**
	 * Initializes the PageFactory for the specified component class using the WebDriver instance.
	 * @param coms The component class.
	 * @param <T> The type of the component class.
	 * @return An instance of the component class.
	 */
	public static <T> T initElements(Class<T> coms) {
		return PageFactory.initElements(getDriver(), coms);
	}

	private static String getLocalClassParameter(String key) {
		String value = null;
		try {
			value = Reporter.getCurrentTestResult().getTestClass().getXmlClass().getLocalParameters().get(key);
		} catch (NullPointerException e) {
		}
		return value;
	}
		
	/**
	 * Retrieves the value of the specified parameter.
	 * @param key The parameter key.
	 * @return The value of the parameter, or the default value if not found.
	 */
	public static String getParameter(String key) {
		try {
			String value = System.getProperty(key);
			String defValue = getITestContext().getCurrentXmlTest().getParameter(key);
			String defLocalValue = getLocalClassParameter(key);
			return isBlank(value) ? (isBlank(defLocalValue) ? defValue : defLocalValue) : value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sets the test description for the current thread's PDF report object.
	 * This will update the summary table in PDF document
	 * @param testDescription 
	 */
	public static void setTestDescription(String testDescription) {
		ThreadUtils.getPDFReportObj().setTestDescription(testDescription);
	}
	
	/**
	 * Sets the test details for the current thread's PDF report object.
	 * This will update the summary table in word document
	 * @param reportObject The PDFReportObject containing the test details.
	 */
	public static void setTestDetails(PDFReportObject reportObject) {
		PDFReportObject p = ThreadUtils.getPDFReportObj();
		p.setDescription(reportObject.getDescription());
		p.setProcess(reportObject.getProcess());
		p.setUser(reportObject.getUser());
		p.setPrerequisites(reportObject.getPrerequisites());
		p.setNotes(reportObject.getNotes());
		p.setUsecaseId(reportObject.getUsecaseId());
	}

	/**
	 * Retrieves the value of the specified parameter with a default value if not found.
	 * @param key The parameter key.
	 * @param defaultValue The default value.
	 * @return The value of the parameter, or the default value if not found.
	 */
	public static String getParameter(String key, String defaultValue) {
		String value = getParameter(key);
		return (value != null) ? value : defaultValue;
	}

	/**
	 * Captures a screenshot with the given description.
	 * @param description The description of the screenshot.
	 */
	public static void screenshot(String description) {
		byte[] data = null;
		if (!ThreadUtils.getIsScreen()) {
			try {
				data = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			} catch (TimeoutException exp) {
				try {
					Robot robot = new Robot();
					Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
					BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(screenFullImage, "png", baos);
					baos.flush();
					data = baos.toByteArray();
				} catch (Exception e) {
					ThreadUtils.getLogger().error("[ERROR] Unable to capture the screenshot!", e);
				}
			}
		} else if (ThreadUtils.getIsScreen()) {
			try {
				Robot robot = new Robot();
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(screenFullImage, "png", baos);
				baos.flush();
				data = baos.toByteArray();
			} catch (Exception e) {
				log().error("Unable to capture the screenshot!", e);
			}
		}

		String fPath = ThreadUtils.getScreenshotDirectoryPath() + File.separator + RuntimeData.getRandomChars(9, 10)+ ".jpg";
		ThreadUtils.getSSObjRef().add(Arrays.asList(description, fPath));
		
		try {
			FileUtils.writeByteArrayToFile(new File(fPath), data);
			log().info("ScreenShot : " + description);
			writeDescriptionList(description);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeDescriptionList(String description) throws IOException {
		String filePath = ThreadUtils.getScreenshotDirectoryPath() + File.separator + "descriptionList.txt";
	    
	    File file = new File(filePath);
	    if (!file.exists()) 
			file.createNewFile();
			
		FileUtils.writeStringToFile(file, description + "\n", true);
	}
	
	/**
	 * @deprecated (when, why, refactoring advice...)
	 * 
	 * @param e WebElement
	 * @param keysToSend input data
	 */
	@Deprecated
	public static void type(WebElement e, CharSequence... keysToSend) {
		List<String> newList = Arrays.asList(keysToSend).stream()
                .map(String::valueOf)
                .collect(Collectors.toList());
		
		String keySeq = String.join("", newList);
		
		
		ArtefactBuilder.setCustAct(false);
						try {
							e.click();
							e.clear();
							forceType(e, "");
							e.sendKeys(keysToSend);
						} catch (Exception exp) {
							forceType(e, keysToSend[0].toString());
							System.err.println("Entered only "+ keysToSend[0].toString());
						}
		ArtefactBuilder.artefactSS("Type '" + keySeq + "' in highlighted field", e);
		ArtefactBuilder.setCustAct(true);
		
	}

	/**
	 * Creates and returns a WebDriverWait object with the specified timeout duration.
	 * @param timeout The timeout duration in seconds.
	 * @return The WebDriverWait object.
	 */
	public static WebDriverWait await(long timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		return wait;
	}

	/**
	 * Pauses the execution for the specified duration.
	 * @param timeout The pause duration in seconds.
	 */
	public static void pause(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns a JavascriptExecutor object to perform actions related to JavaScript execution.
	 * @return The JavascriptExecutor object.
	 */
	public static JavascriptExecutor executor() {
		return (JavascriptExecutor) getDriver();
	}

	/**
	 * Performs a click operation using JavascriptExecutor.
	 * @param element The WebElement to be clicked.
	 * @return The JavascriptExecutor object.
	 */
	public static JavascriptExecutor forceClick(WebElement element) {
		String stmt = "Click highlighted " ;
		String innerText = "";
		innerText = element.getAttribute("innerText");
		String tagName = element.getTagName();
		
		if(isNoneBlank(innerText)) 
			stmt = "Click '" + innerText + "' ";
		
		if(tagName.equalsIgnoreCase("button")) {
			if(isNoneBlank(innerText))
				stmt = "Click '" + innerText + "' button";
			if(isNoneBlank(element.getAttribute("title")))
				stmt = "Click '" + element.getAttribute("title") + "' button";
		}
		
		boolean linkOrNot =false;
		try {
			linkOrNot = isNotBlank(element.getAttribute("href").replace("javascript:void(0)", ""));
		} catch (NullPointerException e) {
		}
		if(tagName.equalsIgnoreCase("a") && linkOrNot)
			stmt = stmt + "link";
		
		ArtefactBuilder.takeArtefact(stmt, element);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
		return executor;
	}

	/**
	 * Performs a type operation using JavascriptExecutor to enter text into a web element.
	 * @param webElement The WebElement to enter text into.
	 * @param text The text to be entered.
	 * @return The JavascriptExecutor object.
	 */
	public static JavascriptExecutor forceType(WebElement webElement, String text) {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].value='" + text + "';", webElement);
		ArtefactBuilder.takeArtefact("Enter '" + text + "' in highlighted field", webElement);
		return executor;
	}

	/**
	 * Returns an Actions object to perform advanced user interactions.
	 * @return The Actions object.
	 */
	public static Actions actions() {
		return new Actions(getDriver());
	}

	/**
	 * Returns a By object based on the selector and value.
	 * @param selector The selector type.
	 * @param value The value for the selector.
	 * @return The By object.
	 * @throws IllegalStateException if the selector is not found.
	 */
	private static By getLocator(String selector, String value) {
		By by = null;
		switch (selector) {
		case "id":
			by = By.id(value);
			break;
		case "className":
			by = By.className(value);
			break;
		case "tagName":
			by = By.tagName(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "cssSelector":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			throw new IllegalStateException("locator : " + selector + " not found!!!");
		}
		return by;
	}
	
	/**
	 * Returns a map of locator details (string, selector, value) for a WebElement.
	 * @param e The WebElement to get locator details for.
	 * @return The map of locator details.
	 */
	private static Map<String, String> getLocatorSelector(WebElement e) {
		Map<String, String> locator = new HashMap<>();
		String[] pathVariables = null;
		String selector = null;
		String value = null;

		getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(2));
		String str = e.toString();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(getParameter(IMPLICIT_WAIT_TIME, "10"))));
		
		if (str.contains("DefaultElementLocator")) {
			pathVariables = (str.split("->")[1].trim()).split(":", 2);
			selector = pathVariables[0].trim().split("[.]")[1];
			value = pathVariables[1].trim();
		} else {
			pathVariables = ((str.split("->"))[1].trim()).split(":", 2);
			selector = pathVariables[0].trim();
			value = pathVariables[1].trim().substring(0, pathVariables[1].trim().length() - 2);
		}

		locator.put("string", str);
		locator.put("selector", selector);
		locator.put("value", value);
		return locator;

	}
	
	/**
	 * Checks if an element is present on the web page.
	 * @param we The WebElement to check for presence.
	 * @return true if the element is present, false otherwise.
	 */
	public static boolean isElementPresent(WebElement we) {

		int t = Integer.parseInt(getParameter(IMPLICIT_WAIT_TIME, "10"));
		boolean condition;
		By by = null;

		try {
			Map<String, String> locator = getLocatorSelector(we);
			String selector = locator.get("selector");
			String value = locator.get("value");

			by = getLocator(selector, value);
			await(t).pollingEvery(Duration.ofMillis(2)).withTimeout(Duration.ofSeconds(t))
					.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.presenceOfElementLocated(by));
			condition = true;
		} catch (Exception e) {
			condition = false;
		}
		return condition;
	}

	/**
	 * Checks if an element is present on the web page.
	 * @param we The WebElement to check for presence.
	 * @param varStr Optional variable arguments used to format the value of the locator.
	 * @return true if the element is present, false otherwise.
	 */
	public static boolean isElementPresent(WebElement we, String... varStr) {

		int t = Integer.parseInt(getParameter(IMPLICIT_WAIT_TIME, "10"));
		boolean condition;
		By by = null;

		try {
			Map<String, String> locator = getLocatorSelector(we);
			String selector = locator.get("selector");
			String value = String.format(locator.get("value"), (Object[])varStr);

			by = getLocator(selector, value);
			await(t).pollingEvery(Duration.ofMillis(2)).withTimeout(Duration.ofSeconds(t))
					.ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.presenceOfElementLocated(by));
			condition = true;
		} catch (Exception e) {
			condition = false;
		}
		return condition;
	}

	/**
	 * Returns a dynamic WebElement based on the provided WebElement and variable arguments.
	 * @param we The base WebElement.
	 * @param strVar The variable arguments used to format the value of the locator.
	 * @return The dynamic WebElement.
	 * @throws NoSuchElementException if the element cannot be found.
	 */
	public static WebElement getDynamicElement(WebElement we, String... strVar) throws NoSuchElementException {
		By by = null;

		Map<String, String> loc = getLocatorSelector(we);
		String selector = loc.get("selector");
		String value = String.format(loc.get("value"), (Object[])strVar);

		by = getLocator(selector, value);
		return getDriver().findElement(by);
	}
	
	/**
	 * Returns a dynamic WebElements based on the provided WebElement and variable arguments.
	 * @param we The base WebElement.
	 * @param strVar The variable arguments used to format the value of the locator.
	 * @return The dynamic WebElement.
	 * @throws NoSuchElementException if the element cannot be found.
	 */
	public static List<WebElement> getDynamicElements(WebElement we, String... strVar) throws NoSuchElementException {
		By by = null;

		Map<String, String> loc = getLocatorSelector(we);
		String selector = loc.get("selector");
		String value = String.format(loc.get("value"), (Object[])strVar);

		by = getLocator(selector, value);
		return getDriver().findElements(by);
	}

	/**
	 * Returns the XPath string of a WebElement.
	 * @param e The WebElement to get the XPath string for.
	 * @return The XPath string if the selector is "xpath", null otherwise.
	 */
	public static String getXpathString(WebElement e) {
		Map<String, String> locator = getLocatorSelector(e);
		return locator.get("selector").equalsIgnoreCase("xpath") ? locator.get("value") : null;
	}

	/*
	 * ==========================================================================================================================================
	 */
	/**
	 * Waits for jQuery and JavaScript to finish loading.
	 * @param timeInSeconds The maximum time to wait in seconds.
	 */
	public static void waitForJQueryAndJSToLoad(long timeInSeconds) {
		await(timeInSeconds).until(ExpectedConditions.jsReturnsValue("return (document.readyState == 'complete' && jQuery.active == 0);"));
	}


	/**
	 * Gets the list of downloaded files in the temporary directory.
	 * @return An array of File objects representing the downloaded files.
	 */
	public static File[] getDownloadedFileslist() {
		File[] files = null;
		try {
			files = new File(ThreadUtils.getTempDirectoryPath() + DOWNLOAD).listFiles();
		} catch (SecurityException e) {
			log().warn("Verify the read permissions " + e.toString());
		} catch (NullPointerException e) {
			log().warn("Verify the path exists or not: " + e.toString());
		}
		return files;
	}

	/**
	 * Gets the downloaded file with the specified filename.
	 * @param filename The name of the downloaded file.
	 * @return The File object representing the downloaded file, or null if it doesn't exist.
	 */
	public static File getDownloadedFile(String filename) {
		File file = null;
		try {
			file = new File(ThreadUtils.getTempDirectoryPath() + DOWNLOAD + File.separator + filename);
			if (file.exists()) {
				return file;
			} else {
				log().warn(filename + " is not available in " + ThreadUtils.getTempDirectoryPath() + DOWNLOAD);
			}
		} catch (Exception e) {
			log().warn("Verify the read path/permissions " + e.toString());
		}
		return null;
	}

	/**
	 * Gets the latest file from the specified directory.
	 * @param dirPath The path to the directory.
	 * @return The File object representing the latest file, or null if the directory is empty.
	 */
	public static File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 0; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	/**
	 * Returns the logger instance.
	 * @return The logger instance.
	 */
	public static Logger log() {
		return ThreadUtils.getLogger();
	}

	private static String opaqueWindow() {
		return getDriver().getWindowHandle();
	}

	/**
	 * Returns the handle of the current window.
	 * @return The handle of the current window, or null if it doesn't exist.
	 */
	public static String currentWindow() {
		try {
			return opaqueWindow();
		} catch (NoSuchWindowException var2) {
			return null;
		}
	}

	/**
	 * Returns the title of the current window.
	 * @return The title of the current window.
	 */
	public static String title() {
		return getDriver().getTitle();
	}


	/**
	 * Handles a pop-up window with the specified timeout.
	 * @param popUp The PopUp object representing the pop-up window.
	 * @param timeOutInSeconds The timeout value in seconds.
	 */
	public static void handlePopUp(PopUp popUp, Long timeOutInSeconds) {
		if (timeOutInSeconds != null) {
			await(timeOutInSeconds);
		}else {
			await(Long.parseLong(getParameter(IMPLICIT_WAIT_TIME, "10")));
		}
		handlePopUp(popUp);
	}

	/**
	 * Handles a pop-up window.
	 * @param popUp The PopUp object representing the pop-up window.
	 */
	public static void handlePopUp(PopUp popUp) {

		String parentWindow = opaqueWindow();
		String parentTitle = title();
		String subWindowHandler = null;
		Set<String> handles = getDriver().getWindowHandles();
		log().warn(String.format("Parent Window=%s", parentTitle));
		Iterator<String> iterator = handles.iterator();

		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
			if (!StringUtils.equals(parentWindow, subWindowHandler)) {
				break;
			}
		}

		getDriver().switchTo().window(subWindowHandler);
		log().warn(String.format("switched to %s Window", title()));
		popUp.handle();
		getDriver().switchTo().window(parentWindow);
		log().warn(String.format("switched to Parent- %s Window ", parentTitle));
	}

	/**
	 * Switches to a window with the specified page title.
	 * @param pageTitle The title of the page to switch to.
	 * @throws Exception 
	 */
	public static void switchToWindow(String pageTitle) {
		Set<String> listOfWindows = getDriver().getWindowHandles();
		String[] windows = listOfWindows.toArray(new String[listOfWindows.size()]);
		for (int i = 0; i < windows.length; i++) {
			String activeWindowTitle = getDriver().switchTo().window(windows[i]).getTitle();
			log().info("INFO : ========>>>>> Active window title is " + activeWindowTitle + " <<<<<=========");
			if (activeWindowTitle.equalsIgnoreCase(pageTitle)) {
				break;
			}
		}
		ArtefactBuilder.takeArtefact("Switch to window '"+pageTitle+"'" , null);
	}
	
	/**
	 * Switches to a window with the specified index.
	 * @param pageTitle The title of the page to switch to.
	 * @throws Exception 
	 */
	public static WebDriver switchToWindow(int index){
        ArrayList<String> newTab = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(newTab.get(index - 1));
        String salutation ="th";
        switch (index % 10) {
            case 1:  salutation ="st";
            	   break;
            case 2:  salutation ="nd";
                   break;
            case 3:  salutation ="rd";
                   break;
            default: salutation ="th";
        }
		ArtefactBuilder.takeArtefact("Switch to '"+String.valueOf(index) +salutation+"' window ", null);
        return getDriver();
    }
	
	/**
	 * Sets a value in the cache.
	 * @param object The key for the cache entry.
	 * @param value The value to be stored in the cache.
	 */
	public static void setCache(String object, String value) {
		ThreadUtils.getITestContext().getSuite().setAttribute(object, value);
	}

	/**
	 * Retrieves a value from the cache.
	 * @param object The key for the cache entry.
	 * @return The value from the cache, or null if it doesn't exist.
	 */
	public static Object getCache(String object) {
		return ThreadUtils.getITestContext().getSuite().getAttribute(object);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Clicks on the specified element with an optional step description.
	 * @param stepDescription The description of the step.
	 * @param element The WebElement to click on.
	 */
	public static void click(String stepDescription, WebElement element) {
		if (isNotBlank(stepDescription)) {
			ArtefactBuilder.setCustAct(false);
			ArtefactBuilder.artefactSS(stepDescription, element);
			element.click();
			ArtefactBuilder.setCustAct(true);
		} else {
			element.click();
		}
	}
	
	/**
	 * Sends keys to the specified element with an optional step description.
	 * @param stepDescription The description of the step.
	 * @param element The WebElement to send keys to.
	 * @param keysToSend The keys to send.
	 */
	public static void sendKeys(String stepDescription, WebElement element, CharSequence... keysToSend) {
		if (isNotBlank(stepDescription)) {
			ArtefactBuilder.setCustAct(false);
			element.sendKeys(keysToSend);
			ArtefactBuilder.artefactSS(stepDescription, element);
			ArtefactBuilder.setCustAct(true);
		} else {
			element.sendKeys(keysToSend);
		}
	}
	
	/**
	 * Performs a forceful click on the specified element with an optional step description.
	 * @param stepDescription The description of the step.
	 * @param webElement The WebElement to perform a forceful click on.
	 */
	public static void forceClick(String stepDescription, WebElement webElement) {
		if (isNotBlank(stepDescription)) {
			ArtefactBuilder.takeArtefact(stepDescription, webElement);
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].click();", webElement);
		}else {
			forceClick(webElement);
		}
	}
	
	/**
	 * Performs a forceful type action on the specified element with an optional step description.
	 * @param stepDescription The description of the step.
	 * @param webElement The WebElement to perform a forceful type action on.
	 * @param text The text to type.
	 */
	public static void forceType(String stepDescription, WebElement webElement, String text) {
		if (isNotBlank(stepDescription)) {
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].value='" + text + "';", webElement);
			ArtefactBuilder.takeArtefact(stepDescription, webElement);
		} else {
			forceType(webElement, text);
		}
	}
	
	/**
	 * step/method which are implemented using this methods will not be record in Artefact Document.
	 * @param popUp
	 */
	public static void noRecordInDocument(PopUp popUp) {
		String flag = getParameter("generateDocument", "false");
		try {
			Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().setParameters(Maps.newHashMap(ImmutableMap.of("generateDocument", "false")));
			popUp.handle();
		} finally {
			Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().setParameters(Maps.newHashMap(ImmutableMap.of("generateDocument", flag)));
		}
	}

	/**
	 * Extract text from screenshot
	 * @param imagePath complete path of the screenshot.
	 * @param regexCode text to be extracted from specified pattern
	 */
	/*
	 * public static String extractImageText(String regexCode) throws IOException {
	 * clientBuilder.setCredentials(new AWSStaticCredentialsProvider( new
	 * BasicAWSCredentials("your-aws-access-key-here",
	 * "your-aws-secret-key-here"))); ByteBuffer imageBytes; try
	 * (InputStream inputStream = new FileInputStream(new
	 * File(ThreadUtils.getSSObjRef().get(ThreadUtils.getSSObjRef().size()-1).get(1)
	 * ))) { imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream)); }
	 * 
	 * AmazonTextract client = clientBuilder.build(); DetectDocumentTextRequest
	 * request = new DetectDocumentTextRequest().withDocument( new
	 * Document().withBytes(imageBytes));
	 * 
	 * DetectDocumentTextResult result = client.detectDocumentText(request);
	 * 
	 * // Extract text based on specified regex String extractedText =
	 * extractText(result.toString(), regexCode);
	 * log().info("INFO : ========Order number: "+extractedText+"========="); return
	 * extractedText; }
	 */

	/**
	 * Extract required text from JSON response
	 */
   public static String extractText(String response, String regex) {
       Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
       Matcher matcher = pattern.matcher(response);
       if (matcher.find()) {
           return matcher.group(1);
       }
       return null;
   }
}