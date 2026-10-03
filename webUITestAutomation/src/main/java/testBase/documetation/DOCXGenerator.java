package testBase.documetation;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.assertj.core.api.Assertions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import testBase.TestData;
import testBase.ThreadUtils;

/**
 * DOCXGenerator is a class that represents a page event helper for adding headers and footers to a DOCX document.
 */
public class DOCXGenerator {
	
	/**
	 * Private constructor to prevent instantiation of the DOCXGenerator class.
	 */
	private DOCXGenerator() {
	}
	
	/**
	 * Generates a excel file with test case steps
	 * @param testCaseName the name of the test case
	 * @param startTime the start time of the test case
	 * @param endTime the end time of the test case
	 * @param status the status of the test case
	 * @throws IOException if an I/O error occurs
	 */
    public static void writeToXLS(String testCaseName, String startTime, String endTime, String status) throws IOException {
        String excelFileDir = ThreadUtils.getTempDirectoryPath() + testCaseName + File.separator + "artefact" + File.separator;
        String excelFilePath = excelFileDir + testCaseName + ".xls";
    	ThreadUtils.getPDFReportObj().xlsReportFilePath = excelFilePath;
    	
        List<ArtefactObject> aoLst = ThreadUtils.getArtefactRef();

        Files.createDirectories(Paths.get(excelFileDir));
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test Case Steps");

        HSSFCellStyle boldStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        boldStyle.setFont(font);
        boldStyle.setAlignment(HorizontalAlignment.LEFT);
        boldStyle.setVerticalAlignment(VerticalAlignment.BOTTOM);
        HSSFRow headerRow = sheet.createRow(0);

        HSSFCell headerCell0 = headerRow.createCell(0);
        headerCell0.setCellValue("Step No.");
        headerCell0.setCellStyle(boldStyle);

        HSSFCell headerCell1 = headerRow.createCell(1);
        headerCell1.setCellValue("Step Description");
        headerCell1.setCellStyle(boldStyle);

        HSSFCell headerCell2 = headerRow.createCell(2);
        headerCell2.setCellValue("Customer Data");
        headerCell2.setCellStyle(boldStyle);

        int rowIndex = 1;
        int stepNumber = 1;
        for (int i = 0, j = 0; i < aoLst.size(); i++, j++) {
            String currentDesc = aoLst.get(i).getDesc();
            HSSFRow row = sheet.createRow(rowIndex);
            
            if (Objects.isNull(aoLst.get(i).getScreenImg())) {
                HSSFCell cell = row.createCell(0);
                cell.setCellValue(currentDesc);
                cell.setCellStyle(boldStyle);
                sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 2));
            } else {
                row.createCell(0).setCellValue(stepNumber++);
                currentDesc = currentDesc.startsWith("_INFO : ") ? currentDesc.substring(1) : currentDesc;
                row.createCell(1).setCellValue(currentDesc);
            }
            rowIndex++;
        }
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }
        try (FileOutputStream out = new FileOutputStream(excelFilePath)) {
            workbook.write(out);
        }
        workbook.close();
    }
	
    
    /**
	 * Generates a DOCX file without screenshots for a given test case.
	 *
	 * @param testCaseName the name of the test case
	 * @param startTime the start time of the test case
	 * @param endTime the end time of the test case
	 * @param status the status of the test case
	 * @throws IOException if an I/O error occurs
	 */
	@Deprecated
	public static void generateDOCXWoSS(String testCaseName, String startTime, String endTime, String status)
			throws IOException {
		
		String docxFileDir = System.getProperty("user.dir") + File.separator + "artefact" + File.separator;
		String docxFile = docxFileDir + testCaseName + "_woSS.docx";
		File fname = new File(docxFile);
		if (!fname.exists())
			fname.mkdirs();

		ThreadUtils.getPDFReportObj().docxReportFilePath = docxFile;
		boolean delete = new File(docxFile).delete();
		boolean create = new File(docxFile).createNewFile();
		Assertions.assertThat(create).isTrue().describedAs("File is not created");
		
		
		XWPFDocument document = null;
		FileOutputStream out = null;

		try{
			document = new XWPFDocument();
			out = new FileOutputStream(fname, true);
			
			//
			addHeaderFooter(document, testCaseName);
			addSummaryTable(document);
			
			//
			List<ArtefactObject> aoLst = ThreadUtils.getArtefactRef();
			addTableForStepsHeader(document);
			XWPFTable stepsTable  = addTableForSteps(document, aoLst);

			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			
			for (int i = 0; i < aoLst.size(); i++) {
				XWPFTableRow r = stepsTable.getRow(i);
				XWPFTableCell c = r.getCell(0);

				// Step Number
				c.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(800));
				c.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				setCellTextWithCarriageReturn(c, String.valueOf(i+1));
				setParaAndLineSpacing(c.getParagraphs().get(0), ParagraphAlignment.CENTER);

				// Step Description
				c = r.getCell(1);
				c.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5600));
				c.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
				setCellTextWithCarriageReturn(c, " " + aoLst.get(i).getDesc());
				mergeCellsHorizontally(stepsTable, i, 1, 2);
				
				c = r.getCell(3);
				c.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1600));
				setCellTextWithCarriageReturn(c, " ");
				setParaAndLineSpacing(c.getParagraphs().get(0), ParagraphAlignment.LEFT);
			}
			document.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
			document.close();
		}
	}
	
	
	/**
	 * Generates a DOCX file for a given test case.
	 *
	 * @param testCaseName the name of the test case
	 * @param startTime the start time of the test case
	 * @param endTime the end time of the test case
	 * @param status the status of the test case
	 * @throws IOException if an I/O error occurs
	 */
	public static void generateDOCX(String testCaseName, String startTime, String endTime, String status)
			throws IOException {
		// Define the directory and file paths for the DOCX file
		String docxFileDir = ThreadUtils.getTempDirectoryPath() + testCaseName + File.separator + "artefact" + File.separator;
		String docxFile = docxFileDir + testCaseName + ".docx";
		
		ThreadUtils.getPDFReportObj().docxReportFilePath = docxFile;
		// Create the necessary directory and file
		File fname = new File(docxFile);
		if (!fname.exists())
			fname.mkdirs();
		
		// Delete and create a new file
		new File(docxFile).delete();
		new File(docxFile).createNewFile();
		
		// Write the test case details to the DOCX file
		writeToDOCX(testCaseName, fname, startTime, endTime, status);
	}
	
	/**
	 * Writes test case details to a DOCX file.
	 *
	 * @param methodName the name of the method
	 * @param f the file to write to
	 * @param startTime the start time of the test case
	 * @param endTime the end time of the test case
	 * @param status the status of the test case
	 * @throws IOException if an I/O error occurs
	 */
	public static void writeToDOCX(String methodName, File f, String startTime, String endTime, String status) throws IOException {

		XWPFDocument document = null;
		FileOutputStream out = null;

		try{
			// Create a new XWPFDocument and FileOutputStream
			document = new XWPFDocument();
			out = new FileOutputStream(f, true);
			int stepCnt = 1;
			
			// Add header and footer
			addHeaderFooter(document, methodName);
			
			// Create summary table
			addSummaryTable(document);
			
			// Get the list of ArtefactObjects
			List<ArtefactObject> aoLst = ThreadUtils.getArtefactRef();
			
			// add table header
			addTableForStepsHeader(document);
			XWPFTable stepsTable  = addTableForSteps(document, aoLst);

			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			
			for (int i = 0, j = 0; i < aoLst.size(); i++, j++) {
				XWPFTableRow r = stepsTable.getRow(i);
				if(!Objects.isNull(aoLst.get(i).getScreenImg())) {
					// Step Number
					XWPFTableCell c = r.getCell(0);
					c.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(800));
					c.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					setCellTextWithCarriageReturn(c, String.valueOf(j + 1));
					setParaAndLineSpacing(c.getParagraphs().get(0), ParagraphAlignment.CENTER);
	
					// Step Description
					c = r.getCell(1);
					c.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5600));
					c.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					String text = aoLst.get(i).getDesc();
					setCellTextWithCarriageReturn(c, " " + (text.startsWith("_INFO : ") ? text.substring(1) : text));
					mergeCellsHorizontally(stepsTable, i, 1, 2);
					
					// Custom Data Column
					c = r.getCell(3);
					c.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1600));
					setCellTextWithCarriageReturn(c, " ");
					setParaAndLineSpacing(c.getParagraphs().get(0), ParagraphAlignment.LEFT);
				} else {
					XWPFTableCell c = r.getCell(0);
					c.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
					c.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
					setCellTextWithCarriageReturn(c, " " + aoLst.get(i).getDesc());
					setParaAndLineSpacing(c.getParagraphs().get(0), ParagraphAlignment.LEFT);
					c.getCTTc().addNewTcPr().addNewShd().setFill("cfe2f3");
					c.getParagraphs().get(0).getRuns().get(0).setBold(true);
					mergeCellsHorizontally(stepsTable, i, 0, 3);
					j = j - 1;
				}
			}
			
			run.addBreak(BreakType.PAGE);
