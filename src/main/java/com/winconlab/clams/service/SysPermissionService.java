package com.winconlab.clams.service;

import com.winconlab.clams.exception.CustomMsgException;
import com.winconlab.clams.pojo.SysPermission;
import com.winconlab.clams.pojo.SysRolePermission;

import java.util.List;

public interface SysPermissionService {

    List<SysRolePermission> findPermissionsByRoleId(String sysRoleId);

    SysPermission findPermissionByPermissionId(String permissionId);

    void addPermissionsToRole(String sysRoleId, String sysPermissionId) throws CustomMsgException;

    void addPermission(SysPermission sysPermission) throws CustomMsgException;

    void updatePermission(SysPermission sysPermission);

    void delPermission(String sysPermissionId);
}
