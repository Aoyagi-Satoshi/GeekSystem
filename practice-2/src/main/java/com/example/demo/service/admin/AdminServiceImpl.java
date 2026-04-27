package com.example.demo.service.admin;

import java.util.List;
import java.util.Locale;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.admin.AdminDetailDto;
import com.example.demo.dto.admin.AdminListDto;
import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.StoreEntity;
import com.example.demo.exception.AdminNotFoundException;
import com.example.demo.form.admin.AdminEditForm;
import com.example.demo.form.admin.AdminForm;
import com.example.demo.repository.admin.AdminRepository;
import com.example.demo.repository.admin.PermissionRepository;
import com.example.demo.repository.admin.RoleRepository;
import com.example.demo.repository.store.StoreRepository;

@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MessageSource messageSource;

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
		admin.setPassword(passwordEncoder.encode(adminForm.getPassword()));
		adminRepository.save(admin);
	}

	@Override
	public AdminEditForm getEdit(Long id) {
		AdminEntity admin = adminRepository.findById(id)
				.orElseThrow(() -> new AdminNotFoundException(
						messageSource.getMessage("admin.notfound", null, Locale.getDefault())));
		AdminEditForm edit = new AdminEditForm();
		edit.setId(admin.getId());
		edit.setStoreId(admin.getStoreId());
		edit.setLastName(admin.getLastName());
		edit.setFirstName(admin.getFirstName());
		edit.setEmail(admin.getEmail());
		edit.setRoleId(admin.getRoleId());
		edit.setPermissionId(admin.getPermissionId());
		edit.setPhone(admin.getPhone());
		return edit;
	}

	@Override
	public AdminEntity getAdminByEmail(String email) {
		return adminRepository.findByEmail(email)
				.orElseThrow(() -> new AdminNotFoundException(
						messageSource.getMessage("admin.notfound", null, Locale.getDefault())));
	}

	public void updateAdmin(AdminEditForm adminEditForm) {
		AdminEntity admin = adminRepository.findById(adminEditForm.getId())
				.orElseThrow(() -> new AdminNotFoundException(
						messageSource.getMessage("admin.notfound", null, Locale.getDefault())));
		admin.setStoreId(adminEditForm.getStoreId());
		admin.setLastName(adminEditForm.getLastName());
		admin.setFirstName(adminEditForm.getFirstName());
		admin.setEmail(adminEditForm.getEmail());
		admin.setRoleId(adminEditForm.getRoleId());
		admin.setPermissionId(adminEditForm.getPermissionId());
		admin.setPhone(adminEditForm.getPhone());

		adminRepository.save(admin);
	}

	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		AdminEntity admin = adminRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + email));

		return User.withUsername(admin.getEmail())
				.password(admin.getPassword())
				.roles("ADMIN")
				.build();
	}

	@Override
	public List<AdminListDto> getAllAdmin() {
		return adminRepository.findAll().stream().map(admin -> {
			AdminListDto dto = new AdminListDto();
			dto.setId(admin.getId());
			dto.setLastName(admin.getLastName());
			dto.setFirstName(admin.getFirstName());
			dto.setEmail(admin.getEmail());
			dto.setStoreName(admin.getStore().getStoreName());
			dto.setRoleName(admin.getRole().getRoleName());
			dto.setCreatedAt(admin.getCreatedAt());
			dto.setUpdatedAt(admin.getUpdatedAt());
			return dto;
		}).toList();
	}

	@Override
	public AdminDetailDto getDetailAdmin(Long id) {
		AdminEntity admin = adminRepository.findById(id)
				.orElseThrow(() -> new AdminNotFoundException(
						messageSource.getMessage("admin.notfound", null, Locale.getDefault())));

		AdminDetailDto dto = new AdminDetailDto();
		dto.setId(admin.getId());
		dto.setLastName(admin.getLastName());
		dto.setFirstName(admin.getFirstName());
		dto.setEmail(admin.getEmail());
		dto.setPhone(admin.getPhone());
		dto.setStoreName(admin.getStore().getStoreName());
		dto.setRoleName(admin.getRole().getRoleName());
		dto.setPermissionName(admin.getPermission().getPermissionName());

		return dto;
	}

	@Override
	public void delete(Long id) {
		adminRepository.deleteById(id);
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
	}

	@Override
	public PermissionEntity getPermissionById(Long id) {
		return permissionRepository.findById(id).orElse(null);
	}

}
