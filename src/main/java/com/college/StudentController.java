package com.college;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.college.Student;
import com.college.db.StudentDB;
@RestController
public class StudentController {

	@GetMapping("/hello")
	public String welcome() {

		return "Hello";
	}

	@GetMapping("/getallstudents")
	public List<Student> getAllStudents() {

		List<Student> studentList = new ArrayList<Student>();

		try {
			StudentDB studentDB = new StudentDB();

			studentList = studentDB.fetchAllStudentDetials();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentList;

	}
	
	
	@GetMapping("/getstudent")
	public Student getStudent(@RequestParam("id") int id) {

		Student student = null;
		System.out.println("inside getstudent method:"+id);
		try {
			StudentDB studentDB = new StudentDB();
		   // int idnum= Integer.parseInt(id);
			student = studentDB.fetchSpecificStudentData(id);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to fetch student details.!");
		}

		return student;

	}
	
	@PostMapping("/createstudent")
	//@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String createStudentData(@RequestBody Student student) {
		
		System.out.println("Inside post method..!");
		String status = null;
		try {
			System.out.println(student.getId());
			System.out.println(student.getName());
			System.out.println(student.getAddress());
			
			StudentDB studentDB = new StudentDB();
			status = studentDB.CreateStudentDetails(student);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "Unable to insert student data";
		}
		
		
		return status;
	}

	@DeleteMapping("/deletestudent")
	public String deleteStudent(@RequestParam("id") int id) {

		String status=null;
		System.out.println("inside getstudent method:"+id);
		try {
			StudentDB studentDB = new StudentDB();
		   // int idnum= Integer.parseInt(id);
			status = studentDB.deleteStudentDetails(id);		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB-error:unable to delete student record please verify the ID" +id);
			status="DB-error:unable to delete student record please verify the ID" +id;
		}

		return status;

	}
}
