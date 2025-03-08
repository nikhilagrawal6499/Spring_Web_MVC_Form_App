package com.nikhilit.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nikhilit.entities.StudentEntity;
import com.nikhilit.models.Student;
import com.nikhilit.repository.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	// method to load Student form

	@GetMapping("/")
	public String loadForm(Model model) {

		loadFormData(model);

		return "index";
	}

	private void loadFormData(Model model) {
		List<String> coursesList = new ArrayList<>();
		coursesList.add("Java");
		coursesList.add("Python");
		coursesList.add("AWS");
		coursesList.add("DevOps");

		List<String> timingsList = new ArrayList<>();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");

		Student student = new Student();

		model.addAttribute("courses", coursesList);
		model.addAttribute("timings", timingsList);
		model.addAttribute("student", student);
	}
	// method to save student data

	@PostMapping("/save")
	public String handleSubmit(Student s, Model model) {
		System.out.println(s);

//		logic to save

		StudentEntity entity = new StudentEntity();
		// copy data from model object to entity object
		BeanUtils.copyProperties(s, entity);
		entity.setTimings(Arrays.toString(s.getTimings()));

		studentRepository.save(entity);

		model.addAttribute("msg", "Student Saved");

		loadFormData(model);

		return "index";
	} 
	// method to display saved student data

	@GetMapping("/viewStudents")
	public String getStudentData(Model model) {

		// logic to fetch students data
		List<StudentEntity> studentsList = studentRepository.findAll();

		model.addAttribute("students", studentsList);

		return "data";
	}
}
