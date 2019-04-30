package com.winconlab.clams.service.impl;

import com.winconlab.clams.exception.CustomMsgException;
import com.winconlab.clams.mapper.SysRoleMapper;
import com.winconlab.clams.mapper.SysUserRoleMapper;
import com.winconlab.clams.pojo.SysRole;
import com.winconlab.clams.pojo.SysRoleExample;
import com.winconlab.clams.pojo.SysUserRole;
import com.winconlab.clams.pojo.SysUserRoleExample;
import com.winconlab.clams.service.SysRoleService;
import com.winconlab.clams.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public SysRole findRoleByRoleId(String roleId) {
        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(sysRoleExample);
        if (sysRoles != null && sysRoles.size() >= 0)
            return sysRoles.get(0);
        return null;
    }

    @Transactional
    @Override
    public void addRolesToUser(String sysuserId, String sysRolesId) throws CustomMsgException {
        if (StringUtils.isEmpty(sysuserId) && StringUtils.isEmpty(sysRolesId))
            throw new CustomMsgException("用户不存在或角色id为空");
        String[] roles = sysRolesId.split(",");
        for (String sysRoleId : roles) {
            SysUserRole sysUserRole = new SysUserRole(IdUtil.generateUUID(), sysuserId, sysRoleId);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }

    @Transactional
    @Override
    public void addRole(SysRole sysRole) throws CustomMsgException {
        if (StringUtils.isEmpty(sysRole.getName()))
            throw new CustomMsgException("角色名称不能为空");
        sysRole.setRoleId(IdUtil.generateUUID());
        sysRole.setAvailable("0");
        sysRoleMapper.insert(sysRole);
    }

    @Transactional
    @Override
    public void updateRole(SysRole sysRole) {

    }

    @Transactional
    @Override
    public void delRole(String sysRoleId) {

    }
}
