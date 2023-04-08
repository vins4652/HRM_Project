package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.GenericUtility.ExcelUtility;
import com.GenericUtility.IPathConstant;

public class DataProviderFromSameClass {

	@Test(dataProvider = "Qspider")
	public void TYSS(String studentName, String conpanyName) {
		System.out.println(studentName+":\t This persion is trained in TYSS");
		System.out.println(studentName + ":\t is placed in this company -->"+conpanyName);
	}
	
	
	@DataProvider
	public Object[][] Qspider() {
		Object[][] selenium=new Object[3][2];
		selenium[0][0]="Harikrishna";
		selenium[0][1]="Osborn, USA";
		selenium[1][0]="Najeer";
		selenium[1][1]="FastFive, Dubai";
		selenium[2][0]="Anupam";
		selenium[2][1]="Stark Industries, New York";
		
		return selenium;
	}
	
	@DataProvider(name = "dataForNextClass")
	public Object[][] Qspider1() {
		Object[][] selenium=new Object[3][2];
		selenium[0][0]="Harikrishna";
		selenium[0][1]="Osborn, USA";
		selenium[1][0]="Najeer";
		selenium[1][1]="FastFive, Dubai";
		selenium[2][0]="Anupam";
		selenium[2][1]="Stark Industries, New York";
		
		return selenium;
	}
	
	@DataProvider(name = "empdata")
	public Object[][] excelToScriptVIAdataProvider() throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.EXCELFILEPATH);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("expdata");
		int repitation = sh.getLastRowNum()+1;
		int dataSets = sh.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[repitation][dataSets];
		for (int i = 0; i <repitation ; i++) {
			for (int j = 0; j < dataSets; j++) {
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
	
	
	@DataProvider
	public Object[][] excelToMapToObjToScriptInMap() throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(IPathConstant.EXCELFILEPATH);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("expdata");
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < sh.getLastRowNum(); i++) {
			String key=sh.getRow(i).getCell(0).getStringCellValue();
			String value = sh.getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
		}
		
		Object[][] obj = new Object[1][1];
		obj[0][0] = map;
		return obj;
		
	}
	
	@DataProvider
	public Object[][] dataFromExcel() throws EncryptedDocumentException, IOException {
		ExcelUtility xLib = new ExcelUtility();
		Object[][] obj = xLib.readMultipledataForDataProvider("expdata");
		return obj;
		
	}
	
}	