package com.np.school.management.exceptionHandling;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setContentType("application/json;charset=UTF-8");
		String errorMessage = (authException!=null && authException.getMessage()!=null)?authException.getMessage():"Unauthorized";
		response.getWriter().write("Error occurred: "+errorMessage);
	}

}
