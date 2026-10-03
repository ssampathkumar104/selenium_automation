package testBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import dataUtils.RuntimeData;
import testBase.documetation.ArtefactObject;
import testBase.documetation.DOCXGenerator;
import testBase.documetation.PDFGenerator;
import testBase.documetation.PDFReportObject;

/**
 *
 * ArtefactBuilder class defining ArtefactBuiler activities.
 */
public class ArtefactBuilder {

	private static String color = "";

	private static String getColor() {
		List<String> list = Arrays.asList(color.split(","));
		String randColor = list.get(new Random().nextInt(list.size()));
		return randColor;
	}

	private static void setColor(String colour) {
		color = colour;
	}

	/**
	 * Sets the custom action flag in ThreadUtils.
	 * always make sure that this is used in custom functions appropriately, code block should be between the false and true flags as below
	 * @param flag the value to set the custom action flag
	 */
	public static void setCustAct(Boolean flag) {
		ThreadUtils.setCustFlagRef(flag);
	}

	// constant
	private static final String GENERATE_DOCUMENT = "generateDocument";

	/**
	 * Takes an artifact screenshot with a description and a web element.
	 *
	 * @param description the description of the artifact
	 * @param e           the web element to capture in the screenshot
	 */
	protected static void takeArtefact(String description, WebElement e) {
		boolean flag = BaseClass.getParameter(GENERATE_DOCUMENT, "").trim().toLowerCase().matches("pdf|docx");
		if (!flag) {
			return;
		}
		if (!ThreadUtils.getCustFlagRef()) {
			return;
		}
		artefactSS(description, e);
	}

	/**
	 * @param description text that needs to be add as sub header in the document
	 */
	public static void addSubHeader(String description) {
		boolean flag = BaseClass.getParameter(GENERATE_DOCUMENT, "").trim().toLowerCase().matches("pdf|docx|xls");
		if (!flag) {
			return;
		}

		File elementImg = null, screenImg = null;
		String screenImgPath = null;

		ArtefactObject ao = new ArtefactObject(description, elementImg, screenImg, screenImgPath);
		ThreadUtils.getArtefactRef().add(ao);
	}
	
	/**
	 * @param description text that needs to be add as sub header in the document
	 */
	public static void addInfoNotes(String description) {
		artefactSS("_INFO : " + description, null);
	}

	/**
	 * Takes artefact screenshots with descriptions and multiple web elements. Use
	 * this method only in custom functions
	 * 
	 * @param description the description of the artefacts
	 * @param ele         the web elements to capture in the screenshots
	 */
	public static void artefactSS(String description, WebElement firstElement, WebElement... additionalElements) {
		boolean flag = BaseClass.getParameter(GENERATE_DOCUMENT, "").trim().toLowerCase().matches("pdf|docx|xls");
		if (!flag) {
			return;
		}

		File elementImg = null;

		WebElement[] ele = Stream.concat(
										firstElement != null ? Stream.of(firstElement) : Stream.empty(),
										Stream.of(additionalElements)
								).toArray(WebElement[]::new);

		for (WebElement e : ele) {
			updateBorderAttribute(e, "solid", "4.5");
		}
		
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}

