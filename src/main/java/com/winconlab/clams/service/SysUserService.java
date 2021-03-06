package com.winconlab.clams.service;

import com.winconlab.clams.exception.CustomMsgException;
import com.winconlab.clams.pojo.SysUser;

public interface SysUserService {

    SysUser findSysUserByUsername(String username);

    void addSysUser(SysUser user) throws CustomMsgException;

    void delSysUserByUserId(int userId);

    void updateSysUser(SysUser user);
}
