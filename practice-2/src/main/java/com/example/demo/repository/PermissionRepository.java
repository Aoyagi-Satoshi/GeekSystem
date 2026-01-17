package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>{

}
