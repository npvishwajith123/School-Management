package com.np.school.management.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.np.school.management.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByUsername(String username);

}