		File screenImg = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.FILE);
		
		String name = RuntimeData.getRandomChars(11, 12) + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
		String screenImgPath = ThreadUtils.getArtefactDirectoryPath() + File.separator + name.replaceAll("[^a-zA-Z0-9]", "") + ".png";
		
		try {
			FileUtils.copyFile(screenImg, new File(screenImgPath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ArtefactObject ao = new ArtefactObject(description, elementImg, screenImg, screenImgPath);
		ThreadUtils.getArtefactRef().add(ao);
		
		// Reset borders
		for (WebElement e : ele) {
			updateBorderAttribute(e, "", "0");
		}

	
	}

	private static File updateBorderAttribute(WebElement e1, String solid, String border) {
		int attempts = 0;
		boolean ss = false;
		File elementImg = null; 
		while (!ss && attempts < 5) {
			try {
				BaseClass.executor().executeScript("arguments[0].style.border='"+border+"px " + solid + " " + getColor() + "' ; arguments[0].offsetHeight;", e1);
				elementImg = ((TakesScreenshot) e1).getScreenshotAs(OutputType.FILE);
				ss = true;
			} catch (StaleElementReferenceException exp) {
				attempts++;
				BaseClass.pause(1);
				e1 = BaseClass.getDynamicElement(e1);
				BaseClass.log().debug("StaleElementReference exception caught, element is updated");
			}
		}
		return elementImg;
	}
	
	/**
	 * Initializes the ArtefactBuilder by setting necessary objects and flags.
	 */
	protected static void initArtefactBuilder() {
		Boolean custActFlag = true;
		PDFReportObject obj = new PDFReportObject();
		List<ArtefactObject> ao1 = new ArrayList<>();

		setColor(TestData.getElementHighlightColor());

		String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a"));
		obj.setStartTime(startTime);

		ThreadUtils.setArtefactRef(ao1);
		ThreadUtils.setPDFReportObj(obj);
		ThreadUtils.setCustFlagRef(custActFlag);
	}

	/**
	 * Generates a DOCX file with artefact screenshots and details.
	 *
	 * @param testClassName the name of the test class
	 * @param startTime     the start time of the test
	 * @param endTime       the end time of the test
	 * @param status        the status of the test
	 * @throws IOException if an I/O error occurs
	 */
	protected static void artefactDocxBuilder(String testClassName, String startTime, String endTime, String status) throws IOException {
		if (BaseClass.getParameter(GENERATE_DOCUMENT, "false").equalsIgnoreCase("docx")) {
			DOCXGenerator.generateDOCX(testClassName, startTime, endTime, status);
			File srcFile = new File(ThreadUtils.getPDFReportObj().docxReportFilePath);
			File destFile = new File(System.getProperty("user.dir") + File.separator + "artefact" + File.separator + testClassName + ".docx");
			FileUtils.copyFile(srcFile, destFile);
		}
	}

	/**
	 * Generates a Excel file without artefact screenshots, only with details.
	 * @param testClassName the name of the test class
	 * @param startTime     the start time of the test
	 * @param endTime       the end time of the test
	 * @param status        the status of the test
	 * @throws IOException if an I/O error occurs
	 */
	protected static void generateXLS(String testClassName, String startTime, String endTime, String status) throws IOException {
		
		if (BaseClass.getParameter(GENERATE_DOCUMENT, "false").trim().toLowerCase().matches("xls")) {
			DOCXGenerator.writeToXLS(testClassName, startTime, endTime, status);
			File srcFile = new File(ThreadUtils.getPDFReportObj().xlsReportFilePath);
			File destFile = new File(System.getProperty("user.dir") + File.separator + "artefact" + File.separator + testClassName + ".xls");
			FileUtils.copyFile(srcFile, destFile);
		}
	}

	/**
	 * Generates a PDF file with artefact screenshots and details.
	 *
	 * @param testClassName the name of the test class
	 * @param startTime     the start time of the test
	 * @param endTime       the end time of the test
	 * @param status        the status of the test
	 * @throws IOException if an I/O error occurs
	 */
	protected static void artefactPDFBuilder(String testClassName, String startTime, String endTime, String status) throws IOException {
		if (BaseClass.getParameter(GENERATE_DOCUMENT, "").equalsIgnoreCase("pdf")) {
			PDFGenerator.generatePDF(testClassName, startTime, endTime, status);
			File srcFile = new File(ThreadUtils.getPDFReportObj().pdfReportFilePath);
			File destFile = new File(System.getProperty("user.dir") + File.separator + "artefact" + File.separator + testClassName + ".pdf");
			FileUtils.copyFile(srcFile, destFile);
		}
	}

	/**
	 * Generates a PDF file with artifact screenshots and details for ATS (Automated
	 * Test Suite).
	 *
	 * @param testClassName the name of the test class
	 * @param startTime     the start time of the test
	 * @param endTime       the end time of the test
	 * @param status        the status of the test
	 */
	@Deprecated
	protected static void artefactPDFBuilderATS(String testClassName, String startTime, String endTime, String status) {
		if (BaseClass.getParameter("Generate_TestResultsDoc", "No").equalsIgnoreCase("yes")) {
			PDFGenerator.generatePDF(testClassName, ThreadUtils.getPDFReportPath(), startTime, endTime, status);
		}
	}
}