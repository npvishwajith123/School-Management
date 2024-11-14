package com.np.school.management.exceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler  {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> customExceptionRecorder(Exception exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.body("Custom Exception occurred: "+exception.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> customExceptionRecorder2(AccessDeniedException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.body("Custom Exception occurred: "+exception.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> customExceptionRecorder3(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.body("Custom Exception occurred: "+exception.getMessage());
	}

}
