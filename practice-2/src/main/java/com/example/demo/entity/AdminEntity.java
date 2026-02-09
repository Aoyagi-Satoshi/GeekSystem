package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class AdminEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "fk_store_id", nullable = false)
	private Long storeId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_store_id", referencedColumnName = "id", insertable = false, updatable = false)
	private StoreEntity store;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "fk_role_id", nullable = false)
	private Long roleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RoleEntity role;
	
	@Column(name = "fk_permission_id")
	private Long permissionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_permission_id", referencedColumnName = "id", insertable = false, updatable = false)
	private PermissionEntity permission;

	@Column(name = "phone", nullable = false)
	private String phone;

	@Column(name = "password", nullable = false)
	private String password;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private Timestamp updatedAt;

	

}
