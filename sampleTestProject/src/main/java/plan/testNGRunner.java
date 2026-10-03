package plan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.SuiteXmlParser;
import org.testng.xml.XmlSuite;

public class testNGRunner {
	
	static TestNG tg;
	public static void main(String[] args) throws IOException {
		tg =  new TestNG();
//		System.out.println("Hello worlds");

		InputStream inputStream  = testNGRunner.class.getResourceAsStream("mutipleSuites.xml");
		System.out.println(inputStream);
		
		TestNG testNG = new TestNG();
		       SuiteXmlParser suiteXmlParser = new SuiteXmlParser();
		        List<XmlSuite> suites = new ArrayList<>();
		        XmlSuite xmlSuite = suiteXmlParser.parse("mutipleSuites.xml", inputStream, true);
		        suites.add(xmlSuite);
		        testNG.setXmlSuites(suites);
		        testNG.setSuiteThreadPoolSize(3);
		        testNG.run();
	}
}
