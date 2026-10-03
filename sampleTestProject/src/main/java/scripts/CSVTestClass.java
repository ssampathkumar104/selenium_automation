package scripts;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import testBase.BaseClass;
import testBase.TestData;

public class CSVTestClass extends BaseClass{
	
	@BeforeMethod
	public void sampleLog() {
		String DataDir = "TCLN-666";
		TestData.setDataFolder(DataDir);
	}
	
	@Test
	public void csv() throws Exception {
		System.out.println(TestData.getCSVFile("Book1.csv").getTable().get(0, 0));
		System.out.println(TestData.getCSVFile("Book1.csv",",").getTable().getColumnIndex("Data"));
		System.out.println(TestData.getCSVFile("Book1.csv").getTable().column(0));
		System.out.println(TestData.getCSVFile("Book1.csv").getTable().get(0, 0));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get(0, 1));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get(1, 0));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get(1, 1));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get(2, 0));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get(2, 1));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get(3, 0));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get(3, 1));
//		System.out.println("Get "+TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().get("Data", 1));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().isEmpty());
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().getColumnIndex("Data"));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().getColumnIndex("Sno"));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().column(0));
//		System.out.println(TestData.getCSVFile("C:\\shiva\\Book1.csv").getTable().column(1));
	}
}
