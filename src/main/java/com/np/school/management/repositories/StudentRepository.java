package com.np.school.management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.np.school.management.entities.StudentDetails;

public interface StudentRepository extends JpaRepository<StudentDetails, Integer>{

	@Query("Select s from StudentDetails s Where s.classEnrolled = :className")
	Optional<List<StudentDetails>> findByClassEnrolled(String className);

}
