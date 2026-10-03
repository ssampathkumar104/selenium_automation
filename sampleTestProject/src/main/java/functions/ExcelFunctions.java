package functions;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;
import org.testng.xml.XmlTest;

//import com.amazonaws.services.cloudwatch.model.InvalidFormatException;

import dataUtils.ExcelDataFile;
import dataUtils.ExcelData.ExcelData;
import dataUtils.ExcelData.TableData;
import testBase.TestData;
public class ExcelFunctions {
	public static final String outputDirectoryPath = System.getProperty("user.dir") + File.separator + "OutputData";
	public static ArrayList<HashMap<String, String>> dataFromExcel(final String execelFileName,
			final String sheetName) {
		ExcelDataFile dataFile = TestData.getExcelDataFile(execelFileName);
		ExcelData _excel = dataFile.getExcel();
		TableData sheet = _excel.sheet(sheetName);
		int noOfRows = sheet.rowCount();
		List<String> rowHeader = sheet.row(0);
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		for (int i = 1; i < noOfRows; i++) {
			{
				List<String> row = sheet.row(i);
				int colCount = row.size();
				HashMap<String, String> singleDataSet = new LinkedHashMap<String, String>();
				for (int j = 0; j < colCount; j++) {
					{
						String key = rowHeader.get(j);
						String value = row.get(j);
						if (!(value.isEmpty()) || value.equals(" ")) {
							singleDataSet.put(key, value);
						}
					}
				}
				if (!singleDataSet.isEmpty()) {
					data.add(singleDataSet);
				}
			}
		}
		return data;
	}
	public static Map<String, String> getOCRExtractedData(String fileName) throws IOException {
	    File file = null;
	    XmlTest xmlTest = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest();

	    if (xmlTest.getClasses().size() > 1) {
	        file = new File(outputDirectoryPath + File.separator + xmlTest.getName().trim() + ".xls");
	        if (!file.exists()) {
	            file = new File(outputDirectoryPath + File.separator + fileName);
	        }
	    } else {
	        file = new File(outputDirectoryPath + File.separator + fileName);
	    }

	    HashMap<String, String> values = new HashMap<>();

//	    try (FileInputStream inputStream = new FileInputStream(file);
//	         Workbook wb = WorkbookFactory.create(inputStream)) { // Use WorkbookFactory to automatically handle both .xls and .xlsx
//	        Sheet sh = wb.getSheet("Extracted Data");
//	        int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
//	        Row rowHeader = sh.getRow(0);
//	        for (int i = 1; i <= rowCount; i++) {
//	            for (int j = 0; j < rowHeader.getLastCellNum(); j++) {
//	                String key = rowHeader.getCell(j).toString();
//	                String value = sh.getRow(i).getCell(j).toString();
//	                values.put(key, value);
//	            }
//	        }
//	    } catch (IOException | InvalidFormatException e) {
//	        fail("Output File : " + outputDirectoryPath + File.separator + fileName + " is not found or not valid");
//	    }

	    return values;
	}
	public static ArrayList<HashMap<String, String>> dataFromExcelSolution2(final String execelFileName,
			final String sheetName, final String TestCaseId) {
		ExcelDataFile dataFile = TestData.getExcelDataFile(execelFileName);
		ExcelData excel = dataFile.getExcel();
		TableData sheet = excel.sheet(sheetName);
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		int noOfRows = sheet.rowCount();
		for (int i = 1; i < noOfRows; i++) {
			{
				List<String> row = sheet.row(i);
				if (!row.isEmpty() && (!row.get(0).isEmpty() && row.get(0).trim().equalsIgnoreCase(TestCaseId))) {
					int colCount = row.size();
					for (int k = (i + 1); (k < noOfRows); k++) {
						{
							HashMap<String, String> singleDataSet = new LinkedHashMap<String, String>();
							List<String> valueSet = sheet.row(k);
							if (!(valueSet.isEmpty() || valueSet.get(1).isEmpty()
									|| valueSet.get(1).trim().equalsIgnoreCase("iteration"))) {
								for (int j = 2; j < colCount; j++) {
									{
										String key = row.get(j);
										String value = valueSet.get(j);
										if (!value.isEmpty()) {
											singleDataSet.put(key, value);
										}
									}
								}
								if (!singleDataSet.isEmpty()) {
									data.add(singleDataSet);
								}
							} else {
								k = noOfRows;
							}
						}
					}
				}
			}
		}
		return data;
	}

}
