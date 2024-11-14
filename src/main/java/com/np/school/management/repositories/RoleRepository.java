package com.np.school.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.school.management.entities.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer>{

}
