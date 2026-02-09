package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
	boolean existsByEmail(String email);

	Optional<AdminEntity> findByEmail(String email);

}
