package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.StoreEntity;
import com.example.demo.form.AdminForm;

public interface AdminService {
	void saveAdmin(AdminForm adminForm);

	List<StoreEntity> getStores();
	List<RoleEntity> getRoles();
	List<PermissionEntity> getPermissions();

	StoreEntity getStoreById(Long id);
	RoleEntity getRoleById(Long id);
	PermissionEntity getPermissionById(Long id);


	List<AdminEntity> getAllAdmin();

	AdminEntity getDetailAdmin(Long id);

	AdminEntity getEdit(Long id);

	void updateAdmin(AdminForm adminForm);

	void delete(Long id);
}

