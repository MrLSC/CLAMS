package com.winconlab.clams.service.impl;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.mapper.SysRoleMapper;
import com.winconlab.clams.mapper.SysUserRoleMapper;
import com.winconlab.clams.pojo.SysRole;
import com.winconlab.clams.pojo.SysUserRole;
import com.winconlab.clams.pojo.SysUserRoleExample;
import com.winconlab.clams.service.SysRoleService;
import com.winconlab.clams.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> findRolesByUserId(String sysUserId) {
        SysUserRoleExample example = new SysUserRoleExample();
        SysUserRoleExample.Criteria criteria = example.createCriteria();
        criteria.andSysUserIdEqualTo(sysUserId);
        return sysUserRoleMapper.selectByExample(example);
    }

    @Override
    public void addRolesToUser(String sysuserId, String sysRolesId) throws SysUserException {
        if (StringUtils.isEmpty(sysuserId) && StringUtils.isEmpty(sysRolesId))
            throw new SysUserException("用户不存在或权限id为空");
        String[] roles = sysRolesId.split(",");
        for (String sysRoleId : roles) {
            SysUserRole sysUserRole = new SysUserRole(IdUtil.generateUUID(), sysuserId, sysRoleId);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }

    @Override
    public void addRole(SysRole sysRole) throws SysUserException {
        if (StringUtils.isEmpty(sysRole.getName()))
            throw new SysUserException("角色名称不能为空");
        sysRole.setRoleId(IdUtil.generateUUID());
        sysRole.setAvailable("0");
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void updateRole(SysRole sysRole) {

    }

    @Override
    public void delRole(String sysRoleId) {

    }
}
