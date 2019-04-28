package com.winconlab.clams.service;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.pojo.SysPermission;

public interface SysPermissionService {

    void findPermissionsByRoleId(String sysRoleId);

    void addPermissionsToRole(String sysRoleId, String sysPermissionId) throws SysUserException;

    void addPermission(SysPermission sysPermission);

    void updatePermission(SysPermission sysPermission);

    void delPermission(String sysPermissionId);
}
