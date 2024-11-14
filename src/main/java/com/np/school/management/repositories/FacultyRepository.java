package com.np.school.management.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.np.school.management.entities.FacultyDetails;

public interface FacultyRepository extends JpaRepository<FacultyDetails, Integer>{

	/**
	 * @param facultyId
	 * @return
	 */
	@Query("Select f from FacultyDetails f where f.id=:facultyId")
	Optional<FacultyDetails> findByFacultyIdAndFetchStudentDetails(int facultyId);

}
