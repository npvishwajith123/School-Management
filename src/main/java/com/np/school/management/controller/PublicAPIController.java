package com.np.school.management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.np.school.management.entities.Users;
import com.np.school.management.repositories.UserRepository;
import com.np.school.management.utilities.JwtUtility;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PublicAPIController {

	private final JwtUtility jwtUtility;
	private final UserRepository userRepository;
	
	@PostMapping("/public/login")
	public ResponseEntity<?> login(@RequestBody Users user) {
		try {
		Users userDetails = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> {
			return new Exception("User not found for: "+user.getUsername());
		});
		
		if(userDetails.getPassword().equals(user.getPassword())) {
			return ResponseEntity.ok(jwtUtility.generateJwt(userDetails));
		} else {
			throw new Exception("Invalid password");
		}
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	
}
