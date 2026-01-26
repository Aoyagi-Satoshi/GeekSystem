package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AdminEntity;
import com.example.demo.entity.PermissionEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.StoreEntity;
import com.example.demo.form.AdminEditForm;
import com.example.demo.form.AdminForm;

public interface AdminService {
	  // 登録
    void saveAdmin(AdminForm adminForm);

    // 一覧
    List<AdminEntity> getAllAdmin();
    // 詳細
    AdminEntity getDetailAdmin(Long id);

    // 編集画面表示用
    AdminEditForm getEdit(Long id);

    // 更新
    void updateAdmin(AdminEditForm adminEditForm);

    // 削除
    void delete(Long id);

    // マスタ系
    List<StoreEntity> getStores();
    List<RoleEntity> getRoles();
    List<PermissionEntity> getPermissions();

    StoreEntity getStoreById(Long id);
    RoleEntity getRoleById(Long id);
    PermissionEntity getPermissionById(Long id);
}


