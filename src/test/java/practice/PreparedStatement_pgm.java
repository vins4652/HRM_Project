package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatement_pgm {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//Prerequisites: Table should be created before running it
		//or run the below command in mysql: in sdet47 database
		//create table employee value (empID int(4) unique, empName varchar(20) not null);
		
		String url = "jdbc:mysql://localhost:3306/sdet47";
		String username = "root";
		String password = "root";
		int empID = 2;
		String empName = "Najeer";
		String quarry = "insert into employee value (?,?)";
		
		//register the driver without creating the object of DRIVER
		Class.forName("com.mysql.cj.jdbc.Driver");
		//get the connection with the database
		Connection con = DriverManager.getConnection(url, username, password);
		//prepare statement to execute the same quarry with multiple values using the variables
		PreparedStatement stmt = con.prepareStatement(quarry);
		//insert the value into the column using column index
		stmt.setInt(1, empID);
		stmt.setString(2, empName);
		//execute the update 
		int count = stmt.executeUpdate();
		System.out.println(count+" : Detail/s has been added in to the table");
		stmt.close();
		con.close();
		
		
	}

}
