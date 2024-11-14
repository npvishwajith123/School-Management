package com.np.school.management.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.np.school.management.entities.Roles;
import com.np.school.management.entities.Users;
import com.np.school.management.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		try {
			Users user = userRepository.findByUsername(username).orElseThrow(() -> new Exception("Invalid userd details"));
			return new User(user.getUsername(), user.getPassword(), getAuthorities(user.getRole()));
		} catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(List<Roles> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Roles role: roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

}
