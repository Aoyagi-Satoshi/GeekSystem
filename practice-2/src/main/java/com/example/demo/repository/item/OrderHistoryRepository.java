package com.example.demo.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderHistoryEntity;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Long> {
}
