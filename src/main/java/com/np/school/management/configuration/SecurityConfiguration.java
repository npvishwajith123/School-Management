package com.np.school.management.configuration;

import com.np.school.management.exceptionHandling.ApiEntryPoint;
import com.np.school.management.exceptionHandling.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity//(debug=true)
public class SecurityConfiguration {

	private final JwtFilter jwt;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(AbstractHttpConfigurer::disable);
		http.csrf(AbstractHttpConfigurer::disable);
		http.headers(header -> header.frameOptions(FrameOptionsConfig::disable));
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeHttpRequests(request -> request
				.requestMatchers("/h2/**","/public/login").permitAll()
				.requestMatchers("/faculty/**","/staff/department/**").hasAnyRole("FACULTY")
				.requestMatchers("/super/admins/contact").hasAnyRole("ADMIN")
				.requestMatchers("/fees/pay").hasAnyRole("FACULTY","ADMIN")
				.requestMatchers("/students/**","/classes","/admins/contact").hasAnyRole("STUDENT","FACULTY","ADMIN")
				
		);
		
		http.securityContext(context->context.requireExplicitSave(false));
		http.addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling(e -> e.accessDeniedHandler(accessDeniedHandler()));
		http.exceptionHandling(e -> e.authenticationEntryPoint(apiEntry()));
		return http.build();
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}	
	@Bean
	public AuthenticationEntryPoint apiEntry() {
		return new ApiEntryPoint();
	}
	
	@Bean
	PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
