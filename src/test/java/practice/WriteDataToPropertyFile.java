package practice;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class WriteDataToPropertyFile {
	
	public static void main(String[] args) throws IOException {

		//open file in in read stream mode
		//pass the file path in string
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\RMGYantra.properties");
		//create object for the properties class
		Properties pObj= new Properties();
		//load the fis to the properties class
		pObj.load(fis);
		//assign the key and its value
		//use setProperty("key","value") method to set it
		pObj.setProperty("Hero", "Harikrishna");
		//open the file in write mode
		//pass the file path in string
		FileWriter writer = new FileWriter("C:\\Users\\Admin\\eclipse-workspace\\com.humanresourcemanagement\\src\\test\\resources\\RMGYantra.properties");
		//use store method to write the data in properties file
		pObj.store(writer, "Also known as MJ of QSpider");
	}

}
