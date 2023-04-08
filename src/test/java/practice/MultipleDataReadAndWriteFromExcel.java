package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class MultipleDataReadAndWriteFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		readDataFromExcel(".\\src\\test\\resources\\Testdata.xlsx","Sheet1",1);
		writeDataIntoExcel(".\\src\\test\\resources\\Testdata.xlsx","Sheet1",1);

	}
	
	public static void readDataFromExcel(String filePath, String sheetname, int fromRow) throws EncryptedDocumentException, IOException {
		//create objet for physical file
		FileInputStream fis = new FileInputStream(filePath);
		//get the Sheet
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
//		Row row = sheet.getRow(fromRow);
//		Cell cell;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
				Cell cell = sheet.getRow(i).getCell(j);
				String value = cell.getStringCellValue();
				System.out.print(value+"		");
			}
			System.out.println();
		}
		workbook.close();
	}
	
	public static void writeDataIntoExcel(String filePath, String sheetname, int toRow) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		//get the Sheet
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		for (int i = 5; i <= 10; i++) {
			sheet.createRow(i);
			for (int j = 0; j < 3; j++) {
				sheet.getRow(i).createCell(j).setCellValue("Qspider");
				FileOutputStream fout = new FileOutputStream(filePath);
				workbook.write(fout);
			}
			System.out.println();
		}
		workbook.close();
	}
}
