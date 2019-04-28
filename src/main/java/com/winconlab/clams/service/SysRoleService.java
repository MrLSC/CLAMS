package com.winconlab.clams.service;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.pojo.SysRole;
import com.winconlab.clams.pojo.SysUserRole;

import java.util.List;

public interface SysRoleService {

    List<SysUserRole> findRolesByUserId(String sysUserId);

    void addRolesToUser(String sysuserId, String sysRoleId) throws SysUserException;

    void addRole(SysRole sysRole) throws SysUserException;

    void updateRole(SysRole sysRole);

    void delRole(String sysRoleId);
}
