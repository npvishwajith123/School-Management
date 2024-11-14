package com.np.school.management.controller;


import java.util.List;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.np.school.management.entities.StudentDetails;
import com.np.school.management.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StudentsAPIController {

	private final StudentRepository studentRepository;
	
	@GetMapping("/students/{studentId}")
	public ResponseEntity<?> getStudentDetails(@PathVariable int studentId){
		try {
			StudentDetails student = studentRepository.findById(studentId).
					orElseThrow(() -> new Exception("No Student found"));
			return ResponseEntity.ok().
					cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(student);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
		}
	}
	
	@GetMapping("/students/{studentId}/fees")
	public ResponseEntity<?> retrieveStudentFeeRecords(@PathVariable int studentId) {
		try {
			StudentDetails student = studentRepository.findById(studentId).
					orElseThrow(() -> new Exception("No Student found"));
			return ResponseEntity.ok(student.getFees());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
		}
	}
	
	@GetMapping("/classes/{className}/students")
	public ResponseEntity<?> retrieveStudentsByClass(@PathVariable String className) {
		try {
			List<StudentDetails> students = studentRepository.findByClassEnrolled(className).
					orElseThrow(() -> new Exception("No Student found"));
			return ResponseEntity.ok(students);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
		}
	}

}
	
