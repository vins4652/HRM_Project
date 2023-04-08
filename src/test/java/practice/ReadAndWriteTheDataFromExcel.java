package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadAndWriteTheDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		readDataFromExcel(".\\src\\test\\resources\\Testdata.xlsx","Sheet1",1);
		//writeDataIntoExcel(".\\src\\test\\resources\\Testdata.xlsx","RMGYantra",1);

	}
	
	public static void readDataFromExcel(String filePath, String sheetname, int fromRow) throws EncryptedDocumentException, IOException {
		//create objet for physical file
		FileInputStream fis = new FileInputStream(filePath);
		//get the Sheet
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(fromRow);
		Cell cell; 
		for (int i = 0; i < row.getLastCellNum(); i++) {
			cell = row.getCell(i);
			String value = cell.getStringCellValue();
			System.out.println(value);
		}
		workbook.close();
	}
	
	public static void writeDataIntoExcel(String filePath, String sheetname, int toRow) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		//get the Sheet
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(toRow);
		Cell cell = row.createCell(0);
		cell.setCellValue("Qspider");
		FileOutputStream fout = new FileOutputStream(filePath);
		workbook.write(fout);
		workbook.close();
	}
}
