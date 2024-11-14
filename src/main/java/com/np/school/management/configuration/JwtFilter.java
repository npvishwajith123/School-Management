package com.np.school.management.configuration;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.np.school.management.utilities.JwtUtility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtility jwtUtil;
	private final UserDetailsServiceImpl userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (!(request.getRequestURI().contains("public") || request.getRequestURI().contains("h2"))) {
			try {
				String header = request.getHeader("JWT");
				if (header != null && jwtUtil.validateToken(header)) {
					String username = jwtUtil.extractUsername(header);
					UserDetails user = userRepository.loadUserByUsername(username);
					Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(),
							user.getPassword(), user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			} catch (Exception e) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write("Unable to authenticate through filter: " + e.getMessage());
				return;
			}
		}
		filterChain.doFilter(request, response);

	}

}
