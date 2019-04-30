package com.winconlab.clams.service.impl;

import com.winconlab.clams.exception.CustomMsgException;
import com.winconlab.clams.mapper.SysPermissionMapper;
import com.winconlab.clams.mapper.SysRolePermissionMapper;
import com.winconlab.clams.pojo.SysPermission;
import com.winconlab.clams.pojo.SysPermissionExample;
import com.winconlab.clams.pojo.SysRolePermission;
import com.winconlab.clams.pojo.SysRolePermissionExample;
import com.winconlab.clams.service.SysPermissionService;
import com.winconlab.clams.utils.IdUtil;
import com.winconlab.clams.utils.PinYinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysRolePermission> findPermissionsByRoleId(String sysRoleId) {
        SysRolePermissionExample example = new SysRolePermissionExample();
        SysRolePermissionExample.Criteria criteria = example.createCriteria();
        criteria.andSysRoleIdEqualTo(sysRoleId);
        return sysRolePermissionMapper.selectByExample(example);
    }

    @Override
    public SysPermission findPermissionByPermissionId(String permissionId) {
        SysPermissionExample example = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionIdEqualTo(permissionId);
        List<SysPermission> sysPermission = sysPermissionMapper.selectByExample(example);
        if (sysPermission != null && sysPermission.size() > 0)
            return sysPermission.get(0);
        return null;
    }

    @Transactional
    @Override
    public void addPermissionsToRole(String sysRoleId, String sysPermissionsId) throws CustomMsgException {
        if (StringUtils.isEmpty(sysRoleId) && StringUtils.isEmpty(sysPermissionsId))
            throw new CustomMsgException("角色不存在或权限id为空");
        String[] permissions = sysPermissionsId.split(",");
        for (String permission_id : permissions) {
            SysRolePermission sysUserRole = new SysRolePermission(IdUtil.generateUUID(), sysRoleId, permission_id);
            sysRolePermissionMapper.insert(sysUserRole);
        }
    }

    @Transactional
    @Override
    public void addPermission(SysPermission sysPermission) throws CustomMsgException {
        if (StringUtils.isEmpty(sysPermission.getName()) && StringUtils.isEmpty(sysPermission.getType()))
            throw new CustomMsgException("权限名称或者type不能为空");
        sysPermission.setPermissionId(IdUtil.generateUUID());
        sysPermission.setPercode(PinYinUtils.getHanziPinYin(sysPermission.getName()));
        sysPermission.setAvailable("0");
        sysPermissionMapper.insert(sysPermission);
    }

    @Transactional
    @Override
    public void updatePermission(SysPermission sysPermission) {

    }

    @Transactional
    @Override
    public void delPermission(String sysPermissionId) {

    }
}
