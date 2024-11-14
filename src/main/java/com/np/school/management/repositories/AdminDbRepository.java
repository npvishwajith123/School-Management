package com.np.school.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.np.school.management.entities.AdminDatabase;

public interface AdminDbRepository extends JpaRepository<AdminDatabase, Integer>{

}
