package practice;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ReadTheDatabaseDataTest {
	
	
	
	public static void main(String[] args) throws SQLException {
		putDataToJDBC("Employee1");
		getDataFromJDBC("Employee1");
		
	}
	
	public static void getDataFromJDBC(String table_name) throws SQLException {
		
			
			//Step 1: Create object for driver
			Driver driver = new Driver();
			//Step 2: register the database
			DriverManager.registerDriver(driver);
			//Step 3: get connection for the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet47", "root", "root");
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet47", "root@%", "root");
			//Step 4: create the statement to execute querry
			Statement stmt = con.createStatement();
			String querry = "Select * From "+table_name+";";
			//Step 5: execute querry 
			ResultSet result = stmt.executeQuery(querry);
			while(result.next()) {
				String res = result.getInt(1)+ " " + result.getString(2);
				System.out.println(res);
			}
			
//			do{
//				String res = result.getString(1)+ " " + result.getString(2);
//				System.out.println(res);
//			}while(result.next());
			
			String deleteTable= "Drop table "+table_name+";";
			stmt.executeUpdate(deleteTable);
			con.close();
		
	}
	
	public static void putDataToJDBC(String table_name) throws SQLException {
			
			//Step 1: Create object for driver
			Driver driver = new Driver();
			//Step 2: register the database
			DriverManager.registerDriver(driver);
			//Step 3: get connection for the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet47", "root", "root");
			//Step 4: create the statement to execute querry
			Statement stmt = con.createStatement();
			String createTable = "create table "+table_name+"(empID int(4) unique, empName varchar(20) not null);";
			//Step 5: execute querry 
			stmt.executeUpdate(createTable);
			String insertValue = "insert into "+table_name+" values(083, 'AshwiniB'),(081, 'Sindhu');";
			stmt.executeUpdate(insertValue);
			con.close();
	}
}