//			run.addBreak();
			
			run.setText("Steps with Screenshots");
			run.setBold(true);
			run.setFontSize(12);
			run.addBreak();
			paragraph.setAlignment(ParagraphAlignment.CENTER);
			
			XWPFParagraph paragraph2 = document.createParagraph();
			XWPFRun run2 = paragraph2.createRun();
			
			// Iterate through the ArtefactObjects and add step details
			int sideHeader = 0;
			for (ArtefactObject aoRef : aoLst) {
				if(!Objects.isNull(aoRef.getScreenImg())) {
					FileInputStream fImg = new FileInputStream(aoRef.getScreenImg());
					if (aoRef.getDesc().equalsIgnoreCase("Test script failed point")) {
						XWPFRun run3 = paragraph2.createRun();
						run3.setText(aoRef.getDesc());
						run3.setBold(true);
						run3.setColor("FF0000");
						run3.addBreak(BreakType.TEXT_WRAPPING);
						XWPFPicture picture = run3.addPicture(fImg, Document.PICTURE_TYPE_PNG, f.getName(), Units.toEMU(450), Units.toEMU(250));
						picture.getCTPicture().getSpPr().addNewLn().setW(Units.toEMU(2.25));
						picture.getCTPicture().getSpPr().getLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[]{(byte) 250,0,0});
					}
					else if (aoRef.getDesc().startsWith("_INFO : ")) {
						// Create a separate paragraph for the note
//						run2.setText(String.valueOf(stepCnt) + ". " + aoRef.getDesc().substring(1));
						String noteText = aoRef.getDesc().replaceFirst("^_", "");
						if (noteText.contains("\n")) {
							String[] lines = noteText.split("\n");
							System.out.println(lines.length);
							
							run2.setText(String.valueOf(stepCnt) + ". " + lines[0].strip());
							for (int i = 1; i < lines.length; i++) {
								String trimmedLine = lines[i].strip();
								run2.addBreak(BreakType.TEXT_WRAPPING);
								if (trimmedLine.isEmpty()) {
									continue;
								}
								else {
									run2.setText(trimmedLine);
								}
							}
						} else {
							run2.setText(String.valueOf(stepCnt) + ". " + noteText);
							
						}
						run2.setBold(false);
						run2.setColor("000000");
					}else {
						run2.setText(String.valueOf(stepCnt) + ". " + aoRef.getDesc());
						run2.setBold(false);
						run2.setColor("000000");
						run2.addBreak(BreakType.TEXT_WRAPPING);
						XWPFPicture picture = run2.addPicture(fImg, Document.PICTURE_TYPE_PNG, f.getName(), Units.toEMU(450), Units.toEMU(250));
						picture.getCTPicture().getSpPr().addNewLn().setW(Units.toEMU(1));
						picture.getCTPicture().getSpPr().getLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[]{(byte) 0,0,0});
					}
					
					if (stepCnt % 2 == 0 && (aoLst.size() - sideHeader) != stepCnt) {
						run2.addBreak(BreakType.PAGE);
					} else {
						run2.addBreak();
					}
					run2.addBreak();
					
					
