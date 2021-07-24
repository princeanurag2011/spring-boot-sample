package com.college;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.db.StudentDB;

@RestController
public class StudentController {

	@GetMapping("/hello")
	public String welcome() {

		return "Hello";
	}

	@GetMapping("/getstudents")
	public List<Student> getStudents() {

		List<Student> studentList = new ArrayList<Student>();

		try {
			StudentDB studentDB = new StudentDB();

			studentList = studentDB.fetchStudentDetials();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentList;

	}

}
