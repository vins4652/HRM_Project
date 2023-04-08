package practice;

import java.util.HashMap;
import java.util.Map.Entry;

import org.testng.annotations.Test;

public class DataProviderFromAnotherClass {

	@Test(dataProvider = "dataForNextClass", dataProviderClass = DataProviderFromSameClass.class)
	public void dataFromDiffclass(String name, String company) {

		System.out.println(name + "\t:\t" + company);
	}

	@Test(dataProviderClass = DataProviderFromSameClass.class, dataProvider = "empdata")
	public void expDataAndResults(String expData, String expResult) {
		System.out.println(expData + "\t: " + expResult);
	}

	@Test(dataProvider = "excelToMapToObjToScriptInMap", dataProviderClass = DataProviderFromSameClass.class)
	public void testDataFromObjMap(HashMap<String, String> map) {
		
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			System.out.println(key+":\t"+val);
			
		}
	}
	
	@Test(dataProvider = "dataFromExcel", dataProviderClass = DataProviderFromSameClass.class)
	public void readTheDataFromExcelToDP_ToScript(String expName, String expdata) {
		System.out.println(expName+":\t"+expdata);
	}

}