//					if (!aoRef.getDesc().startsWith("_Note: ")) 
						stepCnt++;
				}else {
					sideHeader = sideHeader + 1;
				}
			}
			 // Write the document to the FileOutputStream
			document.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			// Close the FileOutputStream and document
			if (out != null)
				out.close();
			document.close();
		}
	}

	/**
	 * Adds a summary table to the given XWPFDocument.
	 *
	 * @param document the XWPFDocument to which the summary table will be added
	 */
	private static void addSummaryTable(XWPFDocument document) {
		
		// Create an empty paragraph to add spacing before the table
		document.createParagraph();
		
		// Get the required data for the summary table
		String process = isNotBlank(ThreadUtils.getPDFReportObj().getProcess()) ? ThreadUtils.getPDFReportObj().getProcess().trim() : "" ;
		String usecaseId = isNotBlank(ThreadUtils.getPDFReportObj().getUsecaseId()) ? ThreadUtils.getPDFReportObj().getUsecaseId().trim() : "" ;
		String user = isNotBlank(ThreadUtils.getPDFReportObj().getUser()) ? ThreadUtils.getPDFReportObj().getUser().trim() : "" ;
		String prerequisites = isNotBlank(ThreadUtils.getPDFReportObj().getPrerequisites()) ? ThreadUtils.getPDFReportObj().getPrerequisites().trim() : "" ;
		String notes = isNotBlank(ThreadUtils.getPDFReportObj().getNotes()) ? ThreadUtils.getPDFReportObj().getNotes().trim() : "" ;
		String description = isNotBlank(ThreadUtils.getPDFReportObj().getDescription()) ? ThreadUtils.getPDFReportObj().getDescription().trim() : "" ;
		
		// Create the first row of the summary table
		XWPFTable table1 = document.createTable(1,4);
		table1.setWidth("100%");
		table1.setBottomBorder(XWPFBorderType.THICK, 16, 16, "2E8BC0");
		table1.setTopBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		table1.setInsideHBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		table1.setInsideVBorder(XWPFBorderType.THICK, 8, 0, "2E8BC0");
		table1.setRightBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		table1.setLeftBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");

		// Create the second row of the summary table
		XWPFTable table2 = null;
		table2 = isNotBlank(notes) ? document.createTable(3, 4) : document.createTable(2, 4);
		
		table2.setWidth("100%");
		table2.setBottomBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
//		table2.setTopBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		table2.setInsideHBorder(XWPFBorderType.THICK, 8, 0, "2E8BC0");
		table2.setInsideVBorder(XWPFBorderType.THICK, 8, 0, "2E8BC0");
		table2.setRightBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		table2.setLeftBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");

		// Set column widths for the first row
		XWPFTableRow tableRow1 = table1.getRow(0);
		tableRow1.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1400));
		tableRow1.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2200));
		tableRow1.getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2200));
		tableRow1.getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2200));

		 // Set header cell text and formatting for the first row
		setHeaderCellTextWithCarriageReturn(tableRow1.getCell(0), "General Information", "");
		tableRow1.getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("4988D0");
		tableRow1.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
		tableRow1.getCell(0).getParagraphs().get(0).getRuns().get(0).setFontSize(12);
		setHeaderCellTextWithCarriageReturn(tableRow1.getCell(1), "Process:", process);
		setHeaderCellTextWithCarriageReturn(tableRow1.getCell(2), "Use Case Id:", usecaseId);
		setHeaderCellTextWithCarriageReturn(tableRow1.getCell(3), "User:", user);
		
		// Create the second row of the summary table
		XWPFTableRow tableRow2 = table2.getRow(0);
		tableRow2.getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1400));
		tableRow2.getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2200));
		tableRow2.getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2200));
		tableRow2.getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2200));
		
		// Set cell text and formatting for the second row
		tableRow2.getCell(0).setText("Description");
		tableRow2.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
		setCellTextWithCarriageReturn(tableRow2.getCell(1), description);
		
		// Create the third row of the summary table
		XWPFTableRow tableRow3 = table2.getRow(1);
		tableRow3.getCell(0).setText("Prerequisites");
		tableRow3.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
		setCellTextWithCarriageReturn(tableRow3.getCell(1), prerequisites);

		// Merge cells horizontally in the second row
		mergeCellsHorizontally(table2, 0, 1, 3);
		mergeCellsHorizontally(table2, 1, 1, 3);
				
		// Create the fourth row of the summary table
		if (isNotBlank(notes)) {
			XWPFTableRow tableRow4 = table2.getRow(2);
			tableRow4.getCell(0).setText("Notes");
			tableRow4.getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
			setCellTextWithCarriageReturn(tableRow4.getCell(1), notes);
			mergeCellsHorizontally(table2, 2, 1, 3);
		}
	}

	/**
	 * Sets the header cell text with carriage return in the specified XWPFTableCell.
	 *
	 * @param cell   the XWPFTableCell to set the header cell text
	 * @param header the header text to be displayed in the cell
	 * @param text   the text to be displayed below the header, separated by line breaks
	 */
	private static void setHeaderCellTextWithCarriageReturn(XWPFTableCell cell,String header, String text) {
		XWPFRun pre =  cell.getParagraphs().get(0).createRun();
		setParaAndLineSpacing(cell.getParagraphs().get(0), ParagraphAlignment.LEFT);
		String [] str = text.split("\n");
		pre.setText(header);
		if(!header.equalsIgnoreCase("General Information"))
			pre.addBreak();
		for (int i = 0; i < str.length; i++) {
			pre.setText(str[i]);
			if (i != str.length - 1)
				pre.addBreak();
		}
	}
	
	/**
	 * Sets the text with carriage return in the specified XWPFTableCell.
	 *
	 * @param cell the XWPFTableCell to set the text
	 * @param text the text to be displayed in the cell, separated by line breaks
	 */
	private static void setCellTextWithCarriageReturn(XWPFTableCell cell, String text) {
		XWPFRun pre =  cell.getParagraphs().get(0).createRun();
		setParaAndLineSpacing(cell.getParagraphs().get(0), ParagraphAlignment.LEFT);
		String [] str = text.split("\n");
		for (int i = 0; i < str.length; i++) {
			pre.setText(str[i].strip());
			if (i != str.length - 1)
				pre.addBreak();
		}
	}
	
	/**
	 * Adds header and footer to the specified XWPFDocument.
	 *
	 * @param document     the XWPFDocument to add the header and footer to
	 * @param testcaseName the name of the testcase for the header
	 * @throws InvalidFormatException if the format of the document is invalid
	 * @throws IOException            if an I/O error occurs
	 */
	private static void addHeaderFooter(XWPFDocument document, String testcaseName) throws InvalidFormatException, IOException {
		
		// create header-footer
		XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
		if (headerFooterPolicy == null)
			headerFooterPolicy = document.createHeaderFooterPolicy();

		// create header start
		addHeader(headerFooterPolicy, testcaseName);
		
		// Add footer
		addFooter(headerFooterPolicy);
	}

	private static void mergeCellsHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
		for (int cellIndex = fromCol; cellIndex <= toCol; cellIndex++) {
			XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
			if (cellIndex == fromCol) {
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
			} else {
				cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
			}
		}
	}
	
	/**
	 * Merges cells horizontally in the specified XWPFTable.
	 *
	 * @param table    the XWPFTable in which to merge cells
	 * @param row      the row index of the cells to merge
	 * @param fromCol  the starting column index of the cells to merge
	 * @param toCol    the ending column index of the cells to merge
	 */
	private static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow) {
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
			} else {
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	/**
	 * Adds a footer to the document using the provided header footer policy.
	 *
	 * @param headerFooterPolicy the header footer policy to use
	 */
	private static void addFooter(XWPFHeaderFooterPolicy headerFooterPolicy) {

		// create footer start
		XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
		XWPFTable footerTabel = footer.createTable(1, 3);
		footerTabel.setLeftBorder(XWPFBorderType.NONE, 0, 0, "0000");
		footerTabel.setRightBorder(XWPFBorderType.NONE, 0, 0, "0000");
		footerTabel.setInsideVBorder(XWPFBorderType.NONE, 0, 0, "0000");
		footerTabel.setBottomBorder(XWPFBorderType.NONE, 0, 0, "0000");
		footerTabel.setWidth("100%");
		footerTabel.getRow(0).getCell(0).setText("\u00A9 Infor.com");

		// Add page number to the footer
		XWPFParagraph p2 = footerTabel.getRow(0).getCell(1).getParagraphs().get(0);
		XWPFRun r2= p2.createRun();
		r2.setText("Doc Ref: "+ DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(LocalDate.now()));
//		p2.getCTP().addNewFldSimple().setInstr("TIME \\@ \"dd-MMM-yyyy\" \\* MERGEFORMAT");
		p2.setAlignment(ParagraphAlignment.CENTER);
		p2.setBorderTop(Borders.NONE);
				
		// Add page number to the footer
		XWPFParagraph paragraph = footerTabel.getRow(0).getCell(2).getParagraphs().get(0);
		paragraph.getCTP().addNewFldSimple().setInstr("PAGE \\* MERGEFORMAT");
		XWPFRun run = paragraph.createRun();
		run.setText(" of ");
		paragraph.getCTP().addNewFldSimple().setInstr("NUMPAGES \\* MERGEFORMAT");
		paragraph.setAlignment(ParagraphAlignment.RIGHT);
		paragraph.setBorderTop(Borders.NONE);
		
		footerTabel.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
		footerTabel.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
		footerTabel.getRow(0).getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
	}

	

	/**
	 * Adds a header to the document using the provided header footer policy and testcase name.
	 *
	 * @param headerFooterPolicy the header footer policy to use
	 * @param testcaseName       the name of the testcase
	 * @throws InvalidFormatException if the format is invalid
	 * @throws IOException            if an I/O error occurs
	 */
	private static void addHeader(XWPFHeaderFooterPolicy headerFooterPolicy, String testcaseName) throws InvalidFormatException, IOException {
//		String base64LogoImage = "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";
		String base64LogoImage = TestData.getCompanyLogo();
		/**
		 * Converts the base64 image string to a byte array.
		 */
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64LogoImage);
		
		/**
		 * Creates an input stream from the image byte array.
		 */
		InputStream fImg = new ByteArrayInputStream(imageBytes);
		
		 //Creates a header for the XWPFHeaderFooterPolicy with default settings.
		XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
		
		/*
		 * Creates a table with one row and two columns in the header.
		 * Sets the width of the table to 100%.
		 */
		XWPFTable headerTabel = header.createTable(1, 2);
		headerTabel.setWidth("100%");
		
		/*
		 * Retrieves the first row from the header table.
		 */
		XWPFTableRow headerRow = headerTabel.getRow(0);
		
		/*
		 * Retrieves the paragraph in the first cell of the header row and creates a run.
		 * Adds a picture to the run using the provided image input stream and picture type.
		 */
		XWPFParagraph runLogo = headerRow.getCell(0).getParagraphs().get(0);
		XWPFRun run = runLogo.createRun();
		run.addPicture(fImg, Document.PICTURE_TYPE_BMP, "Infor Logo", Units.toEMU(30), Units.toEMU(30));
		
		//Sets the paragraph alignment and line spacing for the header cell containing the logo.
		setParaAndLineSpacing(headerRow.getCell(0).getParagraphs().get(0), ParagraphAlignment.CENTER);
		headerRow.getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
		
		/*
		 * Creates a run in the paragraph of the second cell in the header row.
		 * Sets the text, text position, bold, and italic properties.
		 */      
		XWPFRun tcId = headerTabel.getRow(0).getCell(1).getParagraphs().get(0).createRun();
		tcId.setTextPosition(2);
		tcId.setText(wrapLetters("\n\r \t " + testcaseName));
		tcId.setBold(true);
		tcId.setItalic(true);
		
		/*
		 * Sets the paragraph alignment and line spacing for the header cell containing the testcase name.
		 */
		setParaAndLineSpacing(headerTabel.getRow(0).getCell(1).getParagraphs().get(0), ParagraphAlignment.LEFT);
		headerRow.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
	}
	   
    // Helper method to wrap letters
    private static String wrapLetters(String text) {
        StringBuilder wrappedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            wrappedText.append(c).append("\u200B"); // Zero-width space after each letter
        }
        return wrappedText.toString();
    }

	/**
	 * Sets the paragraph alignment and line spacing properties for the specified XWPFParagraph object.
	 *
	 * @param para The XWPFParagraph object to modify.
	 * @param str  The desired alignment for the paragraph.
	 */
	private static void setParaAndLineSpacing(XWPFParagraph para, ParagraphAlignment str) {
		
		// Sets the spacing properties to 0 to remove any additional spacing
		para.setSpacingAfter(0);
		para.setSpacingBefore(0);
		para.setSpacingAfterLines(0);
		para.setSpacingBeforeLines(0);
		para.setWordWrapped(true);
		
		// Sets the spacing between lines to 0.9
		para.setSpacingBetween(0.9);
		
		// Sets the alignment of the paragraph
		para.setAlignment(str);
	}
	
	/**
	 * Adds a table for steps header to the provided XWPFDocument.
	 *
	 * @param document The XWPFDocument to add the table to.
	 * @throws InvalidFormatException If the format of the document is invalid.
	 * @throws IOException            If an I/O error occurs.
	 */
	private static void addTableForStepsHeader(XWPFDocument document) throws InvalidFormatException, IOException {
//		String base64Image = "";
		
		// Convert base64 image to byte array
//		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
//		InputStream fImg = new ByteArrayInputStream(imageBytes);
		
		// add table for steps header
		document.createParagraph().createRun().addBreak();
		XWPFTable stepsTableHeader = document.createTable(1, 4);
		stepsTableHeader.setWidth("100%");
		
		// Set logo in the first cell of the table
//		XWPFParagraph runLogo = stepsTableHeader.getRow(0).getCell(0).getParagraphs().get(0);
//		XWPFRun run = runLogo.createRun();
//		run.addPicture(fImg, Document.PICTURE_TYPE_BMP, "Steps", Units.toEMU(30), Units.toEMU(30));
		stepsTableHeader.getRow(0).getCell(0).setText("Steps");
		setParaAndLineSpacing(stepsTableHeader.getRow(0).getCell(0).getParagraphs().get(0), ParagraphAlignment.CENTER);
		stepsTableHeader.getRow(0).getCell(0).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
		stepsTableHeader.getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(0).setBold(true);
		stepsTableHeader.getRow(0).getCell(0).getParagraphs().get(0).getRuns().get(0).setFontSize(12);
		stepsTableHeader.getRow(0).getCell(0).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
		
//		stepsTableHeader.getRow(0).getCell(1).setText(" Step Description");
//		setParaAndLineSpacing(stepsTableHeader.getRow(0).getCell(1).getParagraphs().get(0), ParagraphAlignment.CENTER);
//		stepsTableHeader.getRow(0).getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
//		stepsTableHeader.getRow(0).getCell(1).getParagraphs().get(0).getRuns().get(0).setBold(true);
//		stepsTableHeader.getRow(0).getCell(1).getParagraphs().get(0).setAlignment(ParagraphAlignment.LEFT);
		
		stepsTableHeader.getRow(0).getCell(3).setText("Customer Data Value");
		setParaAndLineSpacing(stepsTableHeader.getRow(0).getCell(3).getParagraphs().get(0), ParagraphAlignment.CENTER);
		stepsTableHeader.getRow(0).getCell(3).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
		stepsTableHeader.getRow(0).getCell(3).getParagraphs().get(0).getRuns().get(0).setBold(true);
		stepsTableHeader.getRow(0).getCell(3).getParagraphs().get(0).getRuns().get(0).setFontSize(12);
		stepsTableHeader.getRow(0).getCell(3).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
		
		// Set properties for the first cell
		stepsTableHeader.getRow(0).setHeight(600);
		stepsTableHeader.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(650));
		
		// Set properties for the second cell
		stepsTableHeader.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2800));
		
		// Set properties for the third cell
		stepsTableHeader.getRow(0).getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2800));
		
		// Set properties for the fourth cell
		stepsTableHeader.getRow(0).getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1800));
		
