package com.np.school.management.controller;

import java.net.URI;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.np.school.management.entities.AdminDatabase;
import com.np.school.management.entities.FacultyDetails;
import com.np.school.management.entities.FeesManagement;
import com.np.school.management.entities.StaffDetails;
import com.np.school.management.repositories.AdminDbRepository;
import com.np.school.management.repositories.FacultyRepository;
import com.np.school.management.repositories.FeesManagementRepository;
import com.np.school.management.repositories.StaffRepository;
import com.np.school.management.utilities.JwtUtility;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FacultyAPIController {

	private final JwtUtility jwtUtility;
	private final FacultyRepository facultyRepo;
	private final AdminDbRepository adminDbRepository;
	private final StaffRepository staffRepository;
	private final FeesManagementRepository feeRepo;
	
	@GetMapping("/faculty/{facultyId}")
	public ResponseEntity<?> getFacultyDetails(@PathVariable int facultyId) {
		try {
			FacultyDetails faculty = facultyRepo.findByFacultyIdAndFetchStudentDetails(facultyId).
					orElseThrow(() -> new Exception("No Faculty found"));
			return ResponseEntity.ok().
					cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(faculty);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
		}
	}
	
	@GetMapping("/admins/contact") 
	@JsonView(AdminDatabase.Views.BasicView.class)
	public ResponseEntity<?> retrieveAdminContacts(@RequestHeader("jwt")String jwt){
		try {
			List<String> roles = jwtUtility.extractRoles(jwt);
			List<AdminDatabase> admins = adminDbRepository.findAll();
			if(roles.contains("ROLE_ADMIN")) {
				return ResponseEntity.status(HttpStatus.OK.value())
						.location(URI.create("http://localhost:8181/super/admins/contact")).body(admins);
			}
			return ResponseEntity.ok(admins);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception occurred: "+e.getMessage());
		}
	}
	
	@GetMapping("/super/admins/contact") 
	@JsonView(AdminDatabase.Views.DetailedView.class)
	public ResponseEntity<?> retrieveCompleteAdminContacts(){
		List<AdminDatabase> admins = adminDbRepository.findAll();
		return ResponseEntity.ok(admins);
	}
	
	
	@GetMapping("/staff/department/{departmentName}")
	public ResponseEntity<?> retrieveStaffDetails(@PathVariable String departmentName) {
		try {
			System.out.println("Dept Name: "+departmentName);
			List<StaffDetails> staff = staffRepository.findByDepartmentIgnoringCase(departmentName).
					orElseThrow(() -> new Exception("No Staff found"));
			return ResponseEntity.ok(staff);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
		}
	}
	
	
	@PostMapping("/fees/pay") 
	public ResponseEntity<?> markFeesAsPaid(@RequestBody FeesManagement fees) {
		try {
			int feeId = fees.getFeeId();
			FeesManagement feeDetails = feeRepo.findById(feeId).
					orElseThrow(() ->  new Exception("No fee records found for given Id!!"));
			feeDetails.setPaymentStatus("Paid");
			feeRepo.updateFeeStatus(feeDetails);
			return ResponseEntity.ok("Marked fees as paid for: "+feeDetails.getStudent().getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
		}
	}
	
	
}
