package com.winconlab.clams.service.impl;

import com.winconlab.clams.exception.SysUserException;
import com.winconlab.clams.mapper.SysUserMapper;
import com.winconlab.clams.pojo.SysUser;
import com.winconlab.clams.service.SysUserService;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;

public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findSysUserByUsername(String username) {

        return null;
    }

    @Override
    public void addSysUser(SysUser user) throws SysUserException {
        if (findSysUserByUsername(user.getUsername()) != null)
            throw new SysUserException("用户名重复");

    }

    @Override
    public void delSysUserByUserId(int userId) {

    }

    @Override
    public void updateSysUser(SysUser user) {

    }
}
