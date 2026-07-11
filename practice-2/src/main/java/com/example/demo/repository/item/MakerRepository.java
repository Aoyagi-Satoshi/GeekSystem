package com.example.demo.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MakerEntity;

public interface MakerRepository extends JpaRepository<MakerEntity, Long> {

}
