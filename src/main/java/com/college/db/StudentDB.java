package com.college.db;

import java.sql.Connection;
import java.sql.DriverManager;
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

	public List<Student> fetchStudentDetials() {

		studentList = new ArrayList<Student>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "*******");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from studentdb.student");
			while (rs.next()) {

				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
				student = new Student(rs.getInt(1), rs.getString(2), rs.getString(3));
				studentList.add(student);
			}
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentList;
	}

}
