package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO_Database_Concept {
	public static void main(String[] args) throws Exception {
		//1)create student class, add all the required fields about the object
		//2)create DATA ACCESS OBJECT(DAO) class for the student
		//3)design the methods with the required types ex: add, get, delete
		//4)connect the dao states with the student class states to fetch and insert
		//5)use the methods in main class to operate
		
		/*
		 * Prerequisite : Table should be present in the data base with the given column name
		 */
		
		//to get the student details with roll num
		studentDAO dao = new studentDAO();
		Student s1 = dao.getStudentDetails(1);
		System.out.println(s1.rollnum);
		System.out.println(s1.studentName);
		
		//to get the student details with the student name
		Student s2 = dao.getStudentDetails("Najeer");
		System.out.println(s2.rollnum);
		System.out.println(s2.studentName);
		dao.connectToStudentDetails();
		
		// to add the student into the table
		Student s3 = new Student();
		s3.rollnum = 3;
		s3.studentName = "Vinay";
		dao.addStudent(s3);
		
		//to get the details of the student with student name
		Student s4 = dao.getStudentDetails("Vinay");
		System.out.println(s4.rollnum);
		System.out.println(s4.studentName);
		
		//to delete the record of the student with the student name
		//dao.removeStudent("Vinay");
	}
	
	
}

class studentDAO{
	private Student st = new Student();
	private Connection con=null;
	public void connectToStudentDetails() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet47", "root", "root");
	}
	
	public void addStudent(Student s) {
		String Quarry = "insert into student value (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(Quarry);
			ps.setInt(1, s.rollnum);
			ps.setString(2, s.studentName);
			int count = ps.executeUpdate();
			if(count!=0) {
			System.out.println("Student Details sucessfully updated");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Student getStudentDetails(int rollnum) throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet47", "root", "root");
			Statement stmt = con.createStatement();
			st.rollnum=rollnum;
			String Querry = "select studentName from student where rollnum = "+rollnum;
			ResultSet res = stmt.executeQuery(Querry);
			res.next();
			String studentName = res.getString(1);
			st.studentName=studentName;
			return st;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public Student getStudentDetails(String studentName) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet47", "root", "root");
			Statement stmt = con.createStatement();
			st.studentName=studentName;
			String Querry = "select rollnum from student where studentName = '"+studentName+"';";
			ResultSet res = stmt.executeQuery(Querry);
			res.next();
			int rollNum = res.getInt(1);
			st.rollnum=rollNum;
			return st;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public void removeStudent(String studentName) {
		try {
			Statement stmt = con.createStatement();
			String querry = "DELETE FROM student WHERE studentName ='"+studentName+"';";
			int count = stmt.executeUpdate(querry);
			if(count!=0) {
				System.out.println("Student details with "+studentName+" Name has been removed from the database");
			}
			if (count==0) {
				System.out.println("Student details with "+studentName+" Name has not found in Database");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class Student{
	int rollnum;
	String studentName;
}
