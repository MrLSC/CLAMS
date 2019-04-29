package com.winconlab.clams.service;

import com.winconlab.clams.exception.CustomMsgException;
import com.winconlab.clams.pojo.SysRole;
import com.winconlab.clams.pojo.SysUserRole;

import java.util.List;

public interface SysRoleService {

    List<SysUserRole> findRolesByUserId(String sysUserId);

    SysRole findRoleByRoleId(String roleId);

    void addRolesToUser(String sysuserId, String sysRoleId) throws CustomMsgException;

    void addRole(SysRole sysRole) throws CustomMsgException;

    void updateRole(SysRole sysRole);

    void delRole(String sysRoleId);
}