//		// Set properties for the fifth cell
//		stepsTableHeader.getRow(0).getCell(4).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1400));
//		
		// Merge cells horizontally
		mergeCellsHorizontally(stepsTableHeader, 0, 0, 2);
		
		// Set borders for the table
		stepsTableHeader.setBottomBorder(XWPFBorderType.THICK, 16, 16, "2E8BC0");
		stepsTableHeader.setTopBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		stepsTableHeader.setInsideHBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		stepsTableHeader.setInsideVBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
//		stepsTableHeader.setInsideVBorder(XWPFBorderType.NONE, 8, 0, "2E8BC0");
		stepsTableHeader.setRightBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		stepsTableHeader.setLeftBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
		
		// Set background color for the first cell
		stepsTableHeader.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("ccddff");
//		stepsTableHeader.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewShd().setFill("ccddff");
		stepsTableHeader.getRow(0).getCell(3).getCTTc().addNewTcPr().addNewShd().setFill("ccddff");
	}
	
	/**
	 * Adds a table for steps to the document.
	 * 
	 * @param document The XWPFDocument to which the table will be added.
	 * @param aoLst    The list of ArtefactObject representing the steps.
	 * @return The created XWPFTable for steps.
	 */
	private static XWPFTable addTableForSteps(XWPFDocument document, List<ArtefactObject> aoLst) {
		// Create table for steps
		XWPFTable stepsTable = document.createTable(aoLst.size(), 4);
		if(aoLst.size()!=0) {
	
			// Set border from each side
			stepsTable.setWidth("100%");
			stepsTable.setBottomBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
			stepsTable.setLeftBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
			stepsTable.setRightBorder(XWPFBorderType.THICK, 16, 0, "2E8BC0");
			stepsTable.setInsideHBorder(XWPFBorderType.THICK, 8, 0, "2E8BC0");
			stepsTable.setInsideVBorder(XWPFBorderType.THICK, 8, 0, "2E8BC0");
	
			// Set width for each cell
			stepsTable.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(600));
			stepsTable.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2800));
			stepsTable.getRow(0).getCell(2).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2800));
			stepsTable.getRow(0).getCell(3).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1800));
	//		stepsTable.getRow(0).getCell(4).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1400));
		}
		
		return stepsTable;
	}
}
