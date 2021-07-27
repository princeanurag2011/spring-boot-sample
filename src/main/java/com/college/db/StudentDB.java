package com.college.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.college.Student;

public class StudentDB {

	@Autowired
	private Student student;
	private List<Student> studentList;
	
	
	public Connection getConnection() throws SQLException {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "Devops@321");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	

	public List<Student> fetchAllStudentDetials() {

		studentList = new ArrayList<Student>();
		Connection connection =null;

		try {
			
			connection = getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from studentdb.student");
			while (rs.next()) {

				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
				student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3));
				studentList.add(student);
			}
			connection.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return studentList;
	}
	
	
	public Student fetchSpecificStudentData(int id) {
		
		Connection connection =null;
		Student s = null;
		try {
			connection = getConnection();
			PreparedStatement ps =connection.prepareStatement("select * from studentdb.student where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
				s = new Student(rs.getInt(1), rs.getString(2), rs.getString(3));			
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to fetch data.!");
		}	
		
		return s;		
	}
	
	public String CreateStudentDetails(Student student) {
		
		Connection connection =null;
		String status = null;
		try {
			connection = getConnection();
			PreparedStatement ps =connection.prepareStatement("insert into STUDENTDB.STUDENT VALUES(?,?,?)");
			ps.setInt(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getAddress());
			int i=ps.executeUpdate();  
			System.out.println(i+" records inserted");  
			status =  i+"records inserted";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status= "Unable to Create a student record";
		}	
		
		return status;	
	}

}
