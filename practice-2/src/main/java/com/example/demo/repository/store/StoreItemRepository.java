package com.example.demo.repository.store;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ItemEntity;
import com.example.demo.entity.StoreEntity;
import com.example.demo.entity.StoreItemEntity;

public interface StoreItemRepository extends JpaRepository<StoreItemEntity, Long> {
	Optional<StoreItemEntity> findByStoreAndItem(StoreEntity store, ItemEntity item);
}