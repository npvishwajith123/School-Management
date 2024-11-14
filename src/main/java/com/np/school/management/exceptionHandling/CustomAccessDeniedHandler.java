package com.np.school.management.exceptionHandling;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		System.out.println("403 exception: --> Access Denied");
		response.setStatus(HttpStatus.FORBIDDEN.value());
		response.setContentType("application/json;charset=UTF-8");
		String errorMessage = (accessDeniedException!=null && accessDeniedException.getMessage()!=null)?accessDeniedException.getMessage():"Access Denied";
		response.getWriter().write("Error occurred: "+errorMessage);
//		response.sendError(HttpStatus.FORBIDDEN.value(), "Error occurred: "+errorMessage);
	}
}
