package com.example.demo.repository.item;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	@Query("SELECT i FROM ItemEntity i WHERE i.maker.id = :makerId")
	List<ItemEntity> findByMakerId(Long makerId);

	@Query("""
				SELECT i
				FROM ItemEntity i
				WHERE (:itemName = '' OR i.itemName LIKE %:itemName%)
				  AND (:smallCategoryId IS NULL OR i.smallCategory.id = :smallCategoryId)
				  AND (:middleCategoryId IS NULL OR i.smallCategory.middleCategory.id = :middleCategoryId)
				  AND (:largeCategoryId IS NULL OR i.smallCategory.middleCategory.largeCategory.id = :largeCategoryId)
			""")
	Page<ItemEntity> searchItems(
			@Param("itemName") String itemName,
			@Param("largeCategoryId") Long largeCategoryId,
			@Param("middleCategoryId") Long middleCategoryId,
			@Param("smallCategoryId") Long smallCategoryId,
			Pageable pageable);
}