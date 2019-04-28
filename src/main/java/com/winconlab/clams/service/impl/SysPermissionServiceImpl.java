package com.winconlab.clams.service.impl;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.mapper.SysRolePermissionMapper;
import com.winconlab.clams.pojo.SysPermission;
import com.winconlab.clams.pojo.SysRolePermission;
import com.winconlab.clams.service.SysPermissionService;
import com.winconlab.clams.utils.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public void findPermissionsByRoleId(String sysRoleId) {

    }

    @Override
    public void addPermissionsToRole(String sysRoleId, String sysPermissionsId) throws SysUserException {
        if (StringUtils.isEmpty(sysRoleId) && StringUtils.isEmpty(sysPermissionsId))
            throw new SysUserException("用户不存在或权限id为空");
        String[] roles = sysPermissionsId.split(",");
        for (String permission_id : roles) {
            SysRolePermission sysUserRole = new SysRolePermission(IdUtil.generateUUID(), sysRoleId, permission_id);
            sysRolePermissionMapper.insert(sysUserRole);
        }
    }

    @Override
    public void addPermission(SysPermission sysPermission) {

    }

    @Override
    public void updatePermission(SysPermission sysPermission) {

    }

    @Override
    public void delPermission(String sysPermissionId) {

    }
}
