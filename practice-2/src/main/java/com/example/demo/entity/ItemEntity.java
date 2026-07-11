package com.example.demo.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "items")
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


	@Column(name = "fk_small_id",nullable = false)
	private int smallId;
	
	
	@Column(name = "item_name", nullable = false)
	private String itemName;

	@Column(name = "item_info")
	private String itemInfo;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@Column(name = "cost_price",nullable = false)
	private BigDecimal costPrice;
	
	@Column(name = "fk_maker_id",nullable = false)
	private int makerId;
	
	@Column(name = "maker_price",nullable = false)
	private BigDecimal makerPrice;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

}
