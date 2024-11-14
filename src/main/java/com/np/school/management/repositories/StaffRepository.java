package com.np.school.management.repositories;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.np.school.management.entities.StaffDetails;

public interface StaffRepository extends JpaRepository<StaffDetails, Integer>{

	@Query("Select s from StaffDetails s where LOWER(s.department) = LOWER(:departmentName)")
	Optional<List<StaffDetails>> findByDepartmentIgnoringCase(String departmentName);

}
