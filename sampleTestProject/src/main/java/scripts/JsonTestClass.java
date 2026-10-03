package scripts;

import dataUtils.JsonDataFile;
import testBase.TestData;

public class JsonTestClass {

	public static void main(String[] args) throws Exception {
		JsonDataFile js = TestData.getJsonDataFile("SampleJsonBody.json");
		System.out.println(js.getContent());
		System.out.println(js.valueAtJpath("$.store.bicycle.color"));
		String p = JsonDataFile.updateValueAtJpath(js.getContent(), "$.store.bicycle.color", "456");
		System.out.println(JsonDataFile.valueAtJpathFromJsonSting(p,"$.store.bicycle.color"));
		System.out.println("Updated Json" + p);
	}

}
