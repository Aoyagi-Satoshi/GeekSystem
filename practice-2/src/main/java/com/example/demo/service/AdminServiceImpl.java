package com.example.demo.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.StoreEntity;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StoreRepository;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	//  @Autowired
	//   private PasswordEncoder passwordEncoder;

	@Transactional
	public void saveAdmin(AdminForm adminForm) {
		AdminEntity admin = new AdminEntity();
		admin.setStoreId(adminForm.getStoreId());
		admin.setLastName(adminForm.getLastName());
		admin.setFirstName(adminForm.getFirstName());
		admin.setEmail(adminForm.getEmail());
		admin.setRoleId(adminForm.getRoleId());
		admin.setPermissionId(adminForm.getPermissionId());
		admin.setPhone(adminForm.getPhone());
		//admin.setPassword(passwordEncoder.encode(adminForm.getPassword()));
		adminRepository.save(admin);
	}

	@Override
	public List<StoreEntity> getStores() {
		return storeRepository.findAll();
	}
    @Override
	public StoreEntity getStoreById(Long id) {
	    return storeRepository.findById(id).orElse(null);
	}
	@Override
	public List<RoleEntity> getRoles() {
		return roleRepository.findAll();
	}
	@Override
    public RoleEntity getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
	@Override
	public List<PermissionEntity> getPermissions() {
		return permissionRepository.findAll();
	}    @Override
    public PermissionEntity getPermissionById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }
}
